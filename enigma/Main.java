package enigma;


import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static enigma.EnigmaException.*;

/** Enigma simulator.
 *  @author Citlalli Villarreal
 */
public final class Main {

    /** Process a sequence of encryptions and decryptions, as
     *  specified by ARGS, where 1 <= ARGS.length <= 3.
     *  ARGS[0] is the name of a configuration file.
     *  ARGS[1] is optional; when present, it names an input file
     *  containing messages.  Otherwise, input comes from the standard
     *  input.  ARGS[2] is optional; when present, it names an output
     *  file for processed messages.  Otherwise, output goes to the
     *  standard output. Exits normally if there are no errors in the input;
     *  otherwise with code 1. */
    public static void main(String... args) {
        try {
            new Main(args).process();
            return;
        } catch (EnigmaException excp) {
            System.err.printf("Error: %s%n", excp.getMessage());
        }
        System.exit(1);
    }

    /** Check ARGS and open the necessary files (see comment on main). */
    Main(String[] args) {
        if (args.length < 1 || args.length > 3) {
            throw error("Only 1, 2, or 3 command-line arguments allowed");
        }

        _config = getInput(args[0]);

        if (args.length > 1) {
            _input = getInput(args[1]);
        } else {
            _input = new Scanner(System.in);
        }

        if (args.length > 2) {
            _output = getOutput(args[2]);
        } else {
            _output = System.out;
        }
    }

    /** Return a Scanner reading from the file named NAME. */
    private Scanner getInput(String name) {
        try {
            return new Scanner(new File(name));
        } catch (IOException excp) {
            throw error("could not open %s", name);
        }
    }

    /** Return a PrintStream writing to the file named NAME. */
    private PrintStream getOutput(String name) {
        try {
            return new PrintStream(new File(name));
        } catch (IOException excp) {
            throw error("could not open %s", name);
        }
    }

    /** Configure an Enigma machine from the contents of configuration
     *  file _config and apply it to the messages in _input, sending the
     *  results to _output. */
    private void process() {
        machine = readConfig();
        String cycles = "";
        String [] rotornamestring = new String[machine.numRotors()];
        while (_input.hasNextLine()) {
            String curr = _input.nextLine();
            if (curr.length() > 0) {
                Scanner scan = new Scanner(curr);
                String currscan = scan.next();
                if (currscan.equals("*")) {
                    for (int i = 0; i < machine.numRotors()
                            && scan.hasNext(); i += 1) {
                        currscan = scan.next();
                        if (!currscan.equals("*")) {
                            rotornamestring[i] = "Rotor " + currscan;
                        } else {
                            i = -1;
                        }
                    }
                    String settings = "";
                    if (scan.hasNext()) {
                        settings = scan.next();

                    }
                    while (scan.hasNext()) {
                        String cycle = scan.next();
                        cycles = cycles + cycle;
                    }
                    machine.insertRotors(rotornamestring);
                    setUp(machine, settings);
                    Permutation plugb = new Permutation(cycles, _alphabet);
                    machine.setPlugboard(plugb);

                    if (!_input.hasNext() && _input.hasNextLine()) {
                        curr = _input.nextLine();
                        printMessageLine(curr);
                    }
                    cycles = "";
                } else {
                    printMessageLine(curr);
                }
            } else {
                printMessageLine(curr);
            }
        }
    }
    /** Return an Enigma machine configured from the contents of configuration
     *  file _config. */
    private Machine readConfig() {
        try {
            String alphas = "";
            int rotors = 0;
            int pawls = 0;
            int counter = 0;
            while (_config.hasNext() && counter < 3) {
                if (counter == 0) {
                    alphas = _config.next();
                    counter = counter + 1;
                }
                if (counter == 1 || counter == 2) {
                    if (counter == 1) {
                        rotors = _config.nextInt();
                        counter = counter + 1;
                    } else {
                        pawls = _config.nextInt();
                        counter = counter + 1;
                    }
                }
            }
            _alphabet = new Alphabet(alphas);
            Collection<Rotor> allrotors = new ArrayList<>();
            while (_config.hasNext()) {
                Rotor rotor =  readRotor();
                allrotors.add(rotor);
            }
            return new Machine(_alphabet, rotors, pawls, allrotors);
        } catch (NoSuchElementException excp) {
            throw error("configuration file truncated");
        }

    }

    /** Return a rotor, reading its description from _config. */
    private Rotor readRotor() {
        try {
            String rotorname = ""; String typenotches = ""; String cycles = "";
            int counter = 0; String type = ""; String notches = "";
            boolean moving = false; Rotor rotor;
            while (_config.hasNext() && counter < 2) {
                if (nextsave != null) {
                    rotorname = nextsave; counter = 1; nextsave = null;
                } else if (counter == 0 && nextsave == null) {
                    rotorname = _config.next(); counter = counter + 1;
                    if (rotorname.contains("(") || rotorname.contains(")")) {
                        throw EnigmaException.error("bad rotor description");
                    }
                }
                if (counter == 1) {
                    typenotches = _config.next(); counter = counter + 1;
                    if (typenotches.contains("(")
                            || typenotches.contains(")")) {
                        throw EnigmaException.error("bad rotor description");
                    }
                }
            }
            while (_config.hasNext()) {
                String cycle = _config.next();
                if (!cycle.contains("(") || !cycle.contains(")")) {
                    nextsave = cycle; break;
                } else {
                    cycles = cycles + cycle;
                }
            }
            if (typenotches.length() >= 2) {
                for (int i = 0; i < typenotches.length(); i += 1) {
                    if (i == 0) {
                        Character typechar = typenotches.charAt(i);
                        type = typechar.toString(); moving = true;
                    } else {
                        Character notcheschar = typenotches.charAt(i);
                        notches = notcheschar.toString();
                    }
                }
            }
            if (typenotches.length() == 1) {
                Character typechar = typenotches.charAt(0);
                type = typechar.toString();
            }
            if (moving) {
                Permutation perm = new Permutation(cycles, _alphabet);
                rotor = new MovingRotor(rotorname, perm, notches);
            } else if (type.equals("N")) {
                Permutation perm = new Permutation(cycles, _alphabet);
                rotor = new FixedRotor(rotorname, perm);
            } else {
                Permutation perm = new Permutation(cycles, _alphabet);
                rotor = new FixedRotor(rotorname, perm);
            }
            return rotor;
        } catch (NoSuchElementException excp) {
            throw EnigmaException.error("bad rotor description");
        }
    }
    /** Set M according to the specification given on SETTINGS,
     *  which must have the format specified in the assignment. */
    private void setUp(Machine M, String settings) {
        try {
            if (M.numRotors() - 1 == settings.length()) {
                M.setRotors(settings);
            }
        } catch (NoSuchElementException excp) {
            throw error("bad SETTINGS format");
        }
    }

    /** Print MSG in groups of five (except that the last group may
     *  have fewer letters). */
    private void printMessageLine(String msg) {
        _output.println(machine.convert(msg));
    }

    /** Alphabet used in this machine. */
    private Alphabet _alphabet;

    /** Source of input messages. */
    private Scanner _input;

    /** Source of machine configuration. */
    private Scanner _config;

    /** File for encoded/decoded messages. */
    private PrintStream _output;

    /** Saves the token that is on the next line of the _input file. */
    private String nextsave;

    /** Machine used in thi file. */
    private Machine machine;

}

package enigma;

import java.util.ArrayList;
import java.util.Collection;

import static enigma.EnigmaException.*;


/** Class that represents a complete enigma machine.
 *  @author Citlalli Villarreal
 */

class Machine {

    /** A new Enigma machine with alphabet ALPHA, 1 < NUMROTORS rotor slots,
     *  and 0 <= PAWLS < NUMROTORS pawls.  ALLROTORS contains all the
     *  available rotors. */
    Machine(Alphabet alpha, int numRotors, int pawls,
            Collection<Rotor> allRotors) {
        _alphabet = alpha;
        _numRotors = numRotors;
        _pawls = pawls;
        _allRotors = allRotors;
        _insertRotors = new ArrayList<Rotor>(numRotors);
        _plugboard = new Permutation("", _alphabet);
    }

    /** Return the number of rotor slots I have. */
    int numRotors() {
        return _numRotors;
    }

    /** Return the number pawls (and thus rotating rotors) I have. */
    int numPawls() {
        return _pawls;
    }

    /** Set my rotor slots to the rotors named ROTORS from my set of
     *  available rotors (ROTORS[0] names the reflector).
     *  Initially, all rotors are set at their 0 setting. */
    void insertRotors(String[] rotors) {
        _insertRotors.clear();
        for (int j = 0; j < rotors.length; j += 1) {
            for (Rotor rotor : _allRotors) {
                if (rotor.toString().equals(rotors[j])) {
                    if (j == 0 && rotor.reflecting()) {
                        _insertRotors.add(rotor);
                    } else if (j == 0 && rotor.reflecting()) {
                        throw EnigmaException.error("Reflector in wrong place");
                    } else {
                        _insertRotors.add(rotor);
                    }
                }
            }
        }
    }
    /** Set my rotors according to SETTING, which must be a string of
     *  numRotors()-1 characters in my alphabet. The first letter refers
     *  to the leftmost rotor setting (not counting the reflector).  */
    void setRotors(String setting) {
        int i = 0;
        for (int j = 1; j < _insertRotors.size(); j += 1) {
            if (!_insertRotors.get(j).reflecting() && i < setting.length()) {
                _insertRotors.get(j).set(setting.charAt(i));
                i += 1;
            }
        }
    }
    /** Set the plugboard to PLUGBOARD. */
    void setPlugboard(Permutation plugboard) {
        _plugboard = plugboard;

    }

    /** Returns the result of converting the input character C (as an
     *  index in the range 0..alphabet size - 1), after first advancing
     *  the machine. */
    int convert(int c) {
        boolean fastadvanced = false;
        for (int i = 0; i < _insertRotors.size(); i += 1) {
            Rotor currotor = _insertRotors.get(i);
            Rotor fastrotor = _insertRotors.get(_insertRotors.size() - 1);
            if (currotor.rotates() && currotor.atNotch()
                    && !currotor.name().equals(fastrotor.name())) {
                fastrotor.advance();
                fastadvanced = true;
                if (i > 0) {
                    Rotor prevrotor = _insertRotors.get(i - 1);
                    if (prevrotor.rotates()) {
                        currotor.advance();
                        prevrotor.advance();
                    }
                }
            }
            if (currotor.name().equals(fastrotor.name()) && !fastadvanced) {
                Rotor prevrotor = _insertRotors.get(i - 1);
                if (currotor.atNotch() && prevrotor.rotates()) {
                    currotor.advance();
                    prevrotor.advance();
                } else {
                    currotor.advance();
                }
            }
        }
        int numrotors =  _insertRotors.size();
        int letterint = c;
        letterint = _plugboard.permute(c);
        for (int i = numrotors - 1; i >= 0; i -= 1) {
            Rotor currotor = _insertRotors.get(i);
            letterint = currotor.convertForward(letterint);
        }
        for (int i = 1; i < numrotors; i += 1) {
            Rotor currotor = _insertRotors.get(i);
            letterint = currotor.convertBackward(letterint);
        }
        letterint = _plugboard.invert(letterint);
        return letterint;
    }


    /** Returns the encoding/decoding of MSG, updating the state of
     *  the rotors accordingly. */
    String convert(String msg) {
        String newmsg = "";
        int count = 0;
        for (int i = 0; i < msg.length(); i += 1) {
            Character letter = msg.charAt(i);
            if (letter != ' ') {
                if (count != 5) {
                    int c = _alphabet.toInt(letter);
                    Character charr = _alphabet.toChar(convert(c));
                    String scharr = Character.toString(charr);
                    count = count + 1;
                    newmsg = newmsg + scharr;
                } else {
                    int c = _alphabet.toInt(letter);
                    Character charr = _alphabet.toChar(convert(c));
                    String scharr = Character.toString(charr);
                    count = 1;
                    newmsg = newmsg + " " + scharr;

                }
            }
        }

        return newmsg;
    }

    /** Return all possible rotors the machine can have. */
    Collection<Rotor> allrotors() {
        return _allRotors;
    }

    /** Return the rotors inserted into machine. */
    ArrayList<Rotor> insertrotors()  {
        return _insertRotors;
    }

    /** Return the alphabet I have. */
    Alphabet alphabet() {
        return _alphabet;
    }

    /** Common alphabet of my rotors. */
    private final Alphabet _alphabet;


    /** Number of rotor slots. */
    private final int _numRotors;

    /** Number of pawls that is less than the number of rotors. */
    private final int _pawls;

    /** Contains all the available rotors. */
    private final Collection<Rotor> _allRotors;

    /** Plugboard Permutation. */
    private Permutation _plugboard;

    /** Array List of rotors that are inserted in machine. */
    private ArrayList<Rotor> _insertRotors;

}

package enigma;


import static enigma.EnigmaException.*;

/** Represents a permutation of a range of integers starting at 0 corresponding
 *  to the characters of an alphabet.
 *  @author Citlalli Villarreal
 */
class Permutation {

    /** Set this Permutation to that specified by CYCLES, a string in the
     *  form "(cccc) (cc) ..." where the c's are characters in ALPHABET, which
     *  is interpreted as a permutation in cycle notation.  Characters in the
     *  alphabet that are not included in any cycle map to themselves.
     *  Whitespace is ignored. */
    Permutation(String cycles, Alphabet alphabet) {
        _alphabet = alphabet;
        _cycles = cycles;
        boolean open = false;
        boolean close = true;
        int counter = 0;


        if (_cycles.length() == 1 || _cycles.length() == 2) {
            throw EnigmaException.error("CYCLES is in "
                    + "malformed cycle notation", cycles);
        } else if (_cycles.length() > 2) {
            for (int i = 0; i < _cycles.length(); i += 1) {
                if (!open && _cycles.charAt(i) == '('
                        && counter == 0 && close) {
                    open = true;
                    counter = counter + 1;
                }
                if (open && _cycles.charAt(i) != '('
                        && _cycles.charAt(i) != ')') {
                    if (!_alphabet.contains(_cycles.charAt(i))) {
                        throw EnigmaException.error("CYCLES is in "
                                + "malformed cycle notation", cycles);
                    }
                    counter = counter + 1;
                }
                if (open && _cycles.charAt(i) == ')' && counter > 1) {
                    open = false;
                    close = false;
                    counter = 0;
                }
            }
        }
    }


    /** Add the cycle c0->c1->...->cm->c0 to the permutation, where CYCLE is
     *  c0c1...cm. */
    private void addCycle(String cycle) {
        _cycles = _cycles.concat("(").concat(cycle).concat(")");
    }

    /** Return the value of P modulo the size of this permutation. */
    final int wrap(int p) {
        int r = p % size();
        if (r < 0) {
            r += size();
        }
        return r;
    }

    /** Returns the size of the alphabet I permute. */
    int size() {
        return _alphabet.size();
    }



    /** Return the result of applying this permutation to P modulo the
     *  alphabet size. */
    int permute(int p) {
        for (int i = 0; i < _cycles.length(); i += 1) {
            if (_cycles.charAt(i) == _alphabet.toChar(p)) {
                if (_cycles.charAt(i + 1) == ')') {
                    int j = i;
                    while (_cycles.charAt(j) != '(') {
                        j = j - 1;
                    }
                    return _alphabet.toInt(_cycles.charAt(j + 1));
                } else {
                    return _alphabet.toInt(_cycles.charAt(i + 1));
                }
            }
        }
        return p;
    }

    /** Return the result of applying the inverse of this permutation
     *  to  C modulo the alphabet size. */
    int invert(int c) {
        for (int i = 0; i < _cycles.length(); i += 1) {
            Character charcycle = _cycles.charAt(i);
            Character charalpha = _alphabet.toChar(c);
            if (charcycle == charalpha) {
                if (_cycles.charAt(i - 1) == '(')  {
                    int j = i;
                    while (_cycles.charAt(j) != ')') {
                        j = j + 1;
                    }
                    return _alphabet.toInt(_cycles.charAt(j - 1));
                } else {
                    return _alphabet.toInt(_cycles.charAt(i - 1));
                }
            }

        }
        return c;
    }

    /** Return the result of applying this permutation to the index of P
     *  in ALPHABET, and converting the result to a character of ALPHABET. */
    char permute(char p) {
        int p2 = _alphabet.toInt(p);
        return _alphabet.toChar(permute(p2));
    }

    /** Return the result of applying the inverse of this permutation to C. */
    char invert(char c) {
        int c2 = _alphabet.toInt(c);
        return _alphabet.toChar(invert(c2));
    }

    /** Return the alphabet used to initialize this Permutation. */
    Alphabet alphabet() {
        return _alphabet;
    }

    /** Return true iff this permutation is a derangement (i.e., a
     *  permutation for which no value maps to itself). */
    boolean derangement() {
        for (int i = 0; i != _cycles.length(); i += 1) {
            if (_cycles.charAt(i) == '(') {
                int j = i + 1;
                int counter = 0;
                while (_cycles.charAt(j) != ')') {
                    j = j + 1;
                    counter = counter + 1;
                }
                if (counter <= 1) {
                    return false;
                }

            }
        }
        return true;
    }

    /** Alphabet of this permutation. */
    private Alphabet _alphabet;

    /** String of cycles of this permutation. */
    private String _cycles;

}

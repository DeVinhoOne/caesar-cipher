public class CaesarCipher {

    public String encrypt(String message, int key) {
        if (key > 26 || key < 0) {
            return "Invalid key. [0 - 26] required.";
        }
        StringBuilder encrypted = new StringBuilder(message);
        // create normal 26 letter alphabet and new shifted by 'key' offset.
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        // flag for check if a letter is lower or upper case
        boolean isLowerCase;
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            // set flag
            isLowerCase = Character.isLowerCase(currChar);
            // check if char appears in normal alphabet
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            if ((idx != -1) && (isLowerCase)) {
                char newChar = Character.toLowerCase(shiftedAlphabet.charAt(idx));
                encrypted.setCharAt(i, newChar);
            } else if ((idx != -1)) {
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }

    public void testEncrypt() {
        String msg = "There is a cake in Your room.";
        int key = 3;
        String encrypted = encrypt(msg, key);
        System.out.println("[ENCRYPTED] " + encrypted);
        System.out.println("[DECRYPTED] " + encrypt(encrypted, 26 - key));
        msg = "TRYING to make enCRYPTEd msG with SOME MYSTERIOUS keY.";
        key = 27;
        System.out.println("[ENCRYPTED] " + encrypted);
        System.out.println("[DECRYPTED] " + encrypt(encrypted, 26 - key));
    }
}

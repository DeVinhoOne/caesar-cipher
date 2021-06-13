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

    public String encryptTwoKeys(String input, int key1, int key2) {
        //key1 encrypts letters which are located on ODD positions in input
        //key2 encrypts letters which are located on EVEN positions in input
        StringBuilder encrypted = new StringBuilder(input);
        StringBuilder encryptedOdd = new StringBuilder();
        StringBuilder encryptedEven = new StringBuilder();
        //create two stringbuilders based on location (odd or even) in provided string
        for (int i = 0; i < encrypted.length(); i += 2) {
            encryptedOdd.append(encrypted.charAt(i));
        }
        for (int i = 1; i < encrypted.length(); i += 2) {
            encryptedEven.append(encrypted.charAt(i));
        }
        //encrypt both strings with appropriate key
        encryptedOdd = new StringBuilder(encrypt(encryptedOdd.toString(), key1));
        encryptedEven = new StringBuilder(encrypt(encryptedEven.toString(), key2));

        //create whole encrypted stringbuilder
        int index = 0;
        while (true) {
            if (index >= encryptedOdd.length()) {
                break;
            }
            int temp = index * 2;
            char oddChar = encryptedOdd.charAt(index);
            encrypted.setCharAt(temp, oddChar);
            if (index >= encryptedEven.length()) {
                break;
            }
            char evenChar = encryptedEven.charAt(index);
            encrypted.setCharAt(temp + 1, evenChar);
            index++;
        }

        return encrypted.toString();
    }
    //==============================================
    //=================TESTING======================
    //==============================================
    public void testEncrypt() {
        String msg = "There is a cake in Your room.";
        int key = 3;
        String encrypted = encrypt(msg, key);
        System.out.println("[ENCRYPTED] " + encrypted);
        System.out.println("[DECRYPTED] " + encrypt(encrypted, 26 - key));
        msg = "TRYING to make enCRYPTEd msG with SOME MYSTERIOUS keY.";
        key = 21;
        encrypted = encrypt(msg, key);
        System.out.println("[ENCRYPTED] " + encrypted);
        System.out.println("[DECRYPTED] " + encrypt(encrypted, 26 - key));
    }

    public void testEncryptTwoKeys() {
        String msg = "First Legion";
        int key1 = 23;
        int key2 = 17;
        String encrypted = encryptTwoKeys(msg, key1, key2);
        System.out.println("[ENCRYPTED] " + encrypted);
        System.out.println("[DECRYPTED] " + encryptTwoKeys(encrypted, 26 - key1, 26 - key2));
    }
}

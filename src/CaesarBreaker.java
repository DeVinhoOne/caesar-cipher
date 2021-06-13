public class CaesarBreaker {

    public int[] countLetters(String message) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k < message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }

    public int maxIndex(int[] vals) {
        int maxDex = 0;
        for (int k = 0; k < vals.length; k++) {
            if (vals[k] > vals[maxDex]) {
                maxDex = k;
            }
        }
        return maxDex;
    }

    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return cc.encrypt(encrypted, 26 - dkey);
    }

    public String halfOfString(String message, int start) {
        StringBuilder half = new StringBuilder();
        for (int i = start; i < message.length(); i += 2) {
            char ch = message.charAt(i);
            half.append(ch);
        }
        return half.toString();
    }

    public int getKey(String s) {
        int[] freqs = countLetters(s);
        int maxIndex = maxIndex(freqs);
        int dkey = maxIndex - 4;
        if (maxIndex < 4) {
            dkey = 26 - (4 - maxIndex);
        }
        return dkey;
    }

    public String decryptTwoKeys(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        String oddEncrypted = halfOfString(encrypted, 0);
        String evenEncrypted = halfOfString(encrypted, 1);
        int evenKey = getKey(evenEncrypted);
        int oddKey = getKey(oddEncrypted);
        System.out.println("Key for string starting from index 0: " + oddKey);
        System.out.println("Key for string starting from index 1: " + evenKey);
        return cc.encryptTwoKeys(encrypted,26 - oddKey,26 - evenKey);
    }
    //==============================================
    //================TESTING=======================
    //==============================================
    public void testDecrypt() {
        String encrypted = "Just a test string with lots of eeeeeeeeeeeeeeeees";
        System.out.println(decrypt(encrypted));
    }

    public void testHalfOfString() {
        System.out.println(halfOfString("Qbkm Zgisa", 0));
        System.out.println(halfOfString("Qbkm Zgisa", 1));
    }

    public void testGetKey() {
        System.out.println(getKey("vglkmnonnnn"));
    }

    public void testDecryptTwoKeys() {
        String encrypted = "";
        System.out.println(decryptTwoKeys(encrypted));
    }
}

package Unit5.MarkovModel;

import Unit5.bca.util.*;
import java.lang.IllegalArgumentException;

public class MarkovModel {
    private static final int ASCII = 128;
    private int k;
    private BCAMap<BCAMap<Integer>> bcaMap;

    //creates a Markov model of order k for the specified text
    public MarkovModel(String text, int k){
        this.k = k;
        bcaMap = new BCAMapByHashedArrayList<>(100);

        for (int i = 0; i < text.length(); i++) {
            String textModel = text.substring((i + k) % text.length(), (i + k) % text.length() + 1);
            StringBuilder kgram = new StringBuilder();

            for (int j = 0; j < k; j++) {
                kgram.append(text.charAt((i + j) % text.length()));
            }

            String toString = kgram.toString();

            if (bcaMap.get(toString) == null){
                bcaMap.put(toString, new BCAMapByHashedArrayList<>(100));
            }

            BCAMap<Integer> kgramMap = bcaMap.get(toString);
            kgramMap.put(textModel, kgramMap.getOrDefault(textModel, 0) + 1);
        }
    }

    // returns the order k of this Markov model
    public int order(){
        return k;
    }

    // returns a string representation of the Markov model (as described below)
    public String toString(){
        StringBuilder markovString = new StringBuilder();
        for (int i = 0; i < bcaMap.keys().length; i++) {
            String kgram = bcaMap.keys()[i];
            markovString.append(kgram + ": ");

            BCAMap<Integer> kgramMap = bcaMap.get(kgram);
            for (String letter : kgramMap.keys()) {
                markovString.append(letter + " " + kgramMap.get(letter) + " ");
            }

            markovString.append("\n");
        }
        return markovString.toString();
    }

    // returns the number of times the specified kgram appears in the text
    // throws java.lang.IllegalArgumentException if kgram not of length k
    public int freq(String kgram){
        if (kgram.length() != k){
            throw new IllegalArgumentException("Kgram not of length k!");
        }

        BCAMap<Integer> kgramMap = bcaMap.get(kgram);

        if (kgramMap == null){
            return 0;
        }

        int frequency = 0;

        for (int i = 0; i < kgramMap.keys().length; i++) {
            String letter = kgramMap.keys()[i];
            frequency += kgramMap.get(letter);
        }

        return frequency;
    }

    // returns the number of times the character c follows the specified
    // kgram in the text
    // throws java.lang.IllegalArgumentException if kgram not of length k
    // or if c is not an ASCII character.
    public int freq(String kgram, char c){
        if ((kgram.length() != k) || (c >= ASCII)){
            throw new IllegalArgumentException();
        }

        int frequency = bcaMap.get(kgram).getOrDefault(Character.toString(c), 0);

        return frequency;
    }

    // returns a random character that follows the specified kgram in the text,
    // chosen with weight proportional to the number of times that character
    // follows the specified kgram in the text
    // if the kgram is not of length k OR does not appear in the text, throw a
    // java.lang.IllegalArgumentException.
    public char random(String kgram){
        if ((kgram.length() != 0) || (bcaMap.containsKey(kgram) == false)){
            throw new IllegalArgumentException();
        }

        BCAMap<Integer> kgramMap = bcaMap.get(kgram);
        String[] characters = kgramMap.keys();

        char random = characters[(int) (Math.random() * characters.length)].charAt(0);
        return random;
    }

    // tests this class by directly calling all instance methods
    public static void main(String[] args) {
      String text1 = "banana";
      System.out.println("Source text: " + text1);
      MarkovModel model1 = new MarkovModel(text1, 2);
      System.out.println("model's toString():");
      System.out.println(model1.toString());
      System.out.println("freq(\"an\", 'a')    = " + model1.freq("an", 'a'));
      System.out.println("freq(\"na\", 'b')    = " + model1.freq("na", 'b'));
      System.out.println("freq(\"na\", 'a')    = " + model1.freq("na", 'a'));
      System.out.println("freq(\"na\")         = " + model1.freq("na"));
      System.out.println();

      String text2 = "na na na na hey hey hey goodbye";
      System.out.println("Source text: " + text2);
      MarkovModel model2 = new MarkovModel(text2, 3);
      System.out.println("model's toString():");
      System.out.println(model2.toString());
      System.out.println("freq(\"na \", 'n')   = " + model2.freq("na ", 'n'));
      System.out.println("freq(\"na \", 'h')   = " + model2.freq("na ", 'h'));
      System.out.println("freq(\"na \")        = " + model2.freq("na "));
      System.out.println("freq(\"hey\")        = " + model2.freq("hey"));
      System.out.println();

      String text3 = "one fish two fish red fish blue fish";
      System.out.println("Source text: " + text3);
      MarkovModel model3 = new MarkovModel(text3, 4);
      System.out.println("model's toString():");
      System.out.println(model3.toString());
      System.out.println("freq(\"ish \", 'r')  = " + model3.freq("ish ", 'r'));
      System.out.println("freq(\"ish \", 'x')  = " + model3.freq("ish ", 'x'));
      System.out.println("freq(\"ish \")       = " + model3.freq("ish "));
      System.out.println("freq(\"tuna\")       = " + model3.freq("tuna"));

      // Create additional tests!
    }


}

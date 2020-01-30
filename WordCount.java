package Unit5;

import Unit5.bca.util.*;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.lang.StringBuilder;

public class WordCount {

    public static String cleanWord (String word){
        StringBuilder chris = new StringBuilder();
        char [] arr = word.toLowerCase().toCharArray();
        for(int i = 0; i < arr.length; i++){
            if(Character.isLetter(arr[i]))
                chris.append(arr[i]);
        }

        return chris.toString();
    }

    public static BCAArrayList<String> readlFile (String filename) throws FileNotFoundException{
        Scanner reader = new Scanner (new FileReader(filename));
        BCAArrayList<String> list = new BCAArrayList<String>();

        while (reader.hasNext()){
            String word = cleanWord(reader.next());
            list.add(word);
        }
        return list;
    }


    public static void main(String[] args) throws FileNotFoundException{
        BCAArrayList<String> list = readlFile("TaleOfTwoCities.txt");

        BCAMap<Integer> map = new BCAMapByHashedLinkedList<>(250);
        long start = System.currentTimeMillis();

        for (int i = 0; i < list.size(); i++) {
            String word = list.get(i);
            int frequency = map.getOrDefault(word, 0);
            map.put(word, frequency + 1);
        }
        long end = System.currentTimeMillis();
        System.out.println(((end - start) / 1000.0)+ " Seconds.");

        BCAEntry<Integer>[] entries = map.toArray();
        Arrays.sort(entries);
        for (BCAEntry<Integer> e : entries){
            System.out.println(e);
        }
    }
}

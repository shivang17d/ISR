import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Conflation_Algo {
    static class Pair {
        String word;
        int count;

        Pair(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }

    public static void removeStopWords(Map<String, Integer> map) throws Exception {
        File myFile = new File("stopwords.txt");
        Set<String> set = new HashSet<>(); // set of stopwords
        BufferedReader br = new BufferedReader(new FileReader(myFile));
        String st = br.readLine();
        while (st != null) {
            set.add(st);
            st = br.readLine();
        }
        // Iterates through the map and removes any words that are found in the set of
        // stopwords
        for (String stopWord : set) {
            map.remove(stopWord);
        }
        br.close();
    }

    // It processes the input text to count the frequency of each word in the text.
    public static void countWords(String text, Map<String, Integer> map) {
        String tempText = text.toLowerCase();
        String[] skips = { ".", ",", ":", ";", "'", "\"", "-" };
        for (String c : skips) {
            if (c.equals("-")) {
                tempText = tempText.replace(c, " ");
            } else {
                tempText = tempText.replace(c, "");
            }
        }
        for (String word : tempText.split(" ")) {
            if (!map.containsKey(word)) {
                map.put(word, 0);
            }
            map.put(word, map.get(word) + 1);
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("\n***********OUTPUT************\n");
        File myFile1 = new File("input.txt");
        BufferedReader br = new BufferedReader(new FileReader(myFile1));
        String text = br.readLine();

        // Map<String, Integer> is used to store word frequencies.
        Map<String, Integer> map = new HashMap<>();
        countWords(text, map);
        removeStopWords(map);

        /*
         * PriorityQueue orders the elements in descending order of the word count
         * The lambda expression (a, b) -> (b.count - a.count) compares two Pair objects
         * a and b, subtracting a.count from b.count.
         * This results in pairs with higher counts appearing at the front of the
         * priority queue
         */
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> (b.count - a.count));
        for (String key : map.keySet()) {
            if (map.get(key) >= 2) {
                pq.add(new Pair(key, map.get(key)));
            }
        }

        for (Pair p : pq) {
            System.out.println(p.word + " : " + p.count);
        }
        br.close();
    }
}

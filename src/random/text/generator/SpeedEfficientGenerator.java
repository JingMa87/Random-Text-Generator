package random.text.generator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This version of the RandomTextGenerator maps all the words following the WordSet
 * into a HashMap to make the text generation faster. It does use more space to
 * do this however.
 * n = size of input text
 * m = number of words to be generated
 * The time complexity of this algorithm is O(n);
 * The space complexity of this algorithm is O(n);
 */
public class SpeedEfficientGenerator extends RandomTextGenerator {
    
    private final HashMap<WordSet, ArrayList<String>> wordMap;
    
    public SpeedEfficientGenerator(String text) {
        super(text);
        wordMap = new HashMap<>();
        makeWordMap();
    }
    
    private void makeWordMap() {
        for (int i = 0; i < text.length - SETSIZE; i++) {
            WordSet set = new WordSet(text, i);
            String nextWord = text[i + SETSIZE];
            if (wordMap.containsKey(set))
                wordMap.get(set).add(nextWord);
            else {
                ArrayList<String> list = new ArrayList<>();
                list.add(nextWord);
                wordMap.put(set, list);
            }
        }
    }
    
    public void printWordMap() {
        String format = "%-30s%s%n";
        for (HashMap.Entry entry : wordMap.entrySet())
            System.out.printf(format, "Key: " + entry.getKey(), "Value: " + entry.getValue());
        System.out.println("------------------------------------------------------");
    }
    
    @Override
    public ArrayList<String> getFollowingWords(WordSet set) {
        return wordMap.get(set);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SpeedEfficientGenerator gen = new SpeedEfficientGenerator(defaultText);
        // Uncomment the line below to print all the WordSets and their following words
        // gen.printWordMap();
        System.out.println(gen.generateText(25));
    }
}
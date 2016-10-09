package random.text.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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
    public String generateText(int wordAmount) {
        if (wordAmount <= SETSIZE) return null;
        
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        int n = rand.nextInt(text.length - SETSIZE);
        WordSet set = new WordSet(text, n);
        sb.append(set);
        
        while (wordAmount-- > 0) {
            ArrayList<String> list = wordMap.get(set);
            // Uncomment the lines below for printing every set of three words
            // String format = "%-30s%s%n";
            // System.out.printf(format, "Set: " + set, "Follows: " + list);
            if (list == null || list.isEmpty())
                break;
            n = rand.nextInt(list.size());
            String nextWord = list.get(n);
            sb.append(nextWord).append(" ");
            set = new WordSet(set.getWord2(), set.getWord3(), nextWord);
        }
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        System.out.println("------------------------------------------------------");
        return sb.toString().trim();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String text = "This is a text and this is a great damn text. This text is cool and this is a great freaking piece.";
        SpeedEfficientGenerator gen = new SpeedEfficientGenerator(text);
        gen.printWordMap();
        System.out.println(gen.generateText(25));
    }
}
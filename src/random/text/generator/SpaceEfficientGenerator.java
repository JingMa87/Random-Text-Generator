package random.text.generator;

import java.util.ArrayList;
import java.util.Random;

/**
 * This version of the RandomTextGenerator iterates through the input text on the
 * fly and creates an array of all the words following the specific set of words.
 * As such, it doesn't have to save all the follow-up words at the same time.
 * This method uses less space but more processing power, since you iterate over
 * the whole text for every set of words.
 * n = size of input text
 * m = number of words to be generated
 * The time complexity of this algorithm is O(n*m);
 * The space complexity of this algorithm is O(1);
 */
public class SpaceEfficientGenerator extends RandomTextGenerator {
    
    public SpaceEfficientGenerator(String text) {
        super(text);
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
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < text.length - SETSIZE; i++) {
                WordSet currSet = new WordSet(text, i);
                if (currSet.equals(set)){
                    list.add(text[i + SETSIZE]);
                    System.out.println("CurrSet: "+currSet + ", Main set: "+set);
                    System.out.println("Added word: "+text[i + SETSIZE]);
                }
            }
            String format = "%-30s%s%n";
            System.out.printf(format, "Set: " + set, "List: " + list);
            if (list.isEmpty())
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
        SpaceEfficientGenerator gen = new SpaceEfficientGenerator(text);
        System.out.println(gen.generateText(25));
    }
}
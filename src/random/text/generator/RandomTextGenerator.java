package random.text.generator;

import java.util.ArrayList;
import java.util.Random;

/**
 * A program that takes in a text file and uses the words to randomly generate a new text.
 * This generator looks at sets of three consecutive words and finds all the words
 * that follow the sets. Of all these follow-up words, one is chosen randomly
 * to form a new text. This technique is called the Markov chain.
 */
public abstract class RandomTextGenerator {
    
    public final String[] text;
    public final int SETSIZE = 3;
    
    public RandomTextGenerator(String text) {
        this.text = text.split("\\s+");
    }
    
    public String generateText(int wordAmount) {
        if (wordAmount <= SETSIZE) return null;
        
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        int n = rand.nextInt(text.length - SETSIZE);
        WordSet set = new WordSet(text, n);
        sb.append(set);
        
        while (wordAmount-- > 0) {
            ArrayList<String> list = getFollowingWords(set);
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
    
    public abstract ArrayList<String> getFollowingWords(WordSet set);
}
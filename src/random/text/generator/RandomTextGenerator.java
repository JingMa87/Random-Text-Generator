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
    protected static final String defaultText = "She is into superstitions, black cats and voodoo dolls. "
            + "I feel a premonition that girl is gonna make me fall. "
            + "She is into new sensations, new kicks in the candle light. "
            + "She has got a new addiction for every day and night. "
            + "She will make you take your clothes off and go dancing in the rain. "
            + "She will make you live her crazy life but she will take away your pain like a bullet to your brain. "
            + "Come On! Upside, inside out she is livin la vida loca. "
            + "She will push and pull you down, livin la vida loca. "
            + "Her lips are devil red and her skin is the color mocha. "
            + "She will wear you out livin la vida loca. Come On! Livin la vida loca, Come on! "
            + "She is livin la vida loca. Woke up in New York City in a funky cheap hotel. "
            + "She took my heart and she took my money. She must have slipped me a sleeping pill. "
            + "She never drinks the water and makes you order French Champagne. "
            + "Once you have had a taste of her you will never be the same. "
            + "Yeah, she will make you go insane.";
    public final String[] text;
    public final int SETSIZE = 3;
    
    public RandomTextGenerator(String text) {
        this.text = text.split("\\s+");
    }
    
    public String getDefaultText(){
        return defaultText;
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
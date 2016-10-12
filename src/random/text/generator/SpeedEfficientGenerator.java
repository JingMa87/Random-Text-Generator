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
        final String defaultText = "She is into superstitions, black cats and voodoo dolls. "
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
        SpeedEfficientGenerator gen = new SpeedEfficientGenerator(defaultText);
        // Uncomment the line below to print all the WordSets and their following words
        // gen.printWordMap();
        System.out.println(gen.generateText(25));
    }
}
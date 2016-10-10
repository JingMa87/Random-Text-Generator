package random.text.generator;

import java.util.ArrayList;

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
    public ArrayList<String> getFollowingWords(WordSet set) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < text.length - SETSIZE; i++) {
            WordSet currSet = new WordSet(text, i);
            if (currSet.equals(set))
                list.add(text[i + SETSIZE]);
        }
        return list;
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
        SpaceEfficientGenerator gen = new SpaceEfficientGenerator(defaultText);
        System.out.println(gen.generateText(100));
    }
}
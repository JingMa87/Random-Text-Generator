package random.text.generator;

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
        Random rand = new Random();
        int n = rand.nextInt(text.length - SETSIZE);
        
    }
}
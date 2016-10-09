package random.text.generator;

/**
 * A program that takes in a text file and uses the words to randomly generate a new text.
 * This generator looks at sets of three consecutive words and remembers all the words
 * that follow the sets. Of all these follow-up words, one is chosen randomly
 * to form a new text. 
 */
public abstract class RandomTextGenerator {
    
    public final String[] text;
    
    public RandomTextGenerator(String text) {
        this.text = text.split("\\s+");
    }
    
    public abstract String generateText(int wordAmount);
}
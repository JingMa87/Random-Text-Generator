package random.text.generator;

/**
 * A set of three words for which we will find the words that follow after.
 */
class WordSet {
    
    private final String word1;
    private final String word2;
    private final String word3;
    
    public WordSet(String[] text, int index) {
        word1 = text[index];
        word2 = text[index + 1];
        word3 = text[index + 2];
    }
    
    public WordSet(String word1, String word2, String word3) {
        this.word1 = word1;
        this.word2 = word2;
        this.word3 = word3;
    }
    
    public String getWord1() { return word1; }
    public String getWord2() { return word2; }
    public String getWord3() { return word3; }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(word1).append(" ");
        sb.append(word2).append(" ");
        sb.append(word3).append(" ");
        return sb.toString();
    }
    
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof WordSet)) return false;
        
        WordSet other = (WordSet) obj;
        return this.toString().equals(other.toString());
    }
}
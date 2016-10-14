package random.text.generator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This version of the RandomTextGenerator uses a LRU cache to obtain a balance
 * between the space and time complexity of the program. The cache consists of
 * a circular doubly linked-list to store the nodes in the cache. Each cache
 * node consists of a WordSet object, and its corresponding list of following
 * words. A hash-map that maps WordSet objects to their corresponding 
 * cache nodes in the linked list is used to facilitate O(1) random access.
 * n = size of input text
 * m = number of words to be generated
 * c = Size of the cache
 * The time complexity of this algorithm is O(n * m);
 * The space complexity of this algorithm is O(c);
 */


public class CacheUtilisingGenerator extends RandomTextGenerator {
    
    private static final int DEFAULT_CACHE_SIZE = 10;
    private LRUCache cache;
    
    static class LRUCache {
        /*Using a nested class for cache implementation since only
          CacheUtilizingGenerator objects would require the use of a LRUCache object*/
        
        private HashMap<WordSet, LRUCacheNode> wordmap; 
        
        /*Head node of circular doubly linked-list. Nodes are arranged in LRU
        order, i.e most recently accessed node is moved to the head of the list.
        */
        private LRUCacheNode head;
        int size; //Number of elements stored in the cache.
        int capacity; //Maximum number of elements that can be stored in the cache.
        
        static class LRUCacheNode{ //Implementation of a node of the linked list
            private WordSet wordset;
            private ArrayList<String> followingwords;
            LRUCacheNode prev;
            LRUCacheNode next;
            LRUCacheNode(WordSet wordset, ArrayList<String> followingwords){
                this.wordset = wordset;
                this.followingwords = new ArrayList<>(followingwords);
            }
            WordSet getWordSet(){
                return wordset;
            }
            ArrayList<String> getFollowingWords(){ 
                //Retrieve the list of words stored at this node.
                return followingwords;
            }
        }
        
        LRUCache(int capacity){
            wordmap = new HashMap<>(capacity);
            this.capacity = capacity;
            this.size = 0;
        }
        
        private void appendNodeToListHead(LRUCacheNode tempnode){
            tempnode.next = head;
            tempnode.prev = head.prev;
            head.prev.next = tempnode;
            head.prev = tempnode;
            head = tempnode;
        }
        
        private boolean isFull(){
            return size == capacity;
        }
        
        private void deleteLRUNode(){
            LRUCacheNode tailnode = head.prev;
            head = tailnode.next;
            tailnode.prev.next = tailnode.next;
            tailnode.next.prev = tailnode.prev;
            WordSet wordset = tailnode.getWordSet();
            wordmap.remove(wordset);
            size--;
        }
        
        public boolean containsKey(WordSet wordset){
            return wordmap.containsKey(wordset);
        }
        
        public void addWordSet(WordSet wordset, ArrayList<String> followingwords){
            if(wordmap.containsKey(wordset)) return;
            LRUCacheNode tempnode = new LRUCacheNode(wordset, followingwords);
            if(head == null){
                head = tempnode;
                head.prev = head;
                head.next = head;
            }
            else{
                if(isFull()){
                    //Remove least recently used element from the cache.
                    deleteLRUNode();
                }
                appendNodeToListHead(tempnode);
            }
            wordmap.put(wordset, tempnode);
            size++;
        }
        
        public ArrayList<String> getFollowingWords(WordSet wordset){
            if(!this.containsKey(wordset)) return null;
            LRUCacheNode tempnode = wordmap.get(wordset);
            //Move node to list head
            tempnode.prev.next = tempnode.next;
            tempnode.next.prev = tempnode.prev;
            appendNodeToListHead(tempnode);
            return tempnode.getFollowingWords();
        }
        
        public void printCacheContents(){ //For testing purposes
            System.out.println("\n-----------Cache Contents------------");
            System.out.println("Cache size: " + size + "\n");
            LRUCacheNode curr = head;
            if(head == null)System.out.println("Empty");
            else{
                System.out.println(curr.getWordSet() + ", " + curr.getFollowingWords());
                curr = curr.next;
                while(curr!=head){
                    System.out.println(curr.getWordSet() + ", " + curr.getFollowingWords());
                    curr = curr.next;
                }
            }
            System.out.println("-------------------------------------");
        }
    }
    
    public CacheUtilisingGenerator(String text){
        super(text);
        cache = new LRUCache(DEFAULT_CACHE_SIZE);
    }
    
    public CacheUtilisingGenerator(String text, int cachecapacity){
        super(text);
        cache = new LRUCache(cachecapacity);
    }

    @Override
    public ArrayList<String> getFollowingWords(WordSet set) {
        if(cache.containsKey(set)) return cache.getFollowingWords(set);
        //Scanning the text
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < text.length - SETSIZE; i++) {
            WordSet currSet = new WordSet(text, i);
            if (currSet.equals(set))
                list.add(text[i + SETSIZE]);
        }
        cache.addWordSet(set, list);
        
        //Uncomment to view cache contents after each query
        //cache.printCacheContents();
        
        return list;
    }
    
    public static void main(String args[]){
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
        
        CacheUtilisingGenerator gen = new CacheUtilisingGenerator(defaultText);
        System.out.println(gen.generateText(100));  
    }
    
}

/**
 * Tim Pornyuenyong
 * Project 5
 * 5/10/18
 */

public class Word {

    String word = "";
    int occurences = 0;

    /**
     * Constructor for Word class.
     * Sets the word and occurences.
     * @param input
     * @param occur
     */
    public Word(String input, int occur) {
        word = input;
        occurences = occur;
    }

    /**
     * Getter method
     * @return the number of occurences
     */
    public int getOccurences() {
        return occurences;
    }

    /**
     * Setter method
     * @return the word stored in the Word object
     */
    public String getWord() {
        return word;
    }
}

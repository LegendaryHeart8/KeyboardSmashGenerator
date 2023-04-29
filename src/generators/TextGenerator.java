package generators;

import java.util.Random;

public abstract class TextGenerator {
    protected Random random = new Random();
    protected StringBuilder generated = new StringBuilder();
    protected int lengthNew = 0; //length of new text that has just been generated
     public abstract void generateNew(int characters);
     public String getNewGenerated(){
         return generated.substring(generated.length()-lengthNew);
     }

    /**
     * Takes a lowercase or uppercase letter and returns the letter's position in the alphabet.
     * E.g. letterToInd('a') = 0, letterToInd('Z') = 25.
     * @param letter an alphabetic character
     * @return the position of letter in alphabet or -1 for non-alphabetic characters entered.
     */
    public static int letterToInd(char letter){
         if(!Character.isLetter(letter)) return -1;
         if(Character.isUpperCase(letter)) return (int) letter - (int) 'A';
         else return (int) letter - (int) 'a';
    }
    public static int letterToInd(int letterCode){
        return letterToInd((char) letterCode);
    }

    /**
     * Takes a position in the alphabet and returns the corresponding letter in lowercase.
     * @param position the position of the letter where the letter A is at position 0
     * @return The lowercase letter at that position in the alphabet
     */
    public static char indToLetter(int position){
        return (char) (position + (int) 'a');
    }
}

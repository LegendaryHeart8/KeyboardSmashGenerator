package generators;

import java.io.*;
import java.util.Random;

public class SpamGeneratorStats extends TextGeneratorChar{

    static final int LETTERS = 26;
    private int[][] frequencies = new int[LETTERS][LETTERS]; //frequencies[FROM][TO] = number of occurrences of a letter going from FROM to TO
    private int[] totalFrequencies = new int[LETTERS]; //number of occurrences of a letter
    private int[] totalTo = new int[LETTERS]; //number of times the letter was left
    private int totalLetters = 0;//the total number of letters counted in frequencies

    final char DEFAULT_CHAR = 'a';
    @Override
    protected char generateNewChar() {
        if(generated.length() == 0) return generateStartChar();
        else return generateNextChar(generated.charAt(generated.length()-1));
    }
    private char generateStartChar(){
        if(totalLetters == 0) return DEFAULT_CHAR;
        int randNum = random.nextInt(totalLetters);
        for(int i=0;i<LETTERS;i++){
            randNum -= totalFrequencies[i];
            if(randNum < 0) return indToLetter(i);
        }
        return  DEFAULT_CHAR;
    }
    private char generateNextChar(char pre){
        int prePos = letterToInd(pre);
        if(totalTo[prePos] == 0) return DEFAULT_CHAR;
        int randNum = random.nextInt(totalTo[prePos]);
        for(int i=0;i<LETTERS;i++){
            randNum -= frequencies[prePos][i];
            if(randNum<0) return indToLetter(i);
        }
        return DEFAULT_CHAR;
    }
    /**
     * Sets every entry in frequencies to zero
     */
    private void zeroFrequencies(){
        for(int i=0;i<LETTERS;i++){
            for(int j =0; j<LETTERS;j++){
                frequencies[i][j] = 0;
            }
        }
    }
    public void setFrequencies(File f) throws IOException {
        zeroFrequencies();

        FileReader fileReader = new FileReader(f);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        int prevCode=-1;
        int nextCode =0;
        while((nextCode=bufferedReader.read()) != -1){
            if(letterToInd(nextCode) == -1) continue;
            if(letterToInd(prevCode) != -1) frequencies[letterToInd(prevCode)][letterToInd(nextCode)] += 1;
            prevCode = nextCode;
        }

        setTotalFrequenciesAndTo();
    }
    private void setTotalFrequenciesAndTo(){
        for(int i=0;i<LETTERS; i++){
            totalFrequencies[i] = 0;
            totalTo[i] = 0;
            for(int j=0;j<LETTERS;j++){
                totalFrequencies[i] += frequencies[j][i];
                totalTo[i] += frequencies[i][j];
                totalLetters += frequencies[i][j];
            }
        }
    }
}

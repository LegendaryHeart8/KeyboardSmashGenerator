package main;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

import generators.RandomGenerator;
import generators.SpamGeneratorStats;

public class MainConsole {
    public static void main(String[] args) {
        File spamData;
        RandomGenerator RG = new RandomGenerator();
        SpamGeneratorStats SGS = new SpamGeneratorStats();
        try {
            spamData = getFileFromResource("Accumulated text spam data.txt");
            SGS.setFrequencies(spamData);
        }
        catch (URISyntaxException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner inputScanner = new Scanner(System.in);
        final int maxToGenerate = 9999;
        int toGenerate = 1;
        while(toGenerate != 0){
            System.out.println("How many letters do you want generated? (type 0 to exit)");
            String userInput = inputScanner.nextLine();
            try {
                toGenerate = Integer.parseInt(userInput);
                if(toGenerate > maxToGenerate){
                    System.out.println(String.format("Pick a number smaller than %d", maxToGenerate+1));
                }
                if(toGenerate < 0){
                    System.out.println("Pick a positive number");
                }
            } catch (NumberFormatException e) {
                toGenerate = maxToGenerate+1;
                System.out.println(String.format("%s is not a number, try again", userInput));
            }
            if(toGenerate>0 && toGenerate <= maxToGenerate) {
                //generate text and output
                SGS.generateNew(toGenerate);
                System.out.println(SGS.getNewGenerated());
            }
        }


    }
    private static File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = MainConsole.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {

            // failed if files have whitespaces or special characters
            //return new File(resource.getFile());

            return new File(resource.toURI());
        }

    }
}

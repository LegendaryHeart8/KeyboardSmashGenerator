package main;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import generators.SpamGeneratorStats;

public class MainConsole {
    public static void main(String[] args) {
        File spamData;
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
        //generate text and output
        SGS.generateNew(100);
        System.out.println(SGS.getNewGenerated());
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

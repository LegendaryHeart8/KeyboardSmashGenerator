import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class MainConsole {
    public static void main(String[] args) {
        File spamData;
        SpamGeneratorStats SGS;
        try {
            spamData = getFileFromResource("Accumulated text spam data.txt");
            SGS = new SpamGeneratorStats();
            SGS.setFrequencies(spamData);
        }
        catch (URISyntaxException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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

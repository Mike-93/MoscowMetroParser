import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws ParseException {

        JsonFileCreator j = new JsonFileCreator();
        try {
            j.createJsonFile();
            j.jsonParser();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class JsonFileCreator {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private Parser parser = new Parser();
    private Metro metro;


    public void createJsonFile() throws IOException {
        metro = new Metro(parser.parseLines(), parser.parseStations());
        try (FileWriter file = new FileWriter("src\\main\\resources\\map.json")) {
            file.write(GSON.toJson(metro));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public String getJsonFile(String path) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(line -> builder.append(line));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }
    public void jsonParser() throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(getJsonFile("src\\main\\resources\\map.json"));

        Map<String, List<String>> stations = (Map<String, List<String>>) jsonObject.get("stations");
        for (String lineId : stations.keySet()) {
            JSONArray stationsArray = (JSONArray) stations.get(lineId);
            for (Line line : metro.getLines()) {
                if (line.getId().equals(lineId)) {
                    System.out.println("Линия " + lineId + " <<" + line.getName() + ">> " + "\n" + "Количество станций: " + stationsArray.size());
                }
            }
        }
    }

}


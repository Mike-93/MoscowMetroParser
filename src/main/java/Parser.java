import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {

    public ArrayList<Line> parseLines() {
        Document document;
        ArrayList<Line> lineArrayList = new ArrayList<>();
        try {
            document = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines.ru/").maxBodySize(0).get();
            Elements name = document.select(".t-metrostation-list-header");
            for (Element names : name) {
                Line line = new Line(names.attr("data-line"), names.text());
                lineArrayList.add(line);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return lineArrayList;
    }

    public Map<String, List<String>> parseStations() {
        Document document;
        Map<String, List<String>> stationList = new HashMap<>();
        try {
            document = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines.ru/").maxBodySize(0).get();
            Elements lineName = document.select(".t-metrostation-list-header");
            for (Element lineNames : lineName) {
                Elements stationNames = document.select(".t-metrostation-list-table[data-line=" + lineNames.attr("data-line") + "] span.name");
                stationList.put(lineNames.attr("data-line"), stationNames.eachText());
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return stationList;
    }
}

// public HashMap<String, String> parseConnections() throws IOException {
//     Document document;
//     HashMap<String, String> connections = new HashMap<>();
//     document = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines.ru/").maxBodySize(0).get();
//     Elements lineNames = document.select(".t-metrostation-list-header");
//     for (Element lineName : lineNames) {
//         Elements stationNames = document.select(".t-metrostation-list-table[data-line=" + lineName.attr("data-line") + "] span.name");
//         for (Element name : stationNames) {
//             Elements connectionName = document.select("span.t-icon-metroln.ln" + lineNames.attr("data-line") + "[title]");
//             connections.put(name.text(), connectionName.toString());
//         }
//     }
//     return connections;
// }



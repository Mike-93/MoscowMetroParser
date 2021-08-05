import java.util.*;

public class Metro {

    private Map<String, List<String>> stations;
    private List<Line> lines;


    public Metro(List<Line> lines, Map<String, List<String>> stations) {
        this.lines = lines;
        this.stations = stations;
    }

    public List<Line> getLines() {
        return lines;
    }
}

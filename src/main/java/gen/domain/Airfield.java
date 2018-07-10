package gen.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javafx.util.Pair;

public enum Airfield {
    AL_DHAFRA_AIRBASE(0, MapConstants.PERSIAN_GULF, "P:[01..07, 24..35, 48..59, 87..99, 100, 190] | H:[01..07, 87..99, 190, 191] | B:[91, 93, 94, 96, 98]", new Pair<>(-123.0, -123.0), null, Arrays.asList("130R", "130L"));

    // Passed Data Members
    private int id;
    private MapConstants map;
    private String parkingRange;
    private Pair<Double, Double> airfieldPosition;
    private String tacanChannel;
    private List<String> availableRunways;

    // Calculated Data Members
    private List<Integer> normalParkingSpots;
    private List<Integer> helicopterParkingSpots;
    private List<Integer> heavyParkingSpots;
    private Set<Integer> uniqueParkingSpots;
    private int totalUniqueParkingSpots;

    Airfield(int id, MapConstants map, String parkingRange, Pair<Double, Double> airfieldPosition, String tacanChannel, List<String> availableRunways) {
        this.id = id;
        this.map = map;
        this.parkingRange = parkingRange;
        this.airfieldPosition = airfieldPosition;
        this.tacanChannel = tacanChannel;
        this.availableRunways = availableRunways;
        parseParkingString();
    }


    // Range Strings are interpreted as the following:
    //      Ex: P:[01..07, 87..99, 190, 191] | H: [1] | B: [91, 93]
    //              - Spots 1-7 are available for normal parking
    //              - Spots 87-99 are available for normal parking
    //              - Spot 190 is available for normal parking
    //              - Spot 191 is available for normal parking
    //              - Spot 1 is available for helicopter parking
    //              - Spot 91 is available for bomber/heavy parking
    //              - Spot 93 is available for bomber/heavy parking
    private void parseParkingString() {
        uniqueParkingSpots = new HashSet<>();
        String[] parkingSections = parkingRange.split("\\|");
        for (String parkingSection : parkingSections) {
            String trimmed = parkingSection.trim();
            String[] parts = trimmed.split(":");

            String type = parts[0];
            String pattern = parts[1];

            List<Integer> parsedPattern = parseParkingPatternString(pattern);
            uniqueParkingSpots.addAll(parsedPattern);
            switch (type) {
                case "P":
                    normalParkingSpots = parsedPattern;
                    break;
                case "H":
                    helicopterParkingSpots = parsedPattern;
                    break;
                case "B":
                    heavyParkingSpots = parsedPattern;
                    break;
            }
        }
        totalUniqueParkingSpots = uniqueParkingSpots.size();
    }

    private List<Integer> parseParkingPatternString(String pattern) {
        List<Integer> parsedInts = new ArrayList<>();

        // Replace any spurious characters and split our pattern
        String[] tokens = pattern.replace("[", "").replace("]", "").split(",");

        // For each token trim it, and parse it based on range rules
        for(String token : tokens) {
            token = token.trim();
            if(token.contains("..")) {
                String first = token.substring(0, token.indexOf(".."));
                String last = token.substring(token.indexOf("..") + 2, token.length());
                parsedInts.addAll(IntStream.rangeClosed(Integer.parseInt(first), Integer.parseInt(last)).boxed().collect(Collectors.toList()));
            } else {
                parsedInts.add(Integer.parseInt(token));
            }
        }

        return parsedInts;
    }

    public MapConstants getMap() {
        return map;
    }

    public static List<Airfield> getAirfieldsForMap(MapConstants mapSelection) {
        return Stream.of(Airfield.values()).filter((airfield) -> airfield.getMap() == mapSelection).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Airfield{" +
                "id=" + id +
                ", map=" + map +
                ", airfieldPosition=" + airfieldPosition +
                ", tacanChannel='" + tacanChannel + '\'' +
                ", availableRunways=" + availableRunways +
                ", normalParkingSpots=" + normalParkingSpots +
                ", helicopterParkingSpots=" + helicopterParkingSpots +
                ", heavyParkingSpots=" + heavyParkingSpots +
                ", uniqueParkingSpots=" + uniqueParkingSpots +
                ", totalUniqueParkingSpots=" + totalUniqueParkingSpots +
                '}';
    }
}

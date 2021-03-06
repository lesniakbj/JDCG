package sim.domain.enums;

import sim.util.MathUtil;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public enum AirfieldType {
    // PG airfields
    AL_DHAFRA_AIRBASE(0, MapType.PERSIAN_GULF, "P:[01..07, 24..35, 48..59, 87..99, 100, 190] | H:[01..07, 87..99, 190, 191] | B:[91, 93, 94, 96, 98]", new Point2D.Double(-123, -123), new Point2D.Double(753, 667), null, Arrays.asList("130R", "130L")),
    SIR_ABU_NUAYR(1, MapType.PERSIAN_GULF, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(730, 578), null, Arrays.asList("101R")),
    AL_MAKTOUM_INTL(2, MapType.PERSIAN_GULF, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(804, 609), null, Arrays.asList("101R")),
    AL_MINHAD_AB(3, MapType.PERSIAN_GULF, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(820, 596), null, Arrays.asList("101R")),
    FURJAIRAH_INTL(4, MapType.PERSIAN_GULF, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(898, 590), null, Arrays.asList("101R")),
    DUBAI_INTL(5, MapType.PERSIAN_GULF, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(821, 576), null, Arrays.asList("101R")),
    SHARJAH_INTL(6, MapType.PERSIAN_GULF, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(834, 569), null, Arrays.asList("101R")),
    ABU_MUSA_ISLAND_AIRPORT(7, MapType.PERSIAN_GULF, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(795, 520), null, Arrays.asList("101R")),
    SIRRI_ISLAND(8, MapType.PERSIAN_GULF, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(756, 516), null, Arrays.asList("101R")),
    KHASAB(9, MapType.PERSIAN_GULF, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(892, 494), null, Arrays.asList("101R")),
    TUNB_ISLAND_AFB(10, MapType.PERSIAN_GULF, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(819, 486), null, Arrays.asList("101R")),
    TUNB_KOCHAK(11, MapType.PERSIAN_GULF, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(804, 487), null, Arrays.asList("101R")),
    BANDAR_LENGEH(12, MapType.PERSIAN_GULF, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(779, 459), null, Arrays.asList("101R")),
    QESHM_ISLAND(13, MapType.PERSIAN_GULF, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(865, 441), null, Arrays.asList("101R")),
    HAVADARVA(14, MapType.PERSIAN_GULF, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(887, 405), null, Arrays.asList("101R")),
    BANDAR_ABBAS_INTL(15, MapType.PERSIAN_GULF, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(904, 401), null, Arrays.asList("101R")),
    LAR_AIRBASE(16, MapType.PERSIAN_GULF, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(746, 356), null, Arrays.asList("101R")),
    SHIRAZ_INTL_AIRPORT(59, MapType.PERSIAN_GULF, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(611, 183), null, Arrays.asList("101R")),
    KERMAN_AIRPORT(60, MapType.PERSIAN_GULF, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(949, 123), null, Arrays.asList("101R")),

    // Caucasus airfields
    BATUMI(17, MapType.CAUCASUS, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(1003, 629), null, Arrays.asList("101R")),
    KOBULETI(18, MapType.CAUCASUS, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(1029, 578), null, Arrays.asList("101R")),
    SENAKI_KOLKI(19, MapType.CAUCASUS, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(1044, 530), null, Arrays.asList("101R")),
    KUTAISI(20, MapType.CAUCASUS, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(1090, 533), null, Arrays.asList("101R")),
    SUKHUMI_BABUSHARA(21, MapType.CAUCASUS, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(933, 446), null, Arrays.asList("101R")),
    GUDAUTA(22, MapType.CAUCASUS, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(871, 416), null, Arrays.asList("101R")),
    SOCHI_ADLER(23, MapType.CAUCASUS, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(799, 372), null, Arrays.asList("101R")),
    BESLAN(24, MapType.CAUCASUS, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(1303, 352), null, Arrays.asList("101R")),
    MOZDOK(25, MapType.CAUCASUS, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(1290, 265), null, Arrays.asList("101R")),
    GELENDZHIK(26, MapType.CAUCASUS, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(584, 218), null, Arrays.asList("101R")),
    NOVOROSSIYSK(27, MapType.CAUCASUS, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(559, 206), null, Arrays.asList("101R")),
    NALCHIK(28, MapType.CAUCASUS, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(1192, 318), null, Arrays.asList("101R")),
    MINERALNYE_VODY(29, MapType.CAUCASUS, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(1120, 220), null, Arrays.asList("101R")),
    MAYKOP_KHANSKAYA(30, MapType.CAUCASUS, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(795, 187), null, Arrays.asList("101R")),
    KRASNODAR_PASHKOVSKY(31, MapType.CAUCASUS, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(702, 140), null, Arrays.asList("101R")),
    KRASNODAR_CENTER(32, MapType.CAUCASUS, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(680, 135), null, Arrays.asList("101R")),
    ANAPA_VITYAZEVO(33, MapType.CAUCASUS, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(511, 157), null, Arrays.asList("101R")),
    TBILISI_LOCHINI(34, MapType.CAUCASUS, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(1379, 580), null, Arrays.asList("101R")),
    KRYMSK(35, MapType.CAUCASUS, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(579, 160), null, Arrays.asList("101R")),

    // Nevada Airfields
    LAUGHLIN_AIRPORT(36, MapType.NEVADA, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(846, 672), null, Arrays.asList("101R")),
    JEAN_AIRPORT(37, MapType.NEVADA, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(743, 573), null, Arrays.asList("101R")),
    BOULDER_CITY_AIRPORT(38, MapType.NEVADA, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(804, 543), null, Arrays.asList("101R")),
    MINA_AIRPORT(39, MapType.NEVADA, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(388, 148), null, Arrays.asList("101R")),
    TONOPAH_AIRPORT(40, MapType.NEVADA, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(515, 200), null, Arrays.asList("101R")),
    TONOPAH_TEST_RANGE_AIRFIELD(41, MapType.NEVADA, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(554, 243), null, Arrays.asList("101R")),
    LINCOLN_COUNTY(42, MapType.NEVADA, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(854, 241), null, Arrays.asList("101R")),
    GROOM_LAKE_AIR_FORCE_BASE(43, MapType.NEVADA, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(679, 334), null, Arrays.asList("101R")),
    PAHUTE_MESA_AIRSTRIP(44, MapType.NEVADA, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(615, 358), null, Arrays.asList("101R")),
    BEATTY_AIRPORT(45, MapType.NEVADA, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(554, 397), null, Arrays.asList("101R")),
    CREECH_AIR_FORCE_BASE(46, MapType.NEVADA, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(698, 441), null, Arrays.asList("101R")),
    NORTH_LAS_VEGAS_AIRFIELD(47, MapType.NEVADA, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(758, 501), null, Arrays.asList("101R")),
    NELLIS_AIR_FORCE_BASE(48, MapType.NEVADA, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(781, 496), null, Arrays.asList("101R")),
    MESQUITE(49, MapType.NEVADA, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(904, 395), null, Arrays.asList("101R")),
    HENDERSON_EXECUTIVE_AIRPORT(50, MapType.NEVADA, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(769, 540), null, Arrays.asList("101R")),
    MCCARRAN_INTERNATIONAL_AIRPORT(51, MapType.NEVADA, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(766, 521), null, Arrays.asList("101R")),
    ECHO_BAY_AIRFIELD(52, MapType.NEVADA, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(854, 483), null, Arrays.asList("101R")),

    // Normandy airfields
    EVERUX(53, MapType.NORMANDY, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(1056, 655), null, Arrays.asList("101R")),
    CARPIQUET(54, MapType.NORMANDY, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(743, 627), null, Arrays.asList("101R")),
    LINGEROLLES(55, MapType.NORMANDY, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(681, 628), null, Arrays.asList("101R")),
    LESSAY(56, MapType.NORMANDY, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(549, 624), null, Arrays.asList("101R")),
    MAUPERTUS(57, MapType.NORMANDY, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(552, 494), null, Arrays.asList("101R")),
    NEEDS_OAR_POINT(58, MapType.NORMANDY, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(554, 167), null, Arrays.asList("101R")),
    FUNTINGTON(58, MapType.NORMANDY, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(652, 139), null, Arrays.asList("101R")),
    TANGMERE(58, MapType.NORMANDY, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(683, 144), null, Arrays.asList("101R")),
    FORD(58, MapType.NORMANDY, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(702, 152), null, Arrays.asList("101R")),
    CHALLEY(58, MapType.NORMANDY, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(799, 109), null, Arrays.asList("101R")),
    MEAUTIS(58, MapType.NORMANDY, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(584, 599), null, Arrays.asList("101R")),
    CHIPPELLE(58, MapType.NORMANDY, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(646, 609), null, Arrays.asList("101R")),
    AZEVILLE(58, MapType.NORMANDY, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(580, 542), null, Arrays.asList("101R")),
    LE_MOLAY(58, MapType.NORMANDY, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(663, 603), null, Arrays.asList("101R")),
    RUCQUECILLE(58, MapType.NORMANDY, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(718, 604), null, Arrays.asList("101R")),
    CRETTEVILLE(58, MapType.NORMANDY, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(569, 584), null, Arrays.asList("101R")),
    PICAUVILLE(58, MapType.NORMANDY, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(563, 567), null, Arrays.asList("101R")),
    BEUZEVILLE(58, MapType.NORMANDY, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(584, 559), null, Arrays.asList("101R")),
    BRUCHEV(58, MapType.NORMANDY, "P:[01..07] | H:[] | B:[]", new Point2D.Double(-123, -123), new Point2D.Double(600, 574), null, Arrays.asList("101R")),
    ;

    // Passed Data Members
    private int id;
    private MapType map;
    private String parkingRange;
    private Point2D.Double airfieldPosition;
    private Point2D.Double airfieldMapPosition;
    private String tacanChannel;
    private List<String> availableRunways;

    // Calculated Data Members //1290x1360
    private List<Integer> normalParkingSpots;
    private List<Integer> helicopterParkingSpots;
    private List<Integer> heavyParkingSpots;
    private Set<Integer> uniqueParkingSpots;
    private int totalUniqueParkingSpots;

    AirfieldType(int id, MapType map, String parkingRange, Point2D.Double airfieldPosition, Point2D.Double airfieldMapPosition, String tacanChannel, List<String> availableRunways) {
        this.id = id;
        this.map = map;
        this.parkingRange = parkingRange;
        this.airfieldPosition = airfieldPosition;
        this.airfieldMapPosition = airfieldMapPosition;
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
                if(!token.equals("")) {
                    parsedInts.add(Integer.parseInt(token));
                }
            }
        }

        return parsedInts;
    }

    public Double getDistanceTo(AirfieldType airfieldType) {
        return MathUtil.getDistance(this.getAirfieldMapPosition(), airfieldType.getAirfieldMapPosition());
    }

    public MapType getMap() {
        return map;
    }

    public static List<AirfieldType> getAirfieldsForMap(MapType mapSelection) {
        return Stream.of(AirfieldType.values()).filter((airfieldType) -> airfieldType.getMap() == mapSelection).collect(Collectors.toList());
    }

    public Point2D.Double getAirfieldPosition() {
        return airfieldPosition;
    }

    public Point2D.Double getAirfieldMapPosition() {
        return airfieldMapPosition;
    }

    public String getTacanChannel() {
        return tacanChannel;
    }

    public List<String> getAvailableRunways() {
        return availableRunways;
    }

    public List<Integer> getNormalParkingSpots() {
        return normalParkingSpots;
    }

    public List<Integer> getHelicopterParkingSpots() {
        return helicopterParkingSpots;
    }

    public List<Integer> getHeavyParkingSpots() {
        return heavyParkingSpots;
    }

    public Set<Integer> getUniqueParkingSpots() {
        return uniqueParkingSpots;
    }

    public int getTotalUniqueParkingSpots() {
        return totalUniqueParkingSpots;
    }

    @Override
    public String toString() {
        return "{\"AirfieldType\":"
                + super.toString()
                + ", \"id\":\"" + id + "\""
                + ", \"map\":\"" + map + "\""
                + ", \"parkingRange\":\"" + parkingRange + "\""
                + ", \"airfieldPosition\":" + airfieldPosition
                + ", \"airfieldMapPosition\":" + airfieldMapPosition
                + ", \"tacanChannel\":\"" + tacanChannel + "\""
                + ", \"availableRunways\":" + availableRunways
                + ", \"normalParkingSpots\":" + normalParkingSpots
                + ", \"helicopterParkingSpots\":" + helicopterParkingSpots
                + ", \"heavyParkingSpots\":" + heavyParkingSpots
                + ", \"uniqueParkingSpots\":" + uniqueParkingSpots
                + ", \"totalUniqueParkingSpots\":\"" + totalUniqueParkingSpots + "\""
                + "}";
    }

    public static AirfieldType searchByCoordinates(double mapXLocation, double mapYLocation) {
        Point2D.Double d = new Point2D.Double(mapXLocation, mapYLocation);
        for(AirfieldType type : AirfieldType.values()) {
            if(type.getAirfieldMapPosition().equals(d)) {
                return type;
            }
        }
        return null;
    }
}

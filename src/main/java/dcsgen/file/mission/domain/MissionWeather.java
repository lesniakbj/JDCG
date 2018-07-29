package dcsgen.file.mission.domain;

import java.util.LinkedList;
import java.util.List;

public class MissionWeather implements FilePart {
    private static final String HEADER = "[\"weather\"] = ";

    private static final String SEASON_SUBHEADER = "[\"season\"] = ";
    private static final String CYCLONES_SUBHEADER = "[\"cyclones\"] = ";
    private static final String FOG_SUBHEADER = "[\"fog\"] = ";
    private static final String VISIBILITY_SUBHEADER = "[\"visibility\"] = ";
    private static final String CLOUDS_SUBHEADER = "[\"clouds\"] = ";

    private static final String WIND_SUBHEADER = "[\"wind\"] = ";
    private static final String WIND_8000 = "[\"at8000\"] = ";
    private static final String WIND_GROUND = "[\"atGround\"] = ";
    private static final String WIND_2000 = "[\"at2000\"] = ";

    @Override
    public List<String> getFileParts() {
        List<String> parts = new LinkedList<>();
        parts.add("\t" + HEADER);
        parts.add("\t" + OPEN_BRACE);

        parts.add("\t\t" + "[\"atmosphere_type\"] = " + 0 +",");
        parts.add("\t\t" + "[\"groundTurbulence\"] = " + 0 +",");
        parts.add("\t\t" + "[\"enable_fog\"] = " + false +",");

        parts.add("\t\t" + SEASON_SUBHEADER);
        parts.add("\t\t" + OPEN_BRACE);
        parts.add("\t\t\t" + "[\"temperature\"] = " + 20 + ",");
        parts.add("\t\t" + CLOSE_BRACE_COMMA);

        parts.add("\t\t" + "[\"type_weather\"] = " + 0 +",");
        parts.add("\t\t" + "[\"qnh\"] = " + 760 +",");

        parts.add("\t\t" + CYCLONES_SUBHEADER);
        parts.add("\t\t" + OPEN_BRACE);
        parts.add("\t\t" + CLOSE_BRACE_COMMA);

        parts.add("\t\t" + "[\"name\"]  = \"Winter, clean sky\",");

        parts.add("\t\t" + FOG_SUBHEADER);
        parts.add("\t\t" + OPEN_BRACE);
        parts.add("\t\t\t" + "[\"thickness\"] = " + 0 + ",");
        parts.add("\t\t\t" + "[\"visibility\"] = " + 25 + ",");
        parts.add("\t\t\t" + "[\"density\"] = " + 7 + ",");
        parts.add("\t\t" + CLOSE_BRACE_COMMA);

        parts.add("\t\t" + WIND_SUBHEADER);
        parts.add("\t\t" + OPEN_BRACE);
        parts.add("\t\t\t" + WIND_8000);
        parts.add("\t\t\t" + OPEN_BRACE);
        parts.add("\t\t\t\t" + "[\"speed\"] = 0,");
        parts.add("\t\t\t\t" + "[\"dir\"] = 0,");
        parts.add("\t\t\t" + CLOSE_BRACE_COMMA);
        parts.add("\t\t\t" + WIND_GROUND);
        parts.add("\t\t\t" + OPEN_BRACE);
        parts.add("\t\t\t\t" + "[\"speed\"] = 0,");
        parts.add("\t\t\t\t" + "[\"dir\"] = 0,");
        parts.add("\t\t\t" + CLOSE_BRACE_COMMA);
        parts.add("\t\t\t" + WIND_2000);
        parts.add("\t\t\t" + OPEN_BRACE);
        parts.add("\t\t\t\t" + "[\"speed\"] = 0,");
        parts.add("\t\t\t\t" + "[\"dir\"] = 0,");
        parts.add("\t\t\t" + CLOSE_BRACE_COMMA);
        parts.add("\t\t" + CLOSE_BRACE_COMMA);

        parts.add("\t\t" + VISIBILITY_SUBHEADER);
        parts.add("\t\t" + OPEN_BRACE);
        parts.add("\t\t\t" + "[\"distance\"] = " + 8000 + ",");
        parts.add("\t\t" + CLOSE_BRACE_COMMA);

        parts.add("\t\t" + CLOUDS_SUBHEADER);
        parts.add("\t\t" + OPEN_BRACE);
        parts.add("\t\t\t" + "[\"thickness\"] = " + 200 + ",");
        parts.add("\t\t\t" + "[\"density\"] = " + 0 + ",");
        parts.add("\t\t\t" + "[\"base\"] = " + 360 + ",");
        parts.add("\t\t\t" + "[\"iprecptns\"] = " + 0 + ",");
        parts.add("\t\t" + CLOSE_BRACE_COMMA);

        parts.add("\t" + CLOSE_BRACE_COMMA);
        return parts;
    }
}

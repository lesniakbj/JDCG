package sim.main;

import sim.domain.Coalition;
import sim.domain.Map;
import sim.domain.statics.CampaignType;
import sim.domain.statics.ConflictEra;
import sim.domain.statics.MapConstants;

public class CampaignState {
    private static Map selectedMap;
    private static Coalition redforCoalition;
    private static Coalition blueforCoalition;
    private static Coalition neutralCoalition;
    private static ConflictEra selectedEra;
    private static CampaignType selectedCampaignType;

    public static void setBlueforCoalition(Coalition coalition) {
        blueforCoalition = coalition;
    }

    public static void setRedforCoalition(Coalition coalition) {
        redforCoalition = coalition;
    }

    public static void setNeutralCoalition(Coalition coalition) {
        neutralCoalition = coalition;
    }

    public static void setMapSelection(MapConstants mapSelection) {
        if(selectedMap == null) {
            selectedMap = new Map(mapSelection);
        }
        selectedMap.changeMap(mapSelection);
    }

    public static void setSelectedEra(ConflictEra era) {
        selectedEra = era;
    }

    public static void setSelectedCampaignType(CampaignType type) {
        selectedCampaignType = type;
    }

    public static void clearState() {
        selectedMap = null;
        redforCoalition = null;
        blueforCoalition = null;
        neutralCoalition = null;
        selectedEra = null;
    }

    public static String getStateString() {
        return "CampaignState{" +
                "\t" + selectedMap + ",\n" +
                "\t" + redforCoalition + ",\n" +
                "\t" + blueforCoalition + ",\n" +
                "\t" + neutralCoalition + ",\n" +
                "\t" + selectedEra + ",\n" +
                "\t" + selectedCampaignType + ",\n" +
                "}";
    }
}

package sim.main;

import sim.domain.Coalition;
import sim.domain.Map;
import sim.domain.statics.CampaignType;
import sim.domain.statics.ConflictEra;
import sim.domain.statics.MapConstants;

import java.util.ArrayList;

public class CampaignState {
    private static Map selectedMap;
    private static Coalition redforCoalition;
    private static Coalition blueforCoalition;
    private static Coalition neutralCoalition;
    private static ConflictEra selectedEra;
    private static CampaignType selectedCampaignType;

    static {
        selectedMap = new Map(MapConstants.CAUCASUS);
        redforCoalition = new Coalition(new ArrayList<>());
        blueforCoalition = new Coalition(new ArrayList<>());
        neutralCoalition = new Coalition(new ArrayList<>());
        selectedEra = ConflictEra.MODERN;
        selectedCampaignType = CampaignType.OFFENSIVE;
    }

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

    public static Map getSelectedMap() {
        return selectedMap;
    }

    public static Coalition getRedforCoalition() {
        return redforCoalition;
    }

    public static Coalition getBlueforCoalition() {
        return blueforCoalition;
    }

    public static Coalition getNeutralCoalition() {
        return neutralCoalition;
    }

    public static ConflictEra getSelectedEra() {
        return selectedEra;
    }

    public static CampaignType getSelectedCampaignType() {
        return selectedCampaignType;
    }

    public static void clearState() {
        selectedMap = null;
        redforCoalition = null;
        blueforCoalition = null;
        neutralCoalition = null;
        selectedEra = null;
        selectedCampaignType = null;
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

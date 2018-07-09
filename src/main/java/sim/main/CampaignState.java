package sim.main;

import sim.domain.Coalition;
import sim.domain.GameMap;
import sim.domain.statics.*;

import java.util.ArrayList;

public class CampaignState {
    private static GameMap selectedMap;
    private static Coalition redforCoalition;
    private static Coalition blueforCoalition;
    private static Coalition neutralCoalition;
    private static ConflictEra selectedEra;
    private static CampaignType selectedCampaignType;
    private static FactionSide playerSelectedSide;
    private static SquadronType selectedSquadron;

    static {
        selectedMap = new GameMap(MapConstants.CAUCASUS);
        redforCoalition = new Coalition(new ArrayList<>());
        blueforCoalition = new Coalition(new ArrayList<>());
        neutralCoalition = new Coalition(new ArrayList<>());
        selectedEra = ConflictEra.MODERN;
        selectedCampaignType = CampaignType.OFFENSIVE;
        playerSelectedSide = FactionSide.BLUFOR;
        selectedSquadron = SquadronType.NONE;
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
            selectedMap = new GameMap(mapSelection);
        }
        selectedMap.changeMap(mapSelection);
    }

    public static void setSelectedEra(ConflictEra era) {
        selectedEra = era;
    }

    public static void setSelectedCampaignType(CampaignType type) {
        selectedCampaignType = type;
    }

    public static void setPlayerSelectedSide(FactionSide selectedSide) {
        playerSelectedSide = selectedSide;
    }

    public static void setSelectedSquadron(SquadronType selectedSqd) {
        selectedSquadron = selectedSqd;
    }

    public static GameMap getSelectedMap() {
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

    public static FactionSide getPlayerSelectedSide() {
        return playerSelectedSide;
    }

    public static SquadronType getSelectedSquadron() {
        return selectedSquadron;
    }

    public static void clearState() {
        selectedMap = null;
        redforCoalition = null;
        blueforCoalition = null;
        neutralCoalition = null;
        selectedEra = null;
        selectedCampaignType = null;
        playerSelectedSide = null;
        selectedSquadron = null;
    }

    public static String getStateString() {
        return "CampaignState{" +
                "\t" + selectedMap + ",\n" +
                "\t" + redforCoalition + ",\n" +
                "\t" + blueforCoalition + ",\n" +
                "\t" + neutralCoalition + ",\n" +
                "\t" + selectedEra + ",\n" +
                "\t" + selectedCampaignType + ",\n" +
                "\t" + playerSelectedSide + ",\n" +
                "\t" + selectedSquadron + ",\n" +
                "}";
    }
}

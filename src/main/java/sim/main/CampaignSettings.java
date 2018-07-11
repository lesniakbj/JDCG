package sim.main;

import gen.domain.CampaignType;
import gen.domain.Coalition;
import gen.domain.ConflictEra;
import gen.domain.FactionSide;
import gen.domain.GameMap;
import gen.domain.MapConstants;
import gen.domain.SquadronType;

import java.util.ArrayList;

public class CampaignSettings {
    private GameMap selectedMap;
    private Coalition redforCoalition;
    private Coalition blueforCoalition;
    private Coalition neutralCoalition;
    private ConflictEra selectedEra;
    private CampaignType selectedCampaignType;
    private FactionSide playerSelectedSide;
    private SquadronType selectedSquadron;
    private boolean isComplete;

    public CampaignSettings() {
        selectedMap = new GameMap(MapConstants.CAUCASUS);
        redforCoalition = new Coalition(new ArrayList<>());
        blueforCoalition = new Coalition(new ArrayList<>());
        neutralCoalition = new Coalition(new ArrayList<>());
        selectedEra = ConflictEra.MODERN;
        selectedCampaignType = CampaignType.OFFENSIVE;
        playerSelectedSide = FactionSide.BLUEFOR;
        selectedSquadron = SquadronType.NONE;
    }

    public void setBlueforCoalition(Coalition coalition) {
        blueforCoalition = coalition;
    }

    public void setRedforCoalition(Coalition coalition) {
        redforCoalition = coalition;
    }

    public void setNeutralCoalition(Coalition coalition) {
        neutralCoalition = coalition;
    }

    public void setMapSelection(MapConstants mapSelection) {
        if(selectedMap == null) {
            selectedMap = new GameMap(mapSelection);
        }
        selectedMap.changeMap(mapSelection);
    }

    public void setSelectedEra(ConflictEra era) {
        selectedEra = era;
    }

    public void setSelectedCampaignType(CampaignType type) {
        selectedCampaignType = type;
    }

    public void setPlayerSelectedSide(FactionSide selectedSide) {
        playerSelectedSide = selectedSide;
    }

    public void setSelectedSquadron(SquadronType selectedSqd) {
        selectedSquadron = selectedSqd;
    }

    public GameMap getSelectedMap() {
        return selectedMap;
    }

    public Coalition getCoalitionBySide(FactionSide side) {
        switch (side) {
            case BLUEFOR:
                return getBlueforCoalition();
            case REDFOR:
                return getRedforCoalition();
            case NEUTRAL:
                return getNeutralCoalition();
        }
        return getBlueforCoalition();
    }

    private Coalition getRedforCoalition() {
        return redforCoalition;
    }

    private Coalition getBlueforCoalition() {
        return blueforCoalition;
    }

    private Coalition getNeutralCoalition() {
        return neutralCoalition;
    }

    public ConflictEra getSelectedEra() {
        return selectedEra;
    }

    public CampaignType getSelectedCampaignType() {
        return selectedCampaignType;
    }

    public FactionSide getPlayerSelectedSide() {
        return playerSelectedSide;
    }

    public SquadronType getSelectedSquadron() {
        return selectedSquadron;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public String toString() {
        return "CampaignSettings{" +
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

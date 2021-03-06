package sim.settings;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import sim.domain.enums.AircraftType;
import sim.domain.enums.CampaignType;
import sim.domain.enums.ConflictEraType;
import sim.domain.enums.FactionSideType;
import sim.domain.enums.FactionType;
import sim.domain.enums.MapType;
import sim.domain.enums.SquadronType;
import sim.domain.unit.global.Coalition;
import sim.domain.unit.global.GameMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

public class CampaignSettings {
    private GameMap selectedMap;
    private Coalition redforCoalition;
    private Coalition blueforCoalition;
    private Coalition neutralCoalition;
    private ConflictEraType selectedEra;
    private CampaignType selectedCampaignType;
    private FactionSideType playerSelectedSide;
    private SquadronType selectedSquadron;
    private AircraftType selectedAircraft;
    private boolean isComplete;
    private Date currentCampaignDate;
    private String dateFormat;

    public CampaignSettings() {
        selectedMap = new GameMap(MapType.CAUCASUS);
        redforCoalition = new Coalition(new ArrayList<>(Arrays.asList(FactionType.RUSSIA)));
        blueforCoalition = new Coalition(new ArrayList<>(Arrays.asList(FactionType.USA)));
        neutralCoalition = new Coalition(new ArrayList<>(Arrays.stream(FactionType.values()).filter(ft -> !ft.equals(FactionType.RUSSIA) && !ft.equals(FactionType.USA) && !ft.equals(FactionType.ALL)).collect(Collectors.toList())));
        selectedEra = ConflictEraType.MODERN;
        selectedCampaignType = CampaignType.OFFENSIVE;
        playerSelectedSide = FactionSideType.BLUEFOR;
        selectedSquadron = SquadronType.NONE;
        dateFormat = "MM/dd/yyyy HH:mm:ss";
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

    public void setMapSelection(MapType mapSelection) {
        if(selectedMap == null) {
            selectedMap = new GameMap(mapSelection);
        }
        selectedMap.changeMap(mapSelection);
    }

    public void setSelectedEra(ConflictEraType era) {
        selectedEra = era;
    }

    public void setSelectedCampaignType(CampaignType type) {
        selectedCampaignType = type;
    }

    public void setPlayerSelectedSide(FactionSideType selectedSide) {
        playerSelectedSide = selectedSide;
    }

    public void setSelectedSquadron(SquadronType selectedSqd) {
        selectedSquadron = selectedSqd;
    }

    public GameMap getSelectedMap() {
        return selectedMap;
    }

    public Coalition getCoalitionBySide(FactionSideType side) {
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

    public ConflictEraType getSelectedEra() {
        return selectedEra;
    }

    public CampaignType getSelectedCampaignType() {
        return selectedCampaignType;
    }

    public FactionSideType getPlayerSelectedSide() {
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

    public void setSelectedMap(GameMap selectedMap) {
        this.selectedMap = selectedMap;
    }

    public Date getCurrentCampaignDate() {
        return currentCampaignDate;
    }

    public void setCurrentCampaignDate(Date currentCampaignDate) {
        this.currentCampaignDate = currentCampaignDate;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public AircraftType getSelectedAircraft() {
        return selectedAircraft;
    }

    public void setSelectedAircraft(AircraftType selectedAircraft) {
        this.selectedAircraft = selectedAircraft;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this,
                ToStringStyle.MULTI_LINE_STYLE, true, true);
    }
}

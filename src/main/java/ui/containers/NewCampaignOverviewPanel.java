package ui.containers;

import sim.domain.Coalition;
import sim.domain.Map;
import sim.domain.statics.CampaignType;
import sim.domain.statics.ConflictEra;
import ui.util.SpringUtilities;

import javax.swing.*;

public class NewCampaignOverviewPanel extends JPanel {
    private JLabel mapSelection;
    private String mapSelected;

    private JLabel campaignEraSelection;
    private String campaignEraSelected;

    private JLabel campaignTypeSelection;
    private String campaignTypeSelected;

    public NewCampaignOverviewPanel() {
        setLayout(new SpringLayout());

        JLabel mapSelectionLabel = new JLabel("Map Selected: ");
        mapSelection = new JLabel(mapSelected);
        add(mapSelectionLabel);
        mapSelectionLabel.setLabelFor(mapSelection);
        add(mapSelection);

        JLabel campaignEra = new JLabel("Campaign Era Selected: ");
        campaignEraSelection = new JLabel(campaignEraSelected);
        add(campaignEra);
        campaignEra.setLabelFor(campaignEraSelection);
        add(campaignEraSelection);

        JLabel campaignType = new JLabel("Campaign Type Selected: ");
        campaignTypeSelection = new JLabel(campaignTypeSelected);
        add(campaignType);
        campaignType.setLabelFor(campaignTypeSelection);
        add(campaignTypeSelection);

        SpringUtilities.makeCompactGrid(this, 3, 2, 6, 6,6, 6);
    }

    public void setMapSelection(Map selectedMap) {
        this.mapSelected = selectedMap.getMapName();
        mapSelection.setText(mapSelected);
    }

    public void setBlueforCoalition(Coalition blueforCoalition) {
    }

    public void setRedforCoalition(Coalition redforCoalition) {
    }

    public void setNeutralCoalition(Coalition neutralCoalition) {
    }

    public void setSelectedEra(ConflictEra selectedEra) {
        this.campaignEraSelected = selectedEra.getEraName();
        campaignEraSelection.setText(campaignEraSelected);
    }

    public void setCampaignType(CampaignType selectedCampaignType) {
        this.campaignTypeSelected = selectedCampaignType.getCampaignTypeName();
        campaignTypeSelection.setText(campaignTypeSelected);
    }
}

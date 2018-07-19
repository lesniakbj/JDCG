package sim.domain.enums;

public enum CampaignType {
    OFFENSIVE("Offensive Campaign", "<li>Large number of starting Airbases</li><li>Large number of Enemy Air Defences</li><li>Ground Groups are generated around a Front Line</li></ul>"),
    DEFENSIVE("Defensive Campaign", "<li>Very few starting Airbases</li><li>Small number of Enemy Air Defences</li><li>Ground Groups are generated around a Front Line</li></ul>"),
    ALL_OUT_WAR("All Out War Campaign", "<li>Equal number of starting Airbases</li><li>Decent number of Enemy Air Defences</li><li>Ground Groups are generated around Airbases</li></ul>");

    private String campaignTypeName;
    private String campaignCharacteristics;

    CampaignType(String campaignTypeName, String campaignCharacteristics) {
        this.campaignTypeName = campaignTypeName;
        this.campaignCharacteristics = campaignCharacteristics;
    }

    public String getCampaignTypeName() {
        return campaignTypeName;
    }

    public static CampaignType fromName(String campaignTypeName) {
        for(CampaignType type : CampaignType.values()) {
            if(type.getCampaignTypeName().equalsIgnoreCase(campaignTypeName)) {
                return type;
            }
        }
        return OFFENSIVE;
    }

    public String getCharacteristics() {
        return campaignCharacteristics;
    }
}

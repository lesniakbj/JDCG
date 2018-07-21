package sim.domain.enums;

public enum FactionType {
    ALL(-1, "All", FactionStrengthType.LOW),
    ABKHAZIA(18, "Abkhazia", FactionStrengthType.LOW),
    ALGERIA(70, "Algeria", FactionStrengthType.LOW),
    AUSTRALIA(21, "Australia", FactionStrengthType.MEDIUM),
    AUSTRIA(23, "Austria", FactionStrengthType.LOW),
    BAHRAIN(65, "Bahrain", FactionStrengthType.LOW),
    BELARUS(24, "Belarus", FactionStrengthType.LOW),
    BRAZIL(64, "Brazil", FactionStrengthType.LOW),
    BELGIUM(11, "Belgium", FactionStrengthType.LOW),
    BULGARIA(25, "Bulgaria", FactionStrengthType.LOW),
    CANADA(8, "Canada", FactionStrengthType.MEDIUM),
    CHILE(63, "Chile", FactionStrengthType.LOW),
    CHINA(27, "China", FactionStrengthType.HIGH),
    CROATIA(28, "Croatia", FactionStrengthType.LOW),
    CZECH_REPUBLIC(26, "Czech Republic", FactionStrengthType.LOW),
    DENMARK(13, "Denmark", FactionStrengthType.LOW),
    EGYPT(29, "Egypt", FactionStrengthType.LOW),
    ETHIOPIA(62, "Ethiopia", FactionStrengthType.LOW),
    FINLAND(30, "Finland", FactionStrengthType.LOW),
    FRANCE(5, "France", FactionStrengthType.MEDIUM),
    GEORGIA(16, "Georgia", FactionStrengthType.LOW),
    GERMANY(6, "Germany", FactionStrengthType.MEDIUM),
    GREECE(31, "Greece", FactionStrengthType.LOW),
    HONDURAS(61, "Honduras", FactionStrengthType.LOW),
    HUNGARY(32, "Hungary", FactionStrengthType.LOW),
    INDIA(33, "India", FactionStrengthType.MEDIUM),
    INDONESIA(60, "Indonesia", FactionStrengthType.LOW),
    INSURGENTS(17, "Insurgents", FactionStrengthType.LOW),
    IRAN(34, "Iran", FactionStrengthType.LOW),
    IRAQ(35, "Iraq", FactionStrengthType.LOW),
    ISRAEL(15, "Israel", FactionStrengthType.HIGH),
    ITALIAN_SOCIAL_REPUBLIC(69, "Italian Social Republic", FactionStrengthType.LOW),
    ITALY(20, "Italy", FactionStrengthType.MEDIUM),
    JAPAN(36, "Japan", FactionStrengthType.MEDIUM),
    JORDAN(59, "Jordan", FactionStrengthType.LOW),
    KAZAKHSTAN(37, "Kazakhstan", FactionStrengthType.LOW),
    KUWAIT(71, "Kuwait", FactionStrengthType.LOW),
    LIBYA(58, "Libya", FactionStrengthType.LOW),
    MALAYSIA(57, "Malaysia", FactionStrengthType.LOW),
    MEXICO(56, "Mexico", FactionStrengthType.LOW),
    MOROCCO(55, "Morocco", FactionStrengthType.LOW),
    NORTH_KOREA(38, "North Korea", FactionStrengthType.LOW),
    NORWAY(12, "Norway", FactionStrengthType.LOW),
    OMAN(73, "Oman", FactionStrengthType.LOW),
    PAKISTAN(39, "Pakistan", FactionStrengthType.LOW),
    POLAND(40, "Poland", FactionStrengthType.LOW),
    PHILIPPINES(54, "Philippines", FactionStrengthType.LOW),
    QATAR(72, "Qatar", FactionStrengthType.LOW),
    ROMANIA(41, "Romania", FactionStrengthType.LOW),
    RUSSIA(0, "Russia", FactionStrengthType.HIGH),
    SAUDI_ARABIA(42, "Saudi Arabia", FactionStrengthType.LOW),
    SERBIA(43, "Serbia", FactionStrengthType.LOW),
    SOUTH_KOREA(45, "South Korea", FactionStrengthType.MEDIUM),
    SLOVAKIA(44, "Slovakia", FactionStrengthType.LOW),
    SOUTH_OSSETIA(19, "South Ossetia", FactionStrengthType.LOW),
    SPAIN(9, "Spain", FactionStrengthType.LOW),
    SUDAN(53, "Sudan", FactionStrengthType.LOW),
    SWEDEN(46, "Sweden", FactionStrengthType.LOW),
    SWITZERLAND(22, "Switzerland", FactionStrengthType.LOW),
    SYRIA(47, "Syria", FactionStrengthType.LOW),
    NETHERLANDS(10, "The Netherlands", FactionStrengthType.LOW),
    THAILAND(52, "Thailand", FactionStrengthType.LOW),
    THIRD_REICH(66, "Third Reich", FactionStrengthType.LOW),
    TUNISIA(51, "Tunisia", FactionStrengthType.LOW),
    TURKEY(3, "Turkey", FactionStrengthType.LOW),
    UAE(74, "United Arab Emirates", FactionStrengthType.LOW),
    UK(4, "UK", FactionStrengthType.MEDIUM),
    UKRAINE(1, "Ukraine", FactionStrengthType.LOW),
    USAF_AGGRESSORS(7, "USAF Aggressors", FactionStrengthType.MEDIUM),
    USA(2, "USA", FactionStrengthType.HIGH),
    USSR(68, "USSR", FactionStrengthType.HIGH),
    VENEZUELA(50, "Venezuela", FactionStrengthType.LOW),
    VIETNAM(49, "Vietnam", FactionStrengthType.LOW),
    YEMEN(48, "Yemen", FactionStrengthType.LOW),
    YUGOSLAVIA(67, "Yugoslavia", FactionStrengthType.LOW);

    private int dcsFactionId;
    private String dcsFactionName;
    private FactionStrengthType overallStrength;

    FactionType(int dcsFactionId, String dcsFactionName, FactionStrengthType overallStrength) {
        this.dcsFactionId = dcsFactionId;
        this.dcsFactionName = dcsFactionName;
        this.overallStrength = overallStrength;
    }

    public int getDcsFactionId() {
        return dcsFactionId;
    }

    public String getDcsFactionName() {
        return dcsFactionName;
    }

    public FactionStrengthType getOverallStrength() {
        return overallStrength;
    }

    public static FactionType fromName(String selectedFaction) {
        for(FactionType factionType : FactionType.values()) {
            if(factionType.getDcsFactionName().equalsIgnoreCase(selectedFaction)) {
                return factionType;
            }
        }
        return USA;
    }

    @Override
    public String toString() {
        return "FactionType{" +
                "dcsFactionId=" + dcsFactionId +
                ", dcsFactionName='" + dcsFactionName + '\'' +
                ", overallStrength=" + overallStrength +
                '}';
    }
}

package sim.domain.statics;

public enum Faction {
    ABKHAZIA(18, "Abkhazia", FactionStrength.LOW),
    ALGERIA(70, "Algeria", FactionStrength.LOW),
    AUSTRALIA(21, "Australia", FactionStrength.MEDIUM),
    AUSTRIA(23, "Austria", FactionStrength.LOW),
    BAHRAIN(65, "Bahrain", FactionStrength.LOW),
    BELARUS(24, "Belarus", FactionStrength.LOW),
    BRAZIL(64, "Brazil", FactionStrength.LOW),
    BELGIUM(11, "Belgium", FactionStrength.LOW),
    BULGARIA(25, "Bulgaria", FactionStrength.LOW),
    CANADA(8, "Canada", FactionStrength.MEDIUM),
    CHILE(63, "Chile", FactionStrength.LOW),
    CHINA(27, "China", FactionStrength.HIGH),
    CROATIA(28, "Croatia", FactionStrength.LOW),
    CZECH_REPUBLIC(26, "Czech Republic", FactionStrength.LOW),
    DENMARK(13, "Denmark", FactionStrength.LOW),
    EGYPT(29, "Egypt", FactionStrength.LOW),
    ETHIOPIA(62, "Ethiopia", FactionStrength.LOW),
    FINLAND(30, "Finland", FactionStrength.LOW),
    FRANCE(5, "France", FactionStrength.MEDIUM),
    GEORGIA(16, "Georgia", FactionStrength.LOW),
    GERMANY(6, "Germany", FactionStrength.MEDIUM),
    GREECE(31, "Greece", FactionStrength.LOW),
    HONDURAS(61, "Honduras", FactionStrength.LOW),
    HUNGARY(32, "Hungary", FactionStrength.LOW),
    INDIA(33, "India", FactionStrength.MEDIUM),
    INDONESIA(60, "Indonesia", FactionStrength.LOW),
    INSURGENTS(17, "Insurgents", FactionStrength.LOW),
    IRAN(34, "Iran", FactionStrength.LOW),
    IRAQ(35, "Iraq", FactionStrength.LOW),
    ISRAEL(15, "Israel", FactionStrength.HIGH),
    ITALIAN_SOCIAL_REPUBLIC(69, "Italian Social Republic", FactionStrength.LOW),
    ITALY(20, "Italy", FactionStrength.MEDIUM),
    JAPAN(36, "Japan", FactionStrength.MEDIUM),
    JORDAN(59, "Jordan", FactionStrength.LOW),
    KAZAKHSTAN(37, "Kazakhstan", FactionStrength.LOW),
    KUWAIT(71, "Kuwait", FactionStrength.LOW),
    LIBYA(58, "Libya", FactionStrength.LOW),
    MALAYSIA(57, "Malaysia", FactionStrength.LOW),
    MEXICO(56, "Mexico", FactionStrength.LOW),
    MOROCCO(55, "Morocco", FactionStrength.LOW),
    NORTH_KOREA(38, "North Korea", FactionStrength.LOW),
    NORWAY(12, "Norway", FactionStrength.LOW),
    OMAN(73, "Oman", FactionStrength.LOW),
    PAKISTAN(39, "Pakistan", FactionStrength.LOW),
    POLAND(40, "Poland", FactionStrength.LOW),
    PHILIPPINES(54, "Philippines", FactionStrength.LOW),
    QATAR(72, "Qatar", FactionStrength.LOW),
    ROMANIA(41, "Romania", FactionStrength.LOW),
    RUSSIA(0, "Russia", FactionStrength.HIGH),
    SAUDI_ARABIA(42, "Saudi Arabia", FactionStrength.LOW),
    SERBIA(43, "Serbia", FactionStrength.LOW),
    SOUTH_KOREA(45, "South Korea", FactionStrength.MEDIUM),
    SLOVAKIA(44, "Slovakia", FactionStrength.LOW),
    SOUTH_OSSETIA(19, "South Ossetia", FactionStrength.LOW),
    SPAIN(9, "Spain", FactionStrength.LOW),
    SUDAN(53, "Sudan", FactionStrength.LOW),
    SWEDEN(46, "Sweden", FactionStrength.LOW),
    SWITZERLAND(22, "Switzerland", FactionStrength.LOW),
    SYRIA(47, "Syria", FactionStrength.LOW),
    NETHERLANDS(10, "The Netherlands", FactionStrength.LOW),
    THAILAND(52, "Thailand", FactionStrength.LOW),
    THIRD_REICH(66, "Third Reich", FactionStrength.LOW),
    TUNISIA(51, "Tunisia", FactionStrength.LOW),
    TURKEY(3, "Turkey", FactionStrength.LOW),
    UAE(74, "United Arab Emirates", FactionStrength.LOW),
    UK(4, "UK", FactionStrength.MEDIUM),
    UKRAINE(1, "Ukraine", FactionStrength.LOW),
    USAF_AGGRESSORS(7, "USAF Aggressors", FactionStrength.MEDIUM),
    USA(2, "USA", FactionStrength.HIGH),
    USSR(68, "USSR", FactionStrength.HIGH),
    VENEZUELA(50, "Venezuela", FactionStrength.LOW),
    VIETNAM(49, "Vietnam", FactionStrength.LOW),
    YEMEN(48, "Yemen", FactionStrength.LOW),
    YUGOSLAVIA(67, "Yugoslavia", FactionStrength.LOW);

    private int dcsFactionId;
    private String dcsFactionName;
    private FactionStrength overallStrength;

    Faction(int dcsFactionId, String dcsFactionName, FactionStrength overallStrength) {
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

    public FactionStrength getOverallStrength() {
        return overallStrength;
    }

    public static Faction fromName(String selectedFaction) {
        for(Faction faction : Faction.values()) {
            if(faction.getDcsFactionName().equalsIgnoreCase(selectedFaction)) {
                return faction;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Faction{" +
                "dcsFactionId=" + dcsFactionId +
                ", dcsFactionName='" + dcsFactionName + '\'' +
                ", overallStrength=" + overallStrength +
                '}';
    }
}

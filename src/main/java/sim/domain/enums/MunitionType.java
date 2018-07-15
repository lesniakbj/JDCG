package sim.domain.enums;

public enum MunitionType {
    AIM7M("AIM-7M"),
    AIM9M("AIM-9M"),
    AIM9MX2("AIM-9M x 2"),
    AIM9X("AIM-9X"),
    AIM9XX2("AIM-9X x 2"),
    AIM120B("AIM-120B"),
    AIM120C("AIM-120X"),
    MK_82("Mk82"),
    MK_82X2("Mk82 x 2"),
    MK_82X3("Mk82 x 3"),
    MK_83("Mk83"),
    MK_83X2("Mk83 x 2"),
    MK_83X3("Mk83 x 3"),
    MK_84("Mk84"),
    MK_84X2("Mk84 x 2"),
    MK_84X3("Mk84 x 3");

    private String munitionName;

    MunitionType(String munitionName) {
        this.munitionName = munitionName;
    }

    public String getMunitionName() {
        return munitionName;
    }
}

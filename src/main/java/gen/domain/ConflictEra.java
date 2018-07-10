package gen.domain;

public enum ConflictEra {
    MODERN("Modern Era", "In the Modern Era, the Coalition sees itself against a number of high tech opponents, many of which posses 4th and 4+ Generation Aircraft. Further, advances in AAA and SAM technology have created distinct threats for pilots flying deep into opponents' territories. While the threat level is high, there are many advantages that the Coalition has, from advanced Airframes and Weaponry, to sheer numbers of maintainable aircraft. This Campaign Era is characterized by the following: <ul><li>Modern selection of Aircraft</li><li>Advanced AAA and SAM threats</li><li>A high threat environment from the Air, Sea, and Ground</li>"),
    GULF_WAR("Gulf War Era", "In the Gulf War of 1990–91, dogfighting again proved its usefulness when the Coalition Air Force had to face off against the Iraqi Air Force, which at the time was the fifth largest in the world. By the second day of the war, the Coalition achieved air superiority. Many dogfights occurred during the short conflict, often involving many planes. By the end of January, 1991, the term \"furball\" became a popular word to describe the hectic situation of many dogfights, occurring at the same time within the same relatively small airspace. By the end of the war, the U.S. claimed 39 Iraqi aircraft in air-to-air victories to the loss of only one F/A-18 and one drone. Of the 39 victories, 36 were taken by F-15 Eagles. This Campaign Era is characterized by the following: <ul><li>A more modern environment, with weaker foes</li>"),
    VIETNAM("Vietnam Era", "The Vietnam War was the first 'modern' air war in which air-to-air missiles were the primary weapons during aerial combat, and was the only confrontation between the latest aerial and ground defense technologies between the Soviet Union and the United States. Over the skies of North Vietnam, U.S. aircraft would be attacking the most formidable and most heavily defended targets in the history of aerial warfare. Approximately 612 radar-guided AIM-7 Sparrow missiles were fired during the war, scoring 56 MiG kills, while 454 heat-seeking AIM-9 Sidewinders were launched achieving 81 aerial victories. During Operation Rolling Thunder 54 AIM-4 Falcon missiles were fired, obtaining five kills. By contrast, NVAF MiG-21s obtained 53 air-to-air kills with their AA-2 Atoll missiles, from an unknown number of launchings. At least three MiG-21s, and all of the MiG-17s and MiG-19s (J-6s) made the remaining 37 kills, from their 90 total, with their 23 mm, 30 mm and 37 mm cannons. This Campaign Era is characterized by the following:<ul><li>The golden age of Fighters, heavily focused on Ground Attack and WVR Engagements</li>"),
    KOREA("Korean Era", "After World War II, the question began to rise about the future usefulness of fighter aircraft. This was especially true for the U.S., where the focus was placed on small, fast, long-range bombers capable of delivering atomic bombs. At 100 mph (160 km/h) faster, the MiG-15 was more than a match for the U.S. P-80 Shooting Star, using the same dive and shoot tactic that the Americans found so useful against Japan. The U.S. jets had inferior weaponry, and suffered from problems with production and parts. The U.S. resorted to using mainly the more maneuverable propeller driven fighters during the war, such as the P-51 Mustang which was carried over from World War II. To combat the MiGs, the F-86 Sabre was put into production. The U.S. pilots had a number of major advantages over the Chinese, including the G-suit and the radar-ranging gunsight. This Campaign is characterized by the following: <ul><li>A classical campaign, with selection of WWII and Early Jet Aircraft</li>"),
    WWII("World War II Era", "Dogfighting was very prominent in the skies over Europe. The air force in France, while a major force during World War I, was inadequate and poorly organized, and quickly fell to the German onslaught. As the first battles between the Germans and the British began, the power of the German’s anti-aircraft artillery became readily apparent, with 88 millimeter shells capable of firing 40,000 feet (12,000 m) in the air. General Wolfram von Richthofen noted that these guns were equally destructive when used for ground fire. Technology advanced extremely fast during World War II in ways that would change dogfighting forever. Jet propulsion had been demonstrated long before the war, by German engineer Hans von Ohain in 1934, and by British engineer Frank Whittle in 1937. This Campaign is characterized by the following: <ul><li>A classical campaign, with selection of WWII Aircraft</li><li>Large Bomber Formations</li>");

    private String conflictEraName;
    private String eraDescription;

    ConflictEra(String conflictEraName, String eraDescription) {
        this.conflictEraName = conflictEraName;
        this.eraDescription = eraDescription;
    }

    public String getEraName() {
        return conflictEraName;
    }


    public String getEraDescription() {
        return eraDescription;
    }

    @Override
    public String toString() {
        return "ConflictEra{" +
                "conflictEraName='" + conflictEraName + '\'' +
                '}';
    }

    public static ConflictEra fromName(String eraToFind) {
        for(ConflictEra era : ConflictEra.values()) {
            if(era.getEraName().equalsIgnoreCase(eraToFind)) {
                return era;
            }
        }
        return MODERN;
    }

}

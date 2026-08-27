package REISYO;

public class GachaResult {

    private final String characters;
    private final String rarity;

    public GachaResult(String characters, String rarity) {
        this.characters = characters;
        this.rarity = rarity;
    }

    public String getCharacters() {
        return characters;
    }

    public String getRarity() {
        return rarity;
    }

    public boolean isSSR() {
        return rarity.equals("SSR");
    }

    public boolean isSR() {
        return rarity.equals("SR");
    }

    public boolean isRare() {
        return isSSR() || isSR();
    }
    
    public int getRarityRank() {

        switch (rarity) {

        case "SSR":
            return 3;

        case "SR":
            return 2;

        default:
            return 1;
        }
    }
}


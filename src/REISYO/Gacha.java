package REISYO;

import java.util.Random;

public class Gacha {

    private final Random random = new Random();

    public GachaResult draw() {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 3; i++) {

            switch (random.nextInt(3)) {

            case 0:
                result.append("う");
                break;

            case 1:
                result.append("お");
                break;

            case 2:
                result.append("ｗ");
                break;
            }
        }

        String characters = result.toString();

        return new GachaResult(
                characters,
                judgeRarity(characters));
    }

    public GachaResult[] drawTen() {

        GachaResult[] results =
                new GachaResult[10];

        for (int i = 0; i < 10; i++) {
            results[i] = draw();
        }

        return results;
    }

    private String judgeRarity(String characters) {

        if (characters.equals("うおｗ")) {
            return "SSR";
        }

        if (characters.equals("おうｗ")
                || characters.equals("おおｗ")
                || characters.equals("ｗｗｗ")) {

            return "SR";
        }

        return "通常";
    }
}

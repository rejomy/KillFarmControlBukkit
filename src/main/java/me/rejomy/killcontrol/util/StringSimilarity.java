package me.rejomy.killcontrol.util;

public class StringSimilarity {

    private final float similarity;

    public StringSimilarity(String text, String text2) {

        if(text.length() == 0) {
            similarity = text2.length() == 0? 1 : 0;
            return;
        }

        int value = 0;
        char lastChar = 0;

        for(int number = 0; number < text.length(); number++) {

            for(int number2 = 0; number2 < text2.length(); number2++) {

                if(
                        text.charAt(number) == text2.charAt(number2)
                        && number - number2 < 2
                        && lastChar != text.charAt(number)
                ) {
                    value+=1;
                    break;
                }

            }

            lastChar = text.charAt(number);
        }

        similarity = (float) value / text.length();
    }

    public float getSimilarity() {
        return similarity;
    }

    public int getPercent() {
        return (int) (similarity * 100);
    }

}

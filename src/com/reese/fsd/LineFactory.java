package com.reese.fsd;

public class LineFactory {

    public static Line createFromString(String rawLine) {
        LineType lineType;
        byte prefixSize;

        char firstChar = rawLine.charAt(0);

        switch (firstChar) {
            case '%':
                lineType = LineType.ATC_POSITION;
                prefixSize = 1;
                break;
            case '#':
                prefixSize = 3;

        }
    }

    private static String cut(String str, int prefixSize) {
        return str.substring(prefixSize);
    }

}

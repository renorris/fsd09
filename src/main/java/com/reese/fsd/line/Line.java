package com.reese.fsd.line;

public class Line {

    public LineType lineType;
    public String[] fields;

    public Line(LineType lineType, String[] fields) {
        this.lineType = lineType;
        this.fields = fields;
    }

}

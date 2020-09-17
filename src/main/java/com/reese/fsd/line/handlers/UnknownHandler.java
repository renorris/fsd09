package com.reese.fsd.line.handlers;

public class UnknownHandler extends LineHandler {

    UnknownHandler(LineHandlerArgs args) {
        super(args);
    }

    @Override
    public String[] process() {
        System.out.println("Unknown line: " + this.line);
        return new String[0];
    }
}

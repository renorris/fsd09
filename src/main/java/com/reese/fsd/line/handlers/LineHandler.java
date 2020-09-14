package com.reese.fsd.line.handlers;

import com.reese.fsd.PUCManager;
import com.reese.fsd.line.Line;

public abstract class LineHandler {

    public Line line;
    public PUCManager pucManager;

    LineHandler(Line line, PUCManager pucManager) {
        this.line = line;
        this.pucManager = pucManager;
    }

    // process() is responsible for returning a String[] of lines to be sent to the client on this thread.
    // process() is responsible for distributing lines to other clients as required, but not to return them here.
    public abstract String[] process();

}

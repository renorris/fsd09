package com.reese.fsd.line.handlers;

import com.reese.fsd.UserAPI;
import com.reese.fsd.UserData;
import com.reese.fsd.line.Line;

public abstract class LineHandler {

    protected Line line;
    protected UserAPI userAPI;
    protected UserData userData;

    LineHandler(LineHandlerArgs args) {
        this.line = args.line;
        this.userAPI = args.userAPI;
        this.userData = args.userData;
    }

    // process() is responsible for returning a String[] of lines to be sent to the client on this thread.
    // process() is responsible for distributing lines to other clients as required, but not to return them here.
    public abstract String[] process();

}

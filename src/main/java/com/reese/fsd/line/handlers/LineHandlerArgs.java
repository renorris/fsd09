package com.reese.fsd.line.handlers;

import com.reese.fsd.UserAPI;
import com.reese.fsd.UserData;
import com.reese.fsd.line.Line;

public class LineHandlerArgs {

    public Line line;
    public UserAPI userAPI;
    public UserData userData;

    public LineHandlerArgs(Line line, UserAPI userAPI, UserData userData) {
        this.line = line;
        this.userAPI = userAPI;
        this.userData = userData;
    }

}

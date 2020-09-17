package com.reese.fsd.line.handlers;

import com.reese.fsd.line.Line;

public class HandlerDispatcher {

    public static LineHandler dispatch(LineHandlerArgs args) {
        return switch(args.line.lineType) {
            case CLIENT_IDENTIFICATION -> new ClientIdentificationHandler(args);
            default -> new UnknownHandler(args);
        };
    }

}

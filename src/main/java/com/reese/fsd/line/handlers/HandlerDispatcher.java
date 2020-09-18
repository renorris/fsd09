package com.reese.fsd.line.handlers;

public class HandlerDispatcher {

    public static LineHandler dispatch(LineHandlerArgs args) {

        return switch(args.line.lineType) {
            case CLIENT_IDENTIFICATION -> new ClientIdentificationHandler(args);
            case ADD_ATC -> new AddATCHandler(args);
            case ATC_POSITION -> new ATCPositionHandler(args);
            default -> new UnknownHandler(args);
        };
    }

}

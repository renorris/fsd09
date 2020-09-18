package com.reese.fsd.mailbox;

import com.reese.fsd.user.UserData;

public class Message {

    public MessageType messageType;
    public UserData sender;
    public String recipientCallsign;

    public Message(MessageType messageType, UserData sender, String recipientCallsign) {
        this.messageType = messageType;
        this.sender = sender;
        this.recipientCallsign = recipientCallsign;
    }
}

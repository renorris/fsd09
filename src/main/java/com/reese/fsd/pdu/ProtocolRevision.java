package com.reese.fsd.pdu;

public enum ProtocolRevision {
    UNKNOWN(0),
    CLASSIC(9),
    VATSIM_NO_AUTH(10),
    VATSIM_AUTH(100);

    public int num;

    ProtocolRevision(int num) {
        this.num = num;
    }
}

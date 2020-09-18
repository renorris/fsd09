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

    public static ProtocolRevision fromID(int id) {
        return switch(id) {
            case 9 -> ProtocolRevision.CLASSIC;
            case 10 -> ProtocolRevision.VATSIM_NO_AUTH;
            case 100 -> ProtocolRevision.VATSIM_AUTH;
            default -> ProtocolRevision.UNKNOWN;
        };
    }
}

package com.reese.fsd.line;

public class LineFactory {

    public static Line createFromString(String str) {
        LineType lineType;
        byte prefixSize = 0;
        String[] splitStr = str.split(":");

        if (str.startsWith("%")) {
            lineType = LineType.ATC_POSITION;
            prefixSize = 1;
        }
        else if (str.startsWith("@")) {
            lineType = LineType.PILOT_POSITION;
            prefixSize = 1;
        }
        else if (str.startsWith("#TM")) {
            prefixSize = 3;
            if (splitStr[1].equals("@49999")) {
                lineType = LineType.ATC_MESSAGE;
            }
            else if (splitStr[1].equals("*")) {
                lineType = LineType.BROADCAST_MESSAGE;
            }
            else if (splitStr[1].equals("*S")) {
                lineType = LineType.WALLOP;
            }
            else if (splitStr[1].startsWith("@")) {
                lineType = LineType.RADIO_MESSAGE;
            }
            else {
                lineType = LineType.DIRECT_MESSAGE;
            }
        }
        else if (str.startsWith("#AA")) {
            prefixSize = 3;
            lineType = LineType.ADD_ATC;
        }
        else if (str.startsWith("#AP")) {
            prefixSize = 3;
            lineType = LineType.ADD_PILOT;
        }
        else if (str.startsWith("$ZC")) {
            prefixSize = 3;
            lineType = LineType.AUTH_CHALLENGE;
        }
        else if (str.startsWith("$ZR")) {
            prefixSize = 3;
            lineType = LineType.AUTH_RESPONSE;
        }
        else if (str.startsWith("$ID")) {
            prefixSize = 3;
            lineType = LineType.CLIENT_IDENTIFICATION;
        }
        else if (str.startsWith("$CQ")) {
            prefixSize = 3;
            lineType = LineType.CLIENT_QUERY;
        }
        else if (str.startsWith("$CR")) {
            prefixSize = 3;
            lineType = LineType.CLIENT_QUERY_RESPONSE;
        }
        else if (str.startsWith("#CD")) {
            prefixSize = 3;
            lineType = LineType.CLOUD_DATA;
        }
        else if (str.startsWith("#DA")) {
            prefixSize = 3;
            lineType = LineType.DELETE_ATC;
        }
        else if (str.startsWith("#DP")) {
            prefixSize = 3;
            lineType = LineType.DELETE_PILOT;
        }
        else if (str.startsWith("$FP")) {
            prefixSize = 3;
            lineType = LineType.FLIGHT_PLAN;
        }
        else if (str.startsWith("$AM")) {
            prefixSize = 3;
            lineType = LineType.FLIGHT_PLAN_AMENDMENT;
        }
        else if (str.startsWith("#PC")) {
            prefixSize = 3;
            if (splitStr[2].equals("CCP")) {
                if (splitStr[3].equals("ST")) {
                    lineType = LineType.FLIGHT_STRIP;
                }
                else if (splitStr[3].equals("HC")) {
                    lineType = LineType.HANDOFF_CANCELLED;
                }
                else if (splitStr[3].equals("IH")) {
                    lineType = LineType.I_HAVE_TARGET;
                }
                else if (splitStr[3].equals("ID")) {
                    lineType = LineType.MODERN_CLIENT_CHECK;
                }
                else if (splitStr[3].equals("PT")) {
                    lineType = LineType.POINTOUT;
                }
                else if (splitStr[3].equals("DP")) {
                    lineType = LineType.PUSH_TO_DEPARTURE_LIST;
                }
                else if (splitStr[3].equals("SC")) {
                    lineType = LineType.AMEND_SCRATCHPAD;
                }
                else if (splitStr[3].equals("BC")) {
                    lineType = LineType.AMEND_BEACON_CODE;
                }
                else if (splitStr[3].equals("VT")) {
                    lineType = LineType.AMEND_VOICE_TYPE;
                }
                else if (splitStr[3].equals("TA")) {
                    lineType = LineType.AMEND_TEMP_ALT;
                }
                else if (splitStr[3].equals("VER")) {
                    lineType = LineType.VERSION_REQUEST;
                }
                else {
                    lineType = LineType.UNKNOWN;
                }
            }
            else {
                lineType = LineType.UNKNOWN;
            }
        }
        else if (str.startsWith("$HO")) {
            prefixSize = 3;
            lineType = LineType.HANDOFF;
        }
        else if (str.startsWith("$HA")) {
            prefixSize = 3;
            lineType = LineType.HANDOFF_ACCEPT;
        }
        else if (str.startsWith("$!!")) {
            lineType = LineType.KILL_REQUEST;
        }
        else if (str.startsWith("#SB")) {
            prefixSize = 3;
            if (splitStr[2].equals("PI") && splitStr[3].equals("X")) {
                lineType = LineType.LEGACY_PLANE_INFO_RESPONSE;
            }
            else if (splitStr[2].equals("PI") && splitStr[3].equals("GEN")) {
                lineType = LineType.PLANE_INFO_RESPONSE;
            }
            else if (splitStr[2].equals("PIR")) {
                lineType = LineType.PLANE_INFO_REQUEST;
            }
            else {
                lineType = LineType.UNKNOWN;
            }
        }
        else if (str.startsWith("$AX")) {
            prefixSize = 3;
            lineType = LineType.METAR_REQUEST;
        }
        else if (str.startsWith("$AR")) {
            prefixSize = 3;
            lineType = LineType.METAR_RESPONSE;
        }
        else if (str.startsWith("$PI")) {
            prefixSize = 3;
            lineType = LineType.PING;
        }
        else if (str.startsWith("$PO")) {
            prefixSize = 3;
            lineType = LineType.PONG;
        }
        else if (str.startsWith("$ER")) {
            prefixSize = 3;
            lineType = LineType.PROTOCOL_ERROR;
        }
        else if (str.startsWith("'")) {
            prefixSize = 1;
            lineType = LineType.SECONDARY_VIS_CENTER;
        }
        else if (str.startsWith("$DI")) {
            prefixSize = 3;
            lineType = LineType.SERVER_IDENTIFICATION;
        }
        else if (str.startsWith("#TD")) {
            prefixSize = 3;
            lineType = LineType.TEMPERATURE_DATA;
        }
        else if (str.startsWith("#WX")) {
            prefixSize = 3;
            lineType = LineType.WEATHER_PROFILE_REQUEST;
        }
        else if (str.startsWith("#WD")) {
            prefixSize = 3;
            lineType = LineType.WIND_DATA;
        }
        else {
            lineType = LineType.UNKNOWN;
        }

        if (lineType == LineType.UNKNOWN) {
            return null;
        }
        else {
            String strippedLine = str.substring(prefixSize);
            return new Line(lineType, strippedLine.split(":"));
        }

    }

}

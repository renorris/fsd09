package com.reese.fsd.pdu;

public class PDUFlightPlanAmendment extends PDUFlightPlan {

    public String callsign;

    public PDUFlightPlanAmendment(String from, String to, String callsign, FlightRules rules, String equipment, String tas, String depAirport, String estimatedDepTime, String actualDepTime, String cruiseAlt, String destAirport, String hoursEnroute, String minutesEnroute, String fuelAvailHours, String fuelAvailMinutes, String altAirport, String remarks, String route) {
        super(from, to, rules, equipment, tas, depAirport, estimatedDepTime, actualDepTime, cruiseAlt, destAirport, hoursEnroute, minutesEnroute, fuelAvailHours, fuelAvailMinutes, altAirport, remarks, route);
        this.callsign = callsign;
    }

    @Override
    public String serialize() {
        StringBuilder msg = new StringBuilder("$AM");
        msg.append(this.from);
        msg.append(DELIMITER);
        msg.append(this.to);
        msg.append(DELIMITER);
        msg.append(this.callsign);
        msg.append(DELIMITER);
        msg.append(this.rules.toString().charAt(0));
        msg.append(DELIMITER);
        msg.append(this.equipment);
        msg.append(DELIMITER);
        msg.append(this.tas);
        msg.append(DELIMITER);
        msg.append(this.depAirport);
        msg.append(DELIMITER);
        msg.append(this.estimatedDepTime);
        msg.append(DELIMITER);
        msg.append(this.actualDepTime);
        msg.append(DELIMITER);
        msg.append(this.cruiseAlt);
        msg.append(DELIMITER);
        msg.append(this.destAirport);
        msg.append(DELIMITER);
        msg.append(this.hoursEnroute);
        msg.append(DELIMITER);
        msg.append(this.minutesEnroute);
        msg.append(DELIMITER);
        msg.append(this.fuelAvailHours);
        msg.append(DELIMITER);
        msg.append(this.fuelAvailMinutes);
        msg.append(DELIMITER);
        msg.append(this.altAirport);
        msg.append(DELIMITER);
        msg.append(this.remarks);
        msg.append(DELIMITER);
        msg.append(this.route);
        return msg.toString();
    }

    public static PDUFlightPlanAmendment parse(String[] fields) throws PDUFormatException {
        if (fields.length < 18) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            return new PDUFlightPlanAmendment (
                    fields[0],
                    fields[1],
                    fields[2],
                    FlightRules.fromString(fields[3]),
                    fields[4],
                    fields[5],
                    fields[6],
                    fields[7],
                    fields[8],
                    fields[9],
                    fields[10],
                    fields[11],
                    fields[12],
                    fields[13],
                    fields[14],
                    fields[15],
                    fields[16],
                    fields[17]
            );
        }
        catch (Exception e) {
            throw new PDUFormatException("Parse error -> " + e.toString(), reassemble(fields));
        }
    }
}


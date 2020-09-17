package com.reese.fsd.pdu;

public class PDUFlightPlan extends PDUBase {

    public String from;
    public String to;
    public FlightRules rules;
    public String equipment;
    public String tas;
    public String depAirport;
    public String estimatedDepTime;
    public String actualDepTime;
    public String cruiseAlt;
    public String destAirport;
    public String hoursEnroute;
    public String minutesEnroute;
    public String fuelAvailHours;
    public String fuelAvailMinutes;
    public String altAirport;
    public String remarks;
    public String route;

    public PDUFlightPlan(String from, String to, FlightRules rules, String equipment, String tas, String depAirport, String estimatedDepTime, String actualDepTime, String cruiseAlt, String destAirport, String hoursEnroute, String minutesEnroute, String fuelAvailHours, String fuelAvailMinutes, String altAirport, String remarks, String route) {
        this.from = from;
        this.to = to;
        this.rules = rules;
        this.equipment = equipment;
        this.tas = tas;
        this.depAirport = depAirport;
        this.estimatedDepTime = estimatedDepTime;
        this.actualDepTime = actualDepTime;
        this.cruiseAlt = cruiseAlt;
        this.destAirport = destAirport;
        this.hoursEnroute = hoursEnroute;
        this.minutesEnroute = minutesEnroute;
        this.fuelAvailHours = fuelAvailHours;
        this.fuelAvailMinutes = fuelAvailMinutes;
        this.altAirport = altAirport;
        this.remarks = remarks;
        this.route = route;
    }

    @Override
    public String serialize() {
        StringBuilder msg = new StringBuilder("$FP");
        msg.append(this.from);
        msg.append(DELIMITER);
        msg.append(this.to);
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

    public static PDUFlightPlan parse(String[] fields) throws PDUFormatException {
        if (fields.length < 17) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            return new PDUFlightPlan (
                    fields[0],
                    fields[1],
                    FlightRules.fromString(fields[2]),
                    fields[3],
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
                    fields[16]
            );
        }
        catch (Exception e) {
            throw new PDUFormatException("Parse error -> " + e.toString(), reassemble(fields));
        }
    }
}


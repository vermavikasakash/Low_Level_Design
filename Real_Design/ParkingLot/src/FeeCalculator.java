import enums.VehicleType;

import java.time.Duration;
import java.time.LocalDateTime;

public class FeeCalculator {

    public double calculateFee(Ticket ticket, LocalDateTime exitTime) {
        // calculate duration
        // round to 30-minute blocks
        // determine rate from vehicle type
        // return fee
        Duration duration = Duration.between(ticket.getEntryTime(), exitTime);
        long minutes = duration.toMinutes();
        long blocks = (long) Math.ceil(minutes / 30.0);

        double halfHourlyRate = getHalfHourlyRate(ticket.getVehicle().getVehicleType());

        return blocks * halfHourlyRate;
    }

    private double getHalfHourlyRate(VehicleType vehicleType) {

        if (vehicleType == VehicleType.MOTORCYCLE) {
            return 10;
        } else if (vehicleType == VehicleType.CAR) {
            return 20;
        } else if (vehicleType == VehicleType.TRUCK) {
            return 40;
        }
        throw  new IllegalArgumentException("Vehicle type is not allowed");
    }
}
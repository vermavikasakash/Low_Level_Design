public class ExitGate {

    private ParkingLot parkingLot;

    public ExitGate(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
    // methods
    public void exit(Long ticketId) {
        parkingLot.unparkVehicle(ticketId);
    }

    public void exit(String vehicleNumber) {
        parkingLot.unparkVehicle(vehicleNumber);
    }
}
public class EntryGate {

    private ParkingLot parkingLot;

    // constructor
    public EntryGate(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
    // methods
    public Ticket enter(Vehicle vehicle) {
        return parkingLot.parkVehicle(vehicle);
    }
}
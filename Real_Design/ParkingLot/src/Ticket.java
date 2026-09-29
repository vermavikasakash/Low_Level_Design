import java.time.LocalDateTime;

public class Ticket {

    private Long ticketId;
    private LocalDateTime entryTime;
    private Vehicle vehicle;
    private ParkingSpot parkingSpot;


    public Ticket(Long ticketId, LocalDateTime entryTime, Vehicle vehicle, ParkingSpot parkingSpot) {
        this.ticketId = ticketId;
        this.entryTime = entryTime;
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
    }

    // getter
    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }
}
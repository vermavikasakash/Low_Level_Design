import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class TicketManager {
    private Map<Long, Ticket> ticketsById;
    private Map<String, Ticket> ticketsByVehicleNumber;
    private long nextTicketId = 1;

    public TicketManager() {
        ticketsById = new HashMap<>();
        ticketsByVehicleNumber = new HashMap<>();
    }

    public Long generateTicketId() {
        return nextTicketId++;
    }
    // dummy timer for testing // LocalDateTime.now()
    LocalDateTime entryTime =
            LocalDateTime.of(2026, 9, 29, 10, 0);

    // Create ticket
    public Ticket createTicket(Vehicle vehicle, ParkingSpot parkingSpot) {
        Long ticketId = generateTicketId();
        Ticket ticket = new Ticket(ticketId, entryTime, vehicle, parkingSpot);

        ticketsById.put(ticketId, ticket);
        ticketsByVehicleNumber.put(vehicle.getRegistrationNumber(), ticket);

        return ticket;
    }

    // Check Vehicle already present
    public boolean isVehicleParked(String vehicleNumber) {
        return ticketsByVehicleNumber.containsKey(vehicleNumber);
    }

    // find ticket by id
    public Ticket findByTicketId(Long ticketId) {
        return ticketsById.get(ticketId);
    }

    // find ticket by vehicleNumber
    public Ticket findByVehicleNumber(String vehicleNumber) {
        return ticketsByVehicleNumber.get(vehicleNumber);
    }

    // remove tickets
    public void removeTicket(Ticket ticket) {

        ticketsById.remove(ticket.getTicketId());

        ticketsByVehicleNumber.remove(ticket.getVehicle().getRegistrationNumber());
    }
}

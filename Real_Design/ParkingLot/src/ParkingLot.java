import payment.PaymentService;

import java.time.LocalDateTime;

public class ParkingLot {

    private ParkingSpotManager parkingSpotManager;
    private TicketManager ticketManager;
    private FeeCalculator feeCalculator;
    private PaymentService paymentService;

    public ParkingLot(ParkingSpotManager parkingSpotManager, TicketManager ticketManager, FeeCalculator feeCalculator, PaymentService paymentService) {
        this.parkingSpotManager = parkingSpotManager;
        this.ticketManager = ticketManager;
        this.feeCalculator = feeCalculator;
        this.paymentService = paymentService;
    }

    public Ticket parkVehicle(Vehicle vehicle) {

        if (ticketManager.isVehicleParked(vehicle.getRegistrationNumber())) {

            throw new IllegalStateException("Vehicle is already parked");
        }

        ParkingSpot parkingSpot = parkingSpotManager.assignSpot(vehicle.getVehicleType());

        Ticket ticket = ticketManager.createTicket(vehicle, parkingSpot);

        return ticket;
    }


    public void unparkVehicle(Long ticketId) {

        Ticket ticket = ticketManager.findByTicketId(ticketId);

        if (ticket == null) {
            throw new IllegalArgumentException("Invalid ticket ID: " + ticketId);
        }

        processExit(ticket);
    }

    public void unparkVehicle(String vehicleNumber) {

        Ticket ticket = ticketManager.findByVehicleNumber(vehicleNumber);

        if (ticket == null) {
            throw new IllegalArgumentException("No active ticket found for vehicle: " + vehicleNumber);
        }

        processExit(ticket);
    }

    private void processExit(Ticket ticket) {

        // 1. Calculate fee
        double fee = feeCalculator.calculateFee(ticket, LocalDateTime.now());

        // 2. Process payment
        boolean isPaid = paymentService.pay(fee);

        // 3. Complete exit only after successful payment
        if (isPaid) {
            ticket.getParkingSpot().release();
            ticketManager.removeTicket(ticket);
        } else {
            System.out.println("Payment failed. Please try again.");
        }
    }

}
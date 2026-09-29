import enums.SpotStatus;
import enums.VehicleType;
import payment.CashPayment;
import payment.PaymentMethod;
import payment.PaymentService;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        // 1. Create managers/services
        ParkingSpotManager parkingSpotManager = new ParkingSpotManager();
        TicketManager ticketManager = new TicketManager();
        FeeCalculator feeCalculator = new FeeCalculator();

        PaymentMethod paymentMethod = new CashPayment();
        PaymentService paymentService = new PaymentService(paymentMethod);

        // 2. Create ParkingLot
        ParkingLot parkingLot = new ParkingLot(parkingSpotManager, ticketManager, feeCalculator, paymentService);

        // 3. Create parking spots
        parkingSpotManager.addParkingSpot(new ParkingSpot(1, SpotStatus.AVAILABLE, VehicleType.CAR));

        parkingSpotManager.addParkingSpot(new ParkingSpot(2, SpotStatus.AVAILABLE, VehicleType.MOTORCYCLE));

        parkingSpotManager.addParkingSpot(new ParkingSpot(3, SpotStatus.AVAILABLE, VehicleType.TRUCK));

        // 4. Create gates
        EntryGate entryGate1 = new EntryGate(parkingLot);
        EntryGate entryGate2 = new EntryGate(parkingLot);

        ExitGate exitGate1 = new ExitGate(parkingLot);
        ExitGate exitGate2 = new ExitGate(parkingLot);

        // 5. Create vehicle
        Vehicle car = new Vehicle("KA01AB1234", VehicleType.CAR);

        // 6. Vehicle enters
        Ticket ticket = entryGate1.enter(car);

        System.out.println("Ticket generated: " + ticket.getTicketId());

        // 7. Vehicle exits using ticket
        exitGate1.exit(ticket.getTicketId());

    }
}
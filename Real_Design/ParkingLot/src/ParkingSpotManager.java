import enums.VehicleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingSpotManager {

    private Map<VehicleType, List<ParkingSpot>> spots;

    public ParkingSpotManager() {
        spots = new HashMap<>();
    }

    // Add parking spot
    public void addParkingSpot(ParkingSpot spot) {

        VehicleType vehicleType = spot.getVehicleType();

        if (!spots.containsKey(vehicleType)) {
            spots.put(vehicleType, new ArrayList<>());
        }

        spots.get(vehicleType).add(spot);
    }

    // Assign available spot
    public ParkingSpot assignSpot(VehicleType vehicleType) {

        List<ParkingSpot> parkingSpots = spots.get(vehicleType);

        if (parkingSpots == null) {
            throw new IllegalStateException("No parking spot available for " + vehicleType);
        }

        for (ParkingSpot spot : parkingSpots) {

            if (spot.isAvailable()) {
                spot.occupy();
                return spot;
            }
        }

        throw new IllegalStateException("No parking spot available for " + vehicleType);
    }
}

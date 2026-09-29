import enums.SpotStatus;
import enums.VehicleType;

import java.util.Map;

public class ParkingSpot {

    private int spotNumber;
    private SpotStatus spotStatus;
    private VehicleType vehicleType;

    // getter
    public int getSpotNumber() {
        return spotNumber;
    }
    public SpotStatus getSpotStatus() {
        return spotStatus;
    }
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    // constructor
    public ParkingSpot(int spotNumber, SpotStatus spotStatus, VehicleType vehicleType) {
        this.spotNumber = spotNumber;
        this.spotStatus = spotStatus;
        this.vehicleType = vehicleType;
    }
    // methods
    public boolean isAvailable() {
        return spotStatus == SpotStatus.AVAILABLE;
    }

    public void occupy() {
        if (spotStatus == SpotStatus.OCCUPIED) {
            throw new IllegalStateException("Parking spot is already occupied");
        }

        spotStatus = SpotStatus.OCCUPIED;
    }

    public void release() {
        if (spotStatus == SpotStatus.AVAILABLE) {
            throw new IllegalStateException("Parking spot is already available");
        }

        spotStatus = SpotStatus.AVAILABLE;
    }
}
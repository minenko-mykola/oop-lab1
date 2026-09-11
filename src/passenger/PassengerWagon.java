package passenger;

import enums.ComfortLevel;
import wagons.Wagon;

public class PassengerWagon extends Wagon {

    private final int numberOfPassengers;

    public PassengerWagon(String operator, int weight,
                          ComfortLevel comfortLevel, int numberOfPassengers)
    {
        super(operator, weight, comfortLevel);
        this.numberOfPassengers = numberOfPassengers;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }
}

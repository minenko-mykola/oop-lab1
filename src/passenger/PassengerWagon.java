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

    @Override
    public void printInfo()
    {
        System.out.printf("Operator:%s\n",this.operator);
        System.out.printf("Weight:%s\n",this.weight);
        System.out.printf("Comfort level:%s\n",this.comfortLevel);
        System.out.printf("Passengers:%d\n",getNumberOfPassengers());
    }
}

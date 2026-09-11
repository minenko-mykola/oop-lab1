package trains;

import baggage.BaggageWagon;
import passenger.PassengerWagon;
import wagons.RollingStock;

import java.util.ArrayList;

public class PassengerTrain extends RollingStock {

    private final ArrayList<BaggageWagon> baggageWagons = new ArrayList<>();
    private final ArrayList<PassengerWagon> passengerWagons = new ArrayList<>();

    public PassengerTrain(String operator, int weight)
    {
        super(operator, weight);
    }

    public void addWagon(PassengerWagon wagon)
    {
        passengerWagons.add(wagon);
    }

    public void addWagon(BaggageWagon wagon)
    {
        baggageWagons.add(wagon);
    }

    public void removeWagon(PassengerWagon wagon)
    {
        passengerWagons.remove(wagon);
    }

    public void removeWagon(BaggageWagon wagon)
    {
        baggageWagons.remove(wagon);
    }

    public void printInfo()
    {
        System.out.printf("\n");
        System.out.printf("Total passengers:%d\n",getTotalPassengers());
        System.out.printf("Total baggage:%d\n",getTotalBaggage());
    }

    private int getTotalPassengers()
    {
        int sum = 0;

        for (PassengerWagon wagon : passengerWagons)
        {
            sum += wagon.getNumberOfPassengers();
        }

        return sum;
    }

    private int getTotalBaggage()
    {
        int sum = 0;

        for (BaggageWagon wagon : baggageWagons)
        {
            sum += wagon.getBaggageAmount();
        }

        return sum;
    }
}

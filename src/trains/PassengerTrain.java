package trains;

import baggage.BaggageWagon;
import passenger.PassengerWagon;
import wagons.RollingStock;
import wagons.Wagon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class PassengerTrain extends RollingStock {


    private final ArrayList<Wagon> wagons = new ArrayList<>();

    public PassengerTrain(String operator, int weight)
    {
        super(operator, weight);
    }

    public void addWagon(Wagon wagon)
    {
        wagons.add(wagon);
    }

    public void removeWagon(Wagon wagon)
    {
        wagons.remove(wagon);
    }

    public void printInfo()
    {
        System.out.printf("\n");
        System.out.printf("Total passengers:%d\n",getTotalPassengers());
        System.out.printf("Total baggage:%d\n",getTotalBaggage());
    }

    public void printWagonsInfo()
    {
        int i = 1;

        for (Wagon wagon : wagons)
        {
            System.out.printf("\nWagon #%d\n",i);
            wagon.printInfo();
            i++;
        }
    }

    public void sortByComfort()
    {
        wagons.sort(Comparator.comparing(Wagon::getComfortLevel));
    }

    public ArrayList<Wagon> filterByPassengersRange(int min, int max)
    {
        return wagons.stream()
                .filter(wagon -> wagon instanceof PassengerWagon passengerWagon
                        && passengerWagon.getNumberOfPassengers() >= min
                        && passengerWagon.getNumberOfPassengers() <= max)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private int getTotalPassengers()
    {
        int sum = 0;

        for (Wagon wagon : wagons)
        {
            if(wagon instanceof PassengerWagon passengerWagon)
            {
                sum += passengerWagon.getNumberOfPassengers();
            }
        }

        return sum;
    }

    private int getTotalBaggage()
    {
        int sum = 0;

        for (Wagon wagon : wagons)
        {
            if(wagon instanceof BaggageWagon baggageWagon)
            {
                sum += baggageWagon.getBaggageAmount();
            }
        }

        return sum;
    }
}

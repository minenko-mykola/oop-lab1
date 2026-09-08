package trains;

import baggage.BaggageWagon;
import passenger.PassengerWagon;
import wagons.RollingStock;
import wagons.Wagon;

import java.util.ArrayList;

public class PassengerTrain extends RollingStock {

    private final ArrayList<Wagon> wagons;

    public PassengerTrain(
            String operator,
            int weight,
            ArrayList<Wagon> wagons) {

        super(operator, weight);
        this.wagons = wagons;
    }

    public String getOperator() {
        return operator;
    }

    public int getTotalPassengers() {
        int totalPassengers = 0;

        for (Wagon wagon : wagons) {

            if(wagon instanceof PassengerWagon)
            {
                totalPassengers += ((PassengerWagon) wagon).getCurrentNumberOfPassengers();
            }
        }

        return totalPassengers;
    }

    public int getTotalBaggage() {
        int totalBaggage = 0;

        for (Wagon wagon : wagons) {

            if(wagon instanceof BaggageWagon)
            {
                totalBaggage += ((BaggageWagon) wagon).getCurrentBaggageWeight();
            }
        }

        return totalBaggage;
    }

    public void printTotalPassengersAndBaggage() {
        System.out.printf(
                "Total passengers: %d%n",
                getTotalPassengers()
        );

        System.out.printf(
                "Total baggage: %d%n",
                getTotalBaggage()
        );
    }
}

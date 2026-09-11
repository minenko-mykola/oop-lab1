package baggage;

import enums.ComfortLevel;
import wagons.Wagon;

public class BaggageWagon extends Wagon {

    private final int baggageAmount;

    public BaggageWagon(String operator, int weight,
                        ComfortLevel comfortLevel, int baggageAmount)
    {
        super(operator, weight, comfortLevel);
        this.baggageAmount = baggageAmount;
    }

    public int getBaggageAmount() {
        return baggageAmount;
    }
}

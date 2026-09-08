package baggage;

import enums.ComfortLevel;
import wagons.Wagon;

public class BaggageWagon extends Wagon {

    private final int maxBaggageWeight;
    private int currentBaggageWeight;

    public BaggageWagon(String operator,
                        int weight,
                        ComfortLevel comfortLevel,
                        int maxBaggageWeight,
                        int currentBaggageWeight) {

        super(operator, weight, comfortLevel);

        if (maxBaggageWeight <= 0) {
            throw new IllegalArgumentException(
                    "Maximum baggage weight must be greater than 0");
        }
        else if(currentBaggageWeight < 0)
        {
            throw new IllegalArgumentException(
                    "Current baggage weight must be greater or equal to 0");
        }
        else if(currentBaggageWeight > maxBaggageWeight)
        {
            throw new IllegalArgumentException(
                    "Current baggage weight must be lesser or equal to max baggage weight");
        }else{
            this.currentBaggageWeight = currentBaggageWeight;
        }

        this.maxBaggageWeight = maxBaggageWeight;
    }

    public BaggageWagon(String operator,
                        int weight,
                        ComfortLevel comfortLevel,
                        int maxBaggageWeight) {

        super(operator, weight, comfortLevel);

        if (maxBaggageWeight <= 0) {
            throw new IllegalArgumentException(
                    "Maximum baggage weight must be greater than 0");
        }

        this.maxBaggageWeight = maxBaggageWeight;
    }

    public int getMaxBaggageWeight() {
        return maxBaggageWeight;
    }

    public int getCurrentBaggageWeight() {
        return currentBaggageWeight;
    }

    public void addBaggage(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Baggage amount must be greater than 0");
        }

        if (currentBaggageWeight + amount
                > maxBaggageWeight) {
            throw new IllegalArgumentException(
                    "Maximum baggage capacity exceeded");
        }

        currentBaggageWeight += amount;
    }

    public void removeBaggage(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Baggage amount must be greater than 0");
        }

        if (currentBaggageWeight < amount) {
            throw new IllegalArgumentException(
                    "Not enough baggage");
        }

        currentBaggageWeight -= amount;
    }
}

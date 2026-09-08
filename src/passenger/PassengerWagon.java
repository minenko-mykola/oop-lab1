package passenger;

import enums.ComfortLevel;
import wagons.Wagon;

public class PassengerWagon extends Wagon {

    private final int maxNumberOfPassengers;
    private int currentNumberOfPassengers;

    public PassengerWagon(String operator,
                          int weight,
                          ComfortLevel comfortLevel,
                          int maxNumberOfPassengers,
                          int currentNumberOfPassengers) {

        super(operator, weight, comfortLevel);

        if (maxNumberOfPassengers <= 0) {
            throw new IllegalArgumentException(
                    "Maximum number of passengers must be positive");
        }
        else if(currentNumberOfPassengers < 0)
        {
            throw new IllegalArgumentException(
                    "Current number of passengers must be greater or equal to 0");
        }
        else if(currentNumberOfPassengers > maxNumberOfPassengers)
        {
            throw new IllegalArgumentException(
                    "Current number of passengers must be lesser or equal to max number of passengers");
        }else
        {
            this.currentNumberOfPassengers = currentNumberOfPassengers;
        }


        this.maxNumberOfPassengers = maxNumberOfPassengers;
    }

    public PassengerWagon(String operator,
                          int weight,
                          ComfortLevel comfortLevel,
                          int maxNumberOfPassengers) {

        super(operator, weight, comfortLevel);

        if (maxNumberOfPassengers <= 0) {
            throw new IllegalArgumentException(
                    "Maximum number of passengers must be positive");
        }


        this.maxNumberOfPassengers = maxNumberOfPassengers;
    }

    public int getMaxNumberOfPassengers() {
        return maxNumberOfPassengers;
    }

    public int getCurrentNumberOfPassengers() {
        return currentNumberOfPassengers;
    }

    public void addPassengers(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Passengers amount must be greater than 0");
        }

        if (currentNumberOfPassengers + amount
                > maxNumberOfPassengers) {
            throw new IllegalArgumentException(
                    "Maximum capacity exceeded");
        }

        currentNumberOfPassengers += amount;
    }

    public void removePassengers(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Passengers amount must be greater than 0");
        }

        if (currentNumberOfPassengers < amount) {
            throw new IllegalArgumentException(
                    "Not enough passengers");
        }

        currentNumberOfPassengers -= amount;
    }
}

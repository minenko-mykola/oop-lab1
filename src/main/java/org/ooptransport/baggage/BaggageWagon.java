package org.ooptransport.baggage;


import org.ooptransport.enums.ComfortLevel;
import org.ooptransport.wagons.Wagon;

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

    @Override
    public void printInfo()
    {
        System.out.printf("Operator:%s%n",this.operator);
        System.out.printf("Weight:%s%n",this.weight);
        System.out.printf("Comfort level:%s%n",this.comfortLevel);
        System.out.printf("Baggage:%d%n",getBaggageAmount());
    }
}

package wagons;

import enums.ComfortLevel;

public class Wagon extends RollingStock
{

    protected ComfortLevel comfortLevel;

    public Wagon(String operator, int weight,ComfortLevel comfortLevel)
    {
        super(operator, weight);
        this.comfortLevel = comfortLevel;
    }
}

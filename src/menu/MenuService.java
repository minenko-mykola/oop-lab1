package menu;

import trains.PassengerTrain;

public class MenuService
{
    private static final PassengerTrain train = new PassengerTrain("УЗ",60);

    public static void printWagonsInfo()
    {
        train.printInfo();
    }

    public static void printInfo()
    {
        train.printInfo();
    }

    public static void sortByComfort()
    {
        train.printInfo();
    }

    public static void sortByPassengersRange()
    {
        train.printInfo();
    }
}
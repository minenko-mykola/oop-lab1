package menu;

import console.ConsoleInput;
import trains.PassengerTrain;
import wagons.Wagon;

import java.util.ArrayList;

public class MenuController
{

    private final PassengerTrain train;

    public MenuController(PassengerTrain train)
    {
        this.train = train;
    }

    public void printWagonsInfo()
    {
        train.printWagonsInfo();
    }

    public void printInfo()
    {
        train.printInfo();
    }

    public void sortByComfort()
    {
        train.sortByComfort();
        train.printWagonsInfo();
    }

    public void findWagonsByPassengersRange()
    {
        int min = getMinPassengers();

        if (min < 0)
        {
            System.out.println(
                    "Мінімальне значення не може бути від'ємним."
            );
            return;
        }

        int max = getMaxPassengers();

        if (min > max)
        {
            System.out.println(
                    "Мінімальне значення не може бути більшим за максимальне."
            );
            return;
        }

        ArrayList<Wagon> wagons =
                train.filterByPassengersRange(min, max);

        printWagons(wagons);
    }

    private int getMinPassengers()
    {
        return ConsoleInput.readInteger(
                "Вкажіть мінімальну кількість пасажирів: "
        );
    }

    private int getMaxPassengers()
    {
        return ConsoleInput.readInteger(
                "Вкажіть максимальну кількість пасажирів: "
        );
    }

    private void printWagons(ArrayList<Wagon> wagons)
    {
        int number = 1;

        for (Wagon wagon : wagons)
        {
            System.out.printf("\nWagon #%d\n", number);
            wagon.printInfo();
            number++;
        }
    }
}

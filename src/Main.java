import baggage.BaggageWagon;
import enums.ComfortLevel;
import menu.MenuManager;
import passenger.PassengerWagon;
import trains.PassengerTrain;

void main()
{
    PassengerTrain train = new PassengerTrain("УЗ",60);

    train.addWagon(new PassengerWagon("УЗ",60, ComfortLevel.LOW,10));
    train.addWagon(new BaggageWagon("УЗ",90, ComfortLevel.MEDIUM,20));
    train.addWagon(new PassengerWagon("УЗ",120, ComfortLevel.HIGH,30));

    MenuManager menu = new MenuManager(train);

    menu.run();
}

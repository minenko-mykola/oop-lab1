import org.ooptransport.baggage.BaggageWagon;
import org.ooptransport.enums.ComfortLevel;
import org.ooptransport.menu.MenuManager;
import org.ooptransport.passenger.PassengerWagon;
import org.ooptransport.trains.PassengerTrain;

void main()
{
    PassengerTrain train = new PassengerTrain("УЗ",60);

    train.addWagon(new PassengerWagon("УЗ",60, ComfortLevel.LOW,10));
    train.addWagon(new BaggageWagon("УЗ",90, ComfortLevel.MEDIUM,20));
    train.addWagon(new PassengerWagon("УЗ",120, ComfortLevel.HIGH,30));

    MenuManager menu = new MenuManager(train);

    menu.run();
}

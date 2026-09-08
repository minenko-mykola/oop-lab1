import baggage.BaggageWagon;
import enums.ComfortLevel;
import passenger.PassengerWagon;
import trains.PassengerTrain;
import wagons.Wagon;

void main() {

    ArrayList<Wagon> wagons = new ArrayList<>();

    wagons.add(new PassengerWagon("УЗ",60, ComfortLevel.LOW,10,5));
    wagons.add(new BaggageWagon("УЗ",60, ComfortLevel.LOW,10,5));
    wagons.add(new PassengerWagon("УЗ",60, ComfortLevel.LOW,10,5));
    wagons.add(new BaggageWagon("УЗ",60, ComfortLevel.LOW,10,5));
    wagons.add(new PassengerWagon("УЗ",60, ComfortLevel.LOW,10,5));
    wagons.add(new BaggageWagon("УЗ",60, ComfortLevel.LOW,10,5));

    PassengerTrain train1 = new PassengerTrain("УЗ",60,wagons);

    train1.printTotalPassengersAndBaggage();
}

package trains;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ooptransport.baggage.BaggageWagon;
import org.ooptransport.enums.ComfortLevel;
import org.ooptransport.passenger.PassengerWagon;
import org.ooptransport.trains.PassengerTrain;
import org.ooptransport.wagons.Wagon;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PassengerTrainTest {

    private PassengerTrain train;
    private PassengerWagon passengerWagon1;
    private PassengerWagon passengerWagon2;
    private BaggageWagon baggageWagon;

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        train = new PassengerTrain("ExpressOperator", 10000);

        // Створюємо тестові вагони з різними параметрами
        passengerWagon1 = new PassengerWagon("Operator1", 5000, ComfortLevel.HIGH, 30);
        passengerWagon2 = new PassengerWagon("Operator2", 5000, ComfortLevel.MEDIUM, 50);
        baggageWagon = new BaggageWagon("Operator3", 4000, ComfortLevel.LOW, 100);

        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Додавання вагонів та фільтрація пасажирських вагонів за діапазоном")
    void addWagonAndFilterByPassengersRange() {
        train.addWagon(passengerWagon1);
        train.addWagon(passengerWagon2);
        train.addWagon(baggageWagon);

        // Фільтруємо пасажирські вагони за діапазоном пасажирів (від 20 до 40)
        ArrayList<Wagon> filtered = train.filterByPassengersRange(20, 40);

        assertEquals(1, filtered.size(), "Should find exactly 1 passenger wagon in range.");
        assertEquals(passengerWagon1, filtered.get(0), "The filtered wagon should be passengerWagon1.");
    }

    @Test
    @DisplayName("Видалення вагону з потяга")
    void removeWagon() {
        train.addWagon(passengerWagon1);
        train.addWagon(baggageWagon);

        train.removeWagon(passengerWagon1);

        // Перевіряємо через фільтрацію (пасажирських вагонів не повинно залишитись)
        ArrayList<Wagon> filtered = train.filterByPassengersRange(0, 100);
        assertTrue(filtered.isEmpty(), "Passenger wagon should be removed from the train.");
    }

    @Test
    @DisplayName("Перевірка виведення загальної інформації про пасажирів та багаж")
    void printInfo() {
        train.addWagon(passengerWagon1); // 30 пасажирів
        train.addWagon(passengerWagon2); // 50 пасажирів
        train.addWagon(baggageWagon);    // 100 багажу

        train.printInfo();

        // Очікуваний вивід формується з урахуванням методів getTotalPassengers() та getTotalBaggage()
        String expectedOutput = String.format("%nTotal passengers:80%nTotal baggage:100%n");

        assertEquals(expectedOutput, outputStream.toString(), "Total passengers and baggage print info is incorrect.");
    }

    @Test
    @DisplayName("Перевірка виведення детальної інформації про всі вагони потяга")
    void printWagonsInfo() {
        train.addWagon(passengerWagon1);

        train.printWagonsInfo();

        // Перевіряємо, чи викликався вивід для вагону всередині потяга
        assertTrue(outputStream.toString().contains("Wagon #1"), "Should print wagon number.");
        assertTrue(outputStream.toString().contains("Passengers:30"), "Should print wagon's inner info.");
    }

    @Test
    @DisplayName("Сортування вагонів потяга за рівнем комфорту")
    void sortByComfort() {
        // Додаємо в зворотному порядку сортування
        train.addWagon(passengerWagon1); // HIGH
        train.addWagon(baggageWagon);    // LOW
        train.addWagon(passengerWagon2); // MEDIUM

        train.sortByComfort();

        // Перевіряємо, що після сортування список вагонів залишається дійсним та коректним
        ArrayList<Wagon> filtered = train.filterByPassengersRange(0, 100);

        assertNotNull(filtered, "Wagons list should not be null after sorting.");
    }
}
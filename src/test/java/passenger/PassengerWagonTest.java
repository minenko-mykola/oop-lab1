package passenger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ooptransport.enums.ComfortLevel;
import org.ooptransport.passenger.PassengerWagon;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PassengerWagonTest {

    private final int expectedPassengers = 45;
    private final String expectedOperator = "Intercity";
    private final int expectedWeight = 35000;
    private final ComfortLevel expectedComfort = ComfortLevel.HIGH;

    private PassengerWagon passengerWagon;

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        // Ініціалізуємо об'єкт перед кожним тестом
        passengerWagon = new PassengerWagon(expectedOperator, expectedWeight, expectedComfort, expectedPassengers);

        // Перенаправляємо System.out для тестування printInfo()
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        // Відновлюємо оригінальний системний вивід
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Перевірка отримання коректної кількості пасажирів")
    void getNumberOfPassengers() {
        // Перевіряємо, чи метод повертає правильну кількість пасажирів
        assertEquals(expectedPassengers, passengerWagon.getNumberOfPassengers(),
                "Number of passengers should match the value passed to the constructor.");
    }

    @Test
    @DisplayName("Перевірка форматування та виведення інформації про пасажирський вагон")
    void printInfo() {
        // Викликаємо метод виведення інформації
        passengerWagon.printInfo();

        // Формуємо очікуваний вивід (враховуючи \n, які прописані у PassengerWagon)
        String expectedOutput = String.format(
                "Operator:%s%nWeight:%d%nComfort level:%s%nPassengers:%d%n",
                expectedOperator, expectedWeight, expectedComfort, expectedPassengers
        );

        // Порівнюємо фактичний вивід із консолі з очікуваним
        assertEquals(expectedOutput, outputStream.toString(),
                "Printed info format or content is incorrect.");
    }
}
package baggage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.ooptransport.baggage.BaggageWagon;
import org.ooptransport.enums.ComfortLevel;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BaggageWagonTest {

    private final int expectedBaggageAmount = 15;
    private final String expectedOperator = "ExpressTest";
    private final int expectedWeight = 4200;
    private final ComfortLevel expectedComfort = ComfortLevel.HIGH;

    private BaggageWagon baggageWagon;

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp()
    {
        // Ініціалізуємо тестовий об'єкт перед кожним тестом
        baggageWagon = new BaggageWagon(expectedOperator, expectedWeight, expectedComfort, expectedBaggageAmount);

        // Перенаправляємо System.out для перевірки printInfo()
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown()
    {
        // Відновлюємо оригінальний System.out
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Перевірка отримання коректної кількості багажу")
    void getBaggageAmount_ShouldReturnCorrectAmount()
    {
        // Act & Assert
        assertEquals(expectedBaggageAmount, baggageWagon.getBaggageAmount(),
                "The baggage amount should match the value passed to the constructor.");
    }

    @Test
    @DisplayName("Перевірка форматування та виведення інформації про вагон")
    void printInfo_ShouldPrintCorrectFormattingAndValues()
    {
        // Act
        baggageWagon.printInfo();

        // Формуємо очікуваний результат з урахуванням системних переносів рядків
        String expectedOutput = String.format(
                "Operator:%s%nWeight:%d%nComfort level:%s%nBaggage:%d%n",
                expectedOperator, expectedWeight, expectedComfort, expectedBaggageAmount
        );

        // Assert
        assertEquals(expectedOutput, outputStream.toString(),
                "The printed output format or values do not match expectations.");
    }
}
package menu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.ooptransport.console.ConsoleInput;
import org.ooptransport.menu.MenuController;
import org.ooptransport.trains.PassengerTrain;
import org.ooptransport.wagons.Wagon;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuControllerTest {

    private PassengerTrain trainMock;
    private MenuController menuController;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        // Створюємо мок для PassengerTrain
        trainMock = mock(PassengerTrain.class);
        // Ініціюємо MenuController з цим моком
        menuController = new MenuController(trainMock);
        // Перенаправляємо вивід для перевірки повідомлень
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Перевірка виведення інформації про вагони")
    void printWagonsInfo() {
        menuController.printWagonsInfo();

        // Перевіряємо, чи викликався метод train.printWagonsInfo()
        verify(trainMock, times(1)).printWagonsInfo();
    }

    @Test
    @DisplayName("Перевірка виведеної загальної інформації потяга")
    void printInfo() {
        menuController.printInfo();

        // Перевіряємо, чи викликався метод train.printInfo()
        verify(trainMock, times(1)).printInfo();
    }

    @Test
    @DisplayName("Перевірка сортування вагонів за комфортом та виведення")
    void sortByComfort() {
        menuController.sortByComfort();

        // Перевіряємо, чи викликалися обидва методи у train
        verify(trainMock, times(1)).sortByComfort();
        verify(trainMock, times(1)).printWagonsInfo();
    }

    @Test
    @DisplayName("Фільтрація вагонів за валідним діапазоном пасажирів")
    void findWagonsByPassengersRange_ValidRange() {
        // Тестуємо випадок валідного діапазону: min = 10, max = 50
        try (MockedStatic<ConsoleInput> consoleInputMock = mockStatic(ConsoleInput.class)) {
            consoleInputMock.when(() -> ConsoleInput.readInteger("Вкажіть мінімальну кількість пасажирів: ")).thenReturn(10);
            consoleInputMock.when(() -> ConsoleInput.readInteger("Вкажіть максимальну кількість пасажирів: ")).thenReturn(50);

            ArrayList<Wagon> mockWagons = new ArrayList<>();
            when(trainMock.filterByPassengersRange(10, 50)).thenReturn(mockWagons);

            menuController.findWagonsByPassengersRange();

            // Перевіряємо, що фільтрація викликалася з правильними параметрами
            verify(trainMock, times(1)).filterByPassengersRange(10, 50);
        }
    }

    @Test
    @DisplayName("Помилка фільтрації при введені від'ємного мінімального значення")
    void findWagonsByPassengersRange_NegativeMin() {
        // Тестуємо помилку: мінімальне значення від'ємне (-5)
        try (MockedStatic<ConsoleInput> consoleInputMock = mockStatic(ConsoleInput.class)) {
            consoleInputMock.when(() -> ConsoleInput.readInteger("Вкажіть мінімальну кількість пасажирів: ")).thenReturn(-5);

            menuController.findWagonsByPassengersRange();

            // Перевіряємо вивід повідомлення про помилку
            assertTrue(outputStream.toString().contains("Мінімальне значення не може бути від'ємним."),
                    "Should print error message for negative min value.");

            // Фільтрація не повинна викликатись
            verify(trainMock, never()).filterByPassengersRange(anyInt(), anyInt());
        }
    }

    @Test
    @DisplayName("Помилка фільтрації коли мінімальне значення більше за максимальне")
    void findWagonsByPassengersRange_MinGreaterThanMax() {
        // Тестуємо помилку: min (60) більше ніж max (20)
        try (MockedStatic<ConsoleInput> consoleInputMock = mockStatic(ConsoleInput.class)) {
            consoleInputMock.when(() -> ConsoleInput.readInteger("Вкажіть мінімальну кількість пасажирів: ")).thenReturn(60);
            consoleInputMock.when(() -> ConsoleInput.readInteger("Вкажіть максимальну кількість пасажирів: ")).thenReturn(20);

            menuController.findWagonsByPassengersRange();

            // Перевіряємо вивід повідомлення про помилку
            assertTrue(outputStream.toString().contains("Мінімальне значення не може бути більшим за максимальне."),
                    "Should print error message when min > max.");

            // Фільтрація не повинна викликатись
            verify(trainMock, never()).filterByPassengersRange(anyInt(), anyInt());
        }
    }
}
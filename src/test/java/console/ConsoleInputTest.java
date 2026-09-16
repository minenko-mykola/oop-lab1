package console;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.ooptransport.console.ConsoleInput;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConsoleInputTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        // Перенаправляємо консольний вивід, щоб перевірити виведення помилки
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        // Відновлюємо оригінальний вивід
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Успішне зчитування та парсинг валідного цілого числа")
    void readInteger_ValidInput_ShouldReturnNumber() {
        // Використовуємо mockStatic для перехоплення статичного методу IO.readln
        try (MockedStatic<IO> ioMock = mockStatic(IO.class)) {
            // Налаштовуємо поведінку: при виклику повернути "42"
            ioMock.when(() -> IO.readln(anyString())).thenReturn("42");

            int result = ConsoleInput.readInteger("Введіть число: ");

            assertEquals(42, result, "Method should successfully parse and return the integer.");
        }
    }

    @Test
    @DisplayName("Повторний запит при невалідному вводі та успішне повернення числа з другої спроби")
    void readInteger_InvalidThenValidInput_ShouldRetryAndReturnNumber() {
        try (MockedStatic<IO> ioMock = mockStatic(IO.class)) {
            // Перший виклик поверне невалідні дані, другий — валідні
            ioMock.when(() -> IO.readln(anyString()))
                    .thenReturn("abc")
                    .thenReturn("100");

            int result = ConsoleInput.readInteger("Введіть число: ");

            // Перевіряємо, чи зрештою повернулося правильне число
            assertEquals(100, result, "Method should retry on invalid input and return the valid integer.");

            // Перевіряємо, чи вивелося повідомлення про помилку в консоль
            String expectedError = "Некоректне число. Спробуйте ще раз.";
            assertTrue(outputStream.toString().contains(expectedError),
                    "Error message should be printed when NumberFormatException occurs.");
        }
    }
}
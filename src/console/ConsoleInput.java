package console;

public class ConsoleInput
{
    public static int readInteger(String message)
    {
        while (true)
        {
            try
            {
                return Integer.parseInt(IO.readln(message));
            }
            catch (NumberFormatException e)
            {
                System.out.println(
                        "Некоректне число. Спробуйте ще раз."
                );
            }
        }
    }
}

package menu;

import trains.PassengerTrain;

import java.util.Scanner;

public class MenuManager {

    private final MenuController controller;

    public MenuManager(PassengerTrain train)
    {
        this.controller = new MenuController(train);
    }

    private void show() {
        System.out.println("\n--- Меню залізничного поїзда ---");
        System.out.println("1. Вивести інформацію про вагони");
        System.out.println("2. Порахувати загальну кількість пасажирів і багажу");
        System.out.println("3. Сортувати вагони за рівнем комфортності");
        System.out.println("4. Знайти вагони за діапазоном пасажирів");
        System.out.println("0. Вихід");
        System.out.print("Оберіть опцію: ");
    }

    public void run() {

        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            show();

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // printWagonsInfo()
                    controller.printWagonsInfo();
                    break;
                case "2":
                    //printTotalPassengersAndBaggage()
                    controller.printInfo();
                    break;
                case "3":
                    //sortByComfort()
                    controller.sortByComfort();
                    break;
                case "4":
                    //sortByPassengersRange()
                    controller.findWagonsByPassengersRange();
                    break;
                case "0":
                    running = false;
                    System.out.println("Вихід з програми.");
                    break;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
        scanner.close();
    }

}
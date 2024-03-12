import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String[] monthName = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

        MonthsManager monthsManager = new MonthsManager();
        YearManager yearManager = new YearManager();
        Checker checker = new Checker(monthsManager, yearManager);

        String path;
        Scanner scan = new Scanner(System.in);

        while (true) {
            printMenu();
            int command = scan.nextInt();

            if (command == 1) {
                for (int i = 1; i < 4; i++) {
                    path = "resources/m.20210" + i + ".csv";
                    monthsManager.addMonth(i, path);
                }
                System.out.println("Данные по месяцам считаны!");
            } else if (command == 2) {
                yearManager.addYear(2021, "resources/y.2021.csv");
                System.out.println("Данные за год считаны!");
            } else if (command == 3) {

                monthsManager.getMonthStat();

                checker.check();
            } else if (command == 4) {
                System.out.println("Статистика по месячным отчётам:");
                System.out.println();
                monthsManager.getMonthStat();

                for (int i = 0; i < monthsManager.monthToManage.size(); i++) {
                    System.out.println("Для месяца: " + monthName[i]);
                    System.out.println("Самая большая прибыль {Наименование=стоимость} :");
                    System.out.println(monthsManager.monthItemMaxFalse.get(i + 1));
                    System.out.println("Самая большая трата   {Наименование=стоимость} :");
                    System.out.println(monthsManager.monthItemMaxTrue.get(i + 1));
                    System.out.println();
                }
            } else if (command == 5) {
                yearManager.getYearStat();
            } else if (command == 0) {
                System.out.println("До свидания!");
                break;
            } else {
                System.out.println("Извините, но такой команды нет, поробуйте снова!");
            }
        }
    }

    public static void printMenu() {
        System.out.println();
        System.out.println("Программа `Автоматизация бухгалтерии` v 1.2");
        System.out.println();
        System.out.println("  =Для корректного начала работы, считайте все отчеты=");
        System.out.println();
        System.out.println("1 -    Считать все месячные отчёты");
        System.out.println("2 -    Считать годовой отчёт");
        System.out.println();
        System.out.println("3 -      Сверить отчёты");
        System.out.println("4 -      Вывести информацию о всех месячных отчётах");
        System.out.println("5 -      Вывести информацию о годовом отчёте");
        System.out.println();
        System.out.println("0 - Выйти из приложения");
        System.out.println();
    }
}
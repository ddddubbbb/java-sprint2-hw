import java.util.HashMap;
import java.util.Objects;

public class Checker {
    public MonthsManager monthsManager;
    public YearManager yearManager;

    public Checker(MonthsManager monthsManager, YearManager yearManager) {
        this.monthsManager = monthsManager;
        this.yearManager = yearManager;
    }

    public void check() {
        String[] monthName = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        HashMap<Integer, Integer> ySumFalseByMonth = new HashMap<>();
        HashMap<Integer, Integer> ySumTrueByMonth = new HashMap<>();

        HashMap<Integer, Integer> mSumFalseByMonth = new HashMap<>();
        HashMap<Integer, Integer> mSumTrueByMonth = new HashMap<>();
        int defoltYear = 2021;

        for (int i = defoltYear; i < defoltYear + yearManager.years.size(); i++) {
            for (YearlyReport report : yearManager.yearToManage.get(i)) {

                if (!report.isExpense) {
                    ySumFalseByMonth.put(report.month, report.amount);
                } else {
                    ySumTrueByMonth.put(report.month, report.amount);
                }
            }
        }

        for (int i = 1; i <= monthsManager.monthToManage.size(); i++) {
            int sum = 0;
            for (Integer oneSum : monthsManager.monthsBySaleFalse.get(i).values()) {
                sum = oneSum + sum;
            }
            mSumFalseByMonth.put(i, sum);
        }

        for (int i = 1; i <= monthsManager.monthToManage.size(); i++) {
            int sum = 0;
            for (Integer oneSum : monthsManager.monthsBySaleTrue.get(i).values()) {
                sum = oneSum + sum;
            }
            mSumTrueByMonth.put(i, sum);
        }
        for (int i = 0; i < monthsManager.monthToManage.size(); i++) {
            if (Objects.equals(mSumFalseByMonth.get(i + 1), ySumFalseByMonth.get(i + 1))) {
                System.out.println(monthName[i] + ". Проверка доходов по месячному и годовому отчету завершена успешно!");
            } else {
                System.out.println("Проверка доходов по месячному и годовому отчету не прошла!");
                System.out.println("         Нестыковка в месяце " + monthName[i] + "!!!");
            }
        }
        for (int i = 0; i < monthsManager.monthToManage.size(); i++) {
            if (Objects.equals(mSumTrueByMonth.get(i + 1), ySumTrueByMonth.get(i + 1))) {
                System.out.println(monthName[i] + ". Проверка расходов по месячному и годовому отчету завершена успешно!");
            } else {
                System.out.println();
                System.out.println("Проверка расходов по месячному и годовому отчету не прошла!");
                System.out.println("         Нестыковка в месяце " + monthName[i] + "!!!");
                System.out.println();
            }
        }
    }
}
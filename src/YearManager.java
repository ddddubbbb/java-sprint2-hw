import java.util.ArrayList;
import java.util.HashMap;

public class YearManager {
    public HashMap<Integer, ArrayList<YearlyReport>> yearToManage = new HashMap<>();
    public ArrayList<Integer> years = new ArrayList<>();

    public void addYear(Integer year, String path) {
        Loader loader = new Loader();
        ArrayList<YearlyReport> yReport = new ArrayList<>();

        String content = loader.readFileContentsOrNull(path);
        String[] lines = content.split("\r?\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);
            yReport.add(new YearlyReport(month, amount, isExpense));
        }
        yearToManage.put(year, yReport);
        years.add(year);
    }

    public void getYearStat() {
        int defoltYear = 2021;
        String[] monthName = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        HashMap<Integer, Integer> yearProfitByMonths = new HashMap<>();

        for (int i = defoltYear; i < defoltYear + years.size(); i++) {
            for (YearlyReport report : yearToManage.get(i)) {
                if (!report.isExpense) {
                    yearProfitByMonths.put(report.month, yearProfitByMonths.getOrDefault(report.month, 0) + report.amount);
                } else {
                    yearProfitByMonths.put(report.month, yearProfitByMonths.getOrDefault(report.month, 0) - report.amount);
                }
            }
        }
        HashMap<Boolean, Integer> yearFalseTrue = new HashMap<>();
        for (int j = defoltYear; j < (defoltYear + years.size()); j++) {
            for (YearlyReport report : yearToManage.get(j)) {
                yearFalseTrue.put(report.isExpense, yearFalseTrue.getOrDefault(report.isExpense, 0) + report.amount);
            }
        }

        int mediumFalse = yearFalseTrue.get(false) / yearProfitByMonths.size();
        int mediumTrue = yearFalseTrue.get(true) / yearProfitByMonths.size();
        for (int k = defoltYear; k < defoltYear + years.size(); k++) {
            System.out.println("Статистика за " + k + " год:");
            System.out.println();
            System.out.println("Средний доход за все месяцы в году:");
            System.out.println(mediumFalse);
            System.out.println();
            System.out.println("Средний расход за все месяцы в году:");
            System.out.println(mediumTrue);
            System.out.println();
            System.out.println("Прибыль по каждому месяцу:");

            for (int i = 0; i < yearProfitByMonths.size(); i++) {
                System.out.println(monthName[i] + " = " + yearProfitByMonths.get(i + 1));
            }
        }
    }
}
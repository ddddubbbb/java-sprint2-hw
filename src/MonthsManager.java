import java.util.ArrayList;
import java.util.HashMap;

public class MonthsManager {

    public HashMap<Integer, ArrayList<MonthlyReport>> monthToManage = new HashMap<>();

    HashMap<Integer, HashMap<String, Integer>> monthsBySaleFalse = new HashMap<>();
    HashMap<Integer, HashMap<String, Integer>> monthItemMaxFalse = new HashMap<>();

    HashMap<Integer, HashMap<String, Integer>> monthsBySaleTrue = new HashMap<>();
    HashMap<Integer, HashMap<String, Integer>> monthItemMaxTrue = new HashMap<>();

    public void addMonth(int month, String path) {
        Loader loader = new Loader();
        ArrayList<MonthlyReport> mReport = new ArrayList<>();
        String content = loader.readFileContentsOrNull(path);
        String[] lines = content.split("\r?\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            String itemName = parts[0];
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int cost = Integer.parseInt(parts[3]);
            mReport.add(new MonthlyReport(month, itemName, isExpense, quantity, cost));

        }
        monthToManage.put(month, mReport);
    }

    public void getMonthStat() {

        for (int i = 1; i <= monthToManage.size(); i++) {
            HashMap<String, Integer> itemBySaleFalse = new HashMap<>();
            if (!monthsBySaleFalse.containsKey(i)) {
                monthsBySaleFalse.put(i, new HashMap<>());
            }
            for (MonthlyReport report : monthToManage.get(i)) {
                if (!report.isExpense) {
                    itemBySaleFalse.put(report.itemName, (report.cost * report.quantity));
                }
            }
            monthsBySaleFalse.put(i, itemBySaleFalse);
        }


        for (int i = 1; i <= monthToManage.size(); i++) {
            HashMap<String, Integer> itemMaxFalse = new HashMap<>();
            String maxItemFalse = null;
            for (String item : monthsBySaleFalse.get(i).keySet()) {
                if (maxItemFalse == null) {
                    maxItemFalse = item;
                    continue;
                }
                if (monthsBySaleFalse.get(i).get(maxItemFalse) < monthsBySaleFalse.get(i).get(item)) {
                    maxItemFalse = item;
                }
            }
            itemMaxFalse.put(maxItemFalse, monthsBySaleFalse.get(i).get(maxItemFalse));
            monthItemMaxFalse.put(i, itemMaxFalse); //месяц --> имямакстовара-->цена
        }

        for (int i = 1; i <= monthToManage.size(); i++) {
            HashMap<String, Integer> itemBySaleTrue = new HashMap<>();
            if (!monthsBySaleTrue.containsKey(i)) {
                monthsBySaleTrue.put(i, new HashMap<>());
            }
            for (MonthlyReport report : monthToManage.get(i)) {
                if (report.isExpense) {
                    itemBySaleTrue.put(report.itemName, report.cost * report.quantity);
                }
            }
            monthsBySaleTrue.put(i, itemBySaleTrue);
        }

        for (int i = 1; i <= monthToManage.size(); i++) {
            HashMap<String, Integer> itemMaxTrue = new HashMap<>();
            String maxItemTrue = null;
            for (String item : monthsBySaleTrue.get(i).keySet()) {
                if (maxItemTrue == null) {
                    maxItemTrue = item;
                    continue;
                }
                if (monthsBySaleTrue.get(i).get(maxItemTrue) < monthsBySaleTrue.get(i).get(item)) {
                    maxItemTrue = item;
                }
            }
            itemMaxTrue.put(maxItemTrue, monthsBySaleTrue.get(i).get(maxItemTrue));
            monthItemMaxTrue.put(i, itemMaxTrue);
        }
    }
}
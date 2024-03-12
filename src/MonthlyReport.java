public class MonthlyReport {
    public int month;
    public String itemName;
    public boolean isExpense;
    public int quantity;
    public int cost;

    public MonthlyReport(int month, String itemName, boolean isExpense, int quantity, int cost) {
        this.month = month;
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.cost = cost;
    }
}
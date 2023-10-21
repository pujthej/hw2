package controller;
import view.ExpenseTrackerView;
import java.util.List;
import java.util.ArrayList;
import model.ExpenseTrackerModel;
import model.Transaction;
import model.CategoryFilter;
import model.AmountFilter;
public class ExpenseTrackerController {

  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;

  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;

    // Set up view event handlers
  }

  public void refresh() {

    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // Pass to view
    view.refreshTable(transactions);

  }

  public boolean addTransaction(double amount, String category) {
    if (!InputValidation.isValidAmount(amount)) {
      return false;
    }
    if (!InputValidation.isValidCategory(category)) {
      return false;
    }

    Transaction t = new Transaction(amount, category);
    model.addTransaction(t);
    view.getTableModel().addRow(new Object[]{t.getAmount(), t.getCategory(), t.getTimestamp()});
    refresh();
    return true;
  }

  // Other controller methods
  public boolean applyFilter(String filterType, String filterValue) {
    List<Transaction> filteredTransactions = new ArrayList<>();
    if (filterType.equals("Amount")) {
      try {
        double cAmountValue = Double.parseDouble(filterValue);
        if (!InputValidation.isValidAmount(cAmountValue)) {
          return false;
        }
        AmountFilter amountFilter = new AmountFilter(cAmountValue);
        filteredTransactions = amountFilter.filter(model.getTransactions());
        System.out.println("Amount = " + filteredTransactions);
      } catch (Exception e) {
        return false;
      }
    } else if (filterType.equals("Category")) {
      CategoryFilter categoryFilter = new CategoryFilter(filterValue);
      if (!InputValidation.isValidCategory(filterValue)) {
        return false;
      }
      filteredTransactions = categoryFilter.filter(model.getTransactions());
      System.out.println("Category = " + filteredTransactions);
    } else {
      return false;
    }
    view.highlightTransactions(filteredTransactions);
    return true;
  }
}


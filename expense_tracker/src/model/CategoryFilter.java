package model;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryFilter implements TransactionFilter {
    private String category;

    public CategoryFilter(String category) {
        this.category = category;
    }

    @Override
    public List<Transaction> filter(List<Transaction> transactions) {

        System.out.println("Total transactions before filtering: " + transactions.size());
        List<Transaction> filteredTransactions = transactions.stream()
                .filter(transaction -> transaction.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
        System.out.println("Total transactions after filtering: " + filteredTransactions.size());
        return filteredTransactions;
    }
}

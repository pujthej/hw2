package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AmountFilter implements TransactionFilter {
    private double amount;

    public AmountFilter(double amount) {
        this.amount = amount;
    }

    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
        System.out.println("Total transactions before filtering: " + transactions.size());
        List<Transaction> filteredTransactions = transactions.stream()
                .filter(transaction -> transaction.getAmount() == amount)
                .collect(Collectors.toList());
        System.out.println("Total transactions after filtering: " + filteredTransactions.size());
        return filteredTransactions;
    }
}


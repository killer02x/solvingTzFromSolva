package kz.tzproject.Tz.solva.service;

import kz.tzproject.Tz.solva.model.Transaction;
import kz.tzproject.Tz.solva.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private LimitService limitService;
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(BigDecimal accountFrom, BigDecimal accountTo, String currencyShortname, double sum, String expenseCategory, LocalDate datetime) {

        Transaction transaction = new Transaction();
        transaction.setAccountFrom(accountFrom);
        transaction.setAccountTo(accountTo);
        transaction.setCurrencyShortname(currencyShortname);
        transaction.setSum(sum);
        transaction.setExpenseCategory(expenseCategory);
        transaction.setDatetime(datetime);
        transactionRepository.save(transaction);
        return transaction;
    }

    public List<Transaction> getTransactionsByAccount(BigDecimal accountNumber) {
        return (List<Transaction>) transactionRepository.findByAccountFromOrAccountTo(accountNumber, accountNumber);
    }
}

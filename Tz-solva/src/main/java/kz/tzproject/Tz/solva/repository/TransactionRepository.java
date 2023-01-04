package kz.tzproject.Tz.solva.repository;

import kz.tzproject.Tz.solva.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    BigDecimal findByAccountFromOrAccountTo(BigDecimal bankacc,BigDecimal bankac);
}

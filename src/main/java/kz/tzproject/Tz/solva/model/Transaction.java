package kz.tzproject.Tz.solva.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="transactions")
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal accountFrom;
    private BigDecimal accountTo;
    private String currencyShortname;
    private double sum;
    private String expenseCategory;
    private LocalDate datetime;
}

package kz.tzproject.Tz.solva.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "exchange_rates")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currency")
    private String currency;

    @Column(name = "value")
    private double value;

    @Column(name = "date")
    private LocalDate date;

    public ExchangeRate(String currency, double asDouble, LocalDate date) {
        this.currency = currency;
        this.value = asDouble;
        this.date = date;
    }
}

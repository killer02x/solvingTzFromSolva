package kz.tzproject.Tz.solva.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "limits")
@Getter
@Setter
@AllArgsConstructor
public class Limit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currency")
    private String currency;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "category")
    private String category;
    public Limit() {

    }
}
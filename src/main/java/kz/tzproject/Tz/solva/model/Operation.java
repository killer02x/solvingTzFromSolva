package kz.tzproject.Tz.solva.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "operations")
@Getter
@Setter
@AllArgsConstructor
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "type")
    private String type;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "limit_exceeded")
    private boolean limitExceeded;

    public Operation() {

    }
}

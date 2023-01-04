package kz.tzproject.Tz.solva.repository;

import jakarta.transaction.Transactional;
import kz.tzproject.Tz.solva.model.ExchangeRate;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate,Long> {

    ExchangeRate findByCurrencyAndDate(String currency, LocalDate date);

    ExchangeRate findTopByCurrencyOrderByDateDesc(String currency);
}

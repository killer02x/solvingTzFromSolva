package kz.tzproject.Tz.solva.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import kz.tzproject.Tz.solva.model.ExchangeRate;
import kz.tzproject.Tz.solva.service.ExchangeRateService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/exchange-rates")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping
    public ResponseEntity<ExchangeRate> getExchangeRate(@RequestParam String currency,
                                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws JsonProcessingException {
        ExchangeRate exchangeRate = exchangeRateService.getExchangeRate(currency, date);
        if (exchangeRate == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(exchangeRate);
    }
}
package kz.tzproject.Tz.solva.service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import kz.tzproject.Tz.solva.model.ExchangeRate;
import kz.tzproject.Tz.solva.model.Limit;
import kz.tzproject.Tz.solva.model.Operation;
import kz.tzproject.Tz.solva.repository.ExchangeRateRepository;
import kz.tzproject.Tz.solva.repository.LimitRepository;
import kz.tzproject.Tz.solva.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OperationService {
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private LimitRepository limitRepository;

    @Transactional
    public Operation createOperation(Operation operation) {
        BigDecimal amountInUsd = operation.getAmount();
        String currency = operation.getCurrency();

        if (!currency.equalsIgnoreCase("USD")) {
            ExchangeRate exchangeRate = exchangeRateRepository.findTopByCurrencyOrderByDateDesc(currency);
            if (exchangeRate == null) {
                throw new IllegalArgumentException("No exchange rate found for currency: " + currency);
            }
            Double exchangeRateValue = exchangeRate.getValue();
            amountInUsd = amountInUsd.multiply(BigDecimal.valueOf(exchangeRateValue));
        }
        Limit limit = limitRepository.findTopByOrderByTimestampDesc();
        if (limit != null && amountInUsd.compareTo(limit.getAmount()) > 0) {
            operation.setLimitExceeded(true);
        } else {
            operation.setLimitExceeded(false);
        }

        operation.setTimestamp(LocalDateTime.now());
        return operationRepository.save(operation);
    }

    public List<Operation> getOperations() {
        return operationRepository.findAll();
    }
}
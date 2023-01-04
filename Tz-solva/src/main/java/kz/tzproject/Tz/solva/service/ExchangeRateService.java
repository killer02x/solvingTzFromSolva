package kz.tzproject.Tz.solva.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import kz.tzproject.Tz.solva.model.ExchangeRate;
import kz.tzproject.Tz.solva.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;

@Service
public class ExchangeRateService {

    private static final String API_KEY = "3b54f016e15f431fbe49c1683a07b29f";
    private static final String API_URL = "https://api.twelvedata.com/time_series?symbol=%s&interval=1day&apikey=%s";

    @Autowired
    private final RestTemplate restTemplate;

    @Autowired
    private final ExchangeRateRepository repository;

    public ExchangeRateService(RestTemplate restTemplate, ExchangeRateRepository repository) {
        this.restTemplate = restTemplate;
        this.repository = repository;
    }

    public ExchangeRate getExchangeRate(String currency, LocalDate date) throws JsonProcessingException {
        ExchangeRate exchangeRate = repository.findByCurrencyAndDate(currency, date);
        if (exchangeRate != null) {
            return exchangeRate;
        }
        exchangeRate = fetchExchangeRateFromExternalSource(currency, date);
        if (exchangeRate != null) {
            repository.save(exchangeRate);
            return exchangeRate;
        }
        return null;
    }

    private ExchangeRate fetchExchangeRateFromExternalSource(String currency, LocalDate date) throws JsonProcessingException {
        // Формируем URL для запроса к API
        String url = String.format(API_URL, currency+"/USD", API_KEY);

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Failed to fetch exchange rate from external source, response code: " + response.getStatusCode());
        }
        String responseBody = response.getBody();
        JsonNode rootNode = new ObjectMapper().readTree(responseBody);
        JsonNode valuesNode = rootNode.path("values");
        for (JsonNode valueNode : valuesNode) {
            JsonNode closeValue = valueNode.get("close");
            if (closeValue == null) {
                throw new RuntimeException("Failed to fetch exchange rate from external source, value is null");
            }
            return new ExchangeRate(currency, closeValue.asDouble(), date);
        }
        return null;
    }
}

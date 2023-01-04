package kz.tzproject.Tz.solva;

import com.fasterxml.jackson.core.JsonProcessingException;
import kz.tzproject.Tz.solva.controllers.ExchangeRateController;
import kz.tzproject.Tz.solva.model.ExchangeRate;
import kz.tzproject.Tz.solva.repository.ExchangeRateRepository;
import kz.tzproject.Tz.solva.service.ExchangeRateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class TzSolvaApplicationTests {
	@Autowired
	private ExchangeRateService exchangeRateService;
	@Test
	void contextLoads() {
	}

	@Test
	public void testGetExchangeRate() throws JsonProcessingException {
		LocalDate date = LocalDate.now();
		ExchangeRate exchangeRate = exchangeRateService.getExchangeRate("RUB", date);
		assertNotNull(exchangeRate);
	}
}

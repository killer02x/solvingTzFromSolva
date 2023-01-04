package kz.tzproject.Tz.solva.service;

import kz.tzproject.Tz.solva.model.Limit;
import kz.tzproject.Tz.solva.repository.LimitRepository;
import org.springframework.stereotype.Service;

@Service
public class LimitService {
    private final LimitRepository limitRepository;

    public LimitService(LimitRepository limitRepository) {
        this.limitRepository = limitRepository;
    }

    public Limit setLimit(Limit limit) {
        return limitRepository.save(limit);
    }

    public Limit getCurrentLimit(String category) {
        return limitRepository.getCurrentLimitByCategory(category);
    }

}

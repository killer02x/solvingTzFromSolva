package kz.tzproject.Tz.solva.repository;

import kz.tzproject.Tz.solva.model.Limit;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Long> {


  @Query("SELECT l FROM Limit l WHERE l.category = :category AND l.timestamp = (SELECT MAX(l2.timestamp) FROM Limit l2 WHERE l2.category = :category)")
    Limit getCurrentLimitByCategory(@Param("category") String category);

    Limit findTopByOrderByTimestampDesc();

    Limit findTopByOrderByIdDesc();
}
package kz.tzproject.Tz.solva.repository;


import jakarta.transaction.Transactional;
import kz.tzproject.Tz.solva.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface OperationRepository extends JpaRepository<Operation,Long> {
}

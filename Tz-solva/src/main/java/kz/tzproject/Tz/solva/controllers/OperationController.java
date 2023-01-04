package kz.tzproject.Tz.solva.controllers;

import kz.tzproject.Tz.solva.model.Operation;
import kz.tzproject.Tz.solva.repository.OperationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operations")
public class OperationController {

    private final OperationRepository operationRepository;

    public OperationController(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @PostMapping
    public Operation createOperation(@RequestBody Operation operation) {
        return operationRepository.save(operation);
    }

    @GetMapping
    public List<Operation> getOperations() {
        return operationRepository.findAll();
    }
}
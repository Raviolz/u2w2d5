package Raviolz.u2w2d5.controllers;

import Raviolz.u2w2d5.entities.Employee;
import Raviolz.u2w2d5.payloads.NewEmployeeDTO;
import Raviolz.u2w2d5.payloads.UpdatedEmployeeDTO;
import Raviolz.u2w2d5.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService eService;

    public EmployeeController(EmployeeService eService) {
        this.eService = eService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee save(@RequestBody @Valid NewEmployeeDTO body) {
        return eService.save(body);
    }

    @GetMapping
    public Page<Employee> findAll(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size,
                                  @RequestParam(defaultValue = "surname") String sortBy) {
        return eService.findAll(page, size, sortBy);
    }

    @GetMapping("/{employeeId}")
    public Employee getById(@PathVariable UUID employeeId) {
        return this.eService.findById(employeeId);
    }

    @PutMapping("/{userId}")
    public Employee getByIdAndUpdate(@PathVariable UUID userId, @RequestBody UpdatedEmployeeDTO body) {
        return this.eService.findByIdAndUpdate(userId, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID id) {
        eService.findByIdAndDelete(id);
    }
}
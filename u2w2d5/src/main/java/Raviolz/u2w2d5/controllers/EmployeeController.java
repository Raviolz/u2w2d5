package Raviolz.u2w2d5.controllers;

import Raviolz.u2w2d5.entities.Employee;
import Raviolz.u2w2d5.payloads.NewEmployeeDTO;
import Raviolz.u2w2d5.payloads.UpdatedEmployeeDTO;
import Raviolz.u2w2d5.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    //    // oggetto FormData nel FRONTEND .. sempre chiave valore (come vediamo su postman) devono coincidere nomi FRONT e BACk
    @PatchMapping({"/{employeeId}/avatar"})  // file di tipo MultipartFile ha tutti i suopi metodi getfile
    public void uploadAvatar(@RequestParam("profile_picture") MultipartFile file, @PathVariable UUID employeeId) {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        System.out.println(file.getContentType()); //esiste get content type epr fare controlli sul tipo di file che voglio gif pdf ecc
        // NOTA HO CAMBIATO IN APP PROPERTIES LA GRANDEZZA SE NO NON LA PRENDEVA
        // Per poterlo collegare davvero a cloudinary una delle opzioni e' farsi un bean con dentro tutte le specifiche di configurazione che servono
        //CREO UN OGGETTO CHE FA L UPLOAD non il singolo upload di quest immagine (cosi viene inizializzato ed e' disponibile in tutta l applicazione
        // cosi chianmo solo il suo metodo upload e via
        //  Obbligatoriamente classe di configurazione eprche' cloudinary e' una libreria esterna quindi non posso usare @cComponent su classi non mie
        //  e' da li che prendo la classe Cloudinary dalla loro libreria
        //  inoltre io dovro' passargli degli attributi ecc e quindi quando passo cose e personalizzo mi serve il bean per customizzare ogni volta e non solo far partire ad avvio

        this.eService.avatarUpload(file, employeeId);
    }
}




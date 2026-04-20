package Raviolz.u2w2d5.services;

//2  FACCIO ENDPOINT DI LOGIN nel controller --> 2 DI CONSEGUENZA IL SUO SERVICE E DTO DI DATI IN ENTRATA E USCITA (Entrata dipende cosa voglio solitamente mail e pass, uscita
// sempre token e casomai se voglio altro)

// Nel SERVICE di AUTENTICAZIONE facciamo tornar euna stringa con il metodo per generare il token e fare controlli
//Controllo credenziali --> se esiste utente con quell email, se pass corrisponde
//Genero token o errore se non giusti controlli

import Raviolz.u2w2d5.entities.Employee;
import Raviolz.u2w2d5.exceptions.NotFoundEx;
import Raviolz.u2w2d5.exceptions.UnauthorizedException;
import Raviolz.u2w2d5.payloads.LoginDTO;
import Raviolz.u2w2d5.security.TokenTools;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final EmployeeService employeeService;
    private final TokenTools tokenTools;

    public AuthService(EmployeeService employeeService, TokenTools tokenTools) {
        this.employeeService = employeeService;
        this.tokenTools = tokenTools;
    }

    public String checkCredentialsAndGenerateToken(LoginDTO body) {
        // 1. Controllo credenziali
        // 1.1 Controllo se esiste utente con quell'email
        try { // TODO GUARDO COME SWITCHARE LA NOTFOUND IN UNOTHORIZED per non dare troppe info di errore nel momento di login coin try catch

            Employee found = this.employeeService.findByEmail(body.email());
            // 1.2 Controllo se password corrisponde
            // TODO: Migliorare gestione password
            if (found.getPassword().equals(body.password())) {
                // 2. Se credenziali OK -> Generiamo Token e ritorniamolo
                return this.tokenTools.generateToken(found); // COME GENERARE IL TOKEN --> 3 librerie aggiunte prima ad esempio da jjwt-api Jwts (fornisce metodi per creare e leggere token builder() parser()
                // Faccio una classe helper a parte per generare i tokencon i metodi relativi --> @Component cosi so gia che con l iniziezione dipendente la posso usare o

            } else {
                // 3. Altrimenti -> Error
                throw new UnauthorizedException("Credenziali errate");
            }
        } catch (NotFoundEx ex) {
            throw new UnauthorizedException("Credenziali errate");
        }
    }
}

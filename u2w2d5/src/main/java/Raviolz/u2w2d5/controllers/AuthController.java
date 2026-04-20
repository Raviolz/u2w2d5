package Raviolz.u2w2d5.controllers;

//2  FACCIO ENDPOINT DI LOGIN nel controller --> 2 DI CONSEGUENZA IL SUO SERVICE E DTO DI DATI IN ENTRATA E USCITA (Entrata dipende cosa voglio solitamente mail e pass, uscita
// sempre token e casomai se voglio altro)

import Raviolz.u2w2d5.payloads.LoginDTO;
import Raviolz.u2w2d5.payloads.LoginRespDTO;
import Raviolz.u2w2d5.services.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    //  2 serve quindi un DTO come @RequestBody per prendere le info nel payload e uno per rispondere dando indietro il token quindi di la passo un token e torno una stringa
    public LoginRespDTO login(@RequestBody LoginDTO body) {
        return new LoginRespDTO(this.authService.checkCredentialsAndGenerateToken(body));
    }

}
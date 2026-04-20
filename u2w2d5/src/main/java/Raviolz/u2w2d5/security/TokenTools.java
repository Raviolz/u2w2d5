package Raviolz.u2w2d5.security;


import Raviolz.u2w2d5.entities.Employee;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenTools {

    private final String secret;

    public TokenTools(@Value("${jwt.secret}") String secret) {
        this.secret = secret;
    }

    // Jwts (proviene da jjwt-api) fornisce principalmente 2 metodi: builder() e parser(), il primo lo usiamo per creare i token,
    // il secondo per leggerli (eventualmente estraendo info dal token) e validarli

    public String generateToken(Employee employee) { // evo passargli l utente o il suo id perche cosi e' che si generano combinandolo ad altro
        return Jwts.builder() // uso metodo di jwts per impostare cose
                .setIssuedAt(new Date(System.currentTimeMillis()))// Data di emissione (IaT - Issued At), va messa in millisecondi
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))// (scadenza sempre in mm scelgo io E' IMPORTANTE, SENZA TROPPO DEBOLE ) questo se voglio una settimana.. faccio fare i calcoli al pc da adesso a 1000(i sec) per 60 un minuto per 60 un ora per 24 giorno e poi decido
                .setSubject(String.valueOf(employee.getId())) //a chi appartiene il token, inseriamo id proprietario --> MAI METTERE DATI SENSIBILI AL SUO INTERNO (NO PASS ECC).. devo fare to string perche non lo e'
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))// firmamio il tokern con un segreto e un algoritmo dedicato .. ALGORITMO GIA ESISTENTE IN LIBRERIA .. iL SEGRETO LO METTO DELL ENV ---> jwt.secret= ${ e poi  JWT_SECRET
                .compact(); // tonra la stringa che vogliamo con il token QUINDI PRIMA PER REDUPERARLO CON @VALUE e lo metto negli attriibuti e costruttore per iniettarlo
    }

    // .. passwordgenerator online con 50 caratteri e la usiamo .. 30 troppo poco e poi
    //.compact() --> tonra la stringa che vogliamo con il token QUINDI PRIMA PER REDUPERARLO CON @VALUE e lo metto negli attriibuti e costruttore per iniettarlo

//	public void verifyToken() {
//	}
}
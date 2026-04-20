package Raviolz.u2w2d5.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

//1 CONFIGURAZIONE per far si che controlli quello che voglio e non tutto .. PARTE SUPER CONFIGURTATO NOI MOLLIAMO UN PO LA PRESA E COSTUMIZZARLO

@Configuration
@EnableWebSecurity
//regole, sessione, sbloccare endpoint, disabilitare controlli , servira' anche epr passowrd utrenti CORS ecc
public class SecurityConfig {

    @Bean // classe di configurazione bean accessibile ovunque e configurabile come faremo ora
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) { // nomenclatura e parametro OBBLIGATORIO e' lui che ha i metodi


        // apro tutti gli end point come parametro qui metto dove voglio che faccia i controlli lui .. io voglio che lui apra le porte a tutto perche poi i controlli li faccio io con il tokern
        httpSecurity.authorizeHttpRequests(req -> req.requestMatchers("/**").permitAll()); // uso le lambda per dirgli su tutti gli end point url(asterisco) permetti tutto --> SBLOCCO TUTTE RICHIESTE --> controlli li faro' poi io

        // Siccome JWT Tolgo anche le sessioni (perche' rest restless)
        httpSecurity.sessionManagement(sessions -> sessions.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // Tolgo form perche' usiamo React noi
        httpSecurity.formLogin(formLogin -> formLogin.disable());

        httpSecurity.csrf(csrf -> csrf.disable()); // Disabilitiamo la protezione da attacchi CSRF perché non serve
        // nel caso di autenticazione tramite JWT, anzi ci complicherebbe la vita, anche lato FE.

        // Posso aggiungere anche ulteriori funzionalità custom

        return httpSecurity.build();
    }
}
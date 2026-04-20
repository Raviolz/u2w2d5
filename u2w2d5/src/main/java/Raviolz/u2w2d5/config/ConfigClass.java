package Raviolz.u2w2d5.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConfigClass {

    @Bean // CLoudinary vuole configurazione sotto forma di hashmap (combinazione chiave valore)
    public Cloudinary getImageUploader(@Value("${CLOUDINARY_NAME}") String cloudName,
                                       @Value("${CLOUDINARY_API}") String apiKey,
                                       @Value("${CLOUDINARY_API_SECRET}") String apiSecret)
    // sout per vedere se leggo valori correttamente.. giusto per vedere se ho preso variabili giuste e scritte correttamente ecc
//    System.out.println(cloudName);
//        System.out.println(apiKey);
//        System.out.println(apiSecret);
    {
        Map<String, String> configuration = new HashMap<>(); // da passare al costruttore di cloudinary .. la vuole cosi in documentazione
        configuration.put("cloud_name", cloudName); // Nome cosi OBBLIGATORI per doc loro , i valori sono i miei (da env a application properties)
        configuration.put("api_key", apiKey); // i valori da application properties si leggono con @Value nei parametri --> iniezione dipendenze
        configuration.put("api_secret", apiSecret); // infatti come secondo valore di map metto il nome dle parametro che sto passando con Value preso da ecc
        return new Cloudinary(configuration);
    }
    // IL BEAN POI LO POSSO USARE DOVE VOGLIO.. LO USO NEL SERVICE IN MODO DA POTER PERSONALIZZARE IL METODO IN CUI VOGLIO USARLO
    // Per usarlo dentro il service mi ricordo che devo iniettarlo o autowired o con il costruttore
}

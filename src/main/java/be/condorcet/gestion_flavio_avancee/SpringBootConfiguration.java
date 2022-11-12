package be.condorcet.gestion_flavio_avancee;

import be.condorcet.gestion_flavio_avancee.services.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootConfiguration {

    @Value("${server.mode}")
    private String mode;

    @Bean
    InterfVilleService villeServiceImpl() {
        System.out.println("création du service ville en mode : "+mode);
        switch (mode){
            case "PROD" : return new VilleServiceImpl();
            case "STUB" : return new VilleServiceStub();
            case "MOCK" : return new VilleServiceMock();
            default: return new VilleServiceStub();
        }
    }
    @Bean
    InterfCoureurService coureurServiceImpl() {
        System.out.println("création du service coureur en mode : "+mode);
        switch (mode){
            case "PROD" : return new CoureurServiceImpl();
            case "STUB" : return new CoureurServiceStub();
            case "MOCK" : return new CoureurServiceMock();
            default: return new CoureurServiceStub();
        }
    }
}

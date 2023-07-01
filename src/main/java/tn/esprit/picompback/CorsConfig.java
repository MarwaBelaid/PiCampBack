package tn.esprit.picompback;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200") // Remplacez "http://localhost:4200" par l'URL de votre application Angular
                .allowedMethods("*") // Spécifiez les méthodes HTTP autorisées (par exemple, GET)
                .allowedHeaders("*") // Autorisez tous les en-têtes
                .maxAge(3600); // Spécifiez la durée de mise en cache des résultats de pré-vérification CORS en secondes
    }
}

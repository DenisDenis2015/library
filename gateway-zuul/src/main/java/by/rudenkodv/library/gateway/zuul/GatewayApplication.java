package by.rudenkodv.library.gateway.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    @Bean
    CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        //config.addAllowedOrigin("http://localhost:4200");
        config.applyPermitDefaultValues();
        config.addAllowedHeader("");
        config.addAllowedMethod("");
        PathPatternParser patternParser = new PathPatternParser();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(patternParser);
        source.registerCorsConfiguration("/**", config);
        return new CorsWebFilter(source);
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
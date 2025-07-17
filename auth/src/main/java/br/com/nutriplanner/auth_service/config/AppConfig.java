package br.com.nutriplanner.auth_service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    @LoadBalanced // Magia do Spring Cloud! Habilita o RestTemplate a usar o Eureka para resolver nomes de serviço.
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
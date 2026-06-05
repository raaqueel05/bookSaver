package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {"app", "domain"})
@EnableJpaRepositories(basePackages = "domain.repositories")
@EntityScan(basePackages = "domain.entities")
public class LlibresApplication {

    public static void main(String[] args) {
        SpringApplication.run(LlibresApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

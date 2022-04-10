package uk.co.drumcoder.salon;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring5.SpringTemplateEngine;

public class WebConfiguration {
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addDialect(new LayoutDialect());
        return templateEngine;
    }
}

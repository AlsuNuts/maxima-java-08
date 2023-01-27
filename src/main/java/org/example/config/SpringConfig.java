package org.example.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "org.example")
@PropertySource("classpath:application.properties")
public class SpringConfig implements WebMvcConfigurer {
    @Autowired
    private Environment environment;
    @Bean
    public DataSource dataSource(){
         HikariConfig hikariConfig = new HikariConfig();
         hikariConfig.setDriverClassName(environment.getProperty("db.driver"));
         hikariConfig.setJdbcUrl(environment.getProperty("db.url"));
         return new HikariDataSource(hikariConfig);
    }

    @Bean
    public RowMapper <Product> productRowMapper(){
        return ((rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"))
        );
    }
    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }

    @Bean(initMethod = "init")
    public ProductRepository repository(){
        return new ProductRepository();
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("templates");
        return freeMarkerConfigurer;
    }

    @Bean
    public ViewResolver viewResolver(){
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(true);
        resolver.setPrefix("");
        resolver.setSuffix(".html");
        resolver.setContentType("text/html; charset=UTF-8");
        return resolver;
    }
}

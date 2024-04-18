package id.my.hendisantika.springbootkotlintodo;

import id.my.hendisantika.springbootkotlintodo.util.HibernateStatisticsInterceptor;
import id.my.hendisantika.springbootkotlintodo.util.RequestStatisticsInterceptor;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-kotlin-todo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/18/24
 * Time: 07:04
 * To change this template use File | Settings | File Templates.
 */
@SpringBootApplication
public class SpringBootKotlinTodoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootKotlinTodoApplication.class);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder factory, DataSource dataSource,
            JpaProperties properties) {
        Map<String, Object> jpaProperties = new HashMap<>();
        jpaProperties.putAll(properties.getHibernateProperties(dataSource));
        jpaProperties.put("hibernate.ejb.interceptor", hibernateInterceptor());
        return factory.dataSource(dataSource).packages("io.github.knes1.todo.model")
                .properties(jpaProperties).build();
    }

    @Bean
    public HibernateStatisticsInterceptor hibernateInterceptor() {
        return new HibernateStatisticsInterceptor();
    }

    @Configuration
    public static class WebApplicationConfig extends WebMvcConfigurerAdapter {

        @Autowired
        private RequestStatisticsInterceptor requestStatisticsInterceptor;

        @Bean
        public RequestStatisticsInterceptor requestStatisticsInterceptor() {
            return new RequestStatisticsInterceptor();
        }

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(requestStatisticsInterceptor).addPathPatterns("/**");
        }
    }
}

package com.bs.api;

import javax.naming.NamingException;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Milton BO
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.bs.service"})
@EntityScan(basePackages = "com.bs.domain.model.entities")
@EnableJpaRepositories(basePackages = "com.bs.service", entityManagerFactoryRef = "entityManagerFactory")
@EnableTransactionManagement
public class RepositoryConfiguration {

    @Bean
    public DataSource jndiDataSource() throws IllegalArgumentException, NamingException {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName("java:/BoliviaSolidariaDs");
        bean.setProxyInterface(DataSource.class);
        bean.setLookupOnStartup(true);
        bean.afterPropertiesSet();
        return (DataSource) bean.getObject();
    }

}

package kea.project.reportservice.global.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties
public class DataSourceConfig {
    public static final String COMMAND_DATASOURCE = "commandDataSource";
    public static final String QUERY_DATASOURCE = "queryDataSource";

    @Bean(COMMAND_DATASOURCE)
    @ConfigurationProperties(prefix = "spring.datasource.command")
    public DataSource commandDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(QUERY_DATASOURCE)
    @ConfigurationProperties(prefix = "spring.datasource.query")
    public DataSource queryDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    @Primary
    @DependsOn({COMMAND_DATASOURCE, QUERY_DATASOURCE})
    public DataSource routingDataSource(@Qualifier(COMMAND_DATASOURCE) DataSource commandDataSource,
                                        @Qualifier(QUERY_DATASOURCE) DataSource queryDataSource) {

        RoutingDataSourceConfig routingDataSource = new RoutingDataSourceConfig();
        Map<Object, Object> dataSourceMap = new HashMap<>();

        dataSourceMap.put("command", commandDataSource);
        dataSourceMap.put("query", queryDataSource);

        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(commandDataSource);

        return routingDataSource;
    }
    @Bean
    @DependsOn("routingDataSource")
    public LazyConnectionDataSourceProxy dataSource(DataSource routingDataSource) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }
}

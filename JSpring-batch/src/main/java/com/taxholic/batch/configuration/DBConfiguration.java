package com.taxholic.batch.configuration;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement(mode = AdviceMode.PROXY, order = 0)
public class DBConfiguration {
	
	 private @Value("${jdbc.minimumIdle}") int minimumIdle;
	 private @Value("${jdbc.maximumPoolSize}") int maximumPoolSize;
	 private @Value("${jdbc.validationQuery}") String validationQuery;
	 private @Value("${jdbc.connectionTimeout}") int connectionTimeout;
	 private @Value("${jdbc.autocommit}") boolean isAutoCommit;
	 
	 private @Value("${datasource.driverClassName}") String driverClassName;
	 private @Value("${datasource.cachePrepStmts}") boolean cachePrepStmts;
	 private @Value("${datasource.prepStmtCacheSize}") int prepStmtCacheSize;
	 private @Value("${datasource.prepStmtCacheSqlLimit}") int prepStmtCacheSqlLimit;
	 private @Value("${datasource.useServerPrepStmts}") boolean useServerPrepStmts;
	 
	 private @Value("${datasource.url}") String url;
	 private @Value("${datasource.user}") String user;
	 private @Value("${datasource.password}") String password;

    @Bean(destroyMethod = "shutdown")
    public DataSource dataSource() {
    	
        HikariConfig config = new HikariConfig();
        
        config.setMinimumIdle(minimumIdle);
        config.setMaximumPoolSize(maximumPoolSize);
        config.setConnectionTestQuery(validationQuery);
        config.setConnectionTimeout(connectionTimeout);
        config.setAutoCommit(isAutoCommit);
        
        config.addDataSourceProperty("cachePrepStmts", cachePrepStmts);
        config.addDataSourceProperty("prepStmtCacheSize", prepStmtCacheSize);
        config.addDataSourceProperty("useServerPrepStmts", useServerPrepStmts);
        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);

        HikariDataSource dataSource = new HikariDataSource(config);

        return dataSource;
    }
    

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
    	
    	SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
    	sqlSessionFactory.setDataSource(dataSource());
		PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
		sqlSessionFactory.setConfigLocation(defaultResourceLoader.getResource("classpath:mybatis-config.xml"));
		sqlSessionFactory.setMapperLocations(resourcePatternResolver.getResources("classpath:mapper/**/*.xml"));

		return sqlSessionFactory.getObject();
    }
    
    
    @Bean(destroyMethod = "clearCache")
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
		SqlSessionTemplate sqlSession = new SqlSessionTemplate(sqlSessionFactory);
		return sqlSession;
	}
    
    @Bean
	public DataSourceTransactionManager txManager(@Qualifier("dataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
    
    
}
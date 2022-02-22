package com.emp.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisCacheConfig {

	@Value("${spring.redis.cache-expire-time.cache-manager.employeeInfoByEmpId}")
	private String employeeInfoByEmpIdCacheExpiryTime;

	@Value("${spring.redis.cache-expire-time.cache-manager.getAllEmployee}")
	private String getAllEmployeeCacheExpiryTime;
	
	
	public static final String EMPLOYEE_INFO_CACHE_MANAGER = "EmployeeInfoCacheManager";

	public static final String EMPLOYEE_INFO_BY_ID_CACHE = "EmployeeInfoByIdCache";

	public static final String GETT_ALL_EMPLOYEE_CACHE = "GetAllEmployeeCache";
	
	
	@Primary
	@Bean(name = EMPLOYEE_INFO_CACHE_MANAGER)
	public RedisCacheManager employeeInfocacheManager(LettuceConnectionFactory lettuceCnnectionFactory) {
		Map<String, RedisCacheConfiguration> cacheConfigurationMap = new HashMap<>();

		cacheConfigurationMap.put(EMPLOYEE_INFO_BY_ID_CACHE,
				RedisCacheConfiguration.defaultCacheConfig().disableCachingNullValues()
						.entryTtl(Duration.ofMinutes(2L)));

		cacheConfigurationMap.put(GETT_ALL_EMPLOYEE_CACHE,
				RedisCacheConfiguration.defaultCacheConfig().disableCachingNullValues()
						.entryTtl(Duration.ofMinutes(2L)));

		return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(lettuceCnnectionFactory)
				.withInitialCacheConfigurations(cacheConfigurationMap).build();
	}
	
}

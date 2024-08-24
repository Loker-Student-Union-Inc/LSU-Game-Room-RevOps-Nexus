package edu.csudh.lsu.revops.configuration;

import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * {@code RetryTemplateConfig} is responsible for configuring retry logic in the RevOps application.
 * </p>
 *
 * <p>
 * This class enables retry functionality using the {@link RetryTemplate}, which allows operations to be retried
 * in case of transient failures, such as database connection issues. The retry behavior is configurable via
 * properties, including the backoff period and the maximum number of retry attempts.
 * </p>
 *
 * <p>
 * Created by: Digvijay Hethur Jagadeesha
 * Date: August 22 2024
 * </p>
 *
 * <p>
 * All Rights Reserved by Loker Student Union, Inc at California State University Dominguez Hills from 2024.
 * </p>
 */
@Configuration
@EnableRetry
public class RetryTemplateConfig {

    // Configurable backoff period in milliseconds, injected from application properties
    @Value("${retry.backoffPeriod.ms}")
    long backoffPeriodMs;

    // Configurable maximum retry attempts, injected from application properties
    @Value("${retry.maxAttempts}")
    int maxAttempts;

    /**
     * Configures and returns a {@link RetryTemplate} bean.
     *
     * <p>
     * The {@code RetryTemplate} is configured with a custom retry policy and backoff policy. The retry policy
     * determines which exceptions will trigger a retry, while the backoff policy controls the delay between retries.
     * </p>
     *
     * @return a configured {@link RetryTemplate}
     */
    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(getRetryPolicy());
        retryTemplate.setBackOffPolicy(getBackOffPolicy());
        return retryTemplate;
    }

    /**
     * Configures and returns a {@link FixedBackOffPolicy}.
     *
     * <p>
     * The {@code FixedBackOffPolicy} defines the delay between retries, which is set based on the configured
     * backoff period in milliseconds.
     * </p>
     *
     * @return a configured {@link FixedBackOffPolicy}
     */
    public FixedBackOffPolicy getBackOffPolicy() {
        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(backoffPeriodMs);
        return fixedBackOffPolicy;
    }

    /**
     * Configures and returns a {@link SimpleRetryPolicy}.
     *
     * <p>
     * The {@code SimpleRetryPolicy} defines the exceptions that will trigger a retry and the maximum number of
     * retry attempts. The exceptions are mapped to a boolean value indicating whether they should be retried.
     * </p>
     *
     * @return a configured {@link SimpleRetryPolicy}
     */
    public SimpleRetryPolicy getRetryPolicy() {
        Map<Class<? extends Throwable>, Boolean> exceptionMap = new HashMap<>();
        // Retry on JDBC connection exceptions
        exceptionMap.put(JDBCConnectionException.class, true);
        // Retry on data access resource failures
        exceptionMap.put(DataAccessResourceFailureException.class, true);
        // Retry on JPA system exceptions
        exceptionMap.put(JpaSystemException.class, true);
        // Retry on transaction exceptions
        exceptionMap.put(TransactionException.class, true);
        return new SimpleRetryPolicy(maxAttempts, exceptionMap, true);
    }
}

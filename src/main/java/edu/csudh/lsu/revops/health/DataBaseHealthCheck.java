package edu.csudh.lsu.revops.health;

import edu.csudh.lsu.revops.utils.ApplicationConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * The {@code DataBaseHealthCheck} class is responsible for checking the health of the database
 * connections within the RevOps application. It implements the {@code HealthCheckAware} interface
 * to provide custom health checks related to the database.
 * </p>
 *
 * <p>
 * This class attempts to obtain a connection from the configured {@link DataSource} and returns
 * a health status based on the result. It also logs and reports any exceptions encountered
 * during the health check process.
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
@Component
@Slf4j
public class DataBaseHealthCheck implements HealthCheckAware {

    @Autowired
    DataSource dataSource;

    @Value("${spring.datasource.url}")
    private String url;

    /**
     * Provides a list of health checks for the database.
     *
     * <p>
     * This method returns a list of {@code HealthCheck} objects, including a check for database health.
     * </p>
     *
     * @return a list of health checks for the database
     */
    @Override
    public List<HealthCheck> getChecks() {
        List<HealthCheck> healthChecks = new ArrayList<>();
        healthChecks.add(this::checkDataBaseHealth);
        return healthChecks;
    }

    /**
     * Performs a health check for the database.
     *
     * <p>
     * This method attempts to obtain a connection from the {@link DataSource}. If the connection is successful,
     * the health check result is marked as successful. If an exception occurs, the result is marked as a failure,
     * and relevant error details are added to the {@code HealthCheckResult}.
     * </p>
     *
     * @return the health check result for the database
     */
    public HealthCheckResult checkDataBaseHealth() {
        HealthCheckResult healthCheckResult = new HealthCheckResult(
                ApplicationConstants.DATA_SOURCE, ApplicationConstants.DB_CONNECTION,
                HealthCheckResult.Result.success, "Success."
        );

        try (Connection connection = this.dataSource.getConnection()) {
            if (connection.getMetaData() != null) {
                healthCheckResult.getDetails().put("uri", connection.getMetaData().getURL());
            }
        } catch (SQLException sqlException) {
            healthCheckResult.setResult(HealthCheckResult.Result.failure);
            healthCheckResult.getDetails().put(ApplicationConstants.MESSAGE, sqlException.getMessage());
            if (url != null) {
                healthCheckResult.getDetails().put("uri", url);
                healthCheckResult.getDetails().put("disclaimer", "The uri is provided as a hint and may not reflect the actual uri used.");
            }
        } catch (Exception ex) {
            log.error("Exception occurred while checking health of Database", ex);
            healthCheckResult.setResult(HealthCheckResult.Result.failure);
            healthCheckResult.getDetails().put(ApplicationConstants.MESSAGE, ex.getMessage());
            if (url != null) {
                healthCheckResult.getDetails().put("uri", url);
                healthCheckResult.getDetails().put("disclaimer", "The uri is provided as a hint and may not reflect the actual uri used.");
            }
        }

        return healthCheckResult;
    }
}

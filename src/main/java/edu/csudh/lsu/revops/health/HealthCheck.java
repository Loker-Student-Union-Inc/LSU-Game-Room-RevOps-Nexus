package edu.csudh.lsu.revops.health;

/**
 * <p>
 * The {@code HealthCheck} interface represents a contract for performing health checks.
 * </p>
 *
 * <p>
 * Classes implementing this interface should define the logic to check the health
 * of specific components and return a {@link HealthCheckResult}.
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
public interface HealthCheck {

    /**
     * Performs a health check and returns the result.
     *
     * @return the result of the health check
     */
    HealthCheckResult check();
}

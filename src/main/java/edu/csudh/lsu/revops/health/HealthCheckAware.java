package edu.csudh.lsu.revops.health;

import java.util.List;

/**
 * <p>
 * The {@code HealthCheckAware} interface defines a contract for providing health checks.
 * </p>
 *
 * <p>
 * Classes implementing this interface should return a list of {@link HealthCheck} instances
 * that represent various health checks performed within the application.
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
public interface HealthCheckAware {

    /**
     * Returns a list of health checks.
     *
     * <p>
     * This method should provide a list of {@link HealthCheck} instances,
     * representing the various health checks that can be performed.
     * </p>
     *
     * @return a list of health checks
     */
    List<HealthCheck> getChecks();
}

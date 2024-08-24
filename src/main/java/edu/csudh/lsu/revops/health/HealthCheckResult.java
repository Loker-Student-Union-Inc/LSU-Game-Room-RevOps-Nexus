package edu.csudh.lsu.revops.health;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * The {@code HealthCheckResult} class represents the result of a health check.
 * </p>
 *
 * <p>
 * This class holds information about the health check, including the name, description,
 * result (success or failure), a message, and additional details as a map of key-value pairs.
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
public class HealthCheckResult {

    /**
     * Enum representing the result of the health check.
     */
    public enum Result {
        success, failure
    }

    private final String name;
    private final String description;
    private Result result;
    private final String message;
    private final Map<String, String> details;

    /**
     * Constructs a new {@code HealthCheckResult} instance.
     *
     * @param name        the name of the health check
     * @param description a brief description of the health check
     * @param result      the result of the health check (success or failure)
     * @param message     an optional message associated with the health check
     */
    public HealthCheckResult(String name, String description, Result result, String message) {
        this.name = name;
        this.description = description;
        this.result = result;
        this.message = message;
        this.details = new HashMap<>();
    }

    /**
     * Returns the result of the health check.
     *
     * @return the result of the health check
     */
    public Result getResult() {
        return result;
    }

    /**
     * Sets the result of the health check.
     *
     * @param result the result of the health check
     */
    public void setResult(Result result) {
        this.result = result;
    }

    /**
     * Returns the message associated with the health check.
     *
     * @return the message associated with the health check
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns the additional details of the health check as a map of key-value pairs.
     *
     * @return a map of key-value pairs representing additional details of the health check
     */
    public Map<String, String> getDetails() {
        return details;
    }

    /**
     * Returns the name of the health check.
     *
     * @return the name of the health check
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the description of the health check.
     *
     * @return the description of the health check
     */
    public String getDescription() {
        return description;
    }
}

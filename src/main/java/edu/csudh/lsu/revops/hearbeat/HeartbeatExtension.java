package edu.csudh.lsu.revops.hearbeat;

import java.util.Map;

/**
 * <p>
 * The {@code HeartbeatExtension} interface defines the contract for adding custom
 * data to the application's heartbeat response.
 * </p>
 *
 * <p>
 * Classes implementing this interface are responsible for providing key-value pairs
 * that will be included in the heartbeat response, allowing for custom extensions such as
 * build information, versioning, or additional status checks.
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
public interface HeartbeatExtension {

    /**
     * Provides custom data to be added to the heartbeat response.
     *
     * <p>
     * Implementations of this method should return a {@code Map} containing key-value
     * pairs that represent additional data to be included in the heartbeat response.
     * This could include information such as build timestamps, version numbers, or
     * any other relevant metadata that enhances the application's heartbeat monitoring.
     * </p>
     *
     * @return a map containing custom data for the heartbeat response
     */
    Map<String, Object> getHeartbeatExtension();
}

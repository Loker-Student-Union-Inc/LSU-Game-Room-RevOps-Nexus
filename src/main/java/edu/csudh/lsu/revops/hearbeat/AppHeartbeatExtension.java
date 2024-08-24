package edu.csudh.lsu.revops.hearbeat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * The {@code AppHeartbeatExtension} class is responsible for adding extensions to the application's heartbeat.
 * </p>
 *
 * <p>
 * This class implements the {@code HeartbeatExtension} interface and adds custom data, such as the build timestamp,
 * to the heartbeat response. The build timestamp is injected from the application properties.
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
public class AppHeartbeatExtension implements HeartbeatExtension {

    // Injects the build timestamp from the application properties
    @Value("${application.buildTimestamp:default-timestamp}")
    private String buildTimestamp;


    /**
     * Adds custom data to the heartbeat response.
     *
     * <p>
     * This method adds the build timestamp to the heartbeat response as a key-value pair
     * in a {@code Map} and returns the result.
     * </p>
     *
     * @return a map containing custom data for the heartbeat response
     */
    @Override
    public Map<String, Object> getHeartbeatExtension() {
        Map<String, Object> responseMap = new HashMap<>();
        // Adds the build timestamp to the heartbeat response
        responseMap.put("buildTimestamp", buildTimestamp);
        return responseMap;
    }
}

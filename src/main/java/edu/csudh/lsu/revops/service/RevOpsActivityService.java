package edu.csudh.lsu.revops.service;

import edu.csudh.lsu.persistence.exception.PersistenceException;
import edu.csudh.lsu.revops.dao.ActivityDAO;
import edu.csudh.lsu.revops.model.ActivityResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * The {@code RevOpsActivityService} class encapsulates the business logic for activities.
 * </p>
 *
 * <p>
 * This class is responsible for processing and validating activity data before passing it to the {@code ActivityDAO}.
 * It provides methods for saving, updating, and fetching activity information. All relevant logging is performed
 * for audit and monitoring purposes.
 * </p>
 *
 * <p>
 * Created by: Digvijay Hethur Jagadeesha
 * Date: August 24 2024
 * </p>
 *
 * <p>
 * All Rights Reserved by Loker Student Union, Inc at California State University Dominguez Hills from 2024.
 * </p>
 */
@Slf4j
@Service
public class RevOpsActivityService {

    @Autowired
    private ActivityDAO activityDAO;

    /**
     * Saves or updates an activity by interacting with the {@code ActivityDAO}.
     *
     * <p>
     * This method processes the incoming {@code ActivityResponse} object and passes it to the DAO layer
     * for saving or updating the activity in the database.
     * </p>
     *
     * @param activityResponse The {@code ActivityResponse} object containing activity data from the client.
     * @return int The number of records updated (1 for success, 0 for failure).
     */
    public int saveOrUpdateActivity(ActivityResponse activityResponse) {
        try {
            log.info("Processing saveOrUpdateActivity in RevOpsActivityService.");
            return activityDAO.saveOrUpdateActivity(activityResponse);
        } catch (Exception ex) {
            log.error("An error occurred in RevOpsActivityService while saving or updating activity: {}", ex.getMessage(), ex);
            throw new PersistenceException("Error occurred while saving or updating activity", ex.getMessage());
        }
    }

    /**
     * Partially updates an activity by interacting with the {@code ActivityDAO}.
     *
     * <p>
     * This method processes the incoming partial update data and passes it to the DAO layer
     * for applying the updates to the activity in the database.
     * </p>
     *
     * @param id The ID of the activity to update.
     * @param updates A map of field names and values to update.
     * @return int The number of records updated (1 for success, 0 for failure).
     */
    public int partialUpdateActivity(UUID id, Map<String, Object> updates) {
        try {
            log.info("Processing partialUpdateActivity in RevOpsActivityService for activity ID: {}", id);
            // Implement the logic to partially update the activity in the DAO
            // This might involve fetching the existing activity and applying the updates
            return activityDAO.partialUpdateActivity(id, updates); // You need to implement this in DAO
        } catch (Exception ex) {
            log.error("An error occurred in RevOpsActivityService while partially updating activity: {}", ex.getMessage(), ex);
            throw new PersistenceException("Error occurred while partially updating activity", ex.getMessage());
        }
    }

    /**
     * Fetches all distinct activity categories by interacting with the {@code ActivityDAO}.
     *
     * <p>
     * This method retrieves a list of distinct categories from the DAO layer, which queries the database.
     * </p>
     *
     * @return List of distinct categories as {@code List<String>}.
     */
    public List<String> fetchAllCategories() {
        try {
            log.info("Fetching all categories in RevOpsActivityService.");
            return activityDAO.fetchAllCategories();
        } catch (Exception ex) {
            log.error("An error occurred in RevOpsActivityService while fetching categories: {}", ex.getMessage(), ex);
            throw new PersistenceException("Error occurred while fetching categories", ex.getMessage());
        }
    }
}

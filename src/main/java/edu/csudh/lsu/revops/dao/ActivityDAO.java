package edu.csudh.lsu.revops.dao;

import edu.csudh.lsu.persistence.exception.PersistenceException;
import edu.csudh.lsu.persistence.model.activity.Activity;
import edu.csudh.lsu.persistence.repository.gamesroom.activity.ActivityRepository;
import edu.csudh.lsu.persistence.service.ActivityService;
import edu.csudh.lsu.revops.model.ActivityResponse;
import edu.csudh.lsu.revops.utils.ActivityHelper;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * <p>
 * The {@code ActivityDAO} class is responsible for executing data access operations for the {@code Activity} entity.
 * </p>
 *
 * <p>
 * This class encapsulates the logic for saving, updating, and partially updating activities using the {@code RevOpsActivityService}.
 * It leverages the {@code RetryTemplate} to handle retries for potentially transient failures. All relevant logging is performed
 * for audit and monitoring purposes.
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
@Slf4j
@Component
public class ActivityDAO {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private RetryTemplate retryTemplate;

    /**
     * Saves or updates an activity by interacting with the {@code RevOpsActivityService}.
     *
     * <p>
     * This method attempts to save or update the provided activity data. In case of transient failures,
     * retries are handled by the {@code RetryTemplate}. Logging is performed to record each attempt and
     * its outcome, including successful saves and any exceptions encountered.
     * </p>
     *
     * @param activityResponse The {@code ActivityResponse} object containing data from the frontend.
     * @return int The number of records updated (1 for success, 0 for failure).
     */
    public int saveOrUpdateActivity(ActivityResponse activityResponse) {
        try {
            // Convert ActivityResponse to Activity entity using ActivityHelper
            Activity activity = ActivityHelper.toActivity(activityResponse);

            // Execute the operation with retries
            return retryTemplate.execute(retryContext -> {
                log.info("Attempt {} to save or update activity: {} category: {} price: {}",
                        retryContext.getRetryCount(),
                        activity.getActivity(), activity.getCategory(), activity.getPrice());

                activityService.saveActivity(activity);
                log.info("Activity saved or updated successfully.");
                return 1;  // Return 1 for success
            });
        } catch (JDBCConnectionException | JpaSystemException | TransactionException | DataAccessResourceFailureException ex) {
            log.error("Data access or transaction failure while saving or updating activity.", ex);
            throw ex;
        } catch (Exception ex) {
            log.error("An unexpected error occurred while saving or updating activity.", ex);
            throw new PersistenceException("An exception occurred while upserting a record.", ex.getMessage());
        }
    }

    /**
     * Partially updates an activity by interacting with the {@code RevOpsActivityService}.
     *
     * <p>
     * This method attempts to partially update the provided activity data. Only the fields that are present
     * in the {@code updates} map will be modified. In case of transient failures, retries are handled by the
     * {@code RetryTemplate}. Logging is performed to record each attempt and its outcome, including successful
     * updates and any exceptions encountered.
     * </p>
     *
     * @param id The ID of the activity to update.
     * @param updates A map of field names and values to update.
     * @return int The number of records updated (1 for success, 0 for failure).
     */
    public int partialUpdateActivity(UUID id, Map<String, Object> updates) {
        try {
            // Fetch the existing activity
            Optional<Activity> existingActivityOpt = retryTemplate.execute(retryContext -> {
                log.info("Attempt {} to fetch activity for partial update with ID: {}", retryContext.getRetryCount(), id);
                return activityRepository.findById(id);
            });

            if (existingActivityOpt.isEmpty()) {
                log.warn("Activity with ID: {} not found for partial update.", id);
                return 0;  // Activity not found
            }

            Activity existingActivity = existingActivityOpt.get();
            // Apply the updates using ActivityHelper or direct modification
            ActivityHelper.applyUpdates(existingActivity, updates);

            // Execute the update operation with retries
            return retryTemplate.execute(retryContext -> {
                log.info("Attempt {} to partially update activity with ID: {}", retryContext.getRetryCount(), id);

                activityService.saveActivity(existingActivity);
                log.info("Activity partially updated successfully.");
                return 1;  // Return 1 for success
            });
        } catch (JDBCConnectionException | JpaSystemException | TransactionException | DataAccessResourceFailureException ex) {
            log.error("Data access or transaction failure while partially updating activity with ID: {}", id, ex);
            throw ex;
        } catch (Exception ex) {
            log.error("An unexpected error occurred while partially updating activity with ID: {}", id, ex);
            throw new PersistenceException("An exception occurred while partially updating a record.", ex.getMessage());
        }
    }

    /**
     * Fetches all distinct activity categories by interacting with the {@code RevOpsActivityService}.
     *
     * <p>
     * This method fetches a list of distinct activity categories from the database. In case of
     * transient failures, retries are handled by the {@code RetryTemplate}. Logging is performed
     * to record each attempt and its outcome, including successful fetches and any exceptions encountered.
     * </p>
     *
     * @return List of distinct categories as {@code List<String>}.
     */
    public List<String> fetchAllCategories() {
        try {
            // Execute the operation with retries
            return retryTemplate.execute(retryContext -> {
                log.info("Attempt {} to fetch all activity categories", retryContext.getRetryCount());

                List<String> categories = activityService.fetchAllCategories();
                log.info("Fetched {} categories successfully.", categories.size());
                return categories;
            });
        } catch (JDBCConnectionException | JpaSystemException | TransactionException | DataAccessResourceFailureException ex) {
            log.error("Data access or transaction failure while fetching activity categories.", ex);
            throw ex;
        } catch (Exception ex) {
            log.error("An unexpected error occurred while fetching activity categories.", ex);
            throw new PersistenceException("An exception occurred while fetching categories.", ex.getMessage());
        }
    }
}

package edu.csudh.lsu.revops.controller;

import edu.csudh.lsu.revops.model.ActivityResponse;
import edu.csudh.lsu.revops.service.RevOpsActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * The {@code ActivityController} class handles incoming HTTP requests related to activities.
 * </p>
 *
 * <p>
 * This class acts as the entry point for requests related to creating, updating, deleting, and retrieving activity data.
 * It interacts with the {@code RevOpsActivityService} to process the business logic and return appropriate responses.
 * All relevant logging is performed for audit and monitoring purposes.
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
@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private RevOpsActivityService revOpsActivityService;

    /**
     * Endpoint to create a new activity.
     *
     * <p>
     * This method accepts an {@code ActivityResponse} object, which is then passed to the {@code RevOpsActivityService}
     * to save the activity in the database. The result of the operation is returned as an HTTP response.
     * </p>
     *
     * @param activityResponse The {@code ActivityResponse} object containing activity data from the client.
     * @return {@code ResponseEntity<String>} A response entity with a message indicating the result of the operation.
     */
    @PostMapping("/create")
    public ResponseEntity<String> createActivity(@RequestBody ActivityResponse activityResponse) {
        try {
            int result = revOpsActivityService.createActivity(activityResponse);
            if (result > 0) {
                log.info("Activity successfully created.");
                return ResponseEntity.ok("Activity created successfully.");
            } else {
                log.warn("Failed to create activity.");
                return ResponseEntity.status(500).body("Failed to create activity.");
            }
        } catch (Exception ex) {
            log.error("Error occurred while creating activity: {}", ex.getMessage(), ex);
            return ResponseEntity.status(500).body("An error occurred while creating the activity.");
        }
    }

    /**
     * Endpoint to update an existing activity.
     *
     * <p>
     * This method accepts an {@code ActivityResponse} object and an activity ID. The data is passed to the
     * {@code RevOpsActivityService} to update the activity in the database. The result of the operation is returned
     * as an HTTP response.
     * </p>
     *
     * @param id The ID of the activity to update.
     * @param activityResponse The {@code ActivityResponse} object containing updated activity data from the client.
     * @return {@code ResponseEntity<String>} A response entity with a message indicating the result of the operation.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateActivity(@PathVariable UUID id, @RequestBody ActivityResponse activityResponse) {
        try {
            int result = revOpsActivityService.updateActivity(activityResponse);  // Updated to call update method
            if (result > 0) {
                log.info("Activity successfully updated.");
                return ResponseEntity.ok("Activity updated successfully.");
            } else {
                log.warn("Failed to update activity.");
                return ResponseEntity.status(500).body("Failed to update activity.");
            }
        } catch (Exception ex) {
            log.error("Error occurred while updating activity: {}", ex.getMessage(), ex);
            return ResponseEntity.status(500).body("An error occurred while updating the activity.");
        }
    }

    /**
     * Endpoint to partially update an existing activity.
     *
     * <p>
     * This method accepts a map of fields to update and an activity ID. The data is passed to the
     * {@code RevOpsActivityService} to apply the partial updates to the activity in the database. The result of the operation
     * is returned as an HTTP response.
     * </p>
     *
     * @param id The ID of the activity to update.
     * @param updates A map of field names and values to update.
     * @return {@code ResponseEntity<String>} A response entity with a message indicating the result of the operation.
     */
    @PatchMapping("/partial-update/{id}")
    public ResponseEntity<String> partialUpdateActivity(@PathVariable UUID id, @RequestBody Map<String, Object> updates) {
        try {
            int result = revOpsActivityService.partialUpdateActivity(id, updates);
            if (result > 0) {
                log.info("Activity successfully updated.");
                return ResponseEntity.ok("Activity partially updated successfully.");
            } else {
                log.warn("Failed to partially update activity.");
                return ResponseEntity.status(500).body("Failed to partially update activity.");
            }
        } catch (Exception ex) {
            log.error("Error occurred while partially updating activity: {}", ex.getMessage(), ex);
            return ResponseEntity.status(500).body("An error occurred while partially updating the activity.");
        }
    }

    /**
     * Endpoint to delete an activity by ID.
     *
     * <p>
     * This method deletes the activity by its ID in the database. The result of the operation is returned as an HTTP response.
     * </p>
     *
     * @param id The ID of the activity to delete.
     * @return {@code ResponseEntity<String>} A response entity with a message indicating the result of the operation.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteActivity(@PathVariable UUID id) {
        try {
            int result = revOpsActivityService.deleteActivity(id);
            if (result > 0) {
                log.info("Activity successfully deleted.");
                return ResponseEntity.ok("Activity deleted successfully.");
            } else {
                log.warn("Failed to delete activity.");
                return ResponseEntity.status(500).body("Failed to delete activity.");
            }
        } catch (Exception ex) {
            log.error("Error occurred while deleting activity: {}", ex.getMessage(), ex);
            return ResponseEntity.status(500).body("An error occurred while deleting the activity.");
        }
    }

    /**
     * Endpoint to fetch all distinct activity categories.
     *
     * <p>
     * This method interacts with the {@code RevOpsActivityService} to retrieve a list of distinct categories from the database.
     * The result is returned as an HTTP response with a list of categories.
     * </p>
     *
     * @return {@code ResponseEntity<List<String>>} A response entity containing the list of categories.
     */
    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories() {
        try {
            List<String> categories = revOpsActivityService.fetchAllCategories();
            return ResponseEntity.ok(categories);
        } catch (Exception ex) {
            log.error("Error occurred while fetching categories: {}", ex.getMessage(), ex);
            return ResponseEntity.status(500).build();
        }
    }
}

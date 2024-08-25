package edu.csudh.lsu.revops.utils;

import edu.csudh.lsu.persistence.model.activity.Activity;
import edu.csudh.lsu.revops.model.ActivityResponse;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Map;

/**
 * <p>
 * The {@code ActivityHelper} class is a utility class designed to encapsulate the properties of an {@code Activity} entity.
 * </p>
 *
 * <p>
 * This helper class acts as a data transfer object (DTO) that simplifies passing and handling activity-related data
 * in different layers of the application. It is built using the {@code @Builder} pattern, which provides a clean
 * and flexible way to instantiate objects.
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
@Getter
@Builder
public class ActivityHelper {

    private String activity;
    private String category;
    private Integer price;
    private String imageLocation;
    private Timestamp createdTime;
    private LocalDate createdDate;
    private String lastUpdatedBy;
    private String accessedBy;

    /**
     * Static factory method to create an {@code ActivityHelper} instance from an {@code Activity} entity.
     *
     * <p>
     * This method extracts all necessary fields from the provided {@code Activity} object and
     * builds an {@code ActivityHelper} instance, simplifying the process of passing data across layers.
     * It uses the {@code DateUtil} class to handle any date or time-related operations.
     * </p>
     *
     * @param activity The {@code Activity} entity from which to extract data.
     * @return {@code ActivityHelper} The created helper instance containing extracted data.
     */
    public static ActivityHelper fromActivity(Activity activity) {
        // Convert Date to LocalDate using DateUtil
        LocalDate createdDate = DateUtil.convertDateToLocalDate(activity.getCreatedDate());

        // Convert Time to Timestamp using DateUtil
        Timestamp createdTime = DateUtil.convertTimeToTimestamp(activity.getCreatedTime());

        return ActivityHelper.builder()
                .activity(activity.getActivity())
                .category(activity.getCategory())
                .price(activity.getPrice())
                .imageLocation(activity.getImageLocation())
                .createdTime(createdTime)
                .createdDate(createdDate)
                .lastUpdatedBy(activity.getLastUpdatedBy())
                .accessedBy(activity.getAccessedBy())
                .build();
    }

    /**
     * Converts an {@code ActivityResponse} object to an {@code Activity} entity.
     *
     * @param response The {@code ActivityResponse} DTO from the frontend.
     * @return {@code Activity} The created entity to be used by the service layer.
     */
    public static Activity toActivity(ActivityResponse response) {
        Activity activity = new Activity();
        activity.setActivity(response.getActivity());
        activity.setCategory(response.getCategory());
        activity.setPrice(response.getPrice());
        activity.setImageLocation(response.getImageLocation());
        // You may also want to set additional fields like ID, timestamps, etc. if required.
        return activity;
    }

    /**
     * Parses a date string to create a {@code Timestamp} for the {@code createdTime} field using {@code DateUtil}.
     *
     * @param dateString The date string to parse.
     * @return {@code ActivityHelper} instance with parsed {@code Timestamp}.
     */
    public static ActivityHelper fromActivityWithTimestamp(Activity activity, String dateString) {
        Timestamp timestamp = DateUtil.parseTimestamp(dateString);

        // Convert Date to LocalDate using DateUtil
        LocalDate createdDate = DateUtil.convertDateToLocalDate(activity.getCreatedDate());

        return ActivityHelper.builder()
                .activity(activity.getActivity())
                .category(activity.getCategory())
                .price(activity.getPrice())
                .imageLocation(activity.getImageLocation())
                .createdTime(timestamp)
                .createdDate(createdDate)
                .lastUpdatedBy(activity.getLastUpdatedBy())
                .accessedBy(activity.getAccessedBy())
                .build();
    }

    /**
     * Applies partial updates to the provided {@code Activity} entity.
     *
     * @param activity The existing {@code Activity} entity to update.
     * @param updates A map of field names and values to update.
     */
    public static void applyUpdates(Activity activity, Map<String, Object> updates) {
        // Example logic for applying updates to the Activity entity
        updates.forEach((field, value) -> {
            switch (field) {
                case "activity":
                    activity.setActivity((String) value);
                    break;
                case "category":
                    activity.setCategory((String) value);
                    break;
                case "price":
                    activity.setPrice((Integer) value);
                    break;
                case "imageLocation":
                    activity.setImageLocation((String) value);
                    break;
                // Add more cases for other fields that can be updated
                default:
                    throw new IllegalArgumentException("Unknown field: " + field);
            }
        });
    }
}

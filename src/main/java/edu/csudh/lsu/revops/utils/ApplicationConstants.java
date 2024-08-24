package edu.csudh.lsu.revops.utils;

/**
 * <p>
 * {@code ApplicationConstants} is a utility class that defines and maintains constant values
 * used throughout the RevOps application.
 * </p>
 *
 * <p>
 * This class follows the utility class design pattern, which means it should not be instantiated.
 * To enforce this, the constructor is private and throws an {@link IllegalStateException}
 * if called, preventing instantiation.
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
public class ApplicationConstants {

    /**
     * Private constructor for {@link ApplicationConstants}.
     *
     * <p>
     * This constructor is private to prevent instantiation of this utility class.
     * Attempting to instantiate this class will result in an {@link IllegalStateException}
     * with the message "Utility Class".
     * </p>
     *
     * @throws IllegalStateException if the constructor is called
     */
    private ApplicationConstants() {
        throw new IllegalStateException("Utility Class");
    }

    public static final String DATA_SOURCE = "data-source";
    public static final String DB_CONNECTION = "DB Connection";
    public static final String MESSAGE = "message";
    public static final String FAILED = "FAILED";
    public static final String REGISTERED = "REGISTERED";
    public static final String PROCESSING = "PROCESSING";
    public static final String REV_OPS_APP = "REV-OPS-APP";
    public static final String FAILED_MANUAL = "FAILED_MANUAL";
    public static final String STATUS = "STATUS";
    public static final String FAILURE_REASON = "FAILURE_REASON";
    public static final String PROCESSED = "PROCESSED";
    public static final String DISABLE = "DISABLE";
    public static final String ENABLE = "ENABLE";
    public static final String IGNORED = "IGNORED";
    public static final String THE_RECORD_IS_DUPLICATE = "The record is duplicate";

}

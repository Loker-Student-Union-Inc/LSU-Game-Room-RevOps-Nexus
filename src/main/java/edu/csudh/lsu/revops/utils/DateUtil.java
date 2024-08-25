package edu.csudh.lsu.revops.utils;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * <p>
 * The {@code DateUtil} class provides utility methods for working with dates and timestamps.
 * </p>
 *
 * <p>
 * This utility class includes methods for checking whether a given date is in the past or future,
 * parsing string representations of dates into {@link Date} or {@link Timestamp}, and validating date formats.
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
public class DateUtil {

    // Constant for the standard date format "yyyy-MM-dd HH:mm:ss"
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    // Private constructor to prevent instantiation of this utility class
    private DateUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Checks whether a given {@link Date} is in the past.
     *
     * <p>
     * This method compares the given date to the current date and returns {@code true} if the given date is
     * before or equal to the current date. If an exception occurs during the comparison, it returns {@code false}.
     * </p>
     *
     * @param date the date to check
     * @return {@code true} if the date is in the past, {@code false} otherwise
     */
    public static boolean isPastDate(Date date) {
        try {
            Date current = new Date();
            return date.before(current) || date.equals(current);
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * Checks whether a given date string represents a future date.
     *
     * <p>
     * This method parses the date string using the standard format "yyyy-MM-dd HH:mm:ss" and checks if it represents
     * a future date. If the parsed date is {@code null}, it assumes the date is in the future.
     * </p>
     *
     * @param dateString the date string to check
     * @return {@code true} if the date string represents a future date, {@code false} otherwise
     */
    public static boolean isFutureDate(String dateString) {
        Date date = parseDate(YYYY_MM_DD_HH_MM_SS, dateString);
        if (null == date) {
            return true;
        }
        return !isPastDate(date);
    }

    /**
     * Parses a date string using the given format and returns a {@link Date} object.
     *
     * <p>
     * This method attempts to parse the given date string using the specified format. If the parsing fails,
     * it returns {@code null}. This method can also be used to validate if the date string is in the correct format.
     * </p>
     *
     * @param format the date format to use for parsing
     * @param dateString the date string to parse
     * @return the parsed {@link Date}, or {@code null} if the date string is not in the given format
     */
    public static Date parseDate(String format, String dateString) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            return simpleDateFormat.parse(dateString);
        } catch (Exception exception) {
            return null;
        }
    }

    /**
     * Parses a date string in the format "yyyy-MM-dd HH:mm:ss" and returns a {@link Timestamp}.
     *
     * <p>
     * This method parses the given date string using the standard format "yyyy-MM-dd HH:mm:ss" and converts it
     * to a {@link Timestamp}. If the parsing fails, it returns {@code null}.
     * </p>
     *
     * @param dateString the date string to parse
     * @return the parsed {@link Timestamp}, or {@code null} if the parsing fails
     */
    public static Timestamp parseTimestamp(String dateString) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
            Date date = simpleDateFormat.parse(dateString);
            return Timestamp.from(date.toInstant());
        } catch (Exception exception) {
            return null;
        }
    }

    /**
     * Converts {@link Time} to {@link Timestamp}.
     *
     * @param time the {@link Time} object to convert
     * @return the corresponding {@link Timestamp}, or {@code null} if the input is null
     */
    public static Timestamp convertTimeToTimestamp(Time time) {
        return time != null ? new Timestamp(time.getTime()) : null;
    }

    /**
     * Converts {@link Date} to {@link LocalDate}.
     *
     * @param date the {@link Date} object to convert
     * @return the corresponding {@link LocalDate}, or {@code null} if the input is null
     */
    public static LocalDate convertDateToLocalDate(Date date) {
        return date != null ? date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
    }

}

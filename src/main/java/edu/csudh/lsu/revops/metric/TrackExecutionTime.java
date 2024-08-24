package edu.csudh.lsu.revops.metric;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * The {@code TrackExecutionTime} annotation is used to mark methods for which the execution time should be tracked.
 * </p>
 *
 * <p>
 * Methods annotated with this annotation will have their execution time logged by the {@link ExecutionTime} aspect.
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
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TrackExecutionTime {
}

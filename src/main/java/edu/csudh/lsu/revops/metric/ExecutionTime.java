package edu.csudh.lsu.revops.metric;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * <p>
 * The {@code ExecutionTime} class is an aspect that tracks the execution time of methods
 * annotated with {@link TrackExecutionTime}. It logs the time taken for method execution
 * in milliseconds.
 * </p>
 *
 * <p>
 * This class uses Spring AOP to intercept method executions and calculate the time difference
 * between the start and end of the method execution. It logs this information for performance monitoring.
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
@Aspect
@Slf4j
public class ExecutionTime {

    /**
     * Tracks the execution time of methods annotated with {@code TrackExecutionTime}.
     *
     * <p>
     * This method uses the {@code @Around} advice to intercept method executions. It records
     * the start and end time in milliseconds, calculates the time taken, and logs the information.
     * </p>
     *
     * @param point the join point representing the method being intercepted
     * @return the result of the method execution
     * @throws Throwable if the method execution throws an exception
     */
    @Around("@annotation(edu.csudh.lsu.revops.metric.TrackExecutionTime)")
    public Object trackExecutionTime(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis(); // Record start time
        Object object = point.proceed(); // Proceed with method execution
        long endTime = System.currentTimeMillis(); // Record end time
        // Log the method name, declaring type, and execution time
        log.info(point.getSignature().getDeclaringTypeName() + " -> " +
                point.getSignature().getName() + " Execution Time : [ " +
                (endTime - startTime) + " ms ]");
        return object; // Return the result of the method execution
    }
}

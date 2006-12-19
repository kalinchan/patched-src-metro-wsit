/*
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License).  You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the license at
 * https://glassfish.dev.java.net/public/CDDLv1.0.html.
 * See the License for the specific language governing
 * permissions and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL
 * Header Notice in each file and include the License file
 * at https://glassfish.dev.java.net/public/CDDLv1.0.html.
 * If applicable, add the following below the CDDL Header,
 * with the fields enclosed by brackets [] replaced by
 * you own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Copyright 2006 Sun Microsystems Inc. All Rights Reserved
 */

package com.sun.xml.ws.tx.common;

import java.lang.reflect.Field;
import java.util.logging.Level;

/**
 * This is a helper class that provides some convenience methods wrapped around the
 * standard {@link java.util.logging.Logger} interface.
 * <p/>
 * Logging domains:
 * wstx         // general for all web service transactions
 * wstx.wscoord // for ws-coordination logging
 * wstx.at      // for ws-atomic transaction logging.
 *
 * @see #getLogger(Class)
 * @see #getATLogger(Class)
 * @see #getCoordLogger(Class)
 */
public final class TxLogger {
    /**
     * If we run with JAX-WS, we are using its logging domain (appended with ".wstx").
     * Otherwise we default to "wstx".
     */
    private final static String LOGGING_SUBSYSTEM_NAME;

    static {
        String loggingSubsystemName = "wstx";
        try {
            // Looking up JAX-WS class at run-time, so that we don't need to depend
            // on it at compile-time.
            Class jaxwsConstants = Class.forName("com.sun.xml.ws.util.Constants");
            Field loggingDomainField = jaxwsConstants.getField("LoggingDomain");
            Object loggingDomain = loggingDomainField.get(null);
            loggingSubsystemName = loggingDomain.toString().concat(".wstx");
        } catch (Exception e) {
            // If we don't manage to extract the logging domain from JAX-WS, we
            // fall back to a default.
        } finally {
            LOGGING_SUBSYSTEM_NAME = loggingSubsystemName;
        }
    }

    private final String componentClassName;
    private final java.util.logging.Logger logger;

    /**
     * Prevents creation of a new instance of this TxLogger
     */
    private TxLogger(String componentName) {
        this.componentClassName = "[" + componentName + "] ";
        this.logger = java.util.logging.Logger.getLogger(LOGGING_SUBSYSTEM_NAME);
    }

    private TxLogger(String componentName, String subsystem) {
        this.componentClassName = "[" + componentName + "] ";

        this.logger = java.util.logging.Logger.getLogger(LOGGING_SUBSYSTEM_NAME + subsystem);
    }

    /**
     * The factory method returns preconfigured TxLogger wrapper for the class. Since there is no caching implemented,
     * it is advised that the method is called only once per a class in order to initialize a final static logger variable,
     * which is then used through the class to perform actual logging tasks.
     *
     * @param componentClass class of the component that will use the logger instance. Must not be {@code null}.
     * @return logger instance preconfigured for use with the component
     * @throws NullPointerException if the componentClass parameter is {@code null}.
     */
    public static TxLogger getLogger(final Class componentClass) {
        return new TxLogger(componentClass.getName());
    }

    /**
     * Logging specifically for *.wstx.wsat subsystem.
     */
    public static TxLogger getATLogger(final Class componentClass) {
        return new TxLogger(componentClass.getName(), ".wsat");
    }

    /**
     * Logging specifically for *.wstx.wscoord subsystem.
     */
    public static TxLogger getCoordLogger(final Class componentClass) {
        return new TxLogger(componentClass.getName(), ".wscoord");
    }

    public void log(final Level level, final String methodName, final String message) {
        logger.logp(level, componentClassName, methodName, message);
    }

    public void log(final Level level, final String methodName, final String message, final Throwable thrown) {
        logger.logp(level, componentClassName, methodName, message, thrown);
    }

    public void finest(final String methodName, final String message) {
        logger.logp(Level.FINEST, componentClassName, methodName, message);
    }

    public void finest(final String methodName, final String message, final Throwable thrown) {
        logger.logp(Level.FINEST, componentClassName, methodName, message, thrown);
    }

    public void finer(final String methodName, final String message) {
        logger.logp(Level.FINER, componentClassName, methodName, message);
    }

    public void finer(final String methodName, final String message, final Throwable thrown) {
        logger.logp(Level.FINER, componentClassName, methodName, message, thrown);
    }

    public void fine(final String methodName, final String message) {
        logger.logp(Level.FINE, componentClassName, methodName, message);
    }

    public void fine(final String methodName, final String message, final Throwable thrown) {
        logger.logp(Level.FINE, componentClassName, methodName, message, thrown);
    }

    public void info(final String methodName, final String message) {
        logger.logp(Level.INFO, componentClassName, methodName, message);
    }

    public void info(final String methodName, final String message, final Throwable thrown) {
        logger.logp(Level.INFO, componentClassName, methodName, message, thrown);
    }

    public void config(final String methodName, final String message) {
        logger.logp(Level.CONFIG, componentClassName, methodName, message);
    }

    public void config(final String methodName, final String message, final Throwable thrown) {
        logger.logp(Level.CONFIG, componentClassName, methodName, message, thrown);
    }

    public void warning(final String methodName, final String message) {
        logger.logp(Level.WARNING, componentClassName, methodName, message);
    }

    public void warning(final String methodName, final String message, final Throwable thrown) {
        logger.logp(Level.WARNING, componentClassName, methodName, message, thrown);
    }

    public void severe(final String methodName, final String message) {
        logger.logp(Level.SEVERE, componentClassName, methodName, message);
    }

    public void severe(final String methodName, final String message, final Throwable thrown) {
        logger.logp(Level.SEVERE, componentClassName, methodName, message, thrown);
    }

    public void entering(final String methodName) {
        logger.entering(componentClassName, methodName);
    }

    public void entering(final String methodName, final Object parameter) {
        logger.entering(componentClassName, methodName, parameter);
    }

    public void entering(final String methodName, final Object[] parameters) {
        logger.entering(componentClassName, methodName, parameters);
    }

    public void exiting(final String methodName) {
        logger.exiting(componentClassName, methodName);
    }

    public void exiting(final String methodName, final Object result) {
        logger.exiting(componentClassName, methodName, result);
    }

    public boolean isLogging(final Level level) {
        return logger.isLoggable(level);
    }
}

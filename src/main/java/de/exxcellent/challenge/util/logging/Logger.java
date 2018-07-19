package de.exxcellent.challenge.util.logging;

/**
 * Main user entry point of the logging API. Concrete implementations of this class will handle different logging requirements.
 */
public interface Logger {

    /**
     * Log a message as INFO message.
     *
     * @param msg The message to log.
     */
    void info(String msg);

    /**
     * Log a message as WARN message.
     *
     * @param msg The message to log.
     */
    void warn(String msg);

    /**
     * Log a message as ERROR message.
     *
     * @param msg The message to log.
     */
    void error(String msg);
}

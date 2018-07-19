package de.exxcellent.challenge.util.logging;

/**
 * A factory for creating {@link Logger} instances.
 */
public final class LoggerFactory {

    /**
     * Returns a logger named corresponding to the class passed as parameter,
     *
     * @param clazz The returned logger will be named after clazz.
     * @return A {@link Logger} instance.
     */
    public static Logger getLogger(Class<?> clazz) {
        return new ConsoleLogger(clazz);
    }
}

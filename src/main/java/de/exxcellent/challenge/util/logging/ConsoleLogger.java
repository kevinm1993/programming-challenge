package de.exxcellent.challenge.util.logging;

/**
 * A concrete logging implementation that simply writes to stdout.
 */
public class ConsoleLogger implements Logger {

    private final String name;

    ConsoleLogger(Class<?> clazz) {
        this.name = clazz.getSimpleName();
    }

    @Override
    public void info(String msg) {
        print("INFO", msg);
    }

    @Override
    public void warn(String msg) {
        print("WARN", msg);
    }

    @Override
    public void error(String msg) {
        print("ERROR", msg);
    }

    private void print(String prefix, String msg) {
        System.out.println(String.format("%-15s - %-5s: %s", name, prefix, msg));
    }
}

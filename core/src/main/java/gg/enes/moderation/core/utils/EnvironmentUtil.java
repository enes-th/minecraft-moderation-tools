package gg.enes.moderation.core.utils;

public final class EnvironmentUtil {
    private EnvironmentUtil() {
    }

    /**
     * Checks if the current environment is a test environment.
     *
     * @return true if the current environment is a test environment, false otherwise.
     */
    public static boolean isTestEnvironment() {
        try {
            Class.forName("org.junit.Test");
            return true;
        } catch (ClassNotFoundException ignored) {
        }

        return false;
    }

    /**
     * Checks if the current environment is a development environment.
     *
     * @return true if the current environment is a development environment, false otherwise.
     */
    public static boolean isDevelopmentEnvironment() {
        return System.getProperty("development") != null;
    }
}

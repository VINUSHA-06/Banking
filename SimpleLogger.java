public class SimpleLogger {

    // Instance methods (non-static)
    public void log(String message) {
        System.out.println(message);
    }

    public void logInfo(String message) {
        System.out.println("[INFO] " + message);
    }

    public void logError(String message) {
        System.out.println("[ERROR] " + message);
    }

    public void logSuccess(String message) {
        System.out.println("[SUCCESS] " + message);
    }
}

package observer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LogObserver {
    private static LogObserver instance = new LogObserver();
    private List<String> logs = new ArrayList<>();

    private LogObserver() {}

    public static LogObserver getInstance() {
        return instance;
    }

    public void log(String message) {
        logs.add(LocalDateTime.now().toString() + " - " + message);
    }

    public List<String> getLogs() {
        return logs;
    }

    public void printLogs() {
        logs.forEach(System.out::println);
    }
}

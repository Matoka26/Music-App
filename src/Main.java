import view.ConsoleApp;

public abstract class Main {
    public static void main(String[] args) {
        ConsoleApp consoleApp = ConsoleApp.getInstance();
        consoleApp.startMenu();
    }
}


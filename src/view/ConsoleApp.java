package view;

import java.util.Map;
import java.util.Scanner;

public class ConsoleApp {
    private static ConsoleApp instance = null;
    private final ArtistConsoleApp artistApp;
    private final ListenerConsoleApp listenerApp;

    private ConsoleApp(){
        artistApp = ArtistConsoleApp.getInstance();
        listenerApp = ListenerConsoleApp.getInstance();
    }

    public static ConsoleApp getInstance(){
        if(instance == null){
            instance = new ConsoleApp();
        }
        return instance;
    }

    public void startMenu(){
        Scanner scanner = new Scanner(System.in);
        int option;

        System.out.println("Welcome to my awesome Music App\n" +
                            "1.LogIn\n" +
                            "2.SignUp\n" +
                            "3.Exit\n");
        option = scanner.nextInt();
        scanner.nextLine();
        switch (option){
            case 1 -> login();
            case 2 -> signup();
            case 3 -> {}
            default -> {
                System.out.println("Invalid option");
                startMenu();
            }
        }
    }

    private void signup(){
        Scanner scanner = new Scanner(System.in);
        int option;

        System.out.println("Choose a type of account\n" +
                            "1.Artist account\n" +
                            "2.Listener account\n" +
                            "3.Exit\n" +
                            "4.End\n");

        option = scanner.nextInt();
        scanner.nextLine();
        switch (option){
            case 1 -> {
                artistApp.createArtist();
                System.out.println("Account created successfully\n");
                login();
            }
            case 2 -> {
                listenerApp.createListener();
                System.out.println("Account created successfully\n");
                login();
            }
            case 3 -> startMenu();
            case 4 -> {}
            default -> {
                System.out.println("Invalid option");
                signup();
            }
        }
    }

    private void login(){
        Scanner scanner = new Scanner(System.in);
        String username, password;

        System.out.println("***FANCY LOGIN SCREEN***");
        System.out.println("Username: ");
        username = scanner.nextLine().trim();
        System.out.println("Password: ");
        password = scanner.nextLine().trim();

        Map.Entry<Integer,Integer> ids = null;

        ids = artistApp.validateLogin(username, password);
        if(ids != null) {
            artistApp.setArtist_id(ids.getValue());
            artistApp.startMenu();
            return;
        }

        ids = listenerApp.validateLogin(username, password);
        if(ids != null) {
            listenerApp.setListener_id(ids.getValue());
            listenerApp.startMenu();
            return;
        }

        System.out.println("Invalid username or password\n");
        startMenu();
    }
}

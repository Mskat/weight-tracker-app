package com.zur.tracker.view;

import com.zur.tracker.model.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WeightTrackerLauncher {
    private Scanner sc = new Scanner(System.in);

    //method showing a menu list with three options of choose:
    //add user, view saved weight and close the program
    public void showMenu() throws IOException {
        String chosenNumber;
        do {
            System.out.println("\n######## MENU ########");
            System.out.println("1. Add user");
            System.out.println("2. View saved weight");
            System.out.println("3. Close program");
            System.out.println("\nChoose the number:");

            chosenNumber = getUserInput();

            switch (chosenNumber) {
                case "1":
                    saveUserData();
                    break;
                case "2":
                    showUser();
                    break;
                case "3":
                    break;
                default:
                    System.out.println("Choose the number from the list. Press ENTER to continue.");
                    sc.nextLine();
            }
        } while (!chosenNumber.equals("3"));
    }

    //method saving given data to a text file
    private void saveDataToFile(User user) throws IOException {
        String filename = user.getName();
//         =======>>>>>>>> !!!!!!!!!!!!! you should define "path" before running the program  !!!!!!!!!!!!! <<<<<<<<=======
        String path = "";
        String filePath = path + filename + ".txt";
        //FileWriter fileWriter = null;
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(filePath, true))) {
            bfw.write(user.toString());
            bfw.write(System.getProperty("line.separator"));
        } catch (NullPointerException | FileNotFoundException npe) {
            System.out.println("Something is wrong!\n" +
                    "Saving the file is not complete. Check the \"path\".");
            showMenu();
        }
        System.out.println("Your weight has been saved in a file called " + filename + ", press ENTER to continue.");
        sc.nextLine();
    }

    //method reading data from a text file
    private void showUser() {
//         =======>>>>>>>> !!!!!!!!!!!!! you should define "path" before running the program  !!!!!!!!!!!!! <<<<<<<<=======
        String path = "";
        int pathLength = path.length();
        int userChoice = 0;
        try (Stream<Path> walk = Files.walk(Paths.get(path))) {
            List<String> result = walk.map(x -> x.toString()).filter(f -> f.endsWith(".txt")).collect(Collectors.toList());
            System.out.println("Which user you want to look through? Choose number:");
            for (String s : result) {
                String user = s.substring(pathLength, s.length() - 4);
                System.out.println((result.indexOf(s) + 1) + ": " + user);
            }
            do {
                System.out.println("Choose number from list!");
                try {
                    userChoice = Integer.parseInt(getUserInput());
                } catch (Exception e) {
                    System.out.println("You didn't give a number!!");
                }
            } while (userChoice <= 0);
            String file = result.get(userChoice - 1);
            readDataFromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Press ENTER to move on.");
        sc.nextLine();
    }

    private void readDataFromFile(String path) {
        try (BufferedReader bfr = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bfr.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't find file " + path);
        } catch (IOException e) {
            System.out.println("Unable to read file " + path);
        }
    }

    //method of saving the user's name, weight and weighing date
    private void saveUserData() throws IOException {
        User user = new User();
        //saving user's name
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        String inputName;
        do {
            System.out.println("\nGive me your name: ");
            inputName = getUserInput();
            if (pattern.matcher(inputName).matches()) {
                user.setName(inputName);
            } else {
                System.out.println("You do not give your name, press ENTER and try again.");
                sc.nextLine();
            }
        } while (user.getName() == null);

        //saving user's weight
        Pattern patternWeight = Pattern.compile("\\d?\\d?\\d\\.?\\d?\\d");
        String inputWeight;
        do {
            System.out.println("Give me your weight in \"x.x\" format: ");
            inputWeight = getUserInput();
            if (patternWeight.matcher(inputWeight).matches()) {
                user.setWeight(Float.parseFloat(inputWeight));
            } else {
                System.out.println("Give me your weight in \"x.x\" format. Sample weight: 65.49, press ENTER and try again.");
                sc.nextLine();
            }
        } while (user.getWeight() == null);

        //saving weighing date
        Pattern patternDate = Pattern.compile("(19|20)\\d\\d.(0[1-9]|1[012]).(0[1-9]|[12][0-9]|3[01])");
        String inputDate;
        LocalDate now = LocalDate.now();
        LocalDate conversedInputDate;
        do {
            System.out.println("Give me the date of weighing in format \"yyyy.MM.dd\": ");
            inputDate = getUserInput();
            if ((patternDate.matcher(inputDate)).matches()) {
                try {
                    conversedInputDate = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                    boolean isProperDate = !conversedInputDate.isAfter(now);
                    if (isProperDate) {
                        user.setDate(conversedInputDate);
                    } else {
                        System.out.println("Date must be until now. Try again!");
                    }
                } catch (DateTimeParseException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Give me date in \"yyyy.MM.dd\" format. Sample date: 2019.09.05, press ENTER and try again.");
                sc.nextLine();
            }
        } while (user.getDate() == null);
        saveDataToFile(user);
    }

    //method getting data from the user
    private String getUserInput() {
        return sc.nextLine().trim();
    }
}

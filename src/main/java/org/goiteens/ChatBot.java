package org.goiteens;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ChatBot {
    private static Map<String, Integer> professions;
    private static Map<String, Integer> dreams;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();

        initProfessions();
        initDreams();

        String botAnswer = process(message);
        System.out.println(botAnswer);
    }
    
    public static void initProfessions() {
        professions = new LinkedHashMap<>();

        professions.put("Designer", 20000);
        professions.put("Java", 55000);
        professions.put("Frontend", 40000);
    }

    public static void initDreams() {
        dreams = new LinkedHashMap<>();

        dreams.put("Машин", 260000);
        dreams.put("iPhone", 27000);
    }
    
    public static String process(String message) {
        if (isHelloMessage(message)) {
            String botName = "ChatBot";
            return "Приветствую,   я - " + botName;
        }

        int professionSalary = find(message, professions);
        int dreamCost = find(message, dreams);

        if (professionSalary < 0) {
            return "Я не нашел в твоем  сообщении названия профессии";
        }

        if (dreamCost < 0) {
            return "Я не нашел в твоем сообщении мечты, которую ты хочешь";
        }

        int monthCount = calculateMonthCount(dreamCost, professionSalary);

        return "Чтобы получить свою мечту, нужно месяцев: " + monthCount;
    }

    public static int find(String message, Map<String, Integer> data) {
        message = message.toLowerCase();

        for(String word: data.keySet()) {
            String lowerCasedWord = word.toLowerCase();

            if (message.contains(lowerCasedWord)) {
                return data.get(word);
            }
        }

        return -1;
    }
    
    public static int calculateMonthCount(int dreamCost, int professionSalary) {
        int monthCount = dreamCost / professionSalary;
        monthCount = validateMonthCount(monthCount);
        return monthCount;
    }

    public static int validateMonthCount(int monthCount) {
        if (monthCount == 0) {
            return 1;
        }

        return monthCount;
    }
    
    private static boolean isHelloMessage(String message) {
        message = message.toLowerCase();

        String helloWord1 = "привет";
        String helloWord2 = "здравствуй";

        return message.contains(helloWord1) || message.contains(helloWord2);
    }
}

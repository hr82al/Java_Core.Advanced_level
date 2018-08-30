package ru.geekbrains.java2.lesson_3;

import java.util.*;

public class LessonCollections {
    public static void main(String[] args) {
        //1. Counting unique words.
        String[] words = {
                "пихта",
                "ясень",
                "пихта",
                "пихта",
                "клён",
                "лиственница",
                "пихта",
                "берёза",
                "ива",
                "сосна",
                "ель",
                "ель",
                "сосна",
                "дуб",
                "клён",
                "ива",
                "тополь",
                "топль",
                "ясень",
                "пихта",
                "сосна"
        };

        System.out.println("Слово : количество таких слов в списке:");
        countUnique(words);

        //2.Phonebook

        Phonebook phonebook = new Phonebook();
        phonebook.add("Иванов", "+7 953 123-45-67");
        phonebook.add("Иванов", "+7 953 123-45-68");
        phonebook.add("Иванов", "+7 953 123-45-69");
        phonebook.add("Иванов", "+7 953 123-45-70");
        phonebook.add("Петров", "+7 953 123-45-71");
        phonebook.add("Петров", "+7 953 123-45-71");
        phonebook.add("Петров", "+7 953 123-45-72");
        phonebook.add("Сидоров", "+7 953 123-45-72");
        phonebook.add("Сидоров", "+7 953 123-45-74");

        System.out.println("\n\nТелефонный справочник.");
        System.out.println("Фамилия : номер(а):");
        phonebook.show();
    }

    private static void countUnique(String[] words) {
        Map<String, Integer> numberOfWords = new LinkedHashMap<>();
        for (String word : words) {
            countWord(numberOfWords, word);
        }
        for (String word: numberOfWords.keySet()) {
            System.out.println(word + " : " + numberOfWords.get(word) + ".");
        }
    }

    private static void countWord(Map<String, Integer> numberOfWords, String word) {
        if (numberOfWords.containsKey(word)) {
            numberOfWords.put(word, numberOfWords.get(word) + 1);
        }
        else {
            numberOfWords.put(word,1);
        }
    }
}

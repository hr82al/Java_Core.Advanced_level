package ru.geekbrains.java2.lesson_3;

import java.sql.SQLOutput;
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
        coutUnique(words);

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

    private static void coutUnique(String[] words) {
        List<String> listWords = new ArrayList<>();
        listWords.addAll(Arrays.asList(words));
        Set<String> setWords = new LinkedHashSet<>();
        setWords.addAll(listWords);
        setWords.forEach(word ->{
            System.out.println(word + " : " +
                    Collections.frequency(listWords, word) + "."
            );
        });
    }
}

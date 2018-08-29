package ru.geekbrains.java2.lesson_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Phonebook extends HashMap<String, List<String>> {

  public void add(String lastName, String number) {
      if (this.containsKey(lastName)) {
          List<String> numbers = this.get(lastName);
          numbers.add(number);
      }
      else {
          List<String> numbers = new ArrayList<>();
          numbers.add(number);
          this.put(lastName, numbers);
      }
  }

    public void show() {
      this.keySet().stream().sorted().forEach(lastName -> {
          System.out.print(lastName + " : ");
          System.out.println(String.join(" , ", this.get(lastName)) + ".");
      });
    }
}

package ru.geekbrains.java2.lesson_1;

public class Member implements Obstacleable {
    private final int age;
    private final String name;
    public boolean finished;

    public Member(String name, int age) {
        this.age = age;
        this.name = name;
        finished = false;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString(){
        return "Имя: " + name + ", " + "возраст: " + age + " лет. ";
    }

    public boolean ablePass(Obscacle[] obscacles, int age){
        for (Obscacle i : obscacles) {
            if (i.ablePass(age))
                return false;
        }
        return true;
    }
}

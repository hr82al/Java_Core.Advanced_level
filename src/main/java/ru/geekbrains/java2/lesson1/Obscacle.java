package ru.geekbrains.java2.lesson_1;

import org.jetbrains.annotations.Contract;

public enum Obscacle {
    RUNING("Бег", 53), CLIMBING("Скалозаланье", 42), JUMPING("Прыжки", 51),
        CRAWLING("Ползаннье", 55), SWINIMNG("Плаванье", 58), BALANCING("Балансирование", 54);
    private String obstacle;
    private int age;
    Obscacle(String obstacle, int age){
        this.obstacle = obstacle;
        this.age = age;
    }

    @Contract(pure = true)
    public boolean ablePass(int age){
        return (age > 14 && age <= this.age );
    }
}

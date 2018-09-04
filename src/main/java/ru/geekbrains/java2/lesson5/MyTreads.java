package ru.geekbrains.java2.lesson5;

import java.util.Arrays;

class MyTreads {
    private static final int SIZE = 10_000_000;
    private static final int H = SIZE / 2;
    private static float[] a1;
    private static float[] a2;

    public static void main(String[] args) {
        calculateArray();
        parallelCalculateArray();
    }

    private static void parallelCalculateArray() {
        float[] arr = new float[SIZE];
        a1 = new float[H];
        a2 = new float[H];
        Arrays.fill(arr, 1f);
        long a = System.currentTimeMillis();
        long b = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, H);
        System.arraycopy(arr, H, a2, 0, H);
        System.out.println("Время разбивки массива: " + (System.currentTimeMillis() - a) + " мс.");
        Thread thread1 = new Thread(() -> {
            long a3 = System.currentTimeMillis();
            for (int i = 0; i < H; i++) {
                a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            System.out.println("Время подсчёта 1-й половины массива: " + (System.currentTimeMillis() - a3) + " мс.");
        });
        Thread thread2 = new Thread(() -> {
            long a3 = System.currentTimeMillis();
            int indx;
            for (int i = H; i < SIZE; i++) {
                indx = i - H;
                a2[indx] = (float) (a2[indx] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            System.out.println("Время подсчёта 2-й половины массива: " + (System.currentTimeMillis() - a3) + " мс.");
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a = System.currentTimeMillis();
        System.arraycopy(a1, 0, arr, 0, H);
        System.arraycopy(a2, 0, arr, H, H);
        System.out.println("Время склейки массива: " + (System.currentTimeMillis() - a) + " мс.");
        System.out.println("Время выполнения метода в 2 потока: " + (System.currentTimeMillis() - b) + " мс.");
    }

    private static void calculateArray() {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1f);
        long a = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Время выполнения метода в один поток: " + (System.currentTimeMillis() - a) + " мс.");
    }
}

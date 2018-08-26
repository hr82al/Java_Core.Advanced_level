package ru.geekbrains.java2.lesson_2;

public class SumArray {
    private static final int ARRAY_SISE = 4;
    private static final String WRONG_SIZE_ARRAY = "Неправильный размер массива!\nОдидаемый размер: 4x4.";

    public static void main(String[] args) {
        String[][] validArray = new String[][]
                {{"56", "32", "1", "32"},
                 {"12", "18", "11", "98"},
                 {"14", "14", "8", "18"},
                 {"15", "56", "15", "91"}};

        try {
            System.out.println(sum(validArray));
        } catch (MyArrayExceptions myArrayExceptions) {
            myArrayExceptions.printStackTrace();
        }
    }

    private static long sum(String[][] validArray) throws MyArrayExceptions{
        checkSizeArray(validArray);
        //TODO FIXME
        return 0;
    }

    private static void checkSizeArray(String[][] _array) throws MyArraySizeException{
        if (_array.length != ARRAY_SISE) {
            throw new MyArraySizeException(WRONG_SIZE_ARRAY);
        }
        for (String[] strings : _array) {
            if (strings.length != ARRAY_SISE) {
                throw new MyArraySizeException(WRONG_SIZE_ARRAY);
            }
        }
    }

}

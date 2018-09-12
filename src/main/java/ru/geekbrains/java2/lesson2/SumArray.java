package ru.geekbrains.java2.lesson2;

public class SumArray {
    private static final int ARRAY_SISE = 4;
    private static final String WRONG_SIZE_ARRAY = "Wrong array size!\nExpected size: 4x4.";
    private static final String WRONG_DATA_ARRAY = "Wrong data dype.";
    private static final String RESULT_CAPTION = "Сумма эелементов:";

    public static void main(String[] args) {
        String[][] validArray = new String[][]
                {{"56", "32", "1", "32"},
                        {"12", "18", "19", "98"},
                        {"14", "14", "8", "18"},
                        {"15", "56", "15", "91"}};
        String[][] wronSizeArray = new String[][]
                {{"56", "32", "1", "32"},
                        {"12", "18", "19", "98"},
                        {"15", "56", "15", "91"}};
        String[][] wronDataArray = new String[][]
                {{"56", "32", "1", "32"},
                        {"12", "NaN", "19", "98"},
                        {"14", "с", "8", "18"},
                        {"15", "56", "Не число", "91"}};
        ;

        try {
            System.out.println(sum(validArray));
            System.out.println(sum(wronSizeArray));

        } catch (MyArrayExceptions myArrayExceptions) {
            myArrayExceptions.printStackTrace();
        }
        try {
            System.out.println(sum(wronDataArray));
        } catch (MyArrayExceptions myArrayExceptions) {
            myArrayExceptions.printStackTrace();
        }
    }

    private static String sum(String[][] _array) throws MyArrayExceptions {
        checkSizeArray(_array);
        checkIntsArray(_array);
        return RESULT_CAPTION + "\n" + _sum(_array);
    }

    private static long _sum(String[][] _array) {
        long result = 0;
        for (String[] strings : _array) {
            for (String string : strings) {
                result += (long) Long.parseLong(string);
            }
        }
        return result;
    }

    private static void checkIntsArray(String[][] _array) throws MyArrayDataException {
        int row;
        int col;
        for (row = 0; row < _array.length; row++) {
            for (col = 0; col < _array[row].length; col++) {
                try {
                    Integer.parseInt(_array[row][col]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(WRONG_DATA_ARRAY +
                            "\n In row: " + (1 + row) + ", column: " + (1 + col));
                }
            }
        }
    }

    private static void checkSizeArray(String[][] _array) throws MyArraySizeException {
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

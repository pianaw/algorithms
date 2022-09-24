package ru.kpfu.task2;

import java.util.Arrays;

public class Main {

    public static int startIndexInMaxArray = 0;
    public static int endIndexInMaxArray = 0;
    public static int max = 0;

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, -3, 4, -7, 2, 8, 7, -16};
        findMaxArrayInfo(array, 0, array.length - 1);

        //Вывод результата
        int j = 0;
        int[] result = new int[endIndexInMaxArray - startIndexInMaxArray + 1];
        for (int i = startIndexInMaxArray; i <= endIndexInMaxArray; i++) {
            result[j] = array[i];
            j++;
        }
        System.out.println(Arrays.toString(result));
    }

    public static int findMaxArrayInfo(int[] array, int startIndex, int endIndex) {
        int sum;
        if (startIndex == endIndex) {
            if (array[startIndex] >= max) {
                max = array[startIndex];
                startIndexInMaxArray = startIndex;
                endIndexInMaxArray = endIndex;

            }
            sum = array[startIndex];
        } else {
            int sum1 = findMaxArrayInfo(array,startIndex + 1, endIndex);
            findMaxArrayInfo(array, startIndex, endIndex - 1);

            sum = sum1 + array[startIndex];

            if (sum >= max) {
                max = sum;
                startIndexInMaxArray = startIndex;
                endIndexInMaxArray = endIndex;
            }

            return sum;
        }
        return sum;
    }
}

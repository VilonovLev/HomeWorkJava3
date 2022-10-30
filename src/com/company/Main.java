package com.company;

import java.util.*;
import java.util.logging.Logger;

public class Main {
    public static int[] mergeSort(int[] arr) {
        if (arr.length > 1) {
            int indexLeft = 0;
            int indexRaght = arr.length;
            int indexMiddle = indexRaght / 2;
            int[] leftArray = mergeSort(Arrays.copyOfRange(arr, indexLeft, indexMiddle));
            int[] ragthArray = mergeSort(Arrays.copyOfRange(arr, indexMiddle, indexRaght));
            return mergArrays(leftArray, ragthArray);
        } else
            return arr;
    }

    public static int[] mergArrays(int[] first, int[] second) {
        int indexFirst = 0;
        int indexSecond = 0;
        int indexResult = 0;
        int[] result = new int[first.length + second.length];
        while (indexFirst < first.length && indexSecond < second.length) {
            if (first[indexFirst] < second[indexSecond]) {
                result[indexResult] = first[indexFirst];
                ++indexFirst;
            } else {
                result[indexResult] = second[indexSecond];
                ++indexSecond;
            }
            ++indexResult;
        }
        if (indexFirst < first.length) {
            for (int i = indexResult; i < result.length; i++)
                result[i] = first[indexFirst++];
        }
        if (indexSecond < second.length) {
            for (int i = indexResult; i < result.length; i++)
                result[i] = second[indexSecond++];
        }
        return result;
    }

    public static int searchMiddleElement(List<Integer> list) {
        double arithmeticMean = (list.stream()
                .mapToInt(e -> e)
                .average())
                .getAsDouble();
        double[] temp = list.stream()
                .mapToDouble(e -> Math.abs(arithmeticMean - e))
                .toArray();
        double min = Arrays.stream(temp)
                .min().getAsDouble();
        int index = 0;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == min) {
                index = i;
                break;
            }
        }
        return list.get(index);
    }

    public static void main(String[] args) {
        Logger logger = Logger.getAnonymousLogger();
//        Реализовать алгоритм сортировки слиянием
        int[] num = {22, 42, 32, 5, 657, 2, 12};
        logger.info(Arrays.toString(mergeSort(num)));

        //Задача 2: Пусть дан произвольный список целых чисел, удалить из него четные числа
        List<Integer> numbers = new ArrayList<>(Arrays.asList(23, 4, 423, 46, 5, 23));
        numbers.removeIf(value -> value % 2 == 0);
        logger.info(numbers.toString());

        //Задача 3: Задан целочисленный список ArrayList. Найти минимальное,
        // максимальное и среднее из этого списка.
        List<Integer> numbers2 = new ArrayList<>(Arrays.asList(1, 2, 3, 10, 52));
        logger.info("Max = " + Collections.max(numbers2));
        logger.info("Min = " + Collections.min(numbers2));
        // Поиск среднего элемента
        logger.info("Middle element " + searchMiddleElement(numbers2));
    }
}

package naumen;

import java.util.Random;

public class task1 {
    public static void main(String[] args) {
        // Создаем массив из 10 элементов
        int[] array = new int[10];
        Random random = new Random();

        // Заполняем массив случайными числами от -50 до 50
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(101) - 50;
        }

        // Выводим массив
        System.out.print("Массив: ");
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Находим минимальное значение по модулю
        int minAbsValue = Math.abs(array[0]);
        for (int i = 1; i < array.length; i++) {
            if (Math.abs(array[i]) < minAbsValue) {
                minAbsValue = Math.abs(array[i]);
            }
        }

        // Выводим минимальное значение по модулю
        System.out.println("Минимальное значение по модулю: " + minAbsValue);
    }
}

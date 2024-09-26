package naumen;
import java.util.ArrayList;
import java.util.Random;


/*
Описание алгоритма быстрой сортировки:
Быстрая сортировка (Quick Sort) — это алгоритм, основанный на принципе "разделяй и властвуй".

Выбор опорного элемента: Из списка выбирается один элемент, называемый "опорный" (pivot).
В примере выше опорным элементом всегда выбирается последний элемент подмассива.

Разделение: Список делится на две части таким образом, что элементы меньше опорного оказываются слева,
а элементы больше — справа.

Рекурсивная сортировка: Алгоритм рекурсивно применяется к двум получившимся частям списка,
пока подмассивы не станут тривиально малыми (с одним элементом или пустыми).
*/


public class task2 {
    public static void main(String[] args) {
        int n = 10; // количество элементов в списке
        ArrayList<Integer> list = new ArrayList<>();
        Random random = new Random();

        // Заполняем список случайными целыми числами
        for (int i = 0; i < n; i++) {
            list.add(random.nextInt(100)); // числа от 0 до 99
        }

        // Выводим исходный список
        System.out.println("Исходный список: " + list);

        // Сортируем список методом быстрой сортировки
        quickSort(list, 0, list.size() - 1);

        // Выводим отсортированный список
        System.out.println("Отсортированный список: " + list);
    }

    // Реализация быстрой сортировки
    public static void quickSort(ArrayList<Integer> list, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(list, low, high);
            quickSort(list, low, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, high);
        }
    }

    // Метод для разделения списка
    public static int partition(ArrayList<Integer> list, int low, int high) {
        int pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (list.get(j) < pivot) {
                i++;
                // Меняем местами элементы
                int temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        // Меняем местами опорный элемент
        int temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);

        return i + 1;
    }
}

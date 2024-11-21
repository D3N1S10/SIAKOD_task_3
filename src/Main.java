import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Element> elements = new ArrayList<>();
        Random random = new Random();

        System.out.println("Выберите способ ввода данных:");
        int choice;
        while (true) {
            try {
                System.out.println("1. Ввести значения вручную");
                System.out.println("2. Использовать автоматически сгенерированные данные (1000 элементов)");
                choice = scanner.nextInt();

                if (choice == 1 || choice == 2) {
                    break;
                } else {
                    System.out.println("Ошибка: введите 1 или 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите целое число (1 или 2).");
                scanner.next();
            }
        }

        if (choice == 1) {
            int n;
            while (true) {
                try {
                    System.out.print("Введите количество элементов: ");
                    n = scanner.nextInt();
                    if (n > 0) {
                        break;
                    } else {
                        System.out.println("Количество элементов должно быть больше 0. Попробуйте снова.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Ошибка ввода. Введите целое число.");
                    scanner.next();
                }
            }

            for (int i = 0; i < n; i++) {
                String value;
                int priority;

                System.out.print("Введите значение для элемента " + (i + 1) + ": ");
                value = scanner.next();

                while (true) {
                    try {
                        System.out.print("Введите приоритет для элемента " + (i + 1) + ": ");
                        priority = scanner.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Ошибка ввода. Введите целое число для приоритета.");
                        scanner.next();
                    }
                }

                elements.add(new Element(value, priority));
            }
        } else {
            for (int i = 0; i < 1000; i++) {
                String value = "Элемент" + i;
                int priority = random.nextInt(1000);
                elements.add(new Element(value, priority));
            }
        }

        List<Long> heapInsertTimes = new ArrayList<>();
        List<Long> arrayInsertTimes = new ArrayList<>();
        List<Long> heapExtractTimes = new ArrayList<>();
        List<Long> arrayExtractTimes = new ArrayList<>();

        PriorityQueueHeap pqHeap = new PriorityQueueHeap();
        long heapStartTimeInsert = System.nanoTime();
        for (Element e : elements) {
            pqHeap.insert(e.getValue(), e.getPriority());
            heapInsertTimes.add(System.nanoTime() - heapStartTimeInsert);
        }
        long heapEndTimeInsert = System.nanoTime();

        long heapStartTimeExtract = System.nanoTime();
        while (!pqHeap.isEmpty()) {
            pqHeap.extractMax();
            heapExtractTimes.add(System.nanoTime() - heapStartTimeExtract);
        }
        long heapEndTimeExtract = System.nanoTime();

        PriorityQueueArray pqArray = new PriorityQueueArray();
        long arrayStartTimeInsert = System.nanoTime();
        for (Element e : elements) {
            pqArray.insert(e.getValue(), e.getPriority());
            arrayInsertTimes.add(System.nanoTime() - arrayStartTimeInsert);
        }
        long arrayEndTimeInsert = System.nanoTime();

        long arrayStartTimeExtract = System.nanoTime();
        while (!pqArray.isEmpty()) {
            pqArray.extractMax();
            arrayExtractTimes.add(System.nanoTime() - arrayStartTimeExtract);
        }
        long arrayEndTimeExtract = System.nanoTime();

        new PriorityQueueVisualizer(heapInsertTimes, arrayInsertTimes, heapExtractTimes, arrayExtractTimes);
        scanner.close();
    }
}

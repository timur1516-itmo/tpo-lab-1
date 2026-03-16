package lab1.task1;

import lab1.task1.utils.Trigonometric;

public class App {
    public static void main(String[] args) {
        System.out.println("Тестирование функции sec(x):");
        System.out.println("============================");

        double[] testValues = {0.0, 0.5, 1.0, Math.PI / 4, Math.PI / 3};

        for (double x : testValues) {
            double result = Trigonometric.sec(x);
            double expected = 1.0 / Math.cos(x);
            System.out.printf("sec(%.4f) = %.6f (ожидается: %.6f)%n", x, result, expected);
        }

        System.out.println("\nТочки разрыва:");
        System.out.printf("sec(π/2) = %.6f%n", Trigonometric.sec(Math.PI / 2));
        System.out.printf("sec(-π/2) = %.6f%n", Trigonometric.sec(-Math.PI / 2));
    }
}
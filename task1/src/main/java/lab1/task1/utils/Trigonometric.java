package lab1.task1.utils;

public class Trigonometric {

    private static final double EPS = 1e-10;

    public static double sec(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            return Double.NaN;
        }

        double cos = cos(x);

        if (Math.abs(cos) < EPS) {
            return Double.NaN;
        }

        return 1.0 / cos(x);
    }

    private static double cos(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            return Double.NaN;
        }

        x = x % (2 * Math.PI);

        double term = 1.0;
        double sum = 1.0;
        int n = 1;

        while (Math.abs(term) > EPS) {
            term *= -x * x / ((2.0 * n - 1) * (2.0 * n));
            sum += term;
            n++;
        }

        return sum;
    }
}

package lab1.task1.utils;

import net.jqwik.api.ForAll;
import net.jqwik.api.Label;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.DoubleRange;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrigonometricTest {

    private static final double EPS = 1e-6;

    @ParameterizedTest(name = "sec({0}) = {1}")
    @DisplayName("Проверка между точками по табличным значениям")
    @CsvSource({
            "-3.1415926536, -1.0",
            "-2.3561944902, -1.4142135624",
            "-1.5707963268, NaN",
            "-0.7853981634, 1.4142135624",
            "0.0, 1.0",
            "0.7853981634, 1.4142135624",
            "1.5707963268, NaN",
            "2.3561944902, -1.4142135624",
            "3.1415926536, -1.0"
    })
    void checkTableValues(double x, double expected) {
        double actual = Trigonometric.sec(x);

        if (Double.isNaN(expected)) {
            assertTrue(Double.isNaN(actual), String.format("Ожидался NaN для sec(%f), получен %f", x, actual));
        } else {
            assertEquals(expected, actual, EPS, String.format("sec(%f) должен быть равен %f", x, expected));
        }
    }

    @Property
    @Label("Тест периодичности: sec(x) = sec(x + 2π)")
    void testSecPeriodicity(@ForAll @DoubleRange(min = -10.0, max = 10.0) double x) {
        double sec1 = Trigonometric.sec(x);
        double sec2 = Trigonometric.sec(x + 2 * Math.PI);

        Assumptions.assumeFalse(Double.isNaN(sec1) || Double.isNaN(sec2));
        assertEquals(sec1, sec2, EPS, "sec должен быть периодическим с периодом 2π");
    }

    @Property
    @Label("Тест четности: sec(-x) = sec(x)")
    void testSecSymmetry(@ForAll @DoubleRange(min = -10.0, max = 10.0) double x) {
        double secPos = Trigonometric.sec(x);
        double secNeg = Trigonometric.sec(-x);

        Assumptions.assumeFalse(Double.isNaN(secPos) || Double.isNaN(secNeg));
        assertEquals(secPos, secNeg, EPS,
                "sec должна быть чётной: sec(-" + x + ") должна быть равна sec(" + x + ")");
    }
}

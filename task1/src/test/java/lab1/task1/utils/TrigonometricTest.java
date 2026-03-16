package lab1.task1.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrigonometricTest {

  @ParameterizedTest(name = "sec({0})")
  @DisplayName("Check corner values")
  @ValueSource(doubles = {
    -999.9,
    -Math.PI,
    -2.5,
    -2.0,
    -1.5,
    -1.0,
    -0.5,
    -0.000001,
    -0.0001,
    -0.0,
    0.0,
    0.0001,
    0.000001,
    0.5,
    1.0,
    1.5,
    Math.PI / 2 - 0.1,
    Math.PI / 2 + 0.1,
    2.0,
    2.5,
    Math.PI,
    999.9,
    Double.NaN,
    Double.POSITIVE_INFINITY,
    Double.NEGATIVE_INFINITY,
    Double.MIN_VALUE,
  })
  void checkCornerDots(double param) {
    double expected = 1.0 / Math.cos(param);
    double actual = Trigonometric.sec(param);

    if (Double.isNaN(expected) || Double.isInfinite(expected)) {
      assertTrue(Double.isNaN(actual) || Double.isInfinite(actual),
        "Expected NaN or Infinity for sec(" + param + ")");
    } else {
      assertAll(
        () -> assertEquals(expected, actual, 0.0001,
          "sec(" + param + ") failed")
      );
    }
  }

  @ParameterizedTest(name = "sec({0}) = {1}")
  @DisplayName("Check between dots with table values")
  @CsvFileSource(resources = "/table_values.csv", numLinesToSkip = 1, delimiter = ';')
  void checkTableValues(double x, double y) {
    assertAll(
      () -> assertEquals(y, Trigonometric.sec(x), 0.001,
        "sec(" + x + ") should equal " + y)
    );
  }

  @Test
  @DisplayName("Test sec(0) = 1")
  void testSecZero() {
    assertEquals(1.0, Trigonometric.sec(0.0), 1e-10);
    assertEquals(1.0, Trigonometric.sec(-0.0), 1e-10);
  }

  @Test
  @DisplayName("Test sec at discontinuity points")
  void testSecDiscontinuity() {
    // sec(π/2) должен быть NaN или бесконечностью
    double result1 = Trigonometric.sec(Math.PI / 2);
    assertTrue(Double.isNaN(result1) || Double.isInfinite(result1));
    
    double result2 = Trigonometric.sec(-Math.PI / 2);
    assertTrue(Double.isNaN(result2) || Double.isInfinite(result2));
  }

  @Test
  @DisplayName("Test sec periodicity")
  void testSecPeriodicity() {
    double x = 0.5;
    double sec1 = Trigonometric.sec(x);
    double sec2 = Trigonometric.sec(x + 2 * Math.PI);
    double sec3 = Trigonometric.sec(x - 2 * Math.PI);
    
    assertEquals(sec1, sec2, 0.001, "sec should be periodic with period 2π");
    assertEquals(sec1, sec3, 0.001, "sec should be periodic with period 2π");
  }

  @Test
  @DisplayName("Test sec symmetry: sec(-x) = sec(x)")
  void testSecSymmetry() {
    double[] testValues = {0.1, 0.5, 1.0, 1.4};
    
    for (double x : testValues) {
      double secPos = Trigonometric.sec(x);
      double secNeg = Trigonometric.sec(-x);
      assertEquals(secPos, secNeg, 0.0001,
        "sec should be even: sec(-" + x + ") should equal sec(" + x + ")");
    }
  }
}
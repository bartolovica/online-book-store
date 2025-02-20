package antun.bart.onlinebookstore.util;

public final class MathOperations {

    private MathOperations() {
    }

    public static Double roundValueOnTwoDecimals(Double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    public static Double calculatePercentage(Double discount) {
        return roundValueOnTwoDecimals(1 - discount) * 100;
    }
}

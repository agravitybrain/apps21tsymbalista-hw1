package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {

    private static final double MIN_VALUE = -273;
    private double[] temperatureSeries;

    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[] {};

    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        if (temperatureSeries.length != 0) {
            checkValues(temperatureSeries);
            this.temperatureSeries = Arrays.copyOf(
                    temperatureSeries, temperatureSeries.length);
        }
    }

    public void chekEmpty() {
        try {
            if(temperatureSeries.length == 0) {
                throw new NullPointerException();
            }
        }
        catch (Exception NullPointerException) {
            throw new IllegalArgumentException();
        }
    }

    public double average() {
        chekEmpty();
        double sumValues = 0;
        int quantity = 0;
        for (double temperatureSer : temperatureSeries) {
            sumValues += temperatureSer;
            quantity++;

        }
        return sumValues / quantity;
    }

    public double deviation() {
        chekEmpty();
        int len = temperatureSeries.length;
        double mean = average();
        double variance = 0;
        if (len == 1) {
            return 0;
        }
        for (double temperatureSer : temperatureSeries) {
            variance += Math.pow(temperatureSer - mean, 2) / (len - 1);
        }
        return Math.sqrt(variance);
    }

    public double min() {
        return findTempClosestToValue(MIN_VALUE);
    }

    public double max() {
        chekEmpty();
        double max = MIN_VALUE;
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] > max) {
                max = temperatureSeries[i];
            }
        }
        return max;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        chekEmpty();
        double closest = Double.MAX_VALUE;
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (Math.abs(tempValue - closest) >
                    Math.abs(tempValue - temperatureSeries[i])) {
                closest = temperatureSeries[i];
            }
            else if (Math.abs(tempValue - closest) ==
                    Math.abs(tempValue - temperatureSeries[i])) {
                if (temperatureSeries[i] > closest) {
                    closest = temperatureSeries[i];
                }
            }
        }
        return closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        chekEmpty();
        return findInRange(MIN_VALUE, tempValue);
    }

    public double[] findTempsGreaterThen(double tempValue) {
        chekEmpty();
        return findInRange(tempValue, Double.MAX_VALUE);
    }

    public double[] findInRange(double fromValue, double toValue) {
        chekEmpty();
        int len = temperatureSeries.length;
        int n = 0;
        double[] values = new double[len];
        for (int i = 0; i < len; i++) {
            if ((temperatureSeries[i] > fromValue) &&
                    (temperatureSeries[i] < toValue)) {
                values[n] = temperatureSeries[i];
                n++;
            }
        }
        if (n == 0) {
            return new double[] {};
        }

        return Arrays.copyOfRange(values, 0, n);
    }

    public TempSummaryStatistics summaryStatistics() {
        chekEmpty();
        TempSummaryStatistics statistics = new TempSummaryStatistics(
                min(), max(), average(), deviation());
        return statistics;
    }

    public int addTemps(double... temps) {
        int n = temperatureSeries.length;
        int m = temps.length;

        if (m != 0) {
            checkValues(temps);
        }
        else {
            return n;
        }
        double[] newTemps = new double[m + n];
        for (int i = 0; i < n + m; i++) {
            if (i < n) {
                newTemps[i] = temperatureSeries[i];
            }
            else {
                newTemps[i] = temps[i - n];
            }
        }
        temperatureSeries = newTemps;
        return n + m;
    }

    private void checkValues(double[] temps) {
        for (int i = 0; i < temps.length; i++) {
            if (temps [i] < MIN_VALUE) {
                throw new InputMismatchException();
            }
        }
    }
}

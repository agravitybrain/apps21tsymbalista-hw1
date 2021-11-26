package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);        
    }

    @Test
    public void testDeviation() {
        double[] temperatureSeries = {1.0, -1.0, 1.0, -1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        double expResult = Math.sqrt(4.0 / 3.0);
        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.00001);

        //when 1 element
        seriesAnalysis = new TemperatureSeriesAnalysis(new double[] {248.0});

        expResult = 0.0;
        actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMin() {
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        double expResult = -1.0;
        double actualResult = seriesAnalysis.min();
        assertEquals(expResult, actualResult, 0.00001);


        seriesAnalysis = new TemperatureSeriesAnalysis(new double[] {-1.0, 20.0, 50.0, 11.0, 34.0});
        actualResult = seriesAnalysis.min();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMax() {
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        double expResult = -1.0;
        double actualResult = seriesAnalysis.max();
        assertEquals(expResult, actualResult, 0.00001);


        seriesAnalysis = new TemperatureSeriesAnalysis(new double[] {-1.0, 20.0, 50.0, 11.0, 34.0});
        expResult = 50.0;
        actualResult = seriesAnalysis.max();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZero() {
        double[] temperatureSeries = {-1.0, 1.0, 3.0, -2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        double expResult = 1.0;
        double actualResult = seriesAnalysis.findTempClosestToZero();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToValue() {
        double[] temperatureSeries = {-1.0, 1.0, 3.0, -2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        double expResult = 3.0;
        double actualResult = seriesAnalysis.findTempClosestToValue(2.875);
        assertEquals(expResult, actualResult, 0.00001);

        expResult = 3.0;
        actualResult = seriesAnalysis.findTempClosestToValue(2.0);
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsLessThen() {
        double[] temperatureSeries = {-1.0, 1.0, 3.0, -2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        double[] result = seriesAnalysis.findTempsLessThen(1.0);
        boolean actualResult = Arrays.equals(new double[] {-1.0, -2.0}, result);

        assertTrue(actualResult);

        result = seriesAnalysis.findTempsLessThen(-10.0);
        actualResult = Arrays.equals(new double[] {}, result);

        assertTrue(actualResult);
    }

    @Test
    public void testFindTempsGreaterThen() {
        double[] temperatureSeries = {-1.0, 1.0, 3.0, -2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        double[] result = seriesAnalysis.findTempsGreaterThen(1.0);
        boolean actualResult = Arrays.equals(new double[] {3.0}, result);

        assertTrue(actualResult);

        result = seriesAnalysis.findTempsGreaterThen(-10.0);
        actualResult = Arrays.equals(temperatureSeries, result);

        assertTrue(actualResult);
    }

    @Test
    public void testSummaryStatistics() {
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        TempSummaryStatistics statistics = seriesAnalysis.summaryStatistics();

        double avr_1 = statistics.getAvgTemp();
        double min_1 = statistics.getMinTemp();
        double max_1 = statistics.getMaxTemp();
        double dev_1 = statistics.getDevTemp();

        assertEquals(-1.0, avr_1, 0.00001);
        assertEquals(-1.0, min_1, 0.00001);
        assertEquals(-1.0, max_1, 0.00001);
        assertEquals(0.0, dev_1, 0.00001);

        seriesAnalysis.addTemps(new double[] {1.0, -1.0, 1.0});
        double avr_2 = statistics.getAvgTemp();
        double min_2 = statistics.getMinTemp();
        double max_2 = statistics.getMaxTemp();
        double dev_2 = statistics.getDevTemp();

        assertEquals(avr_2, avr_1, 0.00001);
        assertEquals(min_2, min_1, 0.00001);
        assertEquals(max_2, max_1, 0.00001);
        assertEquals(dev_2, dev_1, 0.00001);

        TempSummaryStatistics statistics_2 = seriesAnalysis.summaryStatistics();
        double avr_3 = statistics_2.getAvgTemp();
        double min_3 = statistics_2.getMinTemp();
        double max_3 = statistics_2.getMaxTemp();
        double dev_3 = statistics_2.getDevTemp();

        assertNotEquals(avr_2, avr_3, 0.00001);
        assertEquals(min_2, min_3, 0.00001);
        assertNotEquals(max_2, max_3, 0.00001);
        assertNotEquals(dev_2, dev_3, 0.00001);
    }
    @Test(expected = InputMismatchException.class)
    public void testAddBelowAbsolutZeroTemps() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();

        //regular case was tested in testSummaryStatistics
        double[] testArray = new double[] {-10.0, 10000.0, 0.0, -300.0};
        seriesAnalysis.addTemps(testArray);
    }
}

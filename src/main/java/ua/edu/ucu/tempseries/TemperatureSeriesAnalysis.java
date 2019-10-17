package ua.edu.ucu.tempseries;
import java.io.*;
import java.lang.*;
import java.util.*;

public class TemperatureSeriesAnalysis {

    private int tempLength;
    private double[] temperatureSeries;

    public TemperatureSeriesAnalysis() {

    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (int i = 0; i < temperatureSeries.length; i++)
        {
            if (temperatureSeries[i] < -273.0)
            {
                throw new InputMismatchException();
            }
        }
        this.tempLength = temperatureSeries.length;
        this.temperatureSeries = Arrays.copyOf(temperatureSeries, temperatureSeries.length);
    }

    public double average() {
        if (this.temperatureSeries == null || this.temperatureSeries.length == 0)
        {
            throw new IllegalArgumentException();
        }
        double sum = 0;
        int len = this.tempLength;
        for (int i = 0; i < len; i++)
        {
            sum += this.temperatureSeries[i];
        }

        double avg = sum / len;
        return avg;
    }

    public double deviation() {
        if (this.temperatureSeries == null || this.temperatureSeries.length == 0)
        {
            throw new IllegalArgumentException();
        }
        double avg = this.average();
        double sum_sqr = 0;
        int len = this.tempLength;

        for (int i = 0; i < len; i++)
        {
            sum_sqr += Math.pow(this.temperatureSeries[i], 2);
        }

        double avg_sqr = sum_sqr / len;
        double dev = Math.sqrt(avg_sqr - Math.pow(avg,2));

        return dev;
    }

    public double min() {
        if (this.temperatureSeries == null || this.temperatureSeries.length == 0)
        {
            throw new IllegalArgumentException();
        }

        double min = 10000;
        for (int i = 0; i < this.tempLength; i++)
        {

            if (this.temperatureSeries[i] < min)
            {

                min = this.temperatureSeries[i];

            }
        }
        return min;
    }

    public double max() {
        if (this.temperatureSeries == null || this.temperatureSeries.length == 0)
        {
            throw new IllegalArgumentException();
        }
        double max = -10000;
        for (int i = 0; i < this.tempLength; i++)
        {
            if (this.temperatureSeries[i] > max)
            {

                max = this.temperatureSeries[i];


            }
        }
        return max;
    }

    public double findTempClosestToZero() {
        if (this.temperatureSeries == null || this.temperatureSeries.length == 0)
        {
            throw new IllegalArgumentException();
        }
        double min_abs_pos = 10000;
        double min_abs_neg = 10000;
        int index_pos = 0;
        int index_neg = 0;
        for (int i = 0; i < this.tempLength; i++)
        {

            if (this.temperatureSeries[i] < 0)
            {
                if (Math.abs(this.temperatureSeries[i]) <= min_abs_neg)
                {

                    min_abs_neg = Math.abs(this.temperatureSeries[i]);
                    index_neg = i;
                }
            }
            else
            {
                if (Math.abs(this.temperatureSeries[i]) <= min_abs_pos)
                {

                    min_abs_pos = Math.abs(this.temperatureSeries[i]);
                    index_pos = i;
                }
            }

        }
        if (min_abs_pos <= min_abs_neg)
        {
            return this.temperatureSeries[index_pos];
        }
        else
        {
            return this.temperatureSeries[index_neg];
        }
    }

    public double findTempClosestToValue(double tempValue) {
        if (this.temperatureSeries == null || this.temperatureSeries.length == 0)
        {
            throw new IllegalArgumentException();
        }
        double min_abs_pos = 10000;
        double min_abs_neg = 10000;
        int index_pos = this.tempLength;
        int index_neg = this.tempLength;
        for (int i = 0; i < this.tempLength; i++)
        {

            if (this.temperatureSeries[i] < tempValue)
            {

                if (Math.abs(this.temperatureSeries[i] - tempValue) < min_abs_neg)
                {

                    min_abs_neg = Math.abs(this.temperatureSeries[i] - tempValue);
                    index_neg = i;

                }
            }
            else
            {
                if (Math.abs(this.temperatureSeries[i] - tempValue) < min_abs_pos)
                {

                    min_abs_pos = Math.abs(this.temperatureSeries[i] - tempValue);
                    index_pos = i;

                }
            }

        }
        if (min_abs_pos <= min_abs_neg)
        {
            return this.temperatureSeries[index_pos];
        }
        else
        {
            return this.temperatureSeries[index_neg];
        }
    }


    public double[] findTempsLessThen(double tempValue) {
        if (this.temperatureSeries == null || this.temperatureSeries.length == 0)
        {
            throw new IllegalArgumentException();
        }
        int less_len = 0;
        int j = 0;
        for (int i = 0; i < this.tempLength; i++)
        {
            if (this.temperatureSeries[i] < tempValue)
            {
                less_len++;
            }
        }
        double less[] = new double[less_len];
        for (int i = 0; i < this.tempLength; i++)
        {
            if (this.temperatureSeries[i] < tempValue)
            {
                less[j] = this.temperatureSeries[i];
                j++;
                if (j >= less_len) break;
            }
        }
        return less;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        if (this.temperatureSeries == null || this.temperatureSeries.length == 0)
        {
            throw new IllegalArgumentException();
        }
        int greater_len = 0;
        int j = 0;
        for (int i = 0; i < this.tempLength; i++)
        {
            if (this.temperatureSeries[i] > tempValue)
            {
                greater_len++;
            }
        }
        double greater[] = new double[greater_len];
        for (int i = 0; i < this.tempLength; i++)
        {
            if (this.temperatureSeries[i] > tempValue)
            {
                greater[j] = this.temperatureSeries[i];
                j++;
                if (j >= greater_len) break;
            }
        }
        return greater;
    }


    public TempSummaryStatistics summaryStatistics() {
        if (this.temperatureSeries == null || this.temperatureSeries.length == 0)
        {
            throw new IllegalArgumentException();
        }

        TempSummaryStatistics tss = new TempSummaryStatistics(this.average(), this.deviation(), this.min(), this.max());

        return tss;
    }


    public int addTemps(double... temps) {

        if (this.tempLength + temps.length > this.temperatureSeries.length)
        {
            this.temperatureSeries = Arrays.copyOf(this.temperatureSeries, 2*(this.tempLength+temps.length));
        }

        for (int i = 0; i < temps.length; i++)
        {
            this.temperatureSeries[tempLength+i] = temps[i];
            this.tempLength++;
        }
        return tempLength;
    }
}

package ua.edu.ucu.tempseries;
// import java.io.*;
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
        this.temperatureSeries = Arrays.copyOf(temperatureSeries,
                                               temperatureSeries.length);
    }

    public double average() {
        if (this.temperatureSeries == null ||
            this.temperatureSeries.length == 0)
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
        if (this.temperatureSeries == null ||
            this.temperatureSeries.length == 0)
        {
            throw new IllegalArgumentException();
        }
        double avg = this.average();
        double sumsqr = 0;
        int len = this.tempLength;

        for (int i = 0; i < len; i++)
        {
            sumsqr += (this.temperatureSeries[i]) ** 2;
        }

        double avgsqr = sumsqr / len;
        double dev = Math.sqrt(avgsqr - Math.pow(avg, 2));

        return dev;
    }

    public double min() {
        if (this.temperatureSeries == null ||
            this.temperatureSeries.length == 0)
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
        if (this.temperatureSeries == null ||
            this.temperatureSeries.length == 0)
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
        if (this.temperatureSeries == null ||
            this.temperatureSeries.length == 0)
        {
            throw new IllegalArgumentException();
        }
        double minabspos = 10000;
        double minabsneg = 10000;
        int indexpos = 0;
        int indexneg = 0;
        for (int i = 0; i < this.tempLength; i++)
        {

            if (this.temperatureSeries[i] < 0)
            {
                if (Math.abs(this.temperatureSeries[i]) <= minabsneg)
                {

                    minabsneg = Math.abs(this.temperatureSeries[i]);
                    indexneg = i;
                }
            }
            else
            {
                if (Math.abs(this.temperatureSeries[i]) <= minabspos)
                {

                    minabspos = Math.abs(this.temperatureSeries[i]);
                    indexpos = i;
                }
            }

        }
        if (minabspos <= minabsneg)
        {
            return this.temperatureSeries[indexpos];
        }
        else
        {
            return this.temperatureSeries[indexneg];
        }
    }

    public double findTempClosestToValue(double tempValue) {
        if (this.temperatureSeries == null ||
            this.temperatureSeries.length == 0)
        {
            throw new IllegalArgumentException();
        }
        double minabspos = 10000;
        double minabsneg = 10000;
        int indexpos = this.tempLength;
        int indexneg = this.tempLength;
        for (int i = 0; i < this.tempLength; i++)
        {

            if (this.temperatureSeries[i] < tempValue)
            {

                if (Math.abs(this.temperatureSeries[i] - tempValue)
                    < minabsneg)
                {

                    minabsneg = Math.abs(this.temperatureSeries[i]
                                         - tempValue);
                    indexneg = i;

                }
            }
            else
            {
                if (Math.abs(this.temperatureSeries[i] - tempValue)
                    < minabspos)
                {

                    minabspos = Math.abs(this.temperatureSeries[i]
                                         - tempValue);
                    indexpos = i;

                }
            }

        }
        if (minabspos <= minabsneg)
        {
            return this.temperatureSeries[indexpos];
        }
        else
        {
            return this.temperatureSeries[index_neg];
        }
    }


    public double[] findTempsLessThen(double tempValue) {
        if (this.temperatureSeries == null ||
            this.temperatureSeries.length == 0)
        {
            throw new IllegalArgumentException();
        }
        int less_len = 0;
        int j = 0;
        for (int i = 0; i < this.tempLength; i++)
        {
            if (this.temperatureSeries[i] < tempValue)
            {
                lesslen++;
            }
        }
        double[] less = new double[lesslen];
        for (int i = 0; i < this.tempLength; i++)
        {
            if (this.temperatureSeries[i] < tempValue)
            {
                less[j] = this.temperatureSeries[i];
                j++;
                if (j >= lesslen) {
                    break;
                }
            }
        }
        return less;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        if (this.temperatureSeries == null ||
            this.temperatureSeries.length == 0)
        {
            throw new IllegalArgumentException();
        }
        int greaterlen = 0;
        int j = 0;
        for (int i = 0; i < this.tempLength; i++)
        {
            if (this.temperatureSeries[i] > tempValue)
            {
                greaterlen++;
            }
        }
        double[] greater = new double[greaterlen];
        for (int i = 0; i < this.tempLength; i++)
        {
            if (this.temperatureSeries[i] > tempValue)
            {
                greater[j] = this.temperatureSeries[i];
                j++;
                if (j >= greaterlen) {
                    break;
                }
            }
        }
        return greater;
    }


    public TempSummaryStatistics summaryStatistics() {
        if (this.temperatureSeries == null ||
            this.temperatureSeries.length == 0)
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

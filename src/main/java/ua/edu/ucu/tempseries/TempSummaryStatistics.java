package ua.edu.ucu.tempseries;


public final class TempSummaryStatistics {

    final double avgTemp;
    final double devTemp;
    final double minTemp;
    final double maxTemp;


    public TempSummaryStatistics(double avgTemp, double devTemp,
                                 double minTemp, double maxTemp) {
        this.avgTemp = avgTemp;
        this.devTemp = devTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    public double getAvg() {
        return avgTemp;
    }

    public double getDev() {
        return devTemp;
    }

    public double getMin() {
        return minTemp;
    }

    public double getMax() {
        return maxTemp;
    }
}

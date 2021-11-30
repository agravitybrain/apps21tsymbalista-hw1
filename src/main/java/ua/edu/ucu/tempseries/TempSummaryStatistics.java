package ua.edu.ucu.tempseries;

import lombok.Getter;

@Getter
final class TempSummaryStatistics {
    private final double minTemp;
    private final double maxTemp;
    private final double devTemp;
    private final double avgTemp;

    TempSummaryStatistics(
            double minTemp, double maxTemp, double avgTemp, double devTemp) {
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.devTemp = devTemp;
        this.avgTemp = avgTemp;
    }
}

package ua.edu.ucu.tempseries;

import lombok.Getter;

@Getter
final class TempSummaryStatistics {
    final double minTemp;
    final double maxTemp;
    final double devTemp;
    final double avgTemp;

    TempSummaryStatistics(
            double minTemp, double maxTemp, double avgTemp, double devTemp) {
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.devTemp = devTemp;
        this.avgTemp = avgTemp;
    }
}

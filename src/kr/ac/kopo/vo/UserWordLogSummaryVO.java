package kr.ac.kopo.vo;

public class UserWordLogSummaryVO {
    private int logCount;
    private int totalWordCount;
    private int totalCorrect;
    private int totalIncorrect;
    private double accuracy;

    public int getLogCount() {
        return logCount;
    }
    public void setLogCount(int logCount) {
        this.logCount = logCount;
    }

    public int getTotalWordCount() {
        return totalWordCount;
    }
    public void setTotalWordCount(int totalWordCount) {
        this.totalWordCount = totalWordCount;
    }

    public int getTotalCorrect() {
        return totalCorrect;
    }
    public void setTotalCorrect(int totalCorrect) {
        this.totalCorrect = totalCorrect;
    }

    public int getTotalIncorrect() {
        return totalIncorrect;
    }
    public void setTotalIncorrect(int totalIncorrect) {
        this.totalIncorrect = totalIncorrect;
    }

    public double getAccuracy() {
        return accuracy;
    }
    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }
}

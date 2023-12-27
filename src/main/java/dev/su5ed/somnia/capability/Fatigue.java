package dev.su5ed.somnia.capability;

public interface Fatigue {
    double getFatigue();

    void setFatigue(double fatigue);

    int getSideEffectStage();

    void setSideEffectStage(int stage);

    boolean updateFatigueCounter();

    void maxFatigueCounter();

    void setResetSpawn(boolean resetSpawn);

    boolean getResetSpawn();

    boolean sleepOverride();

    void setSleepOverride(boolean override);

    void setSleepNormally(boolean sleepNormally);

    boolean shouldSleepNormally();

    long getWakeTime();

    void setWakeTime(long wakeTime);

    double getExtraFatigueRate();

    void setExtraFatigueRate(double rate);

    double getReplenishedFatigue();

    void setReplenishedFatigue(double replenishedFatigue);
}

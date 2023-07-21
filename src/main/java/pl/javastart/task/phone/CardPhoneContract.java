package pl.javastart.task.phone;

import java.util.Objects;

public class CardPhoneContract extends PhoneContract {
    static final int SECONDS_IN_MINUTE = 60;
    double accountState;
    double smsCost;
    double mmsCost;
    double callCostPerSecond;

    public CardPhoneContract(double accountState, double smsCost, double mmsCost, double callCostPerMinute) {
        this.accountState = accountState;
        this.smsCost = smsCost;
        this.mmsCost = mmsCost;
        this.callCostPerSecond = callCostPerMinute / SECONDS_IN_MINUTE;
    }

    @Override
    boolean performSmsSending() {
        if (accountState >= smsCost) {
            accountState -= smsCost;
            sentSms++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    int performCalling(int seconds) {
        double callCost = seconds * callCostPerSecond;
        if (callCost <= accountState) {
            accountState -= callCost;
            callSeconds += seconds;
            return seconds;
        } else {
            int callInSeconds = (int) (accountState / callCostPerSecond);
            accountState = 0;
            callSeconds += callInSeconds;
            return callInSeconds;
        }
    }

    @Override
    boolean performMmsSending() {
        if (accountState >= mmsCost) {
            accountState -= mmsCost;
            sentMms++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("%s%nNa koncie zostało %.2f zł",
                super.toString(), accountState);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CardPhoneContract that)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        return Double.compare(that.accountState, accountState) == 0 &&
                Double.compare(that.smsCost, smsCost) == 0 &&
                Double.compare(that.mmsCost, mmsCost) == 0 &&
                Double.compare(that.callCostPerSecond, callCostPerSecond) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), accountState, smsCost, mmsCost, callCostPerSecond);
    }
}

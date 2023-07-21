package pl.javastart.task.phone;

import java.util.Objects;

public class MixPhoneContract extends CardPhoneContract {
    int smsAmountLeft;
    int mmsAmountLeft;
    int callMinutesAmountLeft;

    public MixPhoneContract(double accountState, double smsCost, double mmsCost, double callCostPerMinute, 
                            int startSmsAmount, int startMmsAmount, int startCallMinutesAmount) {
        super(accountState, smsCost, mmsCost, callCostPerMinute);
        this.smsAmountLeft = startSmsAmount;
        this.mmsAmountLeft = startMmsAmount;
        this.callMinutesAmountLeft = startCallMinutesAmount;
    }

    @Override
    boolean performSmsSending() {
        if (smsAmountLeft > 0) {
            sentSms++;
            smsAmountLeft--;
            return true;
        } else {
            return super.performSmsSending();
        }
    }

    @Override
    int performCalling(int seconds) {
        int callMinutesAmountLeftInSeconds = callMinutesAmountLeft * SECONDS_IN_MINUTE;
        if (callMinutesAmountLeftInSeconds >= seconds) {
            callMinutesAmountLeft -= seconds / SECONDS_IN_MINUTE;
            callSeconds += seconds;
            return seconds;
        } else {
            callMinutesAmountLeft = 0;
            callSeconds += callMinutesAmountLeftInSeconds;
            int phoneCallInSeconds = super.performCalling(seconds - callMinutesAmountLeftInSeconds);
            return phoneCallInSeconds + callMinutesAmountLeftInSeconds;
        }
    }

    @Override
    boolean performMmsSending() {
        if (mmsAmountLeft > 0) {
            mmsAmountLeft--;
            sentMms++;
            return true;
        } else {
            return super.performMmsSending();
        }
    }

    @Override
    public String toString() {
        return String.format("%s%nSms'y do wykorzystania: %d%nMms'y do wykorzystania: %d%n" +
                "Minuty do wykorzystania: %d", super.toString(), smsAmountLeft, mmsAmountLeft, callMinutesAmountLeft);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MixPhoneContract that)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        return smsAmountLeft == that.smsAmountLeft && mmsAmountLeft == that.mmsAmountLeft &&
                callMinutesAmountLeft == that.callMinutesAmountLeft;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), smsAmountLeft, mmsAmountLeft, callMinutesAmountLeft);
    }
}

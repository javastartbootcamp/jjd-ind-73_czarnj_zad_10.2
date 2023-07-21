package pl.javastart.task.phone;

import java.util.Objects;

public class MixPhoneContract extends CardPhoneContract {
    int smsAmountLeft;
    int mmsAmountLeft;
    int callSecondsAmountLeft;

    public MixPhoneContract(double accountState, double smsCost, double mmsCost, double callCostPerMinute,
                            int startSmsAmount, int startMmsAmount, int startCallMinutesAmount) {
        super(accountState, smsCost, mmsCost, callCostPerMinute);
        this.smsAmountLeft = startSmsAmount;
        this.mmsAmountLeft = startMmsAmount;
        this.callSecondsAmountLeft = startCallMinutesAmount * SECONDS_IN_MINUTE;
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
        if (callSecondsAmountLeft >= seconds) {
            callSecondsAmountLeft -= seconds;
            callSeconds += seconds;
            return seconds;
        } else {
            callSeconds += callSecondsAmountLeft;
            int phoneCallInSeconds = super.performCalling(seconds - callSecondsAmountLeft);
            phoneCallInSeconds += callSecondsAmountLeft;
            callSecondsAmountLeft = 0;
            return phoneCallInSeconds;
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
        return String.format("%s%nSms'y do wykorzystania: %d%nMms'y do wykorzystania: %d%n%s%n",
                super.toString(), smsAmountLeft, mmsAmountLeft, getMinutesOrSecondsCallLeftInfo());
    }

    private String getMinutesOrSecondsCallLeftInfo() {
        if (callSecondsAmountLeft >= SECONDS_IN_MINUTE) {
             return String.format("Minuty do wykorzystania: %d", callSecondsAmountLeft / SECONDS_IN_MINUTE);
        } else {
             return String.format("Sekundy do wykorzystania: %d", callSecondsAmountLeft);
        }
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
                callSecondsAmountLeft == that.callSecondsAmountLeft;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), smsAmountLeft, mmsAmountLeft, callSecondsAmountLeft);
    }
}

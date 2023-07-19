package pl.javastart.task.phone;

import java.util.Objects;

public abstract class PhoneContract {
    int sentSms = 0;
    int sentMms = 0;
    int callSeconds = 0;
    abstract boolean performSmsSending();
    abstract int performCalling(int seconds);
    abstract boolean performMmsSending();

    public int getSentSms() {
        return sentSms;
    }

    public int getSentMms() {
        return sentMms;
    }

    public int getCallSeconds() {
        return callSeconds;
    }

    @Override
    public String toString() {
        return String.format("=== STAN KONTA ===%nWysłanych SMSów: %d%nWysłanych MMSów: %d%n" +
                "Liczba sekund rozmowy: %d", sentSms, sentMms, callSeconds);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneContract that)) return false;
        return sentSms == that.sentSms && sentMms == that.sentMms && callSeconds == that.callSeconds;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sentSms, sentMms, callSeconds);
    }
}

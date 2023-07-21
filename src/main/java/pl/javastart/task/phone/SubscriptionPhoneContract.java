package pl.javastart.task.phone;

import java.util.Objects;

public class SubscriptionPhoneContract extends PhoneContract {
    boolean isAbonamentPaid = true;
    double accountCharge;

    public SubscriptionPhoneContract(double accountCharge) {
        this.accountCharge = accountCharge;
    }

    @Override
    boolean performSmsSending() {
        if (isAbonamentPaid) {
            sentSms++;
            return true;
        }
        return false;
    }

    @Override
    int performCalling(int seconds) {
        if (isAbonamentPaid) {
            callSeconds += seconds;
            return seconds;
        }
        return 0;
    }

    @Override
    boolean performMmsSending() {
        if (isAbonamentPaid) {
            sentMms++;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("%s%nOpłata za abonament %.2f zł%nAbonament opłacony %b", super.toString(),
                accountCharge, isAbonamentPaid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubscriptionPhoneContract that)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        return isAbonamentPaid == that.isAbonamentPaid && Double.compare(that.accountCharge, accountCharge) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isAbonamentPaid, accountCharge);
    }
}

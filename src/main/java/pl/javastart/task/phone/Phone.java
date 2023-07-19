package pl.javastart.task.phone;

import java.util.Objects;

public class Phone {
    private PhoneContract phoneContract;

    public Phone(PhoneContract phoneContract) {
        this.phoneContract = phoneContract;
    }

    public void sendSms() {
        boolean isSmsSent = phoneContract.performSmsSending();
        if (isSmsSent) {
            System.out.println("Sms wysłany");
        } else {
            System.out.println("Nie udało się wysłać sms'a");
        }
    }

    public void call(int seconds) {
        int callSeconds = phoneContract.performCalling(seconds);
        System.out.printf("Rozmowa trwała %d%n", callSeconds);
    }

    public void sendMms() {
        boolean isMmsSent = phoneContract.performMmsSending();
        if (isMmsSent) {
            System.out.println("Mms wysłany");
        } else {
            System.out.println("Nie udało się wysłać mms'a");
        }
    }

    public void printAccountState() {
        System.out.println(phoneContract.toString());
    }

    public PhoneContract getPhoneContract() {
        return phoneContract;
    }

    public void setPhoneContract(PhoneContract phoneContract) {
        this.phoneContract = phoneContract;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Phone phone)) {
            return false;
        }
        return Objects.equals(phoneContract, phone.phoneContract);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneContract);
    }
}

package pl.javastart.task;

import pl.javastart.task.phone.CardPhoneContract;
import pl.javastart.task.phone.MixPhoneContract;
import pl.javastart.task.phone.Phone;
import pl.javastart.task.phone.SubscriptionPhoneContract;

public class Main {

    public static void main(String[] args) {
        Phone phone1 = new Phone(new CardPhoneContract(0.2, .1, .2, 0.5));
        System.out.println("Telefon na kartę");
        performPhoneUsage(phone1);
        System.out.println();

        Phone phone2 = new Phone(new SubscriptionPhoneContract(100));
        System.out.println("Telefon z abonamentem");
        performPhoneUsage(phone2);
        System.out.println();

        Phone phone3 = new Phone(new MixPhoneContract(0.1, 0.1, 0.3, 1, 1, 1, 1));
        System.out.println("Telefon mix");
        performPhoneUsage(phone3);
        System.out.println();
    }

    public static void performPhoneUsage(Phone phone) {
        phone.printAccountState();
        phone.sendSms();
        System.out.println();
        phone.printAccountState();
        phone.sendSms();
        System.out.println();
        phone.printAccountState();
        phone.sendSms();
        System.out.println();
        phone.printAccountState();
        System.out.println();
        phone.call(70);
        System.out.println();
        phone.printAccountState();
        System.out.println();
        phone.call(70);
        System.out.println();
        phone.printAccountState();
    }
}

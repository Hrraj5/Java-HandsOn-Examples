package org.example.javaexamples.designpatterns.behaviourial.observer;

public class StoreApp {
    public static void main(String[] args) {
        PhonesObservable iphonePhonesObservable = new IphonePhonesObservable();


        iphonePhonesObservable.add(new EmailNotificationObserver("hrithik.raj@gmail.com", iphonePhonesObservable));
        iphonePhonesObservable.add(new EmailNotificationObserver("raj.hrdeveloper@gmail.com", iphonePhonesObservable));

        iphonePhonesObservable.setData(100);
        iphonePhonesObservable.setData(9);

        System.out.println("Stock : " + iphonePhonesObservable.getStock());
    }
}

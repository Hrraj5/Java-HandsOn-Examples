package org.example.javaexamples.designpatterns.behaviourial.observer;

public class EmailNotificationObserver implements NotificationObserver {

    private String email;
    private PhonesObservable phonesObservable;

    public EmailNotificationObserver(String email, PhonesObservable phonesObservable){
        this.email = email;
        this.phonesObservable = phonesObservable;
    }
    @Override
    public void update() {
        sendEmail(this.email);
    }

    public void sendEmail(String email){
        System.out.println("Email sent to" + email);
    }
}

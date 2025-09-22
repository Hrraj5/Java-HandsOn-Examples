package org.example.javaexamples.designpatterns.behaviourial.observer;

import java.util.ArrayList;
import java.util.List;

public class IphonePhonesObservable implements PhonesObservable {
    List<NotificationObserver> notificationObservers = new ArrayList<>();
    private int stock = 0;

    @Override
    public void add(NotificationObserver notificationObserver) {
        this.notificationObservers.add(notificationObserver);
    }

    @Override
    public void remove(NotificationObserver notificationObserver) {
        this.notificationObservers.remove(notificationObserver);
    }

    @Override
    public void notify(List<NotificationObserver> notificationObservers) {
        for(NotificationObserver notificationObserver : notificationObservers){
            notificationObserver.update();
        }
    }

    @Override
    public void setData(int stock) {
        if(stock < 10){
            notify(notificationObservers);
        }
        this.stock+= stock;
    }

    @Override
    public int getStock(){
        return this.stock;
    }
}

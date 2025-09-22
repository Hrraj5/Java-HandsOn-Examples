package org.example.javaexamples.designpatterns.behaviourial.observer;

import java.util.List;

public interface PhonesObservable {

    void add(NotificationObserver notificationObserver);

    void remove(NotificationObserver notificationObserver);

    void notify(List<NotificationObserver> notificationObservers);

    void setData(int stock);

    int getStock();
}

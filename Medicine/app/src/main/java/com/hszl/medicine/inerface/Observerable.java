package com.hszl.medicine.inerface;

public interface Observerable {
    void registObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserver();
}

package com.plandiy.observer;

/**
 * Subject interface for the observer pattern.
 * Defines methods for attaching, detaching, and notifying observers.
 */
public interface Subject {

    /**
     * Adds an observer to the subject.
     *
     * @param observer the observer to attach
     */
    void attach(Observer observer);

    /**
     * Removes an observer from the subject.
     *
     * @param observer the observer to detach
     */
    void detach(Observer observer);

    /**
     * Notifies all attached observers of a change.
     */
    void notifyObservers();
}

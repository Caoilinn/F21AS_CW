package Model;

import View.IObserver;

public interface ISubject {
    public void registerObserver(IObserver obs);

    public void removeObserver(IObserver obs);

    public void notifyObservers();
}

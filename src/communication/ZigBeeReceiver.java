package communication;

import data.Database;
import farm.Cattle;
import farm.FarmLocator;
import observer.Observable;
import observer.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * This class observe Zigbee signals. Observe location data for this project.
 */
public class ZigBeeReceiver implements Observable {

    private List<Observer> observers = new ArrayList<>();
    private FarmLocator farmLocator;

    public ZigBeeReceiver(FarmLocator farmLocator) {
        this.farmLocator = farmLocator;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * For all out of farm cattle notify to farmer..
     *
     * @param obj
     */
    @Override
    public void notifyObserver(Object obj) {
        Cattle cattle = (Cattle) obj;
        observers.forEach(observer -> observer.notify(cattle));
    }

    public void send(Cattle cattle) {
        Database.instance().addLocation(cattle.getLocation());
        if (farmLocator.isOutOfFarm(cattle.getLocation())) {
            notifyObserver(cattle);
        }
    }
}
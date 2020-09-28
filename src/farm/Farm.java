package farm;

import communication.ZigBeeDevice;
import communication.ZigBeeReceiver;
import data.Location;
import visitor.Visitable;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Farm implements FarmLocator, Visitable {

    private List<Farmer> farmerList;
    private List<Cattle> cattleList;
    private Location location;
    private Double groundSize;

    public Farm(List<Farmer> farmerList, Location location, Double groundSize) {
        this.farmerList = farmerList;
        this.location = location;
        this.groundSize = groundSize;
        ZigBeeReceiver zigBeeReceiver = new ZigBeeReceiver(this);
        farmerList.forEach(zigBeeReceiver::addObserver);
        this.cattleList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            this.cattleList.add(new MilkCattle(new Location(), new ZigBeeDevice(zigBeeReceiver)).setId("" + i, true));
            this.cattleList.add(new BeefCattle(new Location(), new ZigBeeDevice(zigBeeReceiver)).setId("" + (i + 5), true));
        }
    }

    @Override
    public boolean isOutOfFarm(Location location) {
        return Math.abs(location.getX() - this.location.getX()) > groundSize
                || Math.abs(location.getY() - this.location.getY()) > groundSize;
    }

    public Double getGroundSize() {
        return groundSize;
    }

    public void setGroundSize(Double groundSize) {
        this.groundSize = groundSize;
    }

    @Override
    public void accept(Visitor visitor) {
        for (Cattle cattle : cattleList) {
            cattle.accept(visitor);
        }
    }
}

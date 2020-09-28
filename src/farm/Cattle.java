package farm;

import communication.BluetoothDevice;
import data.Location;
import data.SystemAdapter;
import food.AbstractFoodFactory;
import food.CarbohydratFood;
import food.FoodFactoryProvider;
import food.ProteinFood;
import visitor.Visitable;
import visitor.Visitor;

import java.util.Random;
import java.util.stream.IntStream;

public abstract class Cattle implements Visitable {

    private static final Random RANDOM = new Random();
    private Location location;
    private BluetoothDevice bluetoothDevice;
    private String id;
    private boolean isTempId;

    protected Cattle(Location location, BluetoothDevice bluetoothDevice) {
        this.location = location;
        this.bluetoothDevice = bluetoothDevice;
        updateLocation();
        updateEat();
    }

    /**
     * Random update cattle location.
     */
    private void updateLocation() {
        new Thread(() -> {
            while (true) {
                if (isSleep()) {
                    continue;
                }
                int any = RANDOM.nextInt(2);
                boolean positiveX = new Random().nextBoolean();
                boolean positiveY = new Random().nextBoolean();

                //create data for simulation
                location.setX(location.getX() + getSpeed() * (positiveX ? any : -any));
                location.setY(location.getY() + getSpeed() * (positiveY ? any : -any));

                //send to with cattle data with bluetooth device.
                bluetoothDevice.send(this);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    SystemAdapter.instance().errPrint(e.getMessage());
                }
                SystemAdapter.instance().outPrint("Id:" + id + "  " + location.toString());
            }

        }).start();
    }

    /**
     * Eat async.
     * Eat for cattle type.
     */
    private void updateEat() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(12500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                AbstractFoodFactory abstractFoodFactory = FoodFactoryProvider.getAbstractFoodFactory(getType());
                CarbohydratFood carbohydratFood = abstractFoodFactory.createCarbohydratFood();
                ProteinFood proteinFood = abstractFoodFactory.createProteinFood();
                SystemAdapter.instance().outPrint(getTypeName() + " cattle eat: " + carbohydratFood + " and " + proteinFood);
            }
        }).start();
    }

    /**
     * Template Method Design Pattern
     *
     * @implNote :type of cattle
     */
    protected abstract Class getType();

    /**
     * Template Method Design Pattern
     *
     * @implNote :type name of cattle
     */
    protected abstract String getTypeName();

    /**
     * Visitor pattern
     *
     * @param visitor : visit this cattle
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * Template Method Design Pattern
     *
     * @implNote :speed of cattle
     */
    protected abstract int getSpeed();

    /**
     * Template Method Design Pattern
     *
     * @implNote :Is cattle sleep
     */
    protected abstract boolean isSleep();

    public Cattle setId(String id, boolean isTempId) {
        this.id = id;
        this.isTempId = isTempId;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isTempId() {
        return isTempId;
    }

    public void setTempId(boolean tempId) {
        isTempId = tempId;
    }

    @Override
    public String toString() {
        return "Cattle{" + "location=" + location + ", id='" + id + '\'' + ", isTempId=" + isTempId + '}';
    }
}

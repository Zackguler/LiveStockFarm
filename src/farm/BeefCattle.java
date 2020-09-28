package farm;

import communication.BluetoothDevice;
import data.Location;
import data.SystemAdapter;
import food.AbstractFoodFactory;
import food.CarbohydratFood;
import food.FoodFactoryProvider;
import food.ProteinFood;

public class BeefCattle extends Cattle {

    public BeefCattle(Location location, BluetoothDevice bluetoothDevice) {
        super(location, bluetoothDevice);
    }

    @Override
    protected Class getType() {
        return BeefCattle.class;
    }

    @Override
    protected String getTypeName() {
        return "Beef";
    }

    @Override
    protected int getSpeed() {
        return 5;
    }

    @Override
    protected boolean isSleep() {
        return false;
    }
}

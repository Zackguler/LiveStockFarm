package farm;

import communication.BluetoothDevice;
import data.Location;

public class MilkCattle extends Cattle {

    public MilkCattle(Location location, BluetoothDevice bluetoothDevice) {
        super(location, bluetoothDevice);
    }

    @Override
    protected Class getType() {
        return MilkCattle.class;
    }

    @Override
    protected String getTypeName() {
        return "Milk";
    }

    @Override
    protected int getSpeed() {
        return 2;
    }

    @Override
    protected boolean isSleep() {
        return false;
    }
}

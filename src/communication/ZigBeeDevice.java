package communication;

import farm.Cattle;

public class ZigBeeDevice implements BluetoothDevice {
    private ZigBeeReceiver zigBeeReceiver;

    public ZigBeeDevice(ZigBeeReceiver zigBeeReceiver) {
        this.zigBeeReceiver=zigBeeReceiver;
    }

    @Override
    public void send(Object message) {
        zigBeeReceiver.send((Cattle) message);
    }
}

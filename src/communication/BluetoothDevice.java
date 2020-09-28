package communication;

/**
 * Stratgy pattern BluetoothDevice send message to receiver
 * but device type is changeable.
 */
public interface BluetoothDevice {
    void send(Object message);
}

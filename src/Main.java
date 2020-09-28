import communication.BluetoothDevice;
import communication.ZigBeeDevice;
import communication.ZigBeeReceiver;
import data.Location;
import farm.*;
import visitor.Inspector;
import visitor.Veterinary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Main {

        public static void main(String[] args) {
	    Farm farm = new Farm(new ArrayList<>(Arrays.asList(new Farmer(), new Farmer(), new Farmer())), new Location(),
			    20.00);
	    //Inspector visit farm
	    farm.accept(new Inspector());
	    //Veterinary visit farm
	    farm.accept(new Veterinary());
        }
}

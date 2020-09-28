package farm;

import data.Location;
import data.SystemAdapter;
import observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class Farmer implements Observer {
    private static List<Cattle> catchingList = new ArrayList<>();
    private boolean isBusy;
    @Override
    public void notify(Object o) {
        if (!(o instanceof Cattle)) {
            SystemAdapter.instance().errPrint("Farmer says: It's not a Cattle. No action for me");
            return;
        }
        Cattle cattle = (Cattle) o;
        if (isBusy) {
            SystemAdapter.instance().errPrint("Farmer says: I'm too busy why notify me!");
            return;
        }
        if (catchingList.indexOf(cattle) != -1) {
            SystemAdapter.instance().outPrint("Farmer says: Another farmer catching...");
            return;
        }
        isBusy = true;
        catchingList.add(cattle);
        new Thread(() -> {
            try {
                catchCattle(cattle);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public boolean isBusy() {
        return isBusy;
    }
    private void catchCattle(Cattle cattle) throws InterruptedException {
        Thread.sleep(20000);
        SystemAdapter.instance().outPrint("Cattle catched:" + cattle.toString());
        cattle.setLocation(new Location());
        catchingList.remove(cattle);
        isBusy = false;
    }
}

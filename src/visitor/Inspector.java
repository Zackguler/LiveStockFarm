package visitor;

import data.SystemAdapter;
import farm.Cattle;

import java.util.Random;

public class Inspector implements Visitor {
    @Override
    public void visit(Cattle cattle) {
        SystemAdapter.instance().outPrint("Inspect to "+cattle);
        if (cattle.isTempId()) {
            cattle.setTempId(false);
            cattle.setId(new Random().nextInt(7000)+"-ACPT");
        }
    }
}

package visitor;

import data.SystemAdapter;
import farm.Cattle;

/**
 * Cattle visitor. Its duty is vaccine to cattle.
 */
public class Veterinary implements Visitor {
    @Override
    public void visit(Cattle cattle) {
        SystemAdapter.instance().outPrint("Vaccine to "+cattle);
    }
}

package data;

import com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaLoader;

import java.util.ArrayList;
import java.util.List;

public class Database {

        private static Database database;
        private List<Location> locationList;

        private Database(){
                locationList=new ArrayList<>();
        }

        public static Database instance() {
	    if (database == null) {
	            database=new Database();
	    }
	    return database;
        }

        public void addLocation(Location location) {
	    locationList.add(location);
        }
}

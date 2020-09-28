package food;

import farm.MilkCattle;
import farm.BeefCattle;

public class FoodFactoryProvider {
    public static AbstractFoodFactory getAbstractFoodFactory(Class type) {
        if (type == MilkCattle.class) {
            return new MilkCattleFoodFactory();
        } else if (type == BeefCattle.class) {
            return new BeefCattleFoodFactory();
        }
        //Default:
        return null;
    }
}

package food;

public class MilkCattleFoodFactory implements AbstractFoodFactory {
    @Override
    public CarbohydratFood createCarbohydratFood() {
        return new Corn();
    }

    @Override
    public ProteinFood createProteinFood() {
        return new Soybean();
    }
}

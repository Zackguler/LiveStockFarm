package food;

public class BeefCattleFoodFactory implements AbstractFoodFactory {
    @Override
    public CarbohydratFood createCarbohydratFood() {
        return new Wheat();
    }

    @Override
    public ProteinFood createProteinFood() {
        return new Canola();
    }
}

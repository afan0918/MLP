package activationfunction;

/**
 * @author Afan Chen
 */
public class GrowingCosineUnit implements ActivationFunction {
    @Override
    public double function(double x) {
        return x * Math.cos(x);
    }

    @Override
    public double derivate(double x) {
        return Math.cos(x) - x * Math.sin(x);
    }
}

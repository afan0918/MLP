package activationfunction;

/**
 * @author Afan Chen
 */
public class ReLU implements ActivationFunction {
    @Override
    public double function(double x) {
        return Math.max(x, 0.0);
    }

    @Override
    public double derivate(double x) {
        return 1.0;
    }
}

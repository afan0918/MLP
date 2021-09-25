package activationfunction;

/**
 * @author Afan Chen
 */
public class Gaussian implements ActivationFunction {
    @Override
    public double function(double x) {
        return Math.exp(-Math.pow(x, 2));
    }

    @Override
    public double derivate(double x) {
        return -2 * x * Math.exp(-Math.pow(x, 2));
    }
}

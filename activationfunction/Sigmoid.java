package activationfunction;

/**
 * @author Afan Chen
 */
public class Sigmoid implements ActivationFunction {
    @Override
    public double function(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    @Override
    public double derivate(double x) {
        return (x - Math.pow(x, 2));
    }
}


package activationfunction;

/**
 * @author Afan Chen
 */
public class SiLU implements ActivationFunction {
    @Override
    public double function(double x) {
        return x / (1 + Math.exp(-x));
    }

    @Override
    public double derivate(double x) {
        return (1 + Math.exp(-x) + x * Math.exp(-x)) / Math.pow(1 + Math.exp(-x), 2);
    }
}

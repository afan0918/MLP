package activationfunction;

/**
 * @author Afan Chen
 */
public class Tanh implements ActivationFunction {
    @Override
    public double function(double x) {
        return (Math.exp(x) - Math.exp(-x)) / (Math.exp(x) + Math.exp(-x));
    }

    @Override
    public double derivate(double x) {
        return 1 - Math.pow(x, 2);
    }
}

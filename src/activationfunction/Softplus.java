package activationfunction;

/**
 * @author Afan Chen
 */
public class Softplus implements ActivationFunction {
    @Override
    public double function(double x) {
        double tmp = 1 + Math.exp(x);
        return -Math.log1p(-tmp) / tmp;
    }

    @Override
    public double derivate(double x) {
        return 1 / (1 + Math.exp(-x));
    }
}

package activationfunction;

/**
 * @author Afan Chen
 */
public class LeakyReLU implements ActivationFunction {
    @Override
    public double function(double x) {
        if (x >= 0) return x;
        else return 0.01 * x;
    }

    @Override
    public double derivate(double x) {
        if (x >= 0) return 1;
        else return 0.01;
    }
}

package activationfunction;

/**
 * 激勵函數
 * 主要是參考維基百科寫出來的，如果沒有眼殘就是這樣。
 * @author Afan Chen
 */
public interface ActivationFunction {
    /**
     * 函數本身
     */
    public double function(double x);

    /**
     * 導函數
     */
    public double derivate(double x);
}

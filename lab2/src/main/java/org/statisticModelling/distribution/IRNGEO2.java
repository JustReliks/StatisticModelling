package org.statisticModelling.distribution;

public class IRNGEO2 extends AbstractIRNGEO {

    public IRNGEO2(double P) {
        super(P);
    }

    @Override
    public double generate(int n) {
        double a = Math.random();
        int m = 0;
        while (a > getP()) {
            a = Math.random();
            m++;
        }
        return m;
    }
}

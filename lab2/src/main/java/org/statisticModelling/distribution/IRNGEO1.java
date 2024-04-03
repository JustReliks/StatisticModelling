package org.statisticModelling.distribution;

public class IRNGEO1 extends AbstractIRNGEO {

    public IRNGEO1(double P) {
        super(P);
    }

    @Override
    public double generate(int n) {
        double a = Math.random();
        double pt = getP();
        int m = 0;
        while (a - pt >= 0) {
            a -= pt;
            pt *= (1 - getP());
            m++;
        }
        return m;
    }
}

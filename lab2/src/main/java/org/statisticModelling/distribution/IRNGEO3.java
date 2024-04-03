package org.statisticModelling.distribution;

public class IRNGEO3 extends AbstractIRNGEO {

    public IRNGEO3(double P) {
        super(P);
    }

    @Override
    public double generate(int n) {
        double a = Math.random();
        return Math.log(a) / Math.log(1 - getP());
    }
}

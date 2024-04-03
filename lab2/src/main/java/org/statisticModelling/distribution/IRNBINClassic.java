package org.statisticModelling.distribution;

public class IRNBINClassic extends AbstractIRNBIN {


    public IRNBINClassic(int N, double P) {
        super(N, P);
    }

    @Override
    public double generate(int n) {
        double a = Math.random();
        double pr = Math.pow(1 - getP(), n);
        int m = 0;
        while (a - pr >= 0) {
            a -= pr;
            pr *= ((double) (getP() * (n - m)) / ((m + 1) * (1 - getP())));
            m++;
        }
        return Math.round(m);
    }


}

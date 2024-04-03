package org.statisticModelling.distribution;

public class IRNPOI2 extends AbstractIRNPOI {

    public IRNPOI2(int L) {
        super(L);
    }

    @Override
    public double generate(int n) {
        if (getL() >= 88) {
            return Math.round(getNormalDistribution().sample());
        }
        double a = Math.random();
        double pt = a;
        int m = 1;
        while (pt >= Math.exp(-getL())) {
            a = Math.random();
            pt *= a;
            m++;
        }
        return m;
    }
}

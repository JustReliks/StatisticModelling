package org.statisticModelling.distribution;

public class IRNPOI1 extends AbstractIRNPOI {

    public IRNPOI1(int L) {
        super(L);
    }

    @Override
    public double generate(int n) {
        if (getL() >= 88) {
            return Math.round(getNormalDistribution().sample());
        }
        double a = Math.random();
        double pt = Math.exp(-getL());
        int m = 1;
        while (a - pt >= 0) {
            a -= pt;
            pt = pt * getL() / m;
            m++;
        }
        return m;
    }
}

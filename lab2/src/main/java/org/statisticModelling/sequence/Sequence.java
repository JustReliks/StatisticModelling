package org.statisticModelling.sequence;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.statisticModelling.distribution.Distribution;

import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.DoubleStream;

@RequiredArgsConstructor
public class Sequence {

    @Getter
    private final double[] sequence;
    @Getter
    private final int size;
    private OptionalDouble variance = OptionalDouble.empty();
    private OptionalDouble dispersion = OptionalDouble.empty();

    public Sequence(Distribution distribution, int size) {
        this.size = size;
        AtomicInteger integer = new AtomicInteger(0);
        this.sequence = DoubleStream.generate(() -> distribution.generate(integer.getAndIncrement())).limit(size).toArray();
    }

    public double getVariance() {
        if (variance.isEmpty()) {
            variance = Arrays.stream(sequence).average();
        }
        return variance.getAsDouble();
    }

    public double getDispersion() {
        if (dispersion.isEmpty()) {
            dispersion = OptionalDouble.of(
                    Arrays.stream(sequence).reduce(0, (left, right) -> left + Math.pow(right - getVariance(), 2)) / size
            );
        }
        return dispersion.getAsDouble();
    }


}

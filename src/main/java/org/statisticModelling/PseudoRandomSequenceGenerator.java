package org.statisticModelling;

import org.apache.commons.math3.distribution.BinomialDistribution;

import java.util.Random;

public class PseudoRandomSequenceGenerator {

    private final Random random;
    private final int size;
    private double[] sequence;
    private Double mean;
    private Double variance;

    private boolean generated = false;

    public PseudoRandomSequenceGenerator(int size) {
        this.size = size;
        this.random = new Random();
        sequence = new double[size];
        //this.sequence = generateSequence();
    }

    // Генерируем псевдослучайную последовательность чисел заданного размера
    public void generateSequence() {

        for (int i = 0; i < size; i++) {
            sequence[i] = random.nextDouble();
        }
        generated = true;
    }

    public double[] getSequence() {
        return sequence;
    }

    // Функция для расчета математического ожидания
    public double calculateMean() {
        if (!generated) throw new RuntimeException();
        if (mean != null) return mean;
        double sum = 0;
        for (double value : sequence) {
            sum += value;
        }
        return mean = sum / sequence.length;
    }

    // Функция для расчета дисперсии
    public double calculateVariance() {
        if (!generated) throw new RuntimeException();
        if (variance != null) return variance;
        double mean = calculateMean();
        double sumOfSquares = 0;
        for (double value : sequence) {
            sumOfSquares += Math.pow(value - mean, 2);
        }
        return variance = sumOfSquares / sequence.length;
    }

    // Функция для расчета стандартного отклонения (квадратный корень из дисперсии)
    public double calculateStandardDeviation() {
        if (!generated) throw new RuntimeException();
        return Math.sqrt(calculateVariance());
    }

    public double[] calculateCorrelogramData() {
        double[] res = new double[size];
        for (int f = 1; f < size; f++) {
            double sum = 0;
            for (int i = 0; i < size - f; i++) {
                sum += (sequence[i] - calculateMean()) * (sequence[i + f] - calculateMean());
            }
            res[f] = sum / calculateVariance();
        }
        return res;

    }
}

package org.statisticModelling.graphics;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

import java.io.File;
import java.io.IOException;

public class Histogram  {

    public Histogram(double[] x, String path) throws IOException {
        HistogramDataset dataset = new HistogramDataset();

        dataset.addSeries("Data", x, 20);
        dataset.setType(HistogramType.RELATIVE_FREQUENCY);
        // Create chart
        JFreeChart chart = ChartFactory.createHistogram(
                "Гистограмма",
                "x",
                "f(x)",
                dataset
        );

        ChartUtils.saveChartAsPNG(new File(path + "histogram.png"), chart, 1280, 720);
    }
}


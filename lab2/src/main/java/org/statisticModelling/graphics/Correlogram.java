package org.statisticModelling.graphics;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Correlogram {

    public Correlogram(List<Point> x, String path) throws IOException {
        DefaultXYDataset dataset = new DefaultXYDataset();

        double[][] waveform = new double[2][x.size()];
        for (int i = 0; i < x.size(); i++) {
            waveform[0][i] = x.get(i).getX();
            waveform[1][i] = x.get(i).getY();
        }
        dataset.addSeries("Data", waveform);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Коррелограмма",
                "f",
                "K(f)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        ChartUtils.saveChartAsPNG(new File(path + "correlogram.png"), chart, 1280, 720);
    }
}

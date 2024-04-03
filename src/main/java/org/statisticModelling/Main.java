package org.statisticModelling;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringJoiner;

public class Main {

    private static final int[] N = {10, 100, 1000, 10_000, 100_000};
    private static final String[] headers = {"N", "M", "D", "M_st", "D_st", "M_diff", "D_diff"};

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\programming\\StatisticModelling\\src\\main\\resources\\sequences.xlsx");
        Workbook wb = new SXSSFWorkbook();
        SXSSFSheet sheet = (SXSSFSheet) wb.createSheet();
        SXSSFRow headersRow = sheet.createRow(0);
        for (int j = 0; j < headers.length; j++) {
            headersRow.createCell(j).setCellValue(headers[j]);
        }
        PseudoRandomSequenceGenerator generator;
        for (int i = 0; i < N.length; i++) {
            int n = N[i];
            generator = new PseudoRandomSequenceGenerator(n);
            generator.generateSequence();
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(n);
            double M = generator.calculateMean();
            row.createCell(1).setCellValue(M);
            double D = generator.calculateVariance();
            row.createCell(2).setCellValue(D);
            double M_st = 0.5;
            row.createCell(3).setCellValue(M_st);
            double D_st = 0.08333;
            row.createCell(4).setCellValue(D_st);
            row.createCell(5).setCellValue(Math.abs(M - M_st));
            row.createCell(6).setCellValue(Math.abs(D - D_st));

            File f = new File("D:\\programming\\StatisticModelling\\src\\main\\resources\\data_" + n + ".txt");
            f.createNewFile();
            double[] sequence = generator.getSequence();
            StringJoiner joiner = new StringJoiner(",");
            StringJoiner joiner2 = new StringJoiner(",");
            Arrays.stream(generator.calculateCorrelogramData()).forEach(value -> joiner2.add(String.valueOf(value)));
            Arrays.stream(sequence).forEach(value -> joiner.add(String.valueOf(value)));
            try (FileWriter writer = new FileWriter(f)) {
                writer.write(joiner.toString());
                writer.write('\n');
                writer.write(joiner2.toString());
            }
        }
        if (file.exists()) {
            file.delete();
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            wb.write(fileOutputStream);
        }
        wb.close();

    }
}
package org.statisticModelling.graphics;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Table extends JFrame {

    public Table(Object[][] data, String[] colName) throws IOException {
        super("Таблица");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTable table = new JTable(data, colName);
        table.setPreferredSize(new Dimension(450, 180));

        super.getContentPane().add(new JScrollPane(table));

    }

}

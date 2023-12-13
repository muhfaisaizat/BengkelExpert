package com.bengkel.chart;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class ChartPanel extends JFrame {
    private JFXPanel fxPanel;
    private Connection connection;

    public ChartPanel() {
        initDatabase();
        initFX();
    }

    private void initDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3306/bengkel";
            String user = "root";
            String password = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initFX() {
        fxPanel = new JFXPanel();
        setPreferredSize(new Dimension(400, 200));
        add(fxPanel);

        Platform.runLater(() -> createChart());
    }

    private void createChart() {
        try {
            CategoryAxis xAxis = new CategoryAxis();
            xAxis.setLabel("");
            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("total");

            LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);

            XYChart.Series<String, Number> data = new XYChart.Series<>();
            data.setName("transaksi");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM transaksi");
            while (resultSet.next()) {
                String idT = resultSet.getString("ID_Transaksi");
                double total = resultSet.getDouble("Total_Harga");
                data.getData().add(new XYChart.Data<>(idT, total));
            }
            resultSet.close();
            statement.close();

            lineChart.getData().add(data);

            Scene scene = new Scene(lineChart, 400, 200);
            fxPanel.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new ChartPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

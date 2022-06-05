package com.salekseev.booksmarketclient.view.order.chart;

import com.salekseev.booksmarketclient.model.BookReport;
import com.salekseev.booksmarketclient.service.retrofit.BookMarketService;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;

import java.util.List;

public class BookSoldChart extends StackPane {

    private final BookMarketService service = BookMarketService.getInstance();

    public BookSoldChart() {
        createChart();
    }

    private void createChart() {
        List<BookReport> bookReports = service.getBookReports().join();

        PieChart pieChart = new PieChart();
        pieChart.setLegendSide(Side.LEFT);

        for (BookReport book : bookReports) {
            PieChart.Data slice = new PieChart.Data(book.getTitle(), book.getCountSold());
            pieChart.getData().add(slice);
        }

        getChildren().add(pieChart);
    }

}

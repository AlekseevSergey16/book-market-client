package com.salekseev.booksmarketclient.model;

public class BookReport {

    private Long id;
    private String title;
    private Genre genre;
    private Double cost;
    private Integer countSold;
    private Double totalCostSold;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getCountSold() {
        return countSold;
    }

    public void setCountSold(Integer countSold) {
        this.countSold = countSold;
    }

    public Double getTotalCostSold() {
        return totalCostSold;
    }

    public void setTotalCostSold(Double totalCostSold) {
        this.totalCostSold = totalCostSold;
    }
}

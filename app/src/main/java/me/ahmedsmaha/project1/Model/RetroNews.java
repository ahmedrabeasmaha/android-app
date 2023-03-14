package me.ahmedsmaha.project1.Model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;

public class RetroNews {

    @SerializedName("status")
    private String status;
    @SerializedName("totalResults")
    private Integer totalResults;
    @SerializedName("articles")
    private List<RetroArticles> articles;

    public RetroNews(String status, Integer totalResults, List<RetroArticles> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public List<RetroArticles> getArticles() {
        return articles;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }


    public void setArticles(List<RetroArticles> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "NewsApiResponseDTO{" + "status = '" + status + '\'' + ",totalResults = '" + totalResults + '\'' + ",articles = '" + articles + '\'' + "}";
    }

}


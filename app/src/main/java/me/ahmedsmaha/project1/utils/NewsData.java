package me.ahmedsmaha.project1.utils;

import android.graphics.drawable.Drawable;

public class NewsData {
    String name;
    String price;
    String currency;

    Drawable image;

    public NewsData(String name, String price, String currency, Drawable image) {
        this.name = name;
        this.price = price;
        this.currency = currency;
        this.image = image;
    }
}

package com.example.sqlitecountryapplication;

public class Data {
    int id;
    String country, currency;

    public Data(int id, String country, String currency) {
        this.id = id;
        this.country = country;
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return id+")"+"  "+country+"-"+currency;

    }
}

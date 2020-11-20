package com.pores.webscrapper.client;

import java.math.BigDecimal;
import java.util.List;

public class ProductResource {

    String poresRef;
    String brand;
    String imageLocation;
    String name;
    List<Ingredient> ingredients;
    String volume;
    List<String> skinType;
    BigDecimal rating_pores;
    BigDecimal rating_web;
    String productType;
    List<String> beautyGoal;
    String formulation;
    String spf;
    BigDecimal pores_price;
    List<PriceObject> prices;


    public ProductResource() {
    }

    public ProductResource(String poresRef, String brand, String imageLocation, String name, List<Ingredient> ingredients, String volume, List<String> skinType, BigDecimal rating_pores, BigDecimal rating_web, String productType, List<String> beautyGoal, String formulation, String spf, BigDecimal pores_price, List<PriceObject> prices) {
        this.poresRef = poresRef;
        this.brand = brand;
        this.imageLocation = imageLocation;
        this.name = name;
        this.ingredients = ingredients;
        this.volume = volume;
        this.skinType = skinType;
        this.rating_pores = rating_pores;
        this.rating_web = rating_web;
        this.productType = productType;
        this.beautyGoal = beautyGoal;
        this.formulation = formulation;
        this.spf = spf;
        this.pores_price = pores_price;
        this.prices = prices;
    }

    public String getPoresRef() {
        return poresRef;
    }

    public void setPoresRef(String poresRef) {
        this.poresRef = poresRef;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public List<String> getSkinType() {
        return skinType;
    }

    public void setSkinType(List<String> skinType) {
        this.skinType = skinType;
    }

    public BigDecimal getRating_pores() {
        return rating_pores;
    }

    public void setRating_pores(BigDecimal rating_pores) {
        this.rating_pores = rating_pores;
    }

    public BigDecimal getRating_web() {
        return rating_web;
    }

    public void setRating_web(BigDecimal rating_web) {
        this.rating_web = rating_web;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public List<String> getBeautyGoal() {
        return beautyGoal;
    }

    public void setBeautyGoal(List<String> beautyGoal) {
        this.beautyGoal = beautyGoal;
    }

    public String getFormulation() {
        return formulation;
    }

    public void setFormulation(String formulation) {
        this.formulation = formulation;
    }

    public String getSpf() {
        return spf;
    }

    public void setSpf(String spf) {
        this.spf = spf;
    }

    public BigDecimal getPores_price() {
        return pores_price;
    }

    public void setPores_price(BigDecimal pores_price) {
        this.pores_price = pores_price;
    }

    public List<PriceObject> getPrices() {
        return prices;
    }

    public void setPrices(List<PriceObject> prices) {
        this.prices = prices;
    }
}

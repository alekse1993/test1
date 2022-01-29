package com.company.parsing;

import java.util.List;

public class ProductDto {
    Integer vendorCode;
    List<String> photoLinks;
    String goodsName;
    String brandName;
    Integer brandId;
    Integer priceWithSale;
    Integer priceWithoutSale;
    Integer soldCount;

    public Integer getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(Integer vendorCode) {
        this.vendorCode = vendorCode;
    }

    public List<String> getPhotoLinks() {
        return photoLinks;
    }

    public void setPhotoLinks(List<String> photoLinks) {
        this.photoLinks = photoLinks;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getPriceWithSale() {
        return priceWithSale;
    }

    public void setPriceWithSale(Integer priceWithSale) {
        this.priceWithSale = priceWithSale;
    }

    public Integer getPriceWithoutSale() {
        return priceWithoutSale;
    }

    public void setPriceWithoutSale(Integer priceWithoutSale) {
        this.priceWithoutSale = priceWithoutSale;
    }

    public Integer getSoldCount() {
        return soldCount;
    }

    public void setSoldCount(Integer soldCount) {
        this.soldCount = soldCount;
    }
}

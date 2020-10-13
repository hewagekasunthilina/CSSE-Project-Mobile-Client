package com.example.mobile_client;

public class Po {
    private Integer OrderID;
    private String RefNo, Material,Description,Supplier;
    private Double Price;
    private Float Quantity;
    private String Site,DelDate;

    public Po(String orderID, String refNo, String material, String description, String supplier, String price, String quantity, String site, String delDate) {
    }

    public Po(Integer orderID, String refNo, String material, String description, String supplier, Double price, Float quantity, String site, String delDate) {
        OrderID = orderID;
        RefNo = refNo;
        Material = material;
        Description = description;
        Supplier = supplier;
        Price = price;
        Quantity = quantity;
        Site = site;
        DelDate = delDate;
    }

    public Integer getOrderID() {
        return OrderID;
    }

    public void setOrderID(Integer orderID) {
        OrderID = orderID;
    }

    public String getRefNo() {
        return RefNo;
    }

    public void setRefNo(String refNo) {
        RefNo = refNo;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String material) {
        Material = material;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getSupplier() {
        return Supplier;
    }

    public void setSupplier(String supplier) {
        Supplier = supplier;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public Float getQuantity() {
        return Quantity;
    }

    public void setQuantity(Float quantity) {
        Quantity = quantity;
    }

    public String getSite() {
        return Site;
    }

    public void setSite(String site) {
        Site = site;
    }

    public String getDelDate() {
        return DelDate;
    }

    public void setDelDate(String delDate) {
        DelDate = delDate;
    }
}

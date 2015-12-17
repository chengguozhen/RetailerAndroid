package com.neighbor.retailer_android.bean;

import java.util.List;

/**
 * Created by Vicky on 2015/12/17.
 * Retailer_android
 * contact way: 317461087@qq.com
 */
public class MerchandiseItemBean {
    /**
     * 商品编号
     */
    private String merchandiseId;
    /**
     * 商品名称
     */
    private String merchandiseName;
    /**
     * 商品图片url列表
     */
    private List<String> merchandiseUrl;
    /**
     * 商品状态
     */
    private String merchandiseStatus;
    /**
     * 商品单价
     */
    private String unitPrice;
    /**
     * 生产日期
     */
    private String dateOfManufacture;
    /**
     * 保质期
     */
    private String qualifyPeriod;
    /**
     * 规格
     */
    private String specifications;
    /**
     * 所属批发商
     */
    private String wholesaler;
    /**
     * 类别
     */
    private String category;
    /**
     * 已售数量
     */
    private String saledCounts;
    /**
     * 库存数量
     */
    private String inventoryCounts;

    public String getMerchandiseId() {
        return merchandiseId;
    }

    public void setMerchandiseId(String merchandiseId) {
        this.merchandiseId = merchandiseId;
    }

    public String getMerchandiseName() {
        return merchandiseName;
    }

    public void setMerchandiseName(String merchandiseName) {
        this.merchandiseName = merchandiseName;
    }

    public List<String> getMerchandiseUrl() {
        return merchandiseUrl;
    }

    public void setMerchandiseUrl(List<String> merchandiseUrl) {
        this.merchandiseUrl = merchandiseUrl;
    }

    public String getMerchandiseStatus() {
        return merchandiseStatus;
    }

    public void setMerchandiseStatus(String merchandiseStatus) {
        this.merchandiseStatus = merchandiseStatus;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(String dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }

    public String getQualifyPeriod() {
        return qualifyPeriod;
    }

    public void setQualifyPeriod(String qualifyPeriod) {
        this.qualifyPeriod = qualifyPeriod;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getWholesaler() {
        return wholesaler;
    }

    public void setWholesaler(String wholesaler) {
        this.wholesaler = wholesaler;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSaledCounts() {
        return saledCounts;
    }

    public void setSaledCounts(String saledCounts) {
        this.saledCounts = saledCounts;
    }

    public String getInventoryCounts() {
        return inventoryCounts;
    }

    public void setInventoryCounts(String inventoryCounts) {
        this.inventoryCounts = inventoryCounts;
    }

    /**
     * 加入购物车数量
     */
    private int purchaseNumber;
    /**
     * 起批数
     */
    private int initNumber;

    public int getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(int purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public int getInitNumber() {
        return initNumber;
    }

    public void setInitNumber(int initNumber) {
        this.initNumber = initNumber;
    }
}

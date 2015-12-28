package com.neighbor.retailer_android.bean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Vicky on 2015/12/17.
 * Retailer_android
 * contact way: 317461087@qq.com
 */
/**
 * goodCategory : 神奇的分类
 * stock : 123
 * MANY_PRICE : null
 * sales : null
 * leastBuyCnt : 124
 * dateExpiry : 2015-12-25
 * dateInPro : 2015-12-25
 * midName : null
 * goodName : 123
 * goodCol : null
 */
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MerchandiseItemBean {
    /**
     * 商品编号
     */
    private String merchandiseId;
    /**
     * 商品名称
     */
    @JsonProperty("goodName")
    private String merchandiseName;
    /**
     * 商品图片url列表
     */
    private List<String> merchandiseUrl;
    /**
     * 测试url
     */
    @JsonProperty("URL")
    private String url;
    /**
     * 商品状态
     */
    private String merchandiseStatus;
    /**
     * 商品单价
     */
    @JsonProperty("manyPrice")
    private String unitPrice;

    /**
     * 商品批发价格
     */
    private String cellPrice;
    /**
     * 生产日期
     */
    @JsonProperty("dateInPro")
    private String dateOfManufacture;
    /**
     * 保质期
     */
    @JsonProperty("dateExpire")
    private String qualifyPeriod;
    /**
     * 规格
     */
    @JsonProperty("goodCol")
    private String specifications;
    /**
     * 所属批发商
     */
    @JsonProperty("midName")
    private String wholesaler;
    /**
     * 类别
     */
    @JsonProperty("goodCategory")
    private String category;
    /**
     * 已售数量
     */
    @JsonProperty("sales")
    private String saledCounts;
    /**
     * 库存数量
     */
    @JsonProperty("stock")
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCellPrice() {
        return cellPrice;
    }

    public void setCellPrice(String cellPrice) {
        this.cellPrice = cellPrice;
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
    @JsonProperty("leastBuyCnt")
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

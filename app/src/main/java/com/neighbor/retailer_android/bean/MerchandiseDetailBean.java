package com.neighbor.retailer_android.bean;

/**
 * Created by Vicky on 2015/12/25.
 * Retailer_android
 * contact way: 317461087@qq.com
 */
public class MerchandiseDetailBean {

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

    private String goodCategory;  //商品分类
    private int stock;  //存货
    private double MANY_PRICE;//批发价,单价
    private int sales; //已售
    private int leastBuyCnt;  //起批数
    private String dateExpiry;
    private String dateInPro;
    private String midName; //批发商名
    private String goodName; //商品名
    private String goodCol;  //规格

    public void setGoodCategory(String goodCategory) {
        this.goodCategory = goodCategory;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setMANY_PRICE(double MANY_PRICE) {
        this.MANY_PRICE = MANY_PRICE;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public void setLeastBuyCnt(int leastBuyCnt) {
        this.leastBuyCnt = leastBuyCnt;
    }

    public void setDateExpiry(String dateExpiry) {
        this.dateExpiry = dateExpiry;
    }

    public void setDateInPro(String dateInPro) {
        this.dateInPro = dateInPro;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public void setGoodCol(String goodCol) {
        this.goodCol = goodCol;
    }

    public String getGoodCategory() {
        return goodCategory;
    }

    public int getStock() {
        return stock;
    }

    public Object getMANY_PRICE() {
        return MANY_PRICE;
    }

    public Object getSales() {
        return sales;
    }

    public int getLeastBuyCnt() {
        return leastBuyCnt;
    }

    public String getDateExpiry() {
        return dateExpiry;
    }

    public String getDateInPro() {
        return dateInPro;
    }

    public Object getMidName() {
        return midName;
    }

    public String getGoodName() {
        return goodName;
    }

    public Object getGoodCol() {
        return goodCol;
    }
}

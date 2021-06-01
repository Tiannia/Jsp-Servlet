package com.tian.entity;

public class ORDER_DETAIL {
    private int order_detail_id;
    private int order_id; //跟踪订单ID
    private int product_id;
    private String product_name;
    private int product_cnt;
    private int product_price;
    private String product_filename;

    public int getOrder_detail_id() {
        return order_detail_id;
    }

    public void setOrder_detail_id(int order_detail_id) {
        this.order_detail_id = order_detail_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_cnt() {
        return product_cnt;
    }

    public void setProduct_cnt(int product_cnt) {
        this.product_cnt = product_cnt;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public String getProduct_filename() {
        return product_filename;
    }

    public void setProduct_filename(String product_filename) {
        this.product_filename = product_filename;
    }

    public ORDER_DETAIL(int order_detail_id, int order_id, int product_id, String product_name, int product_cnt, int product_price, String product_filename) {
        this.order_detail_id = order_detail_id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_cnt = product_cnt;
        this.product_price = product_price;
        this.product_filename = product_filename;
    }
}

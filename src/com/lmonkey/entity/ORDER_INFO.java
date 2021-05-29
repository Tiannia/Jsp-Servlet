package com.lmonkey.entity;

public class ORDER_INFO {
    private int order_id;
    private String order_sn;
    private String user_id;

    private int receive_address_id;
    private int payment_method;
    private int payment_money;
    private String pay_time;
    private String shipping_comp_name;
    private String shipping_sn;
    private String shipping_time;
    private String receive_time;
    int order_status; //订单状态，2已经收货，1运输中，0未发货
    //private String modified_time; 最后修改时间


    public ORDER_INFO(int order_id, String order_sn, String user_id, int receive_address_id, int payment_method, int payment_money, String pay_time, String shipping_comp_name, String shipping_sn, String shipping_time, String receive_time, int order_status) {
        this.order_id = order_id;
        this.order_sn = order_sn;
        this.user_id = user_id;
        this.receive_address_id = receive_address_id;
        this.payment_method = payment_method;
        this.payment_money = payment_money;
        this.pay_time = pay_time;
        this.shipping_comp_name = shipping_comp_name;
        this.shipping_sn = shipping_sn;
        this.shipping_time = shipping_time;
        this.receive_time = receive_time;
        this.order_status = order_status;
    }

    public int getReceive_address_id() {
        return receive_address_id;
    }

    public void setReceive_address_id(int receive_address_id) {
        this.receive_address_id = receive_address_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public int getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(int payment_method) {
        this.payment_method = payment_method;
    }

    public int getPayment_money() {
        return payment_money;
    }

    public void setPayment_money(int payment_money) {
        this.payment_money = payment_money;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public String getShipping_comp_name() {
        return shipping_comp_name;
    }

    public void setShipping_comp_name(String shipping_comp_name) {
        this.shipping_comp_name = shipping_comp_name;
    }

    public String getShipping_sn() {
        return shipping_sn;
    }

    public void setShipping_sn(String shipping_sn) {
        this.shipping_sn = shipping_sn;
    }

    public String getShipping_time() {
        return shipping_time;
    }

    public void setShipping_time(String shipping_time) {
        this.shipping_time = shipping_time;
    }

    public String getReceive_time() {
        return receive_time;
    }

    public void setReceive_time(String receive_time) {
        this.receive_time = receive_time;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

}

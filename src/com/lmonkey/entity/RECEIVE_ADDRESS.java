package com.lmonkey.entity;

public class RECEIVE_ADDRESS {
    private int receive_address_id;
    private String user_id;
    private String receive_name;
    private String province;
    private String city;
    private String district;
    private String send_place; //1 学校 2 家庭 3 公司
    private String address;
    private String zipcode;
    private String telephone;

    public int getReceive_address_id() {
        return receive_address_id;
    }

    public void setReceive_address_id(int receive_address_id) {
        this.receive_address_id = receive_address_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getReceive_name() {
        return receive_name;
    }

    public void setReceive_name(String receive_name) {
        this.receive_name = receive_name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSend_place() {
        return send_place;
    }

    public void setSend_place(String send_place) {
        this.send_place = send_place;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


    public RECEIVE_ADDRESS(int receive_address_id, String user_id, String receive_name, String province, String city, String district, String send_place, String address, String zipcode, String telephone) {
        this.receive_address_id = receive_address_id;
        this.user_id = user_id;
        this.receive_name = receive_name;
        this.province = province;
        this.city = city;
        this.district = district;
        this.send_place = send_place;
        this.address = address;
        this.zipcode = zipcode;
        this.telephone = telephone;
    }
}

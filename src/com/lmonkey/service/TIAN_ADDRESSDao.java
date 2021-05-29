package com.lmonkey.service;

import com.lmonkey.dao.Basedao;
import com.lmonkey.entity.ADDRESS_AREA;
import com.lmonkey.entity.LMONKEY_CART;
import com.lmonkey.entity.RECEIVE_ADDRESS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TIAN_ADDRESSDao {

    public static int insert(RECEIVE_ADDRESS ar) {
        String sql = "insert into receive_address values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] params = {
                ar.getUser_id(),
                ar.getReceive_name(),
                ar.getProvince(),
                ar.getCity(),
                ar.getDistrict(),
                ar.getSend_place(),
                ar.getAddress(),
                ar.getZipcode(),
                ar.getTelephone()
        };
        return Basedao.exectuIUD(sql, params);
    }

    public static ArrayList<RECEIVE_ADDRESS> getAddress(String uid) {
        ArrayList<RECEIVE_ADDRESS> list = new ArrayList<RECEIVE_ADDRESS>();
        ResultSet rs = null;//声明结果集
        //获取连接对象
        Connection conn = Basedao.getconn();
        PreparedStatement ps = null;

        try {
            String sql = "select * from receive_address where user_id = ? order by receive_address_id desc";
            ps = conn.prepareStatement(sql);
            ps.setString(1, uid);
            rs = ps.executeQuery();

            while (rs.next()) {
                RECEIVE_ADDRESS c = new RECEIVE_ADDRESS(
                        rs.getInt("receive_address_id"),
                        rs.getString("user_id"),
                        rs.getString("receive_name"),
                        rs.getString("province"),
                        rs.getString("city"),
                        rs.getString("district"),
                        rs.getString("send_place"),
                        rs.getString("address"),
                        rs.getString("zipcode"),
                        rs.getString("telephone")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            Basedao.closeall(rs, ps, conn);
        }
        return list;
    }

    public static RECEIVE_ADDRESS GetAddressByID(int raid) {
        RECEIVE_ADDRESS address = null;
        ResultSet rs = null;//声明结果集
        //获取连接对象
        Connection conn = Basedao.getconn();
        PreparedStatement ps = null;

        try {
            //唯一的
            String sql = "select * from receive_address where receive_address_id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, raid);
            rs = ps.executeQuery();

            while (rs.next()) {
                address = new RECEIVE_ADDRESS(
                        rs.getInt("receive_address_id"),
                        rs.getString("user_id"),
                        rs.getString("receive_name"),
                        rs.getString("province"),
                        rs.getString("city"),
                        rs.getString("district"),
                        rs.getString("send_place"),
                        rs.getString("address"),
                        rs.getString("zipcode"),
                        rs.getString("telephone")
                );
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            Basedao.closeall(rs, ps, conn);
        }
        return address;
    }
}

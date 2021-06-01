package com.tian.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tian.dao.Basedao;
import com.tian.entity.*;

public class ORDER_INFODao {
    public static int insert(ORDER_INFO od) {
        String sql = "insert into order_info values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
        Object[] params = {
                od.getOrder_sn(),
                od.getUser_id(),
                od.getReceive_address_id(),
                od.getPayment_method(),
                od.getPayment_money(),
                od.getPay_time(),
                od.getShipping_comp_name(),
                od.getShipping_sn(),
                od.getShipping_time(),
                od.getReceive_time(),
                od.getOrder_status()
        };
        return Basedao.exectuIUD(sql, params);
    }

    public static int insert_detail(ORDER_DETAIL od) {
        String sql = "insert into order_detail values(null, ?, ?, ?, ?, ?, ?)";
        Object[] params = {
                od.getOrder_id(),
                od.getProduct_id(),
                od.getProduct_name(),
                od.getProduct_cnt(),
                od.getProduct_price(),
                od.getProduct_filename()
        };
        return Basedao.exectuIUD(sql, params);
    }

    public static ArrayList<ORDER_INFO> selectByUserID(String id) {
        ArrayList<ORDER_INFO> list = new ArrayList<ORDER_INFO>();

        ResultSet rs = null;//声明结果集
        //获取连接对象
        Connection conn = Basedao.getconn();

        PreparedStatement ps = null;

        try {
            String sql = "select * from order_info where user_id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                ORDER_INFO or = new ORDER_INFO(
                        rs.getInt("order_id"),
                        rs.getString("order_sn"),
                        rs.getString("user_id"),
                        rs.getInt("receive_address_id"),
                        rs.getInt("payment_method"),
                        rs.getInt("payment_money"),
                        rs.getString("pay_time"),
                        rs.getString("shipping_comp_name"),
                        rs.getString("shipping_sn"),
                        rs.getString("shipping_time"),
                        rs.getString("receive_time"),
                        rs.getInt("order_status")
                );
                list.add(or);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            Basedao.closeall(rs, ps, conn);
        }
        return list;
    }

    public static int getOrderID(String sn) {

        int res = 0;
        ResultSet rs = null;//声明结果集
        //获取连接对象
        Connection conn = Basedao.getconn();
        PreparedStatement ps = null;

        try {
            String sql = "select * from order_info where order_sn=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, sn);
            rs = ps.executeQuery();

            while (rs.next()) {
                res = rs.getInt("order_id");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            Basedao.closeall(rs, ps, conn);
        }
        return res;
    }

    public static ArrayList<ORDER_DETAIL> GetOrderDetailByOrderID(int oid) {
        ArrayList<ORDER_DETAIL> list = new ArrayList<>();
        ResultSet rs = null;//声明结果集
        //获取连接对象
        Connection conn = Basedao.getconn();
        PreparedStatement ps = null;
        try {
            String sql = "select * from order_detail where order_id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, oid);
            rs = ps.executeQuery();

            while (rs.next()) {
                ORDER_DETAIL od = new ORDER_DETAIL(
                        rs.getInt("order_detail_id"),
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getInt("product_cnt"),
                        rs.getInt("product_price"),
                        rs.getString("product_filename")
                );
                list.add(od);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            Basedao.closeall(rs, ps, conn);
        }
        return list;
    }

    public static ORDER_INFO selectByOrderID(int oid) {
        ORDER_INFO or = null;

        ResultSet rs = null;//声明结果集
        //获取连接对象
        Connection conn = Basedao.getconn();

        PreparedStatement ps = null;

        try {
            String sql = "select * from order_info where order_id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, oid);
            rs = ps.executeQuery();

            while (rs.next()) {
                or = new ORDER_INFO(
                        rs.getInt("order_id"),
                        rs.getString("order_sn"),
                        rs.getString("user_id"),
                        rs.getInt("receive_address_id"),
                        rs.getInt("payment_method"),
                        rs.getInt("payment_money"),
                        rs.getString("pay_time"),
                        rs.getString("shipping_comp_name"),
                        rs.getString("shipping_sn"),
                        rs.getString("shipping_time"),
                        rs.getString("receive_time"),
                        rs.getInt("order_status")
                );
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            Basedao.closeall(rs, ps, conn);
        }
        return or;
    }

    public static ArrayList<ORDER_INFO> GetAllOrderInfo() {
        ArrayList<ORDER_INFO> list = new ArrayList<ORDER_INFO>();
        ResultSet rs = null;//声明结果集
        //获取连接对象
        Connection conn = Basedao.getconn();

        PreparedStatement ps = null;

        try {
            String sql = "select * from order_info";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ORDER_INFO or = new ORDER_INFO(
                        rs.getInt("order_id"),
                        rs.getString("order_sn"),
                        rs.getString("user_id"),
                        rs.getInt("receive_address_id"),
                        rs.getInt("payment_method"),
                        rs.getInt("payment_money"),
                        rs.getString("pay_time"),
                        rs.getString("shipping_comp_name"),
                        rs.getString("shipping_sn"),
                        rs.getString("shipping_time"),
                        rs.getString("receive_time"),
                        rs.getInt("order_status")
                );
                list.add(or);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            Basedao.closeall(rs, ps, conn);
        }
        return list;
    }

    public static int DoShipping(String shipping_time, String sn) {
        //修改订单状态和发货时间
        String sql = "update order_info set shipping_time=?, order_status=1 where order_sn=?";
        Object[] params = {
                shipping_time,
                sn
        };
        return Basedao.exectuIUD(sql, params);
    }

    public static int DoReceive(String receive_time, String sn) {
        String sql = "update order_info set receive_time=?, order_status=2 where order_sn=?";
        Object[] params = {
                receive_time,
                sn
        };
        return Basedao.exectuIUD(sql, params);
    }

    public static int DeleteOrder(String order_sn) {
        String sql = "delete from order_info where order_sn=?";
        Object[] params = {
                order_sn
        };
        return Basedao.exectuIUD(sql, params);
    }
}

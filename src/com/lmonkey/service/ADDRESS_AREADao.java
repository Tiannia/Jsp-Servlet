package com.lmonkey.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lmonkey.dao.Basedao;
import com.lmonkey.entity.ADDRESS_AREA;
import com.lmonkey.entity.LMONKEY_USER;

public class ADDRESS_AREADao {
    public static ArrayList<ADDRESS_AREA> selectAll() {
        ArrayList<ADDRESS_AREA> list = new ArrayList<ADDRESS_AREA>();
        ResultSet rs = null;//声明结果集
        //获取连接对象
        Connection conn = Basedao.getconn();
        PreparedStatement ps = null;

        try {
            String sql = "SELECT * FROM areas WHERE id LIKE '%0000'";

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ADDRESS_AREA a = new ADDRESS_AREA(
                        rs.getString("id"),
                        rs.getString("name")
                );
                list.add(a);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            Basedao.closeall(rs, ps, conn);
        }
        return list;
    }

    public static ArrayList<ADDRESS_AREA> selectCity(String id, String ids, int grade) {
        ArrayList<ADDRESS_AREA> list = new ArrayList<ADDRESS_AREA>();
        ResultSet rs = null;//声明结果集
        //获取连接对象
        Connection conn = Basedao.getconn();
        PreparedStatement ps = null;

        try {
            String sql;
            if (grade == 1) {
                sql = "SELECT * FROM areas WHERE id LIKE ? AND id <> ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, ids + "%00");
                ps.setString(2, id);
            } else {
                sql = "SELECT * FROM areas WHERE id LIKE ? AND id <> ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, ids + "%");
                ps.setString(2, id);
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                ADDRESS_AREA a = new ADDRESS_AREA(
                        rs.getString("id"),
                        rs.getString("name")
                );
                //System.out.println(a.getName()+' '+a.getId());
                list.add(a);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            Basedao.closeall(rs, ps, conn);
        }
        return list;
    }

    public static String selectById(String id){
        String name = null;
        ResultSet rs = null;//声明结果集
        //获取连接对象
        Connection conn = Basedao.getconn();
        PreparedStatement ps = null;

        try {
            String sql = "SELECT * FROM areas WHERE id=?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                name = rs.getString("name");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            Basedao.closeall(rs, ps, conn);
        }
        return name;
    }
}

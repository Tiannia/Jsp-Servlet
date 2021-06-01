package com.tian.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tian.dao.Basedao;
import com.tian.entity.LMONKEY_CATEGORY;

/**
 * 获取所有分类
 *
 * @author 23565
 */
public class LMONKEY_CATEGORYDao {
    public static ArrayList<LMONKEY_CATEGORY> selectAll() {//int cpage, int count
        ArrayList<LMONKEY_CATEGORY> list = new ArrayList<LMONKEY_CATEGORY>();
        ResultSet rs = null;//声明结果集
        //获取连接对象
        Connection conn = Basedao.getconn();
        PreparedStatement ps = null;

        try {
            String sql = "select * from category ";
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                LMONKEY_CATEGORY cate = new LMONKEY_CATEGORY(
                        rs.getInt("CATE_ID"),
                        rs.getString("CATE_NAME"),
                        rs.getInt("CATE_PARENT_ID")
                );
                list.add(cate);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            Basedao.closeall(rs, ps, conn);
        }
        return list;
    }

    /**
     * 添加分类
     *
     * @param cate
     * @return
     */


    public static int insert(LMONKEY_CATEGORY cate) {

        String sql = "insert into category values(null, ?, ?)";
        Object[] params = {
                cate.getCATE_NAME(),
                cate.getCATE_PARENT_ID()


        };
        return Basedao.exectuIUD(sql, params);
    }

    /**
     * @param id
     * @return
     */


    public static LMONKEY_CATEGORY selectById(int id) {
        LMONKEY_CATEGORY cate = null;

        ResultSet rs = null;//声明结果集
        //获取连接对象
        Connection conn = Basedao.getconn();

        PreparedStatement ps = null;

        try {
            String sql = "select * from category where CATE_ID=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);


            rs = ps.executeQuery();


            while (rs.next()) {
                cate = new LMONKEY_CATEGORY(
                        rs.getInt("CATE_ID"),
                        rs.getString("CATE_NAME"),
                        rs.getInt("CATE_PARENT_ID")
                );

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            Basedao.closeall(rs, ps, conn);
        }
        return cate;
    }

    /**
     * @param u
     * @return
     */
    public static int update(LMONKEY_CATEGORY cate) {
        //如果选择了别的父id，则将此分别移动到别的父分类中
        String sql = "update category set CATE_NAME=?, CATE_PARENT_ID=? where CATE_ID = ? ";
        Object[] params = {
                cate.getCATE_NAME(),
                cate.getCATE_PARENT_ID(),
                cate.getCATE_ID()

        };
        return Basedao.exectuIUD(sql, params);
    }


    public static int del(int id) {
        String sql = "delete from category where CATE_ID=?";
        Object[] params = {id};
        return Basedao.exectuIUD(sql, params);
    }

    /**
     * 查询分类，子分类和父级分类
     *
     * @param flag flag="father" flag="child"
     * @return
     */

    public static ArrayList<LMONKEY_CATEGORY> selectCat(String flag) {
        ArrayList<LMONKEY_CATEGORY> list = new ArrayList<LMONKEY_CATEGORY>();
        ResultSet rs = null;//声明结果集
        //获取连接对象
        Connection conn = Basedao.getconn();
        PreparedStatement ps = null;

        try {

            String sql = null;

            if (flag != null && flag.equals("father")) {

                sql = "select * from category where CATE_PARENT_ID = 0";
            } else {
                sql = "select * from category where CATE_PARENT_ID != 0";
            }
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                LMONKEY_CATEGORY cate = new LMONKEY_CATEGORY(
                        rs.getInt("CATE_ID"),
                        rs.getString("CATE_NAME"),
                        rs.getInt("CATE_PARENT_ID")

                );


                list.add(cate);

            }


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            Basedao.closeall(rs, ps, conn);
        }


        return list;
    }
}

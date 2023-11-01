/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DBConnection.JDBC;
import dto.User;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author 84859
 */
public class UserDAO implements Serializable {

    Connection cn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public User checkLogin(String email, String pass) throws Exception {
        User result = null;
        String sql = "select * from tblUser where email = ? AND password = ?";
        try {
            cn = JDBC.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, pass);

            rs = ps.executeQuery();
            if (rs.next()) {
                result = new User(rs);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (cn != null) {
                cn.close();//Mở đầu đóng cuối
            }
        }
        return result;
    }

    public User getUser(String index) throws Exception {
        User result = null;
        String sql = "select * from tblUser where Cid = ?";
        try {
            cn = JDBC.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setString(1, index);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = new User(rs);
            }
            return result;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (cn != null) {
                cn.close();//Mở đầu đóng cuối
            }
        }
    }

    public boolean updateUserInfo(String id, String target, String update) throws Exception {
        int result = 0;
        String sql = "update tblUser set " + target + " = N'" + update + "' where Cid = '" + id + "'";
        try {
            cn = JDBC.getConnection();
            ps = cn.prepareStatement(sql);

            result = ps.executeUpdate();
            return result != 0;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
    }

    public boolean addUser(User add) throws Exception {
        //String sql = "update tblCustomer set " + target + " = ? " + "where Cid = " + id;
        String sql = "insert into tblUser(Cid, Address, Cname, Cstatus, Email, password) values (?,?," + "N'" + add.getCname() + "',?,?,? )";
        try {
            cn = JDBC.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setString(1, add.getCid());
            ps.setString(2, add.getAddress());
            ps.setString(3, add.getCstatus());
            ps.setString(4, add.getEmail());
            ps.setString(5, add.getPassword());
            boolean result = (ps.executeUpdate() != 0);
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return false;
    }

    public ArrayList getUserByPurpose(String purpose) throws Exception {
        //lấy hết User hoặc lấy ra list email
        ArrayList list = null;
        String sql = "select " + purpose + " from tblUser";
        try {
            cn = JDBC.getConnection();
            ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            switch (purpose) {
                case "*": {
                    list = new ArrayList<User>();
                    while (rs.next()) {
                        list.add(new User(rs));
                    }
                }
                case "email": {
                    list = new ArrayList<String>();
                    while (rs.next()) {
                        list.add(rs.getString("email"));
                    }
                }
            }

            return list;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (cn != null) {
                cn.close();//Mở đầu đóng cuối
            }
        }
    }

    public ArrayList getListUser() throws Exception {
        //lấy hết User hoặc lấy ra list email
        ArrayList list = null;
        String sql = "select * from tblUser";
        try {
            cn = JDBC.getConnection();
            ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            list = new ArrayList<User>();
            while (rs.next()) {
                list.add(new User(rs));
            }

            return list;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (cn != null) {
                cn.close();//Mở đầu đóng cuối
            }
        }
    }

}

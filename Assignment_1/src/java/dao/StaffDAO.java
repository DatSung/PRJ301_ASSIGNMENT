/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DBConnection.JDBC;
import dto.Service;
import dto.Staff;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author uinic
 */
public class StaffDAO {

    Connection cn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public StaffDAO() {
    }

    public Staff checkLogin(String email, String pass) throws Exception {
        Staff result = null;
        String sql = "select * from tblStaff where email = ? AND password = ?";
        try {
            cn = JDBC.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, pass);

            rs = ps.executeQuery();
            if (rs.next()) {
                result = new Staff(rs);
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

    public ArrayList<Staff> getStaffsByRole(String role) throws Exception {
        ArrayList<Staff> list = new ArrayList();
        try {
            String sql = "select * from tblStaff where Srole = ? ";
            cn = JDBC.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setString(1, role);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Staff(rs, rs.getString("name")));
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

    public ArrayList<Staff> getListStaffs() throws Exception {
        ArrayList<Staff> list = new ArrayList();
        try {
            String sql = "select * from tblStaff";
            cn = JDBC.getConnection();
            ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Staff(rs, rs.getString("name")));
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

    public Staff getStaff(BigDecimal sid) throws Exception {
        Staff result = null;
        try {
            String sql = "select * from tblStaff where Sid = ? ";
            cn = JDBC.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setBigDecimal(1, sid);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = new Staff(rs, rs.getString("name"));
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

    public boolean createStaff(Staff staff) throws Exception {
        boolean result = true;
        String sql = "INSERT INTO tblStaff VALUES (?, ?, ?, ?, ?)";
        try {
            cn = JDBC.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setBigDecimal(1, staff.getSid());
            ps.setString(2, staff.getEmail());
            ps.setString(3, staff.getPassword());
            ps.setString(4, staff.getSrole());
            ps.setString(5, staff.getName());
            int rsResult = ps.executeUpdate();
            if (rsResult == 0) {
                result = false;
            } else {
                result = true;
            }
        } catch (Exception e) {

        }
        return result;
    }

    public int getMaxStaffId() throws Exception {
        int result = 0;
        String sql = "SELECT MAX(Sid) FROM tblStaff;";
        try {
            cn = JDBC.getConnection();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return result;
    }

    public boolean removeStaff(int stid) throws Exception {
        String sql = "DELETE FROM tblStaff WHERE Sid = ? ";
        try {
            cn = JDBC.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setBigDecimal(1, new BigDecimal(stid));
            int rs = ps.executeUpdate();
            return rs != 0;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
    }

    public boolean updateStaff(Staff staff) throws Exception {
        String sql = "UPDATE tblStaff\n"
                + "SET email = ?, [password] = ?, Srole = ?, name = ?\n"
                + "WHERE Sid = ?;";
        try {
            cn = JDBC.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setString(1, staff.getEmail());
            ps.setString(2, staff.getPassword());
            ps.setString(3, staff.getSrole());
            ps.setString(4, staff.getName());
            ps.setBigDecimal(5, staff.getSid());
            int rs = ps.executeUpdate();
            return rs != 0;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
    }
}

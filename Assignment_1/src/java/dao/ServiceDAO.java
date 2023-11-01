/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DBConnection.JDBC;
import dto.Service;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

/**
 *
 * @author 84859
 */
public class ServiceDAO implements Serializable {

    Connection cn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    //lấy các gói mạng từ tblService
    public ArrayList<Service> getServices() throws Exception {
        ArrayList<Service> result = new ArrayList<>();
        String sql = "select * from tblServices";
        try {
            cn = JDBC.getConnection();
            ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new Service(rs));
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

    public ArrayList<Service> serviceList() throws Exception {
        return getServices();
    }

    public int getMaxServiceId() throws Exception {
        int result = 0;
        String sql = "SELECT MAX(id) FROM tblServices";
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

    public boolean createService(Service service) throws Exception {
        boolean result = true;
        String sql = "INSERT INTO tblServices VALUES (?, ?, ?, ?, ?)";
        try {
            cn = JDBC.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, service.getId());
            ps.setString(2, service.getSname());
            ps.setString(3, service.getDes());
            ps.setBoolean(4, service.isWorkingstatus());
            ps.setBigDecimal(5, service.getCostpermonth());
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

    public Service getService(int index) throws SQLException {
        Service result = null;
        String sql = "select * from tblServices where id = ?";
        try {
            cn = JDBC.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, index);

            rs = ps.executeQuery();
            if (rs.next()) {
                result = new Service(rs);
            }
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
            return result;
        }
    }

    public boolean removeService(int svid) throws Exception {
        String sql = "DELETE FROM tblServices WHERE id = ? ";
        try {
            cn = JDBC.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, svid);
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

    public boolean updateService(Service service) throws Exception {
        String sql = "UPDATE tblServices\n"
                + "SET Sname = ?, description = ?, CostPerMonth = ?, status = ?\n"
                + "WHERE id = ?;";
        try {
            cn = JDBC.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setString(1, service.getSname());
            ps.setString(2, service.getDes());
            ps.setBigDecimal(3, service.getCostpermonth());
            ps.setInt(4, service.isWorkingstatus() ? 1 : 0);
            ps.setInt(5, service.getId());
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

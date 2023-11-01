/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DBConnection.JDBC;
import dto.Request;
import dto.RequestType;
import dto.Staff;
import dto.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import utils.Utils;

/**
 *
 * @author 84859
 */
public class RequestDAO {

    Connection cn = null;
    PreparedStatement ps = null;

    public ArrayList<RequestType> getRequestTypeList() throws Exception {
        ArrayList<RequestType> list = new ArrayList<RequestType>();
        String sql = "select * from tblRequestType";
        ResultSet rs = null;
        try {
            cn = JDBC.getConnection();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new RequestType(rs));
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
                cn.close();
            }

        }

    }

    public RequestType getRequestType(int rtid) throws Exception {
        RequestType result = null;
        String sql = "select * from tblRequestType where RTid = ? ";
        ResultSet rs = null;
        try {
            cn = JDBC.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, rtid);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = new RequestType(rs);
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
                cn.close();
            }

        }

    }

    public boolean addRequest(Request add) throws Exception {
        String sql = "insert into tblRequest(RTid, description, Rdate, Rstatus, Cid, Sid) values (?,?,?,?,?,?)";
        try {
            cn = JDBC.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, add.getRtid().getRtid());
            ps.setString(2, add.getDes());
            java.sql.Date rDate = java.sql.Date.valueOf(add.getRdate());
            ps.setDate(3, rDate);
            ps.setInt(4, add.getRstatus());
            ps.setString(5, add.getCid().getCid());
            ps.setBigDecimal(6, add.getSid().getSid());
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

    public ArrayList<Request> getRequests(String cid) throws Exception {
        ArrayList<Request> list = new ArrayList<Request>();
        String sql = "select * from tblRequest where Cid = ? ";
        ResultSet rs = null;
        try {
            UserDAO ud = new UserDAO();
            StaffDAO sd = new StaffDAO();
            cn = JDBC.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setString(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Rid");
                int rtId = rs.getInt("RTid");
                String des = rs.getString("description");
                LocalDate rDate = rs.getDate("Rdate").toLocalDate();
                int rstatus = rs.getInt("Rstatus");
                User c = ud.getUser(rs.getString("Cid"));
                Staff s = sd.getStaff(rs.getBigDecimal("Sid"));
                RequestType rtid = this.getRequestType(rtId);
                list.add(new Request(id, rtid, des, rDate, rstatus, c, s));
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
                cn.close();
            }
        }
    }

    public ArrayList<Request> getListRequests() throws Exception {
        ArrayList<Request> list = new ArrayList<Request>();
        String sql = "select * from tblRequest";
        ResultSet rs = null;
        try {
            UserDAO ud = new UserDAO();
            StaffDAO sd = new StaffDAO();
            cn = JDBC.getConnection();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Rid");
                int rtId = rs.getInt("RTid");
                String des = rs.getString("description");
                LocalDate rDate = rs.getDate("Rdate").toLocalDate();
                int rstatus = rs.getInt("Rstatus");
                User c = ud.getUser(rs.getString("Cid"));
                Staff s = sd.getStaff(rs.getBigDecimal("Sid"));
                RequestType rtid = this.getRequestType(rtId);
                list.add(new Request(id, rtid, des, rDate, rstatus, c, s));
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
                cn.close();
            }
        }
    }

    public boolean removeRequests(int Rid) throws Exception {
        String sql = "delete from tblRequest where Rid = ? ";
        try {
            cn = JDBC.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, Rid);
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

    public boolean updateRequests(int Rid, int Rstatus) throws Exception {
        String sql = "UPDATE tblRequest SET Rstatus = ? WHERE Rid = ?";
        try {
            cn = JDBC.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, Rstatus);
            ps.setInt(2, Rid);
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

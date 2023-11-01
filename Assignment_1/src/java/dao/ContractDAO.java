/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DBConnection.JDBC;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dto.Contract;
import dto.User;
import dto.Service;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author 84859
 */
public class ContractDAO implements Serializable {

    Connection cn = null;
    PreparedStatement ps = null;

    public int addContract(Contract add) throws Exception {
        int resultNO = 0;
        String sql = "insert into tblContract(Cid, Sid, StartDate, ExpDate, status, price) values(?,?,?,?,?,?)";
        try {
            cn = JDBC.getConnection();
            ps = cn.prepareStatement(sql);

            ps.setString(1, add.getCid().getCid());
            ps.setInt(2, add.getSid().getId());

            //Lưu ý: trước khi đưa vào sql thì ta cần chuyển thành java.sql.Date trước
            //đổi mới sử dụng LocalDate 
            //vì java.sql.Date có phương thức valueOf(LocalDate) chuyển LocalDate thành java.sql.Date 
            java.sql.Date start = java.sql.Date.valueOf(add.getStartdate());
            ps.setDate(3, start);
            java.sql.Date end = java.sql.Date.valueOf(add.getExpdate());
            ps.setDate(4, end);
            ps.setInt(5, add.getStatus());
            ps.setBigDecimal(6, add.getTotalprice());

            resultNO = ps.executeUpdate();
            ps.close();
            cn.close();
        } catch (SQLServerException sse) {
            //bắt lỗi khi ko add dc. 
            //cụ thể là lỗi duplicate, ko add dc khi đã có
            System.out.println(sse.getMessage());
            //request.setAttribute("isDuplicated", 1);
            //trả về lại trang service lun
            //request.getRequestDispatcher("service.jsp").forward(request, response);
        }
//        } finally{
//            if (ps != null) {
//                ps.close();
//            }
//            if (cn != null) {
//                cn.close();//Mở đầu đóng cuối
//            }
//        }

        return resultNO;
    }

    public ArrayList<Contract> getContracts() throws Exception {
        ArrayList<Contract> result = new ArrayList<>();
        String sql = "select * from tblContract";
        UserDAO cd = new UserDAO();
        ServiceDAO sd = new ServiceDAO();
        try {
            cn = JDBC.getConnection();
            ps = cn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User c = cd.getUser(rs.getString("Cid"));
                Service s = sd.getService(rs.getInt("Sid"));

                //khi dữ liệu date từ sql lên thì nó dag là java.sql.Date
                //nên cần chuyển về java.util.Date trước
                //để đảm bảo hàm getTime() chạy được thì dữ liệu ko dc null
                if (rs.getDate("StartDate") != null && rs.getDate("ExpDate") != null) {
                    LocalDate start = rs.getDate("StartDate").toLocalDate();
                    LocalDate end = rs.getDate("ExpDate").toLocalDate();
                    Contract temp = new Contract(c,
                            s,
                            start,
                            end,
                            rs.getInt("status"),
                            rs.getBigDecimal("price"));
                    result.add(temp);
                }

            }
            return result;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (cn != null) {
                cn.close();//Mở đầu đóng cuối
            }
        }
    }

    public ArrayList<Contract> getContractsByUserId(String id) throws Exception {
        ArrayList<Contract> result = new ArrayList<>();
        String sql = "select * from tblContract where Cid = ? ORDER BY StartDate DESC";
        UserDAO cd = new UserDAO();
        ServiceDAO sd = new ServiceDAO();
        try {
            cn = JDBC.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User c = cd.getUser(rs.getString("Cid"));
                Service s = sd.getService(rs.getInt("Sid"));

                //khi dữ liệu date từ sql lên thì nó dag là java.sql.Date
                //nên cần chuyển về java.util.Date trước
                //để đảm bảo hàm getTime() chạy được thì dữ liệu ko dc null
                if (rs.getDate("StartDate") != null && rs.getDate("ExpDate") != null) {
                    LocalDate start = rs.getDate("StartDate").toLocalDate();
                    LocalDate end = rs.getDate("ExpDate").toLocalDate();
                    Contract temp = new Contract(c,
                            s,
                            start,
                            end,
                            rs.getInt("status"),
                            rs.getBigDecimal("price"));
                    result.add(temp);
                }

            }
            return result;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (cn != null) {
                cn.close();//Mở đầu đóng cuối
            }
        }
    }

    public boolean isDuplicateContract(String Cid, int Sid) throws Exception {
        String sql = "select * from tblContract where Cid = ? and Sid = ?";
        UserDAO cd = new UserDAO();
        ServiceDAO sd = new ServiceDAO();
        try {
            cn = JDBC.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setString(1, Cid);
            ps.setInt(2, Sid);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) 
               return true;
            return false;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (cn != null) {
                cn.close();//Mở đầu đóng cuối
            }
        }
    }
}

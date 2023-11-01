/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 84859
 */
public class RequestType {
    private int rtid;
    private String rtname;

    public RequestType(int rtid, String rtname) {
        this.rtid = rtid;
        this.rtname = rtname;
    }

    public RequestType(ResultSet rs) throws SQLException {
        this.rtid = rs.getInt("RTid");
        this.rtname = rs.getString("RTname");
    }

    public int getRtid() {
        return rtid;
    }

    public void setRtid(int rtid) {
        this.rtid = rtid;
    }

    public String getRtname() {
        return rtname;
    }

    public void setRtname(String rtname) {
        this.rtname = rtname;
    }
    
    
    
}

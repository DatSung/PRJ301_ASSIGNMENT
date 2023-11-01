/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 84859
 */
public class User implements Serializable {
    private String cid;
    private String address;
    private String cname;
    private String cstatus;
    private String email;
    //private String image;
    private String password;

    public User(String cid, String address, String cname, String cstatus, String email /*,String image*/, String password) {
        this.cid = cid;
        this.address = address;
        this.cname = cname;
        this.cstatus = cstatus;
        this.email = email;
        //this.image = image;
        this.password = password;
    }

    public User(ResultSet rs) throws SQLException {
        this.cid = rs.getString("Cid");
        this.address = rs.getString("address");
        this.cname = rs.getString("Cname");
        this.cstatus = rs.getString("Cstatus");
        this.email = rs.getString("email");
        //this.image = rs.getString("image");
        this.password = rs.getString("password");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
   

//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCstatus() {
        return cstatus;
    }

    public void setCstatus(String cstatus) {
        this.cstatus = cstatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author uinic
 */
public class Staff implements Serializable {

    private BigDecimal sid;
    private String email;
    private String password;
    private String srole;
    private String name;

    public Staff(BigDecimal sid, String email, String password, String srole, String name) {
        this.sid = sid;
        this.email = email;
        this.password = password;
        this.srole = srole;
        this.name = name;
    }

    public Staff(BigDecimal Sid, String email, String password, String Srole) {
        this.sid = Sid;
        this.email = email;
        this.password = password;
        this.srole = Srole;
    }

    public Staff() {
        this.sid = new BigDecimal(0);
        this.email = "";
        this.password = "";
        this.srole = "";
    }

    public Staff(ResultSet rs) throws SQLException {
        this.sid = rs.getBigDecimal(1);
        this.email = rs.getString(2);
        this.password = rs.getString(3);
        this.srole = rs.getString(4);
        this.name = rs.getString(5);
    }

    public Staff(ResultSet rs, String name) throws SQLException {
        this.sid = rs.getBigDecimal(1);
        this.email = rs.getString(2);
        this.password = rs.getString(3);
        this.srole = rs.getString(4);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSid() {
        return sid;
    }

    public void setSid(BigDecimal Sid) {
        this.sid = Sid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSrole() {
        return srole;
    }

    public void setSrole(String Srole) {
        this.srole = Srole;
    }

}

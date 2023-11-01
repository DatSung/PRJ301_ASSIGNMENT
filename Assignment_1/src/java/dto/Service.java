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
 * @author 84859
 */
public class Service implements Serializable {

    private int id;
    private String sname;
    private String des;
    private boolean workingstatus;
    private BigDecimal costpermonth;

    public Service(int id, String Sname, String des, boolean workingStatus, BigDecimal costPerMoth) {
        this.id = id;
        this.sname = Sname;
        this.des = des;
        this.workingstatus = workingStatus;
        this.costpermonth = costPerMoth;
    }

    public Service(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.sname = rs.getString("Sname");
        this.des = rs.getString("description");
        this.workingstatus = rs.getBoolean("status");
        this.costpermonth = rs.getBigDecimal("CostPerMonth");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public boolean isWorkingstatus() {
        return workingstatus;
    }

    public void setWorkingstatus(boolean workingstatus) {
        this.workingstatus = workingstatus;
    }

    public BigDecimal getCostpermonth() {
        return costpermonth;
    }

    public void setCostpermonth(BigDecimal costpermonth) {
        this.costpermonth = costpermonth;
    }

    @Override
    public String toString() {
        return "Service{" + "id=" + id + ", sname=" + sname + ", des=" + des + ", workingstatus=" + workingstatus + ", costpermonth=" + costpermonth + '}';
    }

}

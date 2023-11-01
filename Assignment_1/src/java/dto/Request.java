/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.sql.ResultSet;
import java.time.LocalDate;

/**
 *
 * @author 84859
 */
public class Request {
    private int id;
    private RequestType rtid;
    private String des;
    private LocalDate rdate;
    private int rstatus; //-1.hủy 0.dag xử lý 1. xử lý xong
    private User cid;
    private Staff sid;

    public Request(int id,RequestType rtid, String des, LocalDate rdate, int rstatus, User cid, Staff sid) {
        this.id = id;
        this.rtid = rtid;
        this.des = des;
        this.rdate = rdate;
        this.rstatus = rstatus;
        this.cid = cid;
        this.sid = sid;
    }
    public Request(RequestType rtid, String des, LocalDate rdate, int rstatus, User cid, Staff sid) {
        this.id = 0;
        this.rtid = rtid;
        this.des = des;
        this.rdate = rdate;
        this.rstatus = rstatus;
        this.cid = cid;
        this.sid = sid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public RequestType getRtid() {
        return rtid;
    }

    public void setRtid(RequestType rtid) {
        this.rtid = rtid;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public LocalDate getRdate() {
        return rdate;
    }

    public void setRdate(LocalDate rdate) {
        this.rdate = rdate;
    }

    public int getRstatus() {
        return rstatus;
    }

    public void setRstatus(int rstatus) {
        this.rstatus = rstatus;
    }

    public User getCid() {
        return cid;
    }

    public void setCid(User cid) {
        this.cid = cid;
    }

    public Staff getSid() {
        return sid;
    }

    public void setSid(Staff sid) {
        this.sid = sid;
    }

    
    
    
}

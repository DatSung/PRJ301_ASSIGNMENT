/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;



/**
 *
 * @author 84859
 */
public class Contract implements Serializable {
    private User cid;
    private Service sid;
    private LocalDate startdate;
    private LocalDate expdate;
    private int status;
    private BigDecimal totalprice;

    public Contract() {
    }

    
    public Contract(User Cid, Service Sid, LocalDate startDate, LocalDate expDate, int status, BigDecimal totalPrice) {
        this.cid = Cid;
        this.sid = Sid;
        this.startdate = startDate;
        this.expdate = expDate;
        this.status = status;
        this.totalprice = totalPrice;
    }

    public User getCid() {
        return cid;
    }

    public void setCid(User cid) {
        this.cid = cid;
    }

    public Service getSid() {
        return sid;
    }

    public void setSid(Service sid) {
        this.sid = sid;
    }

    public LocalDate getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }

    public LocalDate getExpdate() {
        return expdate;
    }

    public void setExpdate(LocalDate expdate) {
        this.expdate = expdate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BigDecimal getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

  

    
    
    
    
}

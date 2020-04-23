package com.zlin.task.Models;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="repairs")
public class Repair {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    private Date startDate;

    private Date endDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "computerId", insertable=false, updatable=false)
    private Computer comp;
    private long computerId;


    public Repair() {
    }


    public Repair(Date startDate, long computerId) {
        this.startDate = startDate;
        this.computerId = computerId;
    }


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Computer getComp() {
        return this.comp;
    }

    public void setComp(Computer computer) {
        this.comp = computer;
    }

    public long getComputerId() {
        return this.computerId;
    }

    public void setComputerId(long computerId) {
        this.computerId = computerId;
    }

}
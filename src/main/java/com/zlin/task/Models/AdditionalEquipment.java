package com.zlin.task.Models;

import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

/**
 * AdditionalEquipment
 */
@Entity
@Table(name="additional_equipments")
public class AdditionalEquipment {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    private long invNo;

    @Column(nullable = false)
    @Nationalized
    private String name;

    @Column(nullable = false)
    @Nationalized
    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "computerId", insertable=false, updatable=false)
    private Computer computer;
    private long computerId;


    public long getComputerId() {
        return this.computerId;
    }

    public void setComputerId(long computerId) {
        this.computerId = computerId;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getInvNo() {
        return this.invNo;
    }

    public void setInvNo(long invNo) {
        this.invNo = invNo;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Computer getComputer() {
        return this.computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

}
package com.zlin.task.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

@Entity
@Table(name="computers")
public class Computer {
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
    private String subdivision;

    @Column(nullable = false)
    private Date commissioningDate;

    @Nationalized
    private String movement = "Нет";
    
    @Column(nullable = false)
    @Nationalized
    private String cpu;

    @Column(nullable = false)
    @Nationalized
    private String ram;

    @Column(nullable = false)
    @Nationalized
    private String rom;

    @Column(nullable = false)
    @Nationalized
    private String videocard;

    @Column(nullable = false)
    private boolean cdRom;

    @Column(nullable = false)
    private boolean buildInMonitor = false; 

    public Computer() {
    }


    public Computer(long invNo, String name, String subdivision, Date commissioningDate, String movement, String cpu, String ram, String rom, String videocard, boolean cdRom, boolean buildInMonitor) {
        this.invNo = invNo;
        this.name = name;
        this.subdivision = subdivision;
        this.commissioningDate = commissioningDate;
        this.movement = movement;
        this.cpu = cpu;
        this.ram = ram;
        this.rom = rom;
        this.cdRom = cdRom;
        this.buildInMonitor = buildInMonitor;
        this.videocard = videocard;
    }
    

    @OneToMany(mappedBy = "computer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Equipment> additionalEquipments = new HashSet<Equipment>();

    @OneToMany(mappedBy = "comp", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Repair> repairs = new HashSet<Repair>();

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

    public String getSubdivision() {
        return this.subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public Date getCommissioningDate() {
        return this.commissioningDate;
    }

    public void setCommissioningDate(Date commissioningDate) {
        this.commissioningDate = commissioningDate;
    }

    public String getMovement() {
        return this.movement;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }


    public String getCpu() {
        return this.cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return this.ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getRom() {
        return this.rom;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    public boolean getCdRom() {
        return this.cdRom;
    }

    public void setCdRom(boolean cdRom) {
        this.cdRom = cdRom;
    }

    public boolean getBuildInMonitor() {
        return this.buildInMonitor;
    }

    public void setBuildInMonitor(boolean buildInMonitor) {
        this.buildInMonitor = buildInMonitor;
    }


    public String getVideocard() {
        return this.videocard;
    }

    public void setVideocard(String videocard) {
        this.videocard = videocard;
    }

    public Set<Repair> getRepairs() {
        return this.repairs;
    }

    public void setRepairs(Set<Repair> repairs) {
        this.repairs = repairs;
    }

}
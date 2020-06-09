package com.zlin.task.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

/**
 * Equipment
 */
@Entity
@Table(name="equipments")
public class Equipment {

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

    @Column(nullable = false)
    @Nationalized
    private String subdivision;

    @Column(nullable = false)
    private Date commissioningDate;

    @Nationalized
    private String movement = "Нет";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "computerId", nullable = true, updatable = false, insertable = false)
    private Computer computer;
    private Long computerId;

    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Repair> repairs = new HashSet<Repair>();

    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Process> processes  = new HashSet<Process>();

    public Equipment() {
    }

    public Long getComputerId() {
        return this.computerId;
    }

    public void setComputerId(Long computerId) {
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

    


    /**
     * @return String return the subdivision
     */
    public String getSubdivision() {
        return subdivision;
    }

    /**
     * @param subdivision the subdivision to set
     */
    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    /**
     * @return Date return the commissioningDate
     */
    public Date getCommissioningDate() {
        return commissioningDate;
    }

    /**
     * @param commissioningDate the commissioningDate to set
     */
    public void setCommissioningDate(Date commissioningDate) {
        this.commissioningDate = commissioningDate;
    }

    /**
     * @return String return the movement
     */
    public String getMovement() {
        return movement;
    }

    /**
     * @param movement the movement to set
     */
    public void setMovement(String movement) {
        this.movement = movement;
    }

    /**
     * @return Set<Repair> return the repairs
     */
    public Set<Repair> getRepairs() {
        return repairs;
    }

    /**
     * @param repairs the repairs to set
     */
    public void setRepairs(Set<Repair> repairs) {
        this.repairs = repairs;
    }

}
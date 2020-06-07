package com.zlin.task.models;

import java.util.Set;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "computerId", nullable = true, insertable=false, updatable=false)
    private Computer comp;
    private Long computerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipmentId", nullable = true, insertable=false, updatable=false)
    private Equipment equipment;
    private Long equipmentId;

    @ManyToMany(mappedBy = "repairs")
    private Set<User> users;

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

    public Long getComputerId() {
        return this.computerId;
    }

    public void setComputerId(Long computerId)
    {
        this.computerId = computerId;
    }

    /**
     * @return Equipment return the equipment
     */
    public Equipment getEquipment() {
        return equipment;
    }

    /**
     * @param qEquipment the equipment to set
     */
    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    /**
     * @return long return the equipmentId
     */
    public Long getEquipmentId() {
        return equipmentId;
    }

    /**
     * @param equipmentId the equipmentId to set
     */
    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    /**
     * @return Set<User> return the users
     */
    public Set<User> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(Set<User> users) {
        this.users = users;
    }

}
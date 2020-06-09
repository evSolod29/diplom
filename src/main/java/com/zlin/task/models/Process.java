package com.zlin.task.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

@Entity
@Table(name="process")
public class Process {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "computerId", nullable = true, updatable = false, insertable = false)
    private Computer computer;
    private Long computerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipmentId", nullable = true, updatable = false, insertable = false)
    private Equipment equipment;
    private Long equipmentId;

    @OneToMany(mappedBy = "process", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Work> works = new TreeSet<Work>();
    
    @Column(nullable = false)
    @Nationalized
    private String name;
    
    @Column(nullable = false)
    @Nationalized
    private String customer;

    private Date startDate;

    private boolean inProcess = true;

    /**
     * @return long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Computer return the computer
     */
    public Computer getComputer() {
        return computer;
    }

    /**
     * @param computer the computer to set
     */
    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    /**
     * @return Long return the computerId
     */
    public Long getComputerId() {
        return computerId;
    }

    /**
     * @param computerId the computerId to set
     */
    public void setComputerId(Long computerId) {
        this.computerId = computerId;
    }

    /**
     * @return Equipment return the equipment
     */
    public Equipment getEquipment() {
        return equipment;
    }

    /**
     * @param equipment the equipment to set
     */
    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    /**
     * @return Long return the equipmentId
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
     * @return Set<Work> return the works
     */
    public Set<Work> getWorks() {
        return works;
    }

    /**
     * @param works the works to set
     */
    public void setWorks(Set<Work> works) {
        this.works = works;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return boolean return the inProcess
     */
    public boolean isInProcess() {
        return inProcess;
    }

    /**
     * @param inProcess the inProcess to set
     */
    public void setInProcess(boolean inProcess) {
        this.inProcess = inProcess;
    }

    /**
     * @return String return the customer
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(String customer) {
        this.customer = customer;
    }


    /**
     * @return Date return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

}
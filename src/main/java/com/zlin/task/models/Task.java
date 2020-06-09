package com.zlin.task.models;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "businessProcessId", 
        updatable = false, 
        insertable = false, 
        nullable = false
    )
    private BusinessProcess businessProcess;
    private Long businessProcessId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "equipmentId", updatable = false, insertable = false)
    private Equipment equipment;
    private Long equipmentId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "computerId", updatable = false, insertable = false)
    private Computer computer;
    private Long computerId;

    @Enumerated(EnumType.ORDINAL)
    private StatusType statusType = StatusType.EXECUTION;

    @Column(nullable = false)
    private Date startDate;
    
    private Date pauseTime;

    /**
     * @return Long return the id
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
     * @return BusinessProcess return the businessProcess
     */
    public BusinessProcess getBusinessProcess() {
        return businessProcess;
    }

    /**
     * @param businessProcess the businessProcess to set
     */
    public void setBusinessProcess(BusinessProcess businessProcess) {
        this.businessProcess = businessProcess;
    }

    /**
     * @return Long return the businessProcessId
     */
    public Long getBusinessProcessId() {
        return businessProcessId;
    }

    /**
     * @param businessProcessId the businessProcessId to set
     */
    public void setBusinessProcessId(Long businessProcessId) {
        this.businessProcessId = businessProcessId;
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
     * @return StatusType return the statusType
     */
    public StatusType getStatusType() {
        return statusType;
    }

    /**
     * @param statusType the statusType to set
     */
    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
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

    /**
     * @return Date return the pauseTime
     */
    public Date getPauseTime() {
        return pauseTime;
    }

    /**
     * @param pauseTime the pauseTime to set
     */
    public void setPauseTime(Date pauseTime) {
        this.pauseTime = pauseTime;
    }

}
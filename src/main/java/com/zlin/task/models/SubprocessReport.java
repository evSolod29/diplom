package com.zlin.task.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "s_reports")
public class SubprocessReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "taskId", nullable = false, insertable = false, updatable = false)
    private Task task;
    private Long taskId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subprocessId", nullable = false, insertable = false, updatable = false)
    private Subprocess subprocess;
    private Long subprocessId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false, insertable = false, updatable = false)
    private User user;
    private Long userId;

    @Column(length = 2048)
    private String report;

    @Enumerated(EnumType.ORDINAL)
    private StatusType statusType;

    /**
     * @return Long return the Id
     */
    public Long getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(Long Id) {
        this.Id = Id;
    }

    /**
     * @return Task return the task
     */
    public Task getTask() {
        return task;
    }

    /**
     * @param task the task to set
     */
    public void setTask(Task task) {
        this.task = task;
    }

    /**
     * @return Long return the taskId
     */
    public Long getTaskId() {
        return taskId;
    }

    /**
     * @param taskId the taskId to set
     */
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    /**
     * @return Subprocess return the subprocess
     */
    public Subprocess getSubprocess() {
        return subprocess;
    }

    /**
     * @param subprocess the subprocess to set
     */
    public void setSubprocess(Subprocess subprocess) {
        this.subprocess = subprocess;
    }

    /**
     * @return Long return the subprocessId
     */
    public Long getSubprocessId() {
        return subprocessId;
    }

    /**
     * @param subprocessId the subprocessId to set
     */
    public void setSubprocessId(Long subprocessId) {
        this.subprocessId = subprocessId;
    }

    /**
     * @return User return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return Long return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return String return the report
     */
    public String getReport() {
        return report;
    }

    /**
     * @param report the report to set
     */
    public void setReport(String report) {
        this.report = report;
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

}
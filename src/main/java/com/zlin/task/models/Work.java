package com.zlin.task.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.Nullable;

import org.hibernate.annotations.Nationalized;

/**
 * Данных класс содержит описание свойств работы выполняемой в рамках бизнес-процесса
 */
@Entity
@Table(name="works")
public class Work {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(nullable = false)
    private long id;

    @Nationalized
    @Column(nullable = false)
    private String name;

    private boolean inProcess = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = true, insertable = false, updatable = false)
    private User user;
    private Long userId;

    @Nullable
    private Long preWorkId;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "processId", nullable = true, insertable = false, updatable = false)
    private Process process;
    private Long processId;
    

    @Nationalized
    private String information;

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
     * @return User Возвращает пользователя, закрепленного на выполнение этой работы
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user - пользователь, закрепленный на выполнение этой работы
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return String Возвращает дополнительную информацию о работе
     */
    public String getInformation() {
        return information;
    }

    /**
     * @param information - значение дополнительной информации о работе
     */
    public void setInformation(String information) {
        this.information = information;
    }

    /**
     * @return Process return the Process
     */
    public Process getProcess() {
        return process;
    }

    /**
     * @param Process the Process to set
     */
    public void setProcess(Process process) {
        this.process = process;
    }

    /**
     * @return Long return the processId
     */
    public Long getProcessId() {
        return processId;
    }

    /**
     * @param processId the processId to set
     */
    public void setProcessId(Long processId) {
        this.processId = processId;
    }


    /**
     * @return Long return the preWorkId
     */
    public Long getPreWorkId() {
        return preWorkId;
    }

    /**
     * @param preWorkId the preWorkId to set
     */
    public void setPreWorkId(Long preWorkId) {
        this.preWorkId = preWorkId;
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

}
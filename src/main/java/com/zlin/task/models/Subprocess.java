package com.zlin.task.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "subprocesses")
public class Subprocess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int indexNumber;

    @Nationalized
    @Column(nullable = false)
    private String subprocessName;
    
    @Nationalized
    @Column(columnDefinition = "TEXT")
    private String taskDescription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "businessProcessId",updatable = false, insertable = false, nullable = false)
    private BusinessProcess businessProcess;
    private Long businessProcessId;

    @OneToMany(mappedBy = "subprocess", orphanRemoval = true)
    private Set<SubprocessReport> subprocessReports = new HashSet<SubprocessReport>();

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
     * @return int return the indexNumber
     */
    public int getIndexNumber() {
        return indexNumber;
    }

    /**
     * @param indexNumber the indexNumber to set
     */
    public void setIndexNumber(int indexNumber) {
        this.indexNumber = indexNumber;
    }

    /**
     * @return String return the subprocessName
     */
    public String getSubprocessName() {
        return subprocessName;
    }

    /**
     * @param subprocessName the subprocessName to set
     */
    public void setSubprocessName(String subprocessName) {
        this.subprocessName = subprocessName;
    }

    /**
     * @return String return the taskDescription
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * @param taskDescription the taskDescription to set
     */
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
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
     * @return Set<SubprocessReport> return the subprocessReports
     */
    public Set<SubprocessReport> getSubprocessReports() {
        return subprocessReports;
    }

    /**
     * @param subprocessReports the subprocessReports to set
     */
    public void setSubprocessReports(Set<SubprocessReport> subprocessReports) {
        this.subprocessReports = subprocessReports;
    }

}
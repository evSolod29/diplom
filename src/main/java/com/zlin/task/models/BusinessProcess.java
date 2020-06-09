package com.zlin.task.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "b_processes")
public class BusinessProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized
    private String name;

    @OneToMany(mappedBy = "businessProcess", orphanRemoval = true)
    private Set<Task> tasks;

    @OneToMany(mappedBy = "businessProcess", orphanRemoval = true)
    private Set<Subprocess> subprocesses = new HashSet<Subprocess>();

    private Date leadTime;

    @Nationalized
    @Column(columnDefinition = "TEXT")
    private String desription;

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
     * @return Set<Task> return the tasks
     */
    public Set<Task> getTasks() {
        return tasks;
    }

    /**
     * @param tasks the tasks to set
     */
    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * @return Set<Subprocess> return the subprocesses
     */
    public Set<Subprocess> getSubprocesses() {
        return subprocesses;
    }

    /**
     * @param subprocesses the subprocesses to set
     */
    public void setSubprocesses(Set<Subprocess> subprocesses) {
        this.subprocesses = subprocesses;
    }

    /**
     * @return Date return the leadTime
     */
    public Date getLeadTime() {
        return leadTime;
    }

    /**
     * @param leadTime the leadTime to set
     */
    public void setLeadTime(Date leadTime) {
        this.leadTime = leadTime;
    }

    /**
     * @return String return the desription
     */
    public String getDesription() {
        return desription;
    }

    /**
     * @param desription the desription to set
     */
    public void setDesription(String desription) {
        this.desription = desription;
    }

}
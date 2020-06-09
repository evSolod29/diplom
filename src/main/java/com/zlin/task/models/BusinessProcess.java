package com.zlin.task.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

@Entity
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

    public BusinessProcess() {
       
    }
}
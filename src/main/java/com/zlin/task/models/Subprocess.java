package com.zlin.task.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

@Entity
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
    // @Column(columnDefinition = "TEXT")
    private String taskDescription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "businessProcessId",updatable = false, insertable = false, nullable = false)
    private BusinessProcess businessProcess;
    private Long businessProcessId;

    @OneToMany(mappedBy = "subprocess")
    private Set<SubprocessReport> subprocessReports = new HashSet<SubprocessReport>();

}
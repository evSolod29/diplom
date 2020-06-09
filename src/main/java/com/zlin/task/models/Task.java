package com.zlin.task.models;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "businessProcessId", updatable = false, insertable = false)
    private BusinessProcess businessProcess;
    private Long businessProcessId;

    @Enumerated(EnumType.ORDINAL)
    private StatusType statusType = StatusType.EXECUTION;

    @Column(nullable = false)
    private Date startDate;
    
    private Date pauseTime;
}
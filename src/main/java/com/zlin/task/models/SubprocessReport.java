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

@Entity
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

    @Column(columnDefinition = "TEXT")
    private String report;

    @Enumerated(EnumType.ORDINAL)
    private StatusType statusType;


}
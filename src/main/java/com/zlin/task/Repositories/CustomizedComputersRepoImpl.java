package com.zlin.task.Repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.zlin.task.Models.Computer;

public class CustomizedComputersRepoImpl implements CustomizedComputersRepo<Computer> {

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<Computer> findAllByAllRows(String str) {
        return (List<Computer>)em.createNativeQuery(
            "SELECT * FROM computers WHERE inv_no LIKE '%"+ str +"%' UNION "+
            "SELECT * FROM computers WHERE commissioning_date LIKE '%"+ str +"%' UNION "+
            "SELECT * FROM computers WHERE movement LIKE N'%"+ str +"%' UNION "+
            "SELECT * FROM computers WHERE name LIKE N'%"+ str +"%' UNION "+
            "SELECT * FROM computers WHERE subdivision LIKE N'%"+ str +"%'",
            Computer.class)
        .getResultList();
    }


}
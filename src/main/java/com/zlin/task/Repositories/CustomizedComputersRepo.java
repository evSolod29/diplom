package com.zlin.task.Repositories;

import java.util.List;

public interface CustomizedComputersRepo<T> {
    List<T> findAllByAllRows(String str);
}
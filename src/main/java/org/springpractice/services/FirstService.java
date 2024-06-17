package org.springpractice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springpractice.daos.FirstDao;
import org.springpractice.models.FirstPojo;

import java.util.List;

@Service
public class FirstService {

    private FirstDao firstDao;

    @Autowired
    FirstService(FirstDao firstDao) {
        this.firstDao = firstDao;
    }

    public List<FirstPojo> executeFirstService() {
        return firstDao.getResultFromFirstDAO();
    }
}

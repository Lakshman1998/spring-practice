package org.springpractice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springpractice.daos.FirstDao;

import java.util.Map;

@Service
public class FirstService {

    private FirstDao firstDao;

    @Autowired
    FirstService(FirstDao firstDao) {
        this.firstDao = firstDao;
    }

    public Map<String, Object> executeFirstService() {
        return firstDao.getResultFromFirstDAO();
    }
}

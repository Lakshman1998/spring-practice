package org.springpractice.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;

@Component
public class FirstDao {

    private DataSource dataSource;

    @Autowired
    FirstDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, Object> getResultFromFirstDAO() {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        return template.queryForMap("select * from spring_analysis.first_table");
    }
}

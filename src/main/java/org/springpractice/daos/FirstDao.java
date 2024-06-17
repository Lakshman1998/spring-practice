package org.springpractice.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springpractice.models.FirstPojo;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class FirstDao {

    private DataSource dataSource;


    @Autowired
    FirstDao(@Qualifier("dataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<FirstPojo> getResultFromFirstDAO() {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        return template.query("select * from spring_analysis.first_table", this::mapResultSetToPojo);
    }

    private FirstPojo mapResultSetToPojo(ResultSet rs, int ignored) throws SQLException {
        FirstPojo firstPojo = new FirstPojo();
        firstPojo.setFirstName(rs.getString("name"));
        firstPojo.setDescription(rs.getString("desciption"));
        firstPojo.setId(rs.getLong("id"));
        return firstPojo;
    }
}

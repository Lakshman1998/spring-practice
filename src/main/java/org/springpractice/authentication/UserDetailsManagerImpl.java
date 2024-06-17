package org.springpractice.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Component
public class UserDetailsManagerImpl implements UserDetailsManager {

    private final DataSource dataSource;
    private static final String USER_SELECT_QUERY = "select * from spring_analysis.users where user_name = :userName;";

    @Autowired
    UserDetailsManagerImpl(@Qualifier("dataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void createUser(UserDetails user) {
        SimpleJdbcInsert userInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("spring_analysis.users")
                .usingColumns("user_name", "password");
        userInsert.execute(Map.of("user_name", user.getUsername(), "password", user.getPassword()));
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        List<UserDetails> userDetailsList = namedParameterJdbcTemplate.query(USER_SELECT_QUERY, Map.of("userName", username),this::mapToUserDetails);
        return userDetailsList.stream().findFirst().orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private UserDetails mapToUserDetails(ResultSet rs, int ignored) throws SQLException {
        return User.withUsername(rs.getString("user_name"))
                .password(rs.getString("password"))
                .build();
    }
}

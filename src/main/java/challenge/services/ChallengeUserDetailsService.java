package challenge.services;

import challenge.domain.ChallengeUserPrincipal;
import challenge.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by gaopeng on 5/12/17.
 */
public class ChallengeUserDetailsService implements UserDetailsService {


    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String query = "select * from person where name = :name";


		return jdbcTemplate.queryForObject(query, new MapSqlParameterSource("name", username), new RowMapper<UserDetails>() {
			public UserDetails mapRow(ResultSet resultSet, int rowNum)
					throws SQLException {
				return new ChallengeUserPrincipal(new Person(resultSet.getLong("id"), resultSet.getString("name")));
			}
		});

    }
}

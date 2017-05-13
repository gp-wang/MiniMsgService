package challenge.web;

import challenge.domain.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by gaopeng on 5/11/17.
 */
@RestController
public class ChallengeController {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


    @RequestMapping(value = "messages", method = RequestMethod.GET)
    public @ResponseBody
    List<Tweet> getTweets(@RequestParam(name = "search", required = false) String search) {

        String query = "SELECT * FROM TWEET";


		return jdbcTemplate.query(query, new RowMapper<Tweet>() {
			public Tweet mapRow(ResultSet resultSet, int rowNum)
					throws SQLException {
				return new Tweet(resultSet.getLong("ID"), resultSet.getLong("PERSON_ID"), resultSet.getString("CONTENT"));
			}
		});


    }
}

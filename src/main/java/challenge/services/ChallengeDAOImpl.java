package challenge.services;

import challenge.domain.ChallengeUserPrincipal;
import challenge.domain.Person;
import challenge.domain.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by gaopeng on 5/12/17.
 */
@Component
public class ChallengeDAOImpl implements IChallengeDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Person> getFollowedByPerson(Person person) {

        String query = "select pp.id as id,pp.name as name from person as pp where pp.id in " +
                "(select distinct f.person_id " +
                "from followers as f left join person as p " +
                "on f.follower_person_id = p.id " +
                "where p.id = :follower_id)";

        return jdbcTemplate.query(query,
                new MapSqlParameterSource("follower_id", person.getId()),
                (resultSet, rowNum) -> new Person(resultSet.getLong("id"), resultSet.getString("name")));
    }

    @Override
    public List<Tweet> getTweetsByPeople(List<Person> people) {

        Set<Long> ids = people.stream().map(Person::getId).collect(Collectors.toSet());

        String query = "select id,person_id,content from tweet where person_id in (:ids)";

        return jdbcTemplate.query(query,
                new MapSqlParameterSource("ids", ids),
                (resultSet, rowNum) -> new Tweet(resultSet.getLong("id"),
                        resultSet.getLong("person_id"),
                        resultSet.getString("content")));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String query = "select * from person where name = :name";

        return jdbcTemplate.queryForObject(query, new MapSqlParameterSource("name", username),
                (RowMapper<UserDetails>) (resultSet, rowNum) -> new ChallengeUserPrincipal(new Person(resultSet.getLong("id"), resultSet.getString("name"))));

    }

    @Override
    public List<Person> getFollowersByPerson(Person person) {
        String query = "select id,name from person where id in " +
                "(select distinct follower_person_id " +
                "from followers where person_id = :person_id)";

        return jdbcTemplate.query(query,
                new MapSqlParameterSource("person_id", person.getId()),
                (resultSet, rowNum) -> new Person(resultSet.getLong("id"), resultSet.getString("name")));
    }

    @Override
    public void insertFollowerFollowee(Person follower, Person followee) {
        String query = "insert into followers (person_id, follower_person_id) values (:followee_person_id, :follower_person_id)";

        MapSqlParameterSource mapping = new MapSqlParameterSource();
        mapping.addValue("followee_person_id", followee.getId());
        mapping.addValue("follower_person_id", follower.getId());
        jdbcTemplate.update(query, mapping);

    }


    @Override
    public void deleteFollowerFollowee(Person follower, Person followee) {
        String query = "delete from followers where person_id = :followee_person_id and follower_person_id = :follower_person_id)";

        MapSqlParameterSource mapping = new MapSqlParameterSource();
        mapping.addValue("followee_person_id", followee.getId());
        mapping.addValue("follower_person_id", follower.getId());
        jdbcTemplate.update(query, mapping);

    }

    @Override
    public Person getPersonById(Long personId) {
        String query = "select id,name from person where id=:person_id";

        return jdbcTemplate.queryForObject(query,
                new MapSqlParameterSource("person_id", personId),
                (resultSet, rowNum) -> new Person(resultSet.getLong("id"), resultSet.getString("name")));
    }

    @Override
    public void insertFollowerFollowee(Long followerPersonId, Long followeePersonId) throws SQLException{
        String query = "delete from followers where person_id = :followee_person_id and follower_person_id = :follower_person_id)";

        MapSqlParameterSource mapping = new MapSqlParameterSource();
        mapping.addValue("followee_person_id", followeePersonId);
        mapping.addValue("follower_person_id", followerPersonId);
        jdbcTemplate.update(query, mapping);
    }

    @Override
    public void deleteFollowerFollowee(Long followerPersonId, Long followeePersonId) throws SQLException{
        String query = "delete from followers where person_id = :followee_person_id and follower_person_id = :follower_person_id)";

        MapSqlParameterSource mapping = new MapSqlParameterSource();
        mapping.addValue("followee_person_id", followeePersonId);
        mapping.addValue("follower_person_id", followerPersonId);
        jdbcTemplate.update(query, mapping);

    }


}

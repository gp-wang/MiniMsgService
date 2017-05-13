package challenge.services;

import challenge.domain.Person;
import challenge.domain.Tweet;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by gaopeng on 5/12/17.
 */
@Service
public interface IChallengeService {

    public Person getPersonById(Long personId);

    public List<Person> getFolloweesByPerson(Person person);

    public List<Tweet> getTweetsByPeople(List<Person> people);

    public List<Person> getFollowersByPerson(Person person);

    public void insertFollowerFollowee(Person follower, Person followee);

    public void deleteFollowerFollowee(Person follower, Person followee);

    public void insertFollowerFollowee(Long followerPersonId, Long followeePersonId) throws SQLException;

    public void deleteFollowerFollowee(Long followerPersonId, Long followeePersonId) throws SQLException;
}

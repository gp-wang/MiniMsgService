package challenge.services;

import challenge.domain.Person;
import challenge.domain.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by gaopeng on 5/12/17.
 */
@Component
public class ChallengeServiceImpl implements IChallengeService {
    @Autowired
    IChallengeDAO challengeDAO;

    @Override
    public Person getPersonById(Long personId) {
        return challengeDAO.getPersonById(personId);
    }

    @Override
    public List<Person> getFolloweesByPerson(Person person) {
        return challengeDAO.getFollowedByPerson(person);
    }

    @Override
    public List<Tweet> getTweetsByPeople(List<Person> people) {
        return challengeDAO.getTweetsByPeople(people);
    }

    @Override
    public List<Person> getFollowersByPerson(Person person) {
        return challengeDAO.getFollowersByPerson(person);
    }

    @Override
    public void insertFollowerFollowee(Person follower, Person followee) {
        challengeDAO.insertFollowerFollowee(follower, followee);
    }

    @Override
    public void deleteFollowerFollowee(Person follower, Person followee) {
        challengeDAO.deleteFollowerFollowee(follower, followee);
    }

    @Override
    public void insertFollowerFollowee(Long followerPersonId, Long followeePersonId) throws SQLException {
        challengeDAO.insertFollowerFollowee(followerPersonId, followeePersonId);
    }

    @Override
    public void deleteFollowerFollowee(Long followerPersonId, Long followeePersonId) throws SQLException {
        challengeDAO.deleteFollowerFollowee(followerPersonId, followeePersonId);
    }


}

package challenge.services;

import challenge.domain.Person;
import challenge.domain.Tweet;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by gaopeng on 5/12/17.
 */
public interface IChallengeDAO {

    /**
     * Get a list of people followed by the person
     * @param person
     * @return person's following list
     */
    public List<Person> getFollowedByPerson(Person person);

    /**
     * Get a list of tweets sent by the people
     * @param people
     * @return list of tweets
     */
    public List<Tweet> getTweetsByPeople(List<Person> people);

    /**
     * Get the UserDetails by the provided username
     * @param username
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    /**
     * Get a list of people following  the person
     * @param person
     * @return a list of followers
     */
    public List<Person> getFollowersByPerson(Person person);

    /**
     * Insert followee-follower pair
     * @param follower
     * @param followee
     */
    public void insertFollowerFollowee(Person follower, Person followee);

    /**
     * Delete followee-follower pair
     * @param follower
     * @param followee
     */
    public void deleteFollowerFollowee(Person follower, Person followee);

    /**
     * Get person by its id
     * @param personId
     * @return
     */
    public Person getPersonById(Long personId);


    /**
     * Insert followee-follower pair by their ids
     * @param followerPersonId
     * @param followeePersonId
     */
    public void insertFollowerFollowee(Long followerPersonId, Long followeePersonId) throws SQLException;

    /**
     * Delete followee-follower pair by their ids
     * @param followerPersonId
     * @param followeePersonId
     */
    public void deleteFollowerFollowee(Long followerPersonId, Long followeePersonId) throws SQLException;
}

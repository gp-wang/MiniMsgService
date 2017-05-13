package challenge.web;

import challenge.domain.ChallengeUserPrincipal;
import challenge.domain.Person;
import challenge.domain.Tweet;
import challenge.services.IChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by gaopeng on 5/11/17.
 */
@RestController
public class ChallengeController {

    Logger s_log = Logger.getLogger(ChallengeController.class.getName());

    @Autowired
    private IChallengeService challengeService;

    @RequestMapping(value = "messages", method = RequestMethod.GET)
    public @ResponseBody
    List<Tweet> getMessages(@RequestParam(name = "search", required = false) String search) {
        ChallengeUserPrincipal currentUser = getChallengeUserPrincipal();

        List<Person> followedPeople = challengeService.getFolloweesByPerson(currentUser.getPerson());

        return challengeService.getTweetsByPeople(followedPeople).stream()
                .filter(t->t.getContent().contains(search == null ? "" : search))
                .collect(Collectors.toList());
    }

    private ChallengeUserPrincipal getChallengeUserPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (ChallengeUserPrincipal) authentication.getPrincipal();
    }

    @RequestMapping(value = "followees", method = RequestMethod.GET)
    public @ResponseBody List<Person> getFollowing() {
        ChallengeUserPrincipal currentUser = getChallengeUserPrincipal();

        return challengeService.getFolloweesByPerson(currentUser.getPerson());
    }

    @RequestMapping(value = "followers", method = RequestMethod.GET)
    public @ResponseBody List<Person> getFollowers() {
        ChallengeUserPrincipal currentUser = getChallengeUserPrincipal();

        return challengeService.getFollowersByPerson(currentUser.getPerson());
    }


    @RequestMapping(value = "follow/{followeePersonId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void follow(@PathVariable Long followeePersonId) {
        ChallengeUserPrincipal currentUser = getChallengeUserPrincipal();
        Long followerPersonId = currentUser.getPerson().getId();

        try {
            challengeService.insertFollowerFollowee(followerPersonId, followeePersonId);
        } catch (SQLException se) {
            s_log.fine(String.format("User #:%d already follows User #:%d",followerPersonId, followeePersonId) );
        }


    }

    @RequestMapping(value = "unfollow/{followeePersonId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void unfollow(@PathVariable Long followeePersonId) {
        ChallengeUserPrincipal currentUser = getChallengeUserPrincipal();
        Long followerPersonId = currentUser.getPerson().getId();

        try {
            challengeService.deleteFollowerFollowee(followerPersonId, followeePersonId);
        } catch (SQLException se) {
            s_log.fine(String.format("User #:%d has not followed User #:%d yet!",followerPersonId, followeePersonId));
        }

    }


}

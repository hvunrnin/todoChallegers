package login.loginspring.service;

import login.loginspring.domain.Challenge;
import login.loginspring.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Transactional
public class ChallengeService {
    private final ChallengeRepository challengeRepository;

    @Autowired
    public ChallengeService(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    public String make(Challenge challenge) {
        challengeRepository.save(challenge);
        return challenge.getTitle();
    }

    public boolean checkTitle(Challenge challenge) {
        Optional<Challenge> findTitle = challengeRepository.findByTitle(challenge.getTitle());

        if(findTitle.isPresent()){
            return true;
        }
        else {
            return false;
        }
    }
    public List<Challenge> findChallenges() {
        return challengeRepository.findAll();
    }

    public Challenge getPost(String title){
        Optional<Challenge> findTitle = challengeRepository.findByTitle(title);
        Challenge challenge = findTitle.get();

        return challenge;
    }

    public void deleteChallenge(String title){
        challengeRepository.deleteByTitle(title);
    }

}

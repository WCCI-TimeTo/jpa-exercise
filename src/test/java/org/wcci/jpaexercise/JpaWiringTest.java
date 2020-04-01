package org.wcci.jpaexercise;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JpaWiringTest {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void accountShouldHaveAUserAndAFriendsList() {
        User testUser = new User("Jill");
        userRepo.save(testUser);
        Account testAccount = new Account(testUser);
        accountRepo.save(testAccount);
        User testFriend = new User("Jack");
        userRepo.save(testFriend);
        testAccount.addFriend(testFriend);
        accountRepo.save(testAccount);

        flushAndClear();

        User retrievedUser = userRepo.findById(testUser.getId()).get();
        assertThat(retrievedUser.getAccount()).isEqualTo(testAccount);
        assertThat(retrievedUser.getAccount().getFriends()).contains(testFriend);
    }

    private void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }
}

package org.wcci.jpaexercise;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JpaWiringTest {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private PersonRepository personRepo;

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

    @Test
    public void personShouldHavePersonFriends(){
        Person testPerson = new Person("Larry");
        personRepo.save(testPerson);
        Person testFriend = new Person("Moe");
        personRepo.save(testFriend);
        Person anotherTestFriend = new Person("Curly");
        personRepo.save(anotherTestFriend);
        testPerson.addFriend(testFriend);
        personRepo.save(testPerson);
        testFriend.addFriend(anotherTestFriend);
        personRepo.save(testFriend);

        flushAndClear();

        Person retrievedPerson = personRepo.findById(testPerson.getId()).get();
        assertThat(retrievedPerson.getFriends()).contains(testFriend);

        Person retrievedFriend = personRepo.findById(testFriend.getId()).get();
        assertThat(retrievedFriend.getFriendsWith()).contains(testPerson, anotherTestFriend);
    }

    private void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }
}

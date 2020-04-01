package org.wcci.jpaexercise;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private User user;
    @ManyToMany
    private Set<User> friends;

    protected Account() {
    }

    public Account(User user) {
        friends = new HashSet<>();
        this.user = user;
    }


    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Set<User> getFriends() {
        return friends;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void addFriend(User friend) {
        friends.add(friend);
    }
}

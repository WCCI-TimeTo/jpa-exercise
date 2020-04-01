package org.wcci.jpaexercise;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Entity
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToMany
    private Set<Person> friends;

    protected Person(){}
    public Person(String name) {
        this.friends = new HashSet<>();
        this.name = name;
    }

    public void addFriend(Person friend) {
        friends.add(friend);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Person> getFriends() {
        return friends;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

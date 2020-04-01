package org.wcci.jpaexercise;

import javax.persistence.*;
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
    @ManyToMany(mappedBy = "friends")
    private Set<Person> friendsWith;
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

    public Set<Person> getFriendsWith() {
        return friendsWith;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
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

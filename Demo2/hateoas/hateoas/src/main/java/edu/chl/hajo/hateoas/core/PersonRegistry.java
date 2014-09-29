package edu.chl.hajo.hateoas.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Database mock up
 *
 * @author hajo
 */
public enum PersonRegistry {

    INSTANCE;

    private final List<Person> data = new ArrayList<>();

    private PersonRegistry() {
        data.add(new Person(11L, "Pelle", 11, new Car("1111", "Volvo")));
        data.add(new Person(22L, "Fia", 22, new Car("2222", "Volvo")));
        data.add(new Person(33L, "Kalle", 33, new Car("3333", "Volvo")));
        data.add(new Person(44L, "Siv", 44, new Car("4444", "Volvo")));
        data.add(new Person(55L, "Olle", 55, new Car("5555", "Volvo")));
        data.add(new Person(66L, "Eva", 66, new Car("6666", "Volvo")));
        data.add(new Person(77L, "Otto", 77, new Car("7777", "Volvo")));
    }

    public void insert(Person p) {
        if (data.contains(p)) {
            throw new IllegalArgumentException("Person already exists id " + p.getPnumb());
        }
        data.add(p);
    }

    public Person selectByPk(Long pnum) {
        for (Person p : data) {
            if (p.getPnumb().equals(pnum)) {
                return p;
            }
        }
        return null;
    }

    public Person update(Person p) {
        Person old = selectByPk(p.getPnumb());
        int i = data.indexOf(old);
        if (i > -1) {
            data.set(i, p);
            return old;
        } else {
            throw new IllegalArgumentException("Person not found id " + p.getPnumb());
        }
    }

    public void delete(Long pnumb) {
        Person p = selectByPk(pnumb);
        if (p != null) {
            data.remove(p);
        } else {
            throw new IllegalArgumentException("Person not found id " + pnumb);
        }
    }

    public List<Person> selectAll() {
        return Collections.unmodifiableList(data);
    }

    public List<Person> select(int start, int count) {
        int end = (start + count > data.size()) ? data.size() : start + count;
        List<Person> ps = data.subList(start, end);
        return Collections.unmodifiableList(ps);
    }

    public int count() {
        return data.size();
    }
}

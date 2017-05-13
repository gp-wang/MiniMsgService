package challenge.domain;

/**
 * Created by gaopeng on 5/11/17.
 */
public class Person {

    private final Long id;
    private final String name;

    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

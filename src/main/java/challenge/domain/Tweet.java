package challenge.domain;

import java.io.Serializable;

/**
 * Created by gaopeng on 5/11/17.
 */
public class Tweet implements Serializable {

    private static final long serialVersionUID = 5690458526841304834L;

    public final Long id;

    public final Long personId;

    public final String content;

    public Tweet(Long id, Long personId, String content) {
        this.id = id;
        this.personId = personId;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public Long getPersonId() {
        return personId;
    }

    public String getContent() {
        return content;
    }
}

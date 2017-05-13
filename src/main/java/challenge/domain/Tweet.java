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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tweet tweet = (Tweet) o;

        if (id != null ? !id.equals(tweet.id) : tweet.id != null) return false;
        if (personId != null ? !personId.equals(tweet.personId) : tweet.personId != null) return false;
        return content != null ? content.equals(tweet.content) : tweet.content == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (personId != null ? personId.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}

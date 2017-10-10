package be.howest.jwt.o2.domain.entities;

import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;

/**
 *
 * @author Hayk
 */
public class Partim implements Serializable {

    private static final long serialVersionUID = 1L;

    private final long id;
    private final String code;
    private final String name;
    private final int credits;

    public Partim(long id, String code, String name, int credits) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.credits = credits;
    }

    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
    
    public int getCredits() {
        return credits;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.code.toLowerCase(Locale.ENGLISH));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Partim other = (Partim) obj;
        return code.equalsIgnoreCase(other.code);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(id).append(") ")
                .append("[").append(code).append("] ")
                .append(name).append(" [")
                .append(credits).append("]")
                .toString();

    }
}

package be.howest.jwt.o2.domain.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 *
 * @author Hayk
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private final long id;
    private final String username;
    private final String password;
    private List<Partim> partims;

    public User(long id, String username, String password) {
        this(id, username, password, new ArrayList<>());
    }

    public User(long id, String username, String password, List<Partim> partims) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.partims = partims;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public boolean addPartim(Partim partim) {
        if (partim == null) {
            return false;
        }
        return partims.add(partim);
    }
    
    public void setPartims(List<Partim> partims) {
        this.partims = partims;
    }
    
    public List<Partim> getPartims() {
        return Collections.unmodifiableList(partims);
    }

    public boolean passwordMatch(String password) {
        return this.password.equals(password);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.username.toLowerCase(Locale.ENGLISH));
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
        final User other = (User) obj;
        return username.equalsIgnoreCase(other.username);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(") ")
                .append(username);
        if (partims.size() > 0) {
            sb.append("\n").append(partims.stream()
                    .filter(partim -> partim != null)
                    .map(partim -> "\t(" + partim.toString())
                    .reduce("", (previous, current)
                            -> ("".equals(previous) ? previous : previous + "\n") + current));
        }
        return sb.toString();
    }
}

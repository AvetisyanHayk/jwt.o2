package be.howest.jwt.o2.data;

import be.howest.jwt.o2.domain.entities.Partim;
import be.howest.jwt.o2.domain.entities.User;
import be.howest.jwt.o2.ex.DBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Hayk
 */
public class PartimRepository extends AbstractRepository {

    private static final String SQL = "SELECT * FROM partim";
    private static final String SQL_READ = SQL + " WHERE id = ?";
    private static final String SQL_FIND_ALL = SQL + " ORDER BY code, name";
    private static final String SQL_FIND_BY_USER
            = "SELECT partim.id, code, name, credits FROM partim"
            + " JOIN user_partim ON partim.id = user_partim.partimid"
            + " JOIN user ON user_partim.userid = user.id"
            + " WHERE user.id = ?"
            + " ORDER BY code";
    private static final String SQL_FIND_BY_IDS = SQL
            + " WHERE id IN (%ids)";

    public Partim read(long id) {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_READ)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return build(resultSet);
                }
            }
            return null;
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
    }

    public List<Partim> findAll() {
        List<Partim> entities = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL)) {
            while (resultSet.next()) {
                entities.add(build(resultSet));
            }
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
        return entities;
    }

    public List<Partim> findByUser(User user) {
        List<Partim> entities = new ArrayList<>();
        if (user != null) {
            try (Connection connection = dataSource.getConnection();
                    PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_USER)) {
                statement.setLong(1, user.getId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        entities.add(build(resultSet));
                    }
                }
            } catch (SQLException ex) {
                throw new DBException(ex);
            }
        }
        return entities;
    }

    public List<Partim> findByIds(Set<Long> ids) {
        List<Partim> entities = new ArrayList<>();
        String sql = SQL_FIND_BY_IDS.replace("%ids", getIdsToString(ids));
        System.out.println(sql);
        try (Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                entities.add(build(resultSet));
            }
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
        return entities;
    }

    private String getIdsToString(Set<Long> ids) {
        if (ids.isEmpty()) {
            return "''";
        }
        return ids.stream().map(String::valueOf)
                .reduce("", (previous, current)
                        -> previous + (("".equals(previous)) ? current : ", " + current));
    }

    private Partim build(ResultSet resultSet) throws SQLException {
        return new Partim(
                resultSet.getLong("id"),
                resultSet.getString("code"),
                resultSet.getString("name"),
                resultSet.getInt("credits")
        );
    }
}

package be.howest.jwt.o2.data;

import be.howest.jwt.o2.domain.entities.Partim;
import be.howest.jwt.o2.domain.entities.User;
import be.howest.jwt.o2.ex.DBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Hayk
 */
public class UserRepository extends AbstractRepository {

    private static final String SQL = "SELECT * FROM user";
    private static final String SQL_READ = SQL + " WHERE id = ?";
    private static final String SQL_FIND_BY_USERNAME = SQL + " WHERE username = ?";
    private static final String SQL_INSERT = "INSERT INTO user(username, password) VALUES(?, ?)";
    private static final String SQL_DELETE_USER_PARTIMS = "DELETE FROM user_partim WHERE userid = ?";
    private static final String SQL_INSERT_PARTIMS = "INSERT INTO user_partim(userid, partimid) VALUES(?, ?)";

    private final PartimRepository partimRepository = new PartimRepository();

    public User read(long id) {
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

    public User findByUsername(String username) {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_USERNAME)) {
            statement.setString(1, username);
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

    public User readWithPartims(User user) {
        if (user != null) {
            partimRepository.setDataSource(dataSource);
            user.setPartims(partimRepository.findByUser(user));
        }
        return user;
    }

    public User readWithPartims(long userid) {
        return readWithPartims(read(userid));
    }

    public User findByUsernameWithPartims(String username) {
        return readWithPartims(findByUsername(username));
    }

    public boolean save(String username, String password) {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            statement.setString(1, username);
            statement.setString(2, password);
            int count = statement.executeUpdate();
            connection.commit();
            return count == 1;
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
    }

    public void update(User user) {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statementDelete = connection.prepareStatement(SQL_DELETE_USER_PARTIMS)) {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            statementDelete.setLong(1, user.getId());
            statementDelete.execute();

            try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_PARTIMS)) {
                for (Partim partim : user.getPartims()) {
                    statement.setLong(1, user.getId());
                    statement.setLong(2, partim.getId());
                    statement.addBatch();
                }
                statement.executeBatch();
            }
            connection.commit();
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
    }

    private User build(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong("id"),
                resultSet.getString("username"),
                resultSet.getString("password")
        );
    }
}

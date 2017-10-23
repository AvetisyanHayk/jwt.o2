package be.howest.jwt.o2.data;

import javax.sql.DataSource;

/**
 *
 * @author Hayk
 */
abstract class AbstractRepository {
    public static final String JNDI_NAME = "jdbc/jwto2db";
    
    protected DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}

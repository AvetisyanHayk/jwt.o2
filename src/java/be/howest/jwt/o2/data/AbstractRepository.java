package be.howest.jwt.o2.data;

import javax.sql.DataSource;

/**
 *
 * @author Hayk
 */
public class AbstractRepository {
    public static final String JNDI_NAME = "jdbc/javawebtechdb";
    
    protected DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}

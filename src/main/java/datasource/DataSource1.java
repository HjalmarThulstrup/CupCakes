package datasource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DataSource1
{
    MysqlDataSource dataSource = new MysqlDataSource();
    
    public DataSource1()
    {
        dataSource.setServerName("138.68.103.105");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("cupcake_factory");
        dataSource.setUser("cupcakeboi");
        dataSource.setPassword("cake");
    }
    
    public MysqlDataSource getDataSource()
    {
        return dataSource;
    }
}

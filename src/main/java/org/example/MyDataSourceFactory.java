package org.example;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyDataSourceFactory {
    public static DataSource getDataSource() {
        Properties prop = new Properties();
        try (InputStream input = MyDataSourceFactory.class.getClassLoader().getResourceAsStream("db.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(prop.getProperty("db.driver"));
        dataSource.setUrl(prop.getProperty("db.url"));
        dataSource.setUsername(prop.getProperty("db.username"));
        dataSource.setPassword(prop.getProperty("db.password"));
        dataSource.setInitialSize(5); // Initial number of connections
        dataSource.setMaxTotal(10); // Maximum number of connections
        return dataSource;
    }


}

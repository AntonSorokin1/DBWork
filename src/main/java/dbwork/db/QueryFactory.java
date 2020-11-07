package dbwork.db;

import dbwork.PropertiesLoader;
import dbwork.db.tabels.Table;
import dbwork.db.tabels.TableType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class QueryFactory {
    private static final Properties PROPERTIES = PropertiesLoader.getProperties("query.properties");

    private Connection connection;

    public QueryFactory(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement getInsertStatement(TableType type) throws SQLException {
        String query = PROPERTIES.getProperty("insert_" + type.getQuery());
        PreparedStatement statement = connection.prepareStatement(query);

        // Выставляет null на всех полях запроса
        for (Object enumElem : type.getTable().getEnumConstants()) {
            Table column = (Table) enumElem;
            if (column.ordinal() == 0) continue;
            statement.setNull(column.ordinal(), column.getType());
        }

        return statement;
    }

    public PreparedStatement getSelectStatement(TableType type) throws SQLException {
        String query = PROPERTIES.getProperty("select_" + type.getQuery());
        PreparedStatement statement = connection.prepareStatement(query);
        return statement;
    }
}
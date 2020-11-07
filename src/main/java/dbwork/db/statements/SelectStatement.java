package dbwork.db.statements;

import dbwork.db.QueryFactory;
import dbwork.db.tabels.TableType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectStatement {
    private QueryFactory queryFactory;

    public SelectStatement(Connection connection) {
        queryFactory = new QueryFactory(connection);
    }

    public List<Integer> selectParticipantId(int amount) throws SQLException {
        List<Integer> result = new ArrayList<>();

        try (PreparedStatement statement = queryFactory.getSelectStatement(TableType.PARTICIPANT)) {
            statement.setInt(1, amount);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(resultSet.getInt("participantId"));
                }
            }
        }

        return result;
    }

    public Integer selectSignerId() throws SQLException {
        Integer result = null;

        try (PreparedStatement statement = queryFactory.getSelectStatement(TableType.SIGNER)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result = resultSet.getInt("signerId");
                }
            }
        }

        return result;
    }

    public List<Integer> selectPaymentDocumentId(int amount) throws SQLException {
        List<Integer> result = new ArrayList<>();

        try (PreparedStatement statement = queryFactory.getSelectStatement(TableType.PAYMENT_DOCUMENT)) {
            statement.setInt(1, amount);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(resultSet.getInt("paymentDocumentId"));
                }
            }
        }

        return result;
    }

    public Integer selectDocumentTitleId() throws SQLException {
        Integer result = null;

        try (PreparedStatement statement = queryFactory.getSelectStatement(TableType.DOCUMENT_TITLE)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result = resultSet.getInt("documentTitleTableId");
                }
            }
        }

        return result;
    }
}

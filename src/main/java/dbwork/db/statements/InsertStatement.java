package dbwork.db.statements;

import dbwork.db.QueryFactory;
import dbwork.db.tabels.TableType;
import dbwork.db.tabels.impl.*;
import dbwork.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InsertStatement {
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);

    private QueryFactory queryFactory;

    public InsertStatement(Connection connection) {
        queryFactory = new QueryFactory(connection);
    }

    public PreparedStatement insertDocumentTitle(����.�������� document) throws SQLException, ParseException {
        PreparedStatement statement = queryFactory.getInsertStatement(TableType.DOCUMENT_TITLE);

        // ��� �� ��������� ������������
        statement.setString(DocumentTitlesTable.TAX_CODE.ordinal(), document.get���());

        ����.��������.�������� �������� = document.get��������();
        // ����� �����������
        statement.setInt(DocumentTitlesTable.PATCH_NUMBER.ordinal(), ��������.get�������().get����������().intValue());
        // ���� ����������
        java.sql.Date date = new java.sql.Date(DATE_FORMATTER.parse(��������.get�������().get�����������()).getTime());
        statement.setDate(DocumentTitlesTable.PATCH_DATE.ordinal(), date);
        // ����� �����-�������
        statement.setString(DocumentTitlesTable.INVOICE_NUMBER.ordinal(), ��������.get��������());
        // ���� �����-�������
        date = new java.sql.Date(DATE_FORMATTER.parse(��������.get�������()).getTime());
        statement.setDate(DocumentTitlesTable.INVOICE_DATE.ordinal(), date);
        // ��� ������
        statement.setString(DocumentTitlesTable.CURRENCY_CODE.ordinal(), ��������.get������());

        ����.��������.���������� ���������� = document.get����������();
        // ����� � ������ � �������
        statement.setBigDecimal(DocumentTitlesTable.TOTAL_PAYABLE_WITH_TAX.ordinal(), ����������.get��������().get���������������());
        // ����� � ������ ��� ������
        statement.setBigDecimal(DocumentTitlesTable.TOTAL_PAYABLE_WITHOUT_TAX.ordinal(), ����������.get��������().get����������������());
        // ����� ������
        statement.setBigDecimal(DocumentTitlesTable.TAX_AMOUNT.ordinal(), ����������.get��������().get�����������().get������());

        return statement;
    }

    public PreparedStatement insertPaymentDocument(����.��������.��������.����� paymentDocument) throws SQLException, ParseException {
        PreparedStatement statement = queryFactory.getInsertStatement(TableType.PAYMENT_DOCUMENT);

        // ����� ��������-���������� ���������
        statement.setString(PaymentDocumentsTable.PAYMENT_DOCUMENT_NUMBER.ordinal(), paymentDocument.get��������());
        // ���� ���������� ��������-���������� ���������
        java.sql.Date date = new java.sql.Date(DATE_FORMATTER.parse(paymentDocument.get�������()).getTime());
        statement.setDate(PaymentDocumentsTable.PAYMENT_DOCUMENT_CREATION_DATE.ordinal(), date);

        return statement;
    }

    public List<PreparedStatement> insertParticipant(����.��������.�������� participant) throws SQLException {
        List<PreparedStatement> statements = new ArrayList<>();

        // ��������
        if (participant.get������() != null) {
            PreparedStatement sellerStatement = queryFactory.getInsertStatement(TableType.PARTICIPANT);
            ������������ seller = participant.get������();
            setParticipantSellerCustomer(sellerStatement, seller);
            statements.add(sellerStatement);
        }
        // ����������
        if (participant.get�������() != null) {
            PreparedStatement customerStatement = queryFactory.getInsertStatement(TableType.PARTICIPANT);
            ������������ customer = participant.get�������();
            setParticipantSellerCustomer(customerStatement, customer);
            statements.add(customerStatement);
        }
        // ����������������
        if (participant.get������().get��������() != null) {
            PreparedStatement shipperStatement = queryFactory.getInsertStatement(TableType.PARTICIPANT);
            ���������������� shipper = participant.get������().get��������();
            setParticipantShipperConsignee(shipperStatement, shipper);
            statements.add(shipperStatement);
        }
        // ���������������
        if (participant.get���������() != null) {
            PreparedStatement consigneeStatement = queryFactory.getInsertStatement(TableType.PARTICIPANT);
            ���������������� consignee = participant.get���������();
            setParticipantShipperConsignee(consigneeStatement, consignee);
            statements.add(consigneeStatement);
        }

        return statements;
    }

    private void setParticipantSellerCustomer(PreparedStatement statement, ������������ sellerCustomer) throws SQLException {
        // ��
        if (sellerCustomer.get����().get����() != null) {
            ������������.����.���� fl = sellerCustomer.get����().get����();
            // ��� ���������
            statement.setString(ParticipantsTable.PARTICIPANT_INN.ordinal(), fl.get�����());
            // �������
            statement.setString(ParticipantsTable.SECOND_NAME.ordinal(), fl.get�����().get�������());
            // ���
            statement.setString(ParticipantsTable.FIRST_NAME.ordinal(), fl.get�����().get���());
            // ��������
            statement.setString(ParticipantsTable.LAST_NAME.ordinal(), fl.get�����().get��������());
        }
        // ��
        if (sellerCustomer.get����().get����() != null) {
            ������������.����.���� ul = sellerCustomer.get����().get����();
            // ��� ���������
            statement.setString(ParticipantsTable.PARTICIPANT_INN.ordinal(), ul.get�����());
            // �������� �����������
            statement.setString(ParticipantsTable.ORGANIZATION_NAME.ordinal(), ul.get�������());
            // ��� �����������
            statement.setString(ParticipantsTable.ORGANIZATION_INN.ordinal(), ul.get�����());
            // ��� ���������� �� ����
            statement.setString(ParticipantsTable.REGISTRATION_CODE.ordinal(), ul.get���());
        }
        // �����
        setParticipantAddress(statement, sellerCustomer.get�����());
    }

    private void setParticipantShipperConsignee(PreparedStatement statement, ���������������� shipperConsignee) throws SQLException {
        // �������� �����������
        statement.setString(ParticipantsTable.ORGANIZATION_NAME.ordinal(), shipperConsignee.get�������().get�������());
        // �������
        statement.setString(ParticipantsTable.SECOND_NAME.ordinal(), shipperConsignee.get�������().get�����().get�������());
        // ���
        statement.setString(ParticipantsTable.FIRST_NAME.ordinal(), shipperConsignee.get�������().get�����().get���());
        // ��������
        statement.setString(ParticipantsTable.LAST_NAME.ordinal(), shipperConsignee.get�������().get�����().get��������());
        // �����
        setParticipantAddress(statement, shipperConsignee.get�����());
    }

    private void setParticipantAddress(PreparedStatement statement, �������� address) throws SQLException {
        if (address.get�����() != null) {
            �������� rfAddress = address.get�����();
            // ������
            statement.setString(ParticipantsTable.INDEX.ordinal(), rfAddress.get������());
            // ��� �������
            statement.setString(ParticipantsTable.REGION_CODE.ordinal(), rfAddress.get���������());
            // ������
            statement.setString(ParticipantsTable.REGION.ordinal(), rfAddress.get�����());
            // �����
            statement.setString(ParticipantsTable.CITY.ordinal(), rfAddress.get�����());
            // ���������� �����
            statement.setString(ParticipantsTable.LOCALITY.ordinal(), rfAddress.get����������());
            // �����
            statement.setString(ParticipantsTable.STREET.ordinal(), rfAddress.get�����());
            // ���
            statement.setString(ParticipantsTable.HOUSE.ordinal(), rfAddress.get���());
            // ������
            statement.setString(ParticipantsTable.HOUSING.ordinal(), rfAddress.get������());
            // ��������
            statement.setString(ParticipantsTable.FLAT.ordinal(), rfAddress.get�����());
        }
        if (address.get������() != null) {
            ��������� inoAddress = address.get������();
            // ��� ������
            statement.setString(ParticipantsTable.COUNTRY_CODE.ordinal(), inoAddress.get������());
            // �����
            statement.setString(ParticipantsTable.ADDRESS.ordinal(), inoAddress.get��������());
        }
    }

    public PreparedStatement insertDocumentString(����.��������.����������.������� product) throws SQLException {
        PreparedStatement statement = queryFactory.getInsertStatement(TableType.DOCUMENT_STRING);

        // ����� ������
        statement.setInt(DocumentStringsTable.STRING_ROW_NUMBER.ordinal(), product.get������().intValue());
        // ������������ ������/������
        statement.setString(DocumentStringsTable.GOODS_SERVICES_NAME.ordinal(), product.get�������());
        // ����������
        statement.setBigDecimal(DocumentStringsTable.NUMBER.ordinal(), product.get������());
        // ��� ������� ���������
        statement.setString(DocumentStringsTable.UNIT_CODE.ordinal(), product.get�������());
        // ���� �� �������
        statement.setBigDecimal(DocumentStringsTable.PRICE_PER_UNIT.ordinal(), product.get�������());
        // ��������� �������/����� ��� ������
        statement.setBigDecimal(DocumentStringsTable.GOODS_SERVICES_COST_WITHOUT_TAX.ordinal(), product.get�����������());
        // ����� ������������ �����������
        if (product.get������() != null) {
            statement.setBigDecimal(DocumentStringsTable.BUYER_TAX.ordinal(), product.get������().get������());
        }
        // ��������� �������/����� � �������
        statement.setBigDecimal(DocumentStringsTable.GOODS_SERVICES_COST_WITH_TAX.ordinal(), product.get����������());
        // ����� ������
        if (product.get�����() != null) {
            statement.setBigDecimal(DocumentStringsTable.EXCISE_AMOUNT.ordinal(), product.get�����().get��������());
        }
        // ���� ������
        java.sql.Date date = new java.sql.Date(new Date().getTime());
        statement.setDate(DocumentStringsTable.RECORD_DATE.ordinal(), date);
        // ��� ������ �������������
        statement.setString(DocumentStringsTable.MANUFACTURE_COUNTRY_CODE.ordinal(), product.get����().get(0).get���������());
        // ���� ���������� ������
        statement.setDate(DocumentStringsTable.UPDATE_DATE.ordinal(), date);
        // ��������� ������
        statement.setString(DocumentStringsTable.TAX_RATE.ordinal(), product.get�����());

        return statement;
    }

    public PreparedStatement insertSigner(����.��������.��������� signer) throws SQLException {
        PreparedStatement statement = queryFactory.getInsertStatement(TableType.SIGNER);

        // ��
        if (signer.get��() != null) {
            ����.��������.���������.�� signerIP = signer.get��();

            // ��� ����������
            statement.setString(SignersTable.SIGNER_TYPE.ordinal(), "��");
            // ��� ��
            statement.setString(SignersTable.INDIVIDUAL_INN.ordinal(), signerIP.get�����());
            // ��������� ������������� � ���. �����������
            statement.setString(SignersTable.REQUISITE_REGISTRATION.ordinal(), signerIP.get����������());
            // �������
            statement.setString(SignersTable.SECOND_NAME.ordinal(), signerIP.get���().get�������());
            // ���
            statement.setString(SignersTable.FIRST_NAME.ordinal(), signerIP.get���().get���());
            // ��������
            statement.setString(SignersTable.LAST_NAME.ordinal(), signerIP.get���().get��������());
        }

        // ��
        if (signer.get��() != null) {
            ����.��������.���������.�� signerUL = signer.get��();
            // ��� ����������
            statement.setString(SignersTable.SIGNER_TYPE.ordinal(), "��");
            // ��� ��
            statement.setString(SignersTable.ENTITY_INN.ordinal(), signerUL.get�����());
            // ���������
            statement.setString(SignersTable.POSITION.ordinal(), signerUL.get�����());
            // �������
            statement.setString(SignersTable.SECOND_NAME.ordinal(), signerUL.get���().get�������());
            // ���
            statement.setString(SignersTable.FIRST_NAME.ordinal(), signerUL.get���().get���());
            // ��������
            statement.setString(SignersTable.LAST_NAME.ordinal(), signerUL.get���().get��������());
        }

        // ���� ������� � �������
        java.sql.Date date = new java.sql.Date(new Date().getTime());
        statement.setDate(SignersTable.INSERTION_DATE.ordinal(), date);
        // ���� ���������� ������
        statement.setDate(SignersTable.UPDATE_DATE.ordinal(), date);

        return statement;
    }

    public PreparedStatement insertXMLDocument(String xmlText) throws SQLException {
        PreparedStatement statement = queryFactory.getInsertStatement(TableType.XML_DOCUMENT);
        // ���������� xml-�����
        statement.setString(XMLDocumentsTable.XML_FILE_CONTENT.ordinal(), xmlText);
        // ���� ������
        java.sql.Date date = new java.sql.Date(new Date().getTime());
        statement.setDate(XMLDocumentsTable.RECORD_DATE.ordinal(), date);

        return statement;
    }
}

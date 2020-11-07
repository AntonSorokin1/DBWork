package dbwork.db;

import dbwork.XMLConverter;
import dbwork.db.statements.InsertStatement;
import dbwork.db.statements.SelectStatement;
import dbwork.db.tabels.impl.DocumentStringsTable;
import dbwork.db.tabels.impl.DocumentTitlesTable;
import dbwork.entity.����;

import javax.xml.bind.JAXBException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private String driver;
    private String url;
    private String username;
    private String password;
    private Connection connection;

    private InsertStatement insertStatement;
    private SelectStatement selectStatement;

    public DBManager(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, username, password);
        insertStatement = new InsertStatement(connection);
        selectStatement = new SelectStatement(connection);
        return connection;
    }

    public void disconnect() throws SQLException {
        connection.close();
    }

    public void insert(Object object) throws SQLException, ParseException, JAXBException {
        ���� file = null;
        if (object instanceof ����) {
            file = (����) object;
        }
        if (file == null) return;

        // ��������
        ����.�������� document = file.get��������();

        // Statements ���������� ������
        ����.��������.�������� participants = document.get��������();
        List<PreparedStatement> participantStatements = insertStatement.insertParticipant(participants);
        // Execute
        for (PreparedStatement statement : participantStatements) {
            statement.execute();
            statement.close();
        }
        // Select participants id
        List<Integer> participantIdList = selectStatement.selectParticipantId(participantStatements.size());

        // Statements �����������
        ����.��������.��������� signer = document.get���������();
        PreparedStatement signerStatement = insertStatement.insertSigner(signer);
        // Execute
        signerStatement.execute();
        signerStatement.close();
        // Select signer id
        Integer signerId = selectStatement.selectSignerId();

        // Statements ������� ��������� ����������
        ����.��������.�������� paymentDocumentTable = document.get��������();
        List<PreparedStatement> paymentDocumentStatements = new ArrayList<>();
        for (����.��������.��������.����� paymentDocument: paymentDocumentTable.get�����()) {
            paymentDocumentStatements.add(insertStatement.insertPaymentDocument(paymentDocument));
        }
        // Execute
        for (PreparedStatement statement : paymentDocumentStatements) {
            statement.execute();
            statement.close();
        }
        // Select payment documents id
        List<Integer> paymentDocumentIdList = selectStatement.selectPaymentDocumentId(paymentDocumentStatements.size());

        // Statement ������� ���������� ����������
        PreparedStatement documentTitlesStatement = insertStatement.insertDocumentTitle(document);
        // ���������� �� �����
        // �� ��������
        documentTitlesStatement.setInt(DocumentTitlesTable.SELLER_ID.ordinal(), participantIdList.get(0));
        // �� ��������� (����������)
        documentTitlesStatement.setInt(DocumentTitlesTable.MEMBER_ID.ordinal(), participantIdList.get(1));
        // ���� ���������������� "�� ��"
        if (participantIdList.size() == 3) {
            // �� ����������������
            documentTitlesStatement.setInt(DocumentTitlesTable.SHIPPER_ID.ordinal(), participantIdList.get(0));
            // �� ���������������
            documentTitlesStatement.setInt(DocumentTitlesTable.CONSIGNEE_ID.ordinal(), participantIdList.get(2));
        }
        // ���� ���������������� ����������
        if (participantIdList.size() == 4) {
            // �� ����������������
            documentTitlesStatement.setInt(DocumentTitlesTable.SHIPPER_ID.ordinal(), participantIdList.get(2));
            // �� ���������������
            documentTitlesStatement.setInt(DocumentTitlesTable.CONSIGNEE_ID.ordinal(), participantIdList.get(3));
        }
        // �� ���������� ��������
        documentTitlesStatement.setInt(DocumentTitlesTable.SELLER_SIGNATORY_ID.ordinal(), signerId);
        // �� ���������� ����������
        documentTitlesStatement.setInt(DocumentTitlesTable.BUYER_SIGNATORY_ID.ordinal(), signerId);
        // �� ��������-���������� ���������
        documentTitlesStatement.setInt(DocumentTitlesTable.PAYMENT_SETTLEMENT_DOCUMENT_ID.ordinal(), paymentDocumentIdList.get(0));

        // Execute
        documentTitlesStatement.execute();
        documentTitlesStatement.close();
        // Select document title id
        Integer documentTitlesId = selectStatement.selectDocumentTitleId();

        // Statements ������ ����� ���������
        ����.��������.���������� table = document.get����������();
        for (����.��������.����������.������� product: table.get�������()) {
            PreparedStatement statement = insertStatement.insertDocumentString(product);
            if (documentTitlesId != null) {
                statement.setInt(DocumentStringsTable.TABLE_ID.ordinal(), documentTitlesId);
            }
            statement.execute();
        }

        // Statement ������� ��� �������� ���������� xml
        XMLConverter xmlConverter = new XMLConverter();
        String xmlText = xmlConverter.toXML(file);
        PreparedStatement xmlDocumentStatement = insertStatement.insertXMLDocument(xmlText);
        xmlDocumentStatement.execute();
        xmlDocumentStatement.close();
    }
}

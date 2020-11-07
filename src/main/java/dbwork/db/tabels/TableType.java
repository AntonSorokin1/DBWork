package dbwork.db.tabels;

import dbwork.db.tabels.impl.*;

public enum TableType {
    PARTICIPANT ("participants", ParticipantsTable.class),
    SIGNER ("signers", SignersTable.class),
    DOCUMENT_STRING ("document_strings", DocumentStringsTable.class),
    DOCUMENT_TITLE ("document_titles", DocumentTitlesTable.class),
    PAYMENT_DOCUMENT ("payment_documents", PaymentDocumentsTable.class),
    XML_DOCUMENT ("xml_documents", XMLDocumentsTable.class);

    private String query;
    private Class<?> table;

    TableType(String query, Class<?> table) {
        this.query = query;
        this.table = table;
    }

    public String getQuery() {
        return query;
    }

    public Class<?> getTable() {
        return table;
    }

    @Override
    public String toString() {
        return query;
    }
}

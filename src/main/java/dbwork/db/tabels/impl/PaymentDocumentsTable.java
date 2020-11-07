package dbwork.db.tabels.impl;

import dbwork.db.tabels.Table;

import java.sql.Types;

public enum PaymentDocumentsTable implements Table {
    PAYMENT_DOCUMENT_ID("ИД платежно-расчетного документа", Types.INTEGER),
    SOURCE_LINK("Ссылка источника", Types.INTEGER),
    PAYMENT_DOCUMENT_NUMBER("Номер платежно-расчетного документа", Types.VARCHAR),
    PAYMENT_DOCUMENT_CREATION_DATE("Дата составления платежно-расчетного документа", Types.DATE);

    private String title;
    private int type;

    PaymentDocumentsTable(String title, int type) {
        this.title = title;
        this.type = type;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return title;
    }
}

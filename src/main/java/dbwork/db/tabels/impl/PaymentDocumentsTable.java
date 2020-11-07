package dbwork.db.tabels.impl;

import dbwork.db.tabels.Table;

import java.sql.Types;

public enum PaymentDocumentsTable implements Table {
    PAYMENT_DOCUMENT_ID("�� ��������-���������� ���������", Types.INTEGER),
    SOURCE_LINK("������ ���������", Types.INTEGER),
    PAYMENT_DOCUMENT_NUMBER("����� ��������-���������� ���������", Types.VARCHAR),
    PAYMENT_DOCUMENT_CREATION_DATE("���� ����������� ��������-���������� ���������", Types.DATE);

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

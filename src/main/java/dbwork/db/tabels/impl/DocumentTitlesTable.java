package dbwork.db.tabels.impl;

import dbwork.db.tabels.Table;

import java.sql.Types;

public enum DocumentTitlesTable implements Table {
    TABLE_ID("�� �������", Types.INTEGER),
    INVOICE_NUMBER("����� �����-�������", Types.VARCHAR),
    INVOICE_DATE("���� �����-�������", Types.DATE),
    TAX_CODE("��� �� ��������� ������������", Types.VARCHAR),
    FILE_DATE("���� ����� ������ ���������� ��������", Types.DATE),
    FILE_TIME("����� ����� ������ ���������� ��������", Types.DATE),
    CURRENCY_CODE("��� ������", Types.VARCHAR),
    PATCH_NUMBER("����� �����������", Types.INTEGER),
    PATCH_DATE("���� �����������", Types.DATE),
    TOTAL_PAYABLE_WITH_TAX("����� � ������ � �������", Types.FLOAT),
    TOTAL_PAYABLE_WITHOUT_TAX("����� � ������ ��� ������", Types.FLOAT),
    TAX_AMOUNT("����� ������", Types.FLOAT),
    ENTRY_DATE("���� �������� ������", Types.DATE),
    FILE_ID("�� �����", Types.VARCHAR),
    FILE_SENDER_ID("�� ����������� ����� ������", Types.VARCHAR),
    FILE_RECIPIENT_ID("�� ���������� ����� ������", Types.VARCHAR),
    LAST_UPDATE_DATE("���� ���������� ���������� ������", Types.DATE),
    DOCUMENT_TYPE("��� ���������", Types.VARCHAR),
    ORIGINAL_DOCUMENT_NAME("������������ ���������� ���������", Types.VARCHAR),
    DOCUMENT_NAME_FACT("������������ ��������� �� �����", Types.VARCHAR),
    ECONOMIC_SUBJECT_NAME("������������ �������������� ��������", Types.VARCHAR),
    ECONOMIC_SUBJECT_BASE("��������� �������������� ��������", Types.VARCHAR),
    STATE_CONTACT_ID("�� ���. ���������", Types.VARCHAR),
    CURRENCY_NAME("������������ ������", Types.VARCHAR),
    CURRENCY_RATE("���� ������", Types.FLOAT),
    QUANTITY("���������� (����� �����)", Types.FLOAT),
    SELLER_ID("�� ��������", Types.INTEGER),
    SHIPPER_ID("�� ����������������", Types.INTEGER),
    CONSIGNEE_ID("�� ���������������", Types.INTEGER),
    SELLER_SIGNATORY_ID("�� ���������� ��������", Types.INTEGER),
    BUYER_SIGNATORY_ID("�� ���������� ����������", Types.INTEGER),
    PAYMENT_SETTLEMENT_DOCUMENT_ID("�� ��������-���������� ���������", Types.INTEGER),
    MEMBER_ID("�� ���������", Types.INTEGER);

    private String title;
    private int type;

    DocumentTitlesTable(String title, int type) {
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

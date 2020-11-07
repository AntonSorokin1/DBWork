package dbwork.db.tabels.impl;

import dbwork.db.tabels.Table;

import java.sql.Types;

public enum SignersTable implements Table {
    SIGNER_ID("�� ����������", Types.INTEGER),
    SOURCE_LINK("������ ���������", Types.INTEGER),
    SIGNER_TYPE("��� ����������", Types.VARCHAR),
    AUTHORITY_SPACE("������� ����������", Types.VARCHAR),
    SIGNER_STATUS("������ ����������", Types.VARCHAR),
    AUTHORITY_BASE("��������� ����������", Types.VARCHAR),
    ORGANIZATION_AUTHORITY_BASE("��������� ���������� �����������", Types.VARCHAR),
    INDIVIDUAL_INN("��� ��", Types.VARCHAR),
    INDIVIDUAL_OTHER("������ � ��", Types.VARCHAR),
    ENTITY_INN("��� ��", Types.VARCHAR),
    ORGANIZATION_NAME("�������� �����������", Types.VARCHAR),
    POSITION("���������", Types.VARCHAR),
    REQUISITE_REGISTRATION("��������� ������������� � ���. �����������", Types.VARCHAR),
    SECOND_NAME("�������", Types.VARCHAR),
    FIRST_NAME("���", Types.VARCHAR),
    LAST_NAME("��������", Types.VARCHAR),
    INSERTION_DATE("���� ������� � �������", Types.DATE),
    UPDATE_DATE("���� ���������� ������", Types.DATE);

    private String title;
    private int type;

    SignersTable(String title, int type) {
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

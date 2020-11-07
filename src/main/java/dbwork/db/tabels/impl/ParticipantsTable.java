package dbwork.db.tabels.impl;

import dbwork.db.tabels.Table;

import java.sql.Types;

public enum ParticipantsTable implements Table {
    PARTICIPANT_ID("�� ���������", Types.INTEGER),
    PARTICIPANT_TYPE("��� ���������", Types.VARCHAR),
    PARTICIPANT_INN("��� ���������", Types.VARCHAR),
    REQUISITES("��������� ������������� � ���. �����������", Types.VARCHAR),
    INDIVIDUAL_OTHER("������ � ��", Types.VARCHAR),
    ORGANIZATION_NAME("�������� �����������", Types.VARCHAR),
    ORGANIZATION_INN("��� �����������", Types.VARCHAR),
    REGISTRATION_CODE("��� ���������� �� ����", Types.VARCHAR),
    INDEX("������", Types.VARCHAR),
    REGION_CODE("��� �������", Types.VARCHAR),
    REGION("������", Types.VARCHAR),
    CITY("�����", Types.VARCHAR),
    LOCALITY("���������� �����", Types.VARCHAR),
    STREET("�����", Types.VARCHAR),
    HOUSE("���", Types.VARCHAR),
    HOUSING("������", Types.VARCHAR),
    FLAT("��������", Types.VARCHAR),
    COUNTRY_CODE("��� ������", Types.VARCHAR),
    ADDRESS("�����", Types.VARCHAR),
    PHONE("�������", Types.VARCHAR),
    EMAIL("����������� �����", Types.VARCHAR),
    BANK_NAME("�������� �����", Types.VARCHAR),
    BANK_ID("���������� �� ���", Types.VARCHAR),
    BANK_CORRESPONDENT_ACCOUNT("����������������� ���� �����", Types.VARCHAR),
    BANK_ACCOUNT_NUMBER("����� ����������� �����", Types.VARCHAR),
    RUSSIAN_CLASSIFIER_CODE("��� � �������������� ��������������", Types.VARCHAR),
    STRUCTURAL_SUBDIVISION("����������� �������������", Types.VARCHAR),
    INFO("���� ��� ��������� ����������������", Types.VARCHAR),
    ADDRESS_CODE("��� ������ �������", Types.VARCHAR),
    SECOND_NAME("�������", Types.VARCHAR),
    FIRST_NAME("���", Types.VARCHAR),
    LAST_NAME("��������", Types.VARCHAR);

    private String title;
    private int type;

    ParticipantsTable(String title, int type) {
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

package dbwork.db.tabels.impl;

import dbwork.db.tabels.Table;

import java.sql.Types;

public enum DocumentStringsTable implements Table {
    STRING_ID("�� ������", Types.INTEGER),
    STRING_ROW_NUMBER("����� ������ �������", Types.INTEGER),
    GOODS_SERVICES_NAME("������������ �������/�����", Types.VARCHAR),
    NUMBER("����������", Types.INTEGER),
    UNIT_CODE("��� ������� ���������", Types.VARCHAR),
    PRICE_PER_UNIT("���� �� �������", Types.INTEGER),
    GOODS_SERVICES_COST_WITHOUT_TAX("��������� �������/����� ��� ������", Types.INTEGER),
    BUYER_TAX("����� ������������ �����������", Types.INTEGER),
    GOODS_SERVICES_COST_WITH_TAX("��������� �������/����� � �������", Types.INTEGER),
    EXCISE_AMOUNT("����� ������", Types.INTEGER),
    RECORD_DATE("���� ������", Types.DATE),
    MANUFACTURE_COUNTRY_CODE("��� ������ �������������", Types.INTEGER),
    UPDATE_DATE("���� ����������", Types.DATE),
    NOMENCLATURE_STAGE("������� ������������", Types.VARCHAR),
    FEATURE_ADDITIONAL_INFO("���. ����. � ��������", Types.VARCHAR),
    PRODUCT_CHARACTERISTIC_CODE_ARTICLE_SORT("��������������/���/�������/���� ������", Types.VARCHAR),
    MEASURING_NAME("������������ ���������", Types.VARCHAR),
    MANUFACTURER_COUNTRY_NAME_SHORT("������� ������������ ������ �������������", Types.VARCHAR),
    RELEASED_AMOUNT("���-�� �������� ���������", Types.INTEGER),
    DEBIT("�����", Types.VARCHAR),
    CREDIT("������", Types.VARCHAR),
    TAX_RATE("��������� ������", Types.VARCHAR),
    TABLE_ID("�� �������", Types.INTEGER);

    private String title;
    private int type;

    DocumentStringsTable(String title, int type) {
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

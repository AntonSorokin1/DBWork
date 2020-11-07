package dbwork.db.tabels.impl;

import dbwork.db.tabels.Table;

import java.sql.Types;

public enum DocumentStringsTable implements Table {
    STRING_ID("ИД строки", Types.INTEGER),
    STRING_ROW_NUMBER("Номер строки таблицы", Types.INTEGER),
    GOODS_SERVICES_NAME("Наименование товаров/услуг", Types.VARCHAR),
    NUMBER("Количество", Types.INTEGER),
    UNIT_CODE("Код единицы измерения", Types.VARCHAR),
    PRICE_PER_UNIT("Цена за единицу", Types.INTEGER),
    GOODS_SERVICES_COST_WITHOUT_TAX("Стоимость товаров/услуг без налога", Types.INTEGER),
    BUYER_TAX("Налог оплачиваемый покупателем", Types.INTEGER),
    GOODS_SERVICES_COST_WITH_TAX("Стоимость товаров/услуг с налогом", Types.INTEGER),
    EXCISE_AMOUNT("Сумма акциза", Types.INTEGER),
    RECORD_DATE("Дата записи", Types.DATE),
    MANUFACTURE_COUNTRY_CODE("Код страны производителя", Types.INTEGER),
    UPDATE_DATE("Дата обновления", Types.DATE),
    NOMENCLATURE_STAGE("Признак номенклатуры", Types.VARCHAR),
    FEATURE_ADDITIONAL_INFO("Доп. инфо. о признаке", Types.VARCHAR),
    PRODUCT_CHARACTERISTIC_CODE_ARTICLE_SORT("Характеристика/код/артикул/сорт товара", Types.VARCHAR),
    MEASURING_NAME("Наименование измерения", Types.VARCHAR),
    MANUFACTURER_COUNTRY_NAME_SHORT("Краткое наименование страны производителя", Types.VARCHAR),
    RELEASED_AMOUNT("Кол-во надлежит отпустить", Types.INTEGER),
    DEBIT("Дебет", Types.VARCHAR),
    CREDIT("Кредит", Types.VARCHAR),
    TAX_RATE("Налоговая ставка", Types.VARCHAR),
    TABLE_ID("ИД таблицы", Types.INTEGER);

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

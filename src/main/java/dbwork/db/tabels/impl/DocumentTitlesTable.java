package dbwork.db.tabels.impl;

import dbwork.db.tabels.Table;

import java.sql.Types;

public enum DocumentTitlesTable implements Table {
    TABLE_ID("ИД таблицы", Types.INTEGER),
    INVOICE_NUMBER("Номер счета-фактуры", Types.VARCHAR),
    INVOICE_DATE("Дата счета-фактуры", Types.DATE),
    TAX_CODE("Код по налоговой документации", Types.VARCHAR),
    FILE_DATE("Дата файла обмена информации продавца", Types.DATE),
    FILE_TIME("Время файла обмена информации продавца", Types.DATE),
    CURRENCY_CODE("Код валюты", Types.VARCHAR),
    PATCH_NUMBER("Номер исправления", Types.INTEGER),
    PATCH_DATE("Дата исправления", Types.DATE),
    TOTAL_PAYABLE_WITH_TAX("Всего к оплате с налогом", Types.FLOAT),
    TOTAL_PAYABLE_WITHOUT_TAX("Всего к оплате без налога", Types.FLOAT),
    TAX_AMOUNT("Сумма налога", Types.FLOAT),
    ENTRY_DATE("Дата создания записи", Types.DATE),
    FILE_ID("ИД файла", Types.VARCHAR),
    FILE_SENDER_ID("ИД отправителя файла обмена", Types.VARCHAR),
    FILE_RECIPIENT_ID("ИД получателя файла обмена", Types.VARCHAR),
    LAST_UPDATE_DATE("Дата последнего обновления записи", Types.DATE),
    DOCUMENT_TYPE("Тип документа", Types.VARCHAR),
    ORIGINAL_DOCUMENT_NAME("Наименование первичного документа", Types.VARCHAR),
    DOCUMENT_NAME_FACT("Наименование документа по факту", Types.VARCHAR),
    ECONOMIC_SUBJECT_NAME("Наименование экономического субъекта", Types.VARCHAR),
    ECONOMIC_SUBJECT_BASE("Основание экономического субъекта", Types.VARCHAR),
    STATE_CONTACT_ID("ИД гос. контракта", Types.VARCHAR),
    CURRENCY_NAME("Наименование валюты", Types.VARCHAR),
    CURRENCY_RATE("Курс валюты", Types.FLOAT),
    QUANTITY("Количество (масса нетто)", Types.FLOAT),
    SELLER_ID("ИД Продавца", Types.INTEGER),
    SHIPPER_ID("ИД Грузоотправителя", Types.INTEGER),
    CONSIGNEE_ID("ИД Грузополучателя", Types.INTEGER),
    SELLER_SIGNATORY_ID("ИД подписанта Продавца", Types.INTEGER),
    BUYER_SIGNATORY_ID("ИД подписанта Покупателя", Types.INTEGER),
    PAYMENT_SETTLEMENT_DOCUMENT_ID("ИД платежно-расчетного документа", Types.INTEGER),
    MEMBER_ID("ИД участника", Types.INTEGER);

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

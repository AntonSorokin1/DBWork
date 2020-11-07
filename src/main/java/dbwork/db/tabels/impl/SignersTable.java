package dbwork.db.tabels.impl;

import dbwork.db.tabels.Table;

import java.sql.Types;

public enum SignersTable implements Table {
    SIGNER_ID("ИД подписанта", Types.INTEGER),
    SOURCE_LINK("Ссылка источника", Types.INTEGER),
    SIGNER_TYPE("Тип подписанта", Types.VARCHAR),
    AUTHORITY_SPACE("Область полномочий", Types.VARCHAR),
    SIGNER_STATUS("Статус подписанта", Types.VARCHAR),
    AUTHORITY_BASE("Основание полномочий", Types.VARCHAR),
    ORGANIZATION_AUTHORITY_BASE("Основание полномочий организации", Types.VARCHAR),
    INDIVIDUAL_INN("ИНН ФЛ", Types.VARCHAR),
    INDIVIDUAL_OTHER("Другое о ФЛ", Types.VARCHAR),
    ENTITY_INN("ИНН ЮЛ", Types.VARCHAR),
    ORGANIZATION_NAME("Название организации", Types.VARCHAR),
    POSITION("Должность", Types.VARCHAR),
    REQUISITE_REGISTRATION("Реквизиты свидетельства о гос. регистрации", Types.VARCHAR),
    SECOND_NAME("Фамилия", Types.VARCHAR),
    FIRST_NAME("Имя", Types.VARCHAR),
    LAST_NAME("Отчество", Types.VARCHAR),
    INSERTION_DATE("Дата вставки в таблицу", Types.DATE),
    UPDATE_DATE("Дата обновления строки", Types.DATE);

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

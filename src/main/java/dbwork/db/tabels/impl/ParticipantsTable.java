package dbwork.db.tabels.impl;

import dbwork.db.tabels.Table;

import java.sql.Types;

public enum ParticipantsTable implements Table {
    PARTICIPANT_ID("ИД участника", Types.INTEGER),
    PARTICIPANT_TYPE("Тип участника", Types.VARCHAR),
    PARTICIPANT_INN("ИНН участника", Types.VARCHAR),
    REQUISITES("Реквизиты свидетельства о гос. регистрации", Types.VARCHAR),
    INDIVIDUAL_OTHER("Другое о ФЛ", Types.VARCHAR),
    ORGANIZATION_NAME("Название организации", Types.VARCHAR),
    ORGANIZATION_INN("ИНН организации", Types.VARCHAR),
    REGISTRATION_CODE("Код постановки на учет", Types.VARCHAR),
    INDEX("Индекс", Types.VARCHAR),
    REGION_CODE("Код региона", Types.VARCHAR),
    REGION("Регион", Types.VARCHAR),
    CITY("Город", Types.VARCHAR),
    LOCALITY("Населенный пункт", Types.VARCHAR),
    STREET("Улица", Types.VARCHAR),
    HOUSE("Дом", Types.VARCHAR),
    HOUSING("Корпус", Types.VARCHAR),
    FLAT("Квартира", Types.VARCHAR),
    COUNTRY_CODE("Код страны", Types.VARCHAR),
    ADDRESS("Адрес", Types.VARCHAR),
    PHONE("Телефон", Types.VARCHAR),
    EMAIL("Электронная почта", Types.VARCHAR),
    BANK_NAME("Название банка", Types.VARCHAR),
    BANK_ID("Банковский ИД код", Types.VARCHAR),
    BANK_CORRESPONDENT_ACCOUNT("Корреспондентский счет банка", Types.VARCHAR),
    BANK_ACCOUNT_NUMBER("Номер банковского счета", Types.VARCHAR),
    RUSSIAN_CLASSIFIER_CODE("Код в общероссийском классификаторе", Types.VARCHAR),
    STRUCTURAL_SUBDIVISION("Структурное подразделение", Types.VARCHAR),
    INFO("Инфо для участника документооборота", Types.VARCHAR),
    ADDRESS_CODE("Код адреса объекта", Types.VARCHAR),
    SECOND_NAME("Фамилия", Types.VARCHAR),
    FIRST_NAME("Имя", Types.VARCHAR),
    LAST_NAME("Отчество", Types.VARCHAR);

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

package dbwork.db.tabels.impl;

import dbwork.db.tabels.Table;

import java.sql.Types;

public enum XMLDocumentsTable implements Table {
    TABLE_ID("ИД записи таблицы", Types.INTEGER),
    XML_FILE_CONTENT("Содержимое xml-файла", Types.CLOB),
    SOURCE("Источник", Types.VARCHAR),
    RECORD_DATE("Дата записи", Types.DATE);

    private String title;
    private int type;

    XMLDocumentsTable(String title, int type) {
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

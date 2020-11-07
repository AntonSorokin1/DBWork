package dbwork.db.statements;

import dbwork.db.QueryFactory;
import dbwork.db.tabels.TableType;
import dbwork.db.tabels.impl.*;
import dbwork.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InsertStatement {
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);

    private QueryFactory queryFactory;

    public InsertStatement(Connection connection) {
        queryFactory = new QueryFactory(connection);
    }

    public PreparedStatement insertDocumentTitle(Файл.Документ document) throws SQLException, ParseException {
        PreparedStatement statement = queryFactory.getInsertStatement(TableType.DOCUMENT_TITLE);

        // Код по налоговой документации
        statement.setString(DocumentTitlesTable.TAX_CODE.ordinal(), document.getКНД());

        Файл.Документ.СвСчФакт свСчФакт = document.getСвСчФакт();
        // Номер исправления
        statement.setInt(DocumentTitlesTable.PATCH_NUMBER.ordinal(), свСчФакт.getИспрСчФ().getНомИспрСчФ().intValue());
        // Дата испавления
        java.sql.Date date = new java.sql.Date(DATE_FORMATTER.parse(свСчФакт.getИспрСчФ().getДатаИспрСчФ()).getTime());
        statement.setDate(DocumentTitlesTable.PATCH_DATE.ordinal(), date);
        // Номер счета-фактуры
        statement.setString(DocumentTitlesTable.INVOICE_NUMBER.ordinal(), свСчФакт.getНомерСчФ());
        // Дата счета-фактуры
        date = new java.sql.Date(DATE_FORMATTER.parse(свСчФакт.getДатаСчФ()).getTime());
        statement.setDate(DocumentTitlesTable.INVOICE_DATE.ordinal(), date);
        // Код валюты
        statement.setString(DocumentTitlesTable.CURRENCY_CODE.ordinal(), свСчФакт.getКодОКВ());

        Файл.Документ.ТаблСчФакт таблСчФакт = document.getТаблСчФакт();
        // Всего к оплате с налогом
        statement.setBigDecimal(DocumentTitlesTable.TOTAL_PAYABLE_WITH_TAX.ordinal(), таблСчФакт.getВсегоОпл().getСтТовУчНалВсего());
        // Всего к оплате без налога
        statement.setBigDecimal(DocumentTitlesTable.TOTAL_PAYABLE_WITHOUT_TAX.ordinal(), таблСчФакт.getВсегоОпл().getСтТовБезНДСВсего());
        // Сумма налога
        statement.setBigDecimal(DocumentTitlesTable.TAX_AMOUNT.ordinal(), таблСчФакт.getВсегоОпл().getСумНалВсего().getСумНДС());

        return statement;
    }

    public PreparedStatement insertPaymentDocument(Файл.Документ.СвСчФакт.СвПРД paymentDocument) throws SQLException, ParseException {
        PreparedStatement statement = queryFactory.getInsertStatement(TableType.PAYMENT_DOCUMENT);

        // Номер платежно-расчетного документа
        statement.setString(PaymentDocumentsTable.PAYMENT_DOCUMENT_NUMBER.ordinal(), paymentDocument.getНомерПРД());
        // Дата соствления платежно-расчетного документа
        java.sql.Date date = new java.sql.Date(DATE_FORMATTER.parse(paymentDocument.getДатаПРД()).getTime());
        statement.setDate(PaymentDocumentsTable.PAYMENT_DOCUMENT_CREATION_DATE.ordinal(), date);

        return statement;
    }

    public List<PreparedStatement> insertParticipant(Файл.Документ.СвСчФакт participant) throws SQLException {
        List<PreparedStatement> statements = new ArrayList<>();

        // Продавец
        if (participant.getСвПрод() != null) {
            PreparedStatement sellerStatement = queryFactory.getInsertStatement(TableType.PARTICIPANT);
            СвПродПокТип seller = participant.getСвПрод();
            setParticipantSellerCustomer(sellerStatement, seller);
            statements.add(sellerStatement);
        }
        // Покупатель
        if (participant.getСвПокуп() != null) {
            PreparedStatement customerStatement = queryFactory.getInsertStatement(TableType.PARTICIPANT);
            СвПродПокТип customer = participant.getСвПокуп();
            setParticipantSellerCustomer(customerStatement, customer);
            statements.add(customerStatement);
        }
        // Грузоотправитель
        if (participant.getГрузОт().getГрузОтпр() != null) {
            PreparedStatement shipperStatement = queryFactory.getInsertStatement(TableType.PARTICIPANT);
            СвГрузОтпрПолТип shipper = participant.getГрузОт().getГрузОтпр();
            setParticipantShipperConsignee(shipperStatement, shipper);
            statements.add(shipperStatement);
        }
        // Грузополучатель
        if (participant.getГрузПолуч() != null) {
            PreparedStatement consigneeStatement = queryFactory.getInsertStatement(TableType.PARTICIPANT);
            СвГрузОтпрПолТип consignee = participant.getГрузПолуч();
            setParticipantShipperConsignee(consigneeStatement, consignee);
            statements.add(consigneeStatement);
        }

        return statements;
    }

    private void setParticipantSellerCustomer(PreparedStatement statement, СвПродПокТип sellerCustomer) throws SQLException {
        // ИП
        if (sellerCustomer.getИдСв().getСвФЛ() != null) {
            СвПродПокТип.ИдСв.СвФЛ fl = sellerCustomer.getИдСв().getСвФЛ();
            // ИНН участника
            statement.setString(ParticipantsTable.PARTICIPANT_INN.ordinal(), fl.getИННФЛ());
            // Фамилия
            statement.setString(ParticipantsTable.SECOND_NAME.ordinal(), fl.getФИОИП().getФамилия());
            // Имя
            statement.setString(ParticipantsTable.FIRST_NAME.ordinal(), fl.getФИОИП().getИмя());
            // Отчество
            statement.setString(ParticipantsTable.LAST_NAME.ordinal(), fl.getФИОИП().getОтчество());
        }
        // ЮЛ
        if (sellerCustomer.getИдСв().getСвЮЛ() != null) {
            СвПродПокТип.ИдСв.СвЮЛ ul = sellerCustomer.getИдСв().getСвЮЛ();
            // ИНН участника
            statement.setString(ParticipantsTable.PARTICIPANT_INN.ordinal(), ul.getИННЮЛ());
            // Название организации
            statement.setString(ParticipantsTable.ORGANIZATION_NAME.ordinal(), ul.getНаимОрг());
            // ИНН организации
            statement.setString(ParticipantsTable.ORGANIZATION_INN.ordinal(), ul.getИННЮЛ());
            // Код постановки на учет
            statement.setString(ParticipantsTable.REGISTRATION_CODE.ordinal(), ul.getКПП());
        }
        // Адрес
        setParticipantAddress(statement, sellerCustomer.getАдрес());
    }

    private void setParticipantShipperConsignee(PreparedStatement statement, СвГрузОтпрПолТип shipperConsignee) throws SQLException {
        // Название организации
        statement.setString(ParticipantsTable.ORGANIZATION_NAME.ordinal(), shipperConsignee.getНаимГОП().getНаимОрг());
        // Фамилия
        statement.setString(ParticipantsTable.SECOND_NAME.ordinal(), shipperConsignee.getНаимГОП().getФИОИП().getФамилия());
        // Имя
        statement.setString(ParticipantsTable.FIRST_NAME.ordinal(), shipperConsignee.getНаимГОП().getФИОИП().getИмя());
        // Отчество
        statement.setString(ParticipantsTable.LAST_NAME.ordinal(), shipperConsignee.getНаимГОП().getФИОИП().getОтчество());
        // Адрес
        setParticipantAddress(statement, shipperConsignee.getАдрес());
    }

    private void setParticipantAddress(PreparedStatement statement, АдресТип address) throws SQLException {
        if (address.getАдрРФ() != null) {
            АдрРФТип rfAddress = address.getАдрРФ();
            // Индекс
            statement.setString(ParticipantsTable.INDEX.ordinal(), rfAddress.getИндекс());
            // Код региона
            statement.setString(ParticipantsTable.REGION_CODE.ordinal(), rfAddress.getКодРегион());
            // Регион
            statement.setString(ParticipantsTable.REGION.ordinal(), rfAddress.getРайон());
            // Город
            statement.setString(ParticipantsTable.CITY.ordinal(), rfAddress.getГород());
            // Населенный пункт
            statement.setString(ParticipantsTable.LOCALITY.ordinal(), rfAddress.getНаселПункт());
            // Улица
            statement.setString(ParticipantsTable.STREET.ordinal(), rfAddress.getУлица());
            // Дом
            statement.setString(ParticipantsTable.HOUSE.ordinal(), rfAddress.getДом());
            // Корпус
            statement.setString(ParticipantsTable.HOUSING.ordinal(), rfAddress.getКорпус());
            // Квартира
            statement.setString(ParticipantsTable.FLAT.ordinal(), rfAddress.getКварт());
        }
        if (address.getАдрИно() != null) {
            АдрИноТип inoAddress = address.getАдрИно();
            // Код страны
            statement.setString(ParticipantsTable.COUNTRY_CODE.ordinal(), inoAddress.getКодСтр());
            // Адрес
            statement.setString(ParticipantsTable.ADDRESS.ordinal(), inoAddress.getАдрТекст());
        }
    }

    public PreparedStatement insertDocumentString(Файл.Документ.ТаблСчФакт.СведТов product) throws SQLException {
        PreparedStatement statement = queryFactory.getInsertStatement(TableType.DOCUMENT_STRING);

        // Номер строки
        statement.setInt(DocumentStringsTable.STRING_ROW_NUMBER.ordinal(), product.getНомСтр().intValue());
        // Наименование товара/услуги
        statement.setString(DocumentStringsTable.GOODS_SERVICES_NAME.ordinal(), product.getНаимТов());
        // Количество
        statement.setBigDecimal(DocumentStringsTable.NUMBER.ordinal(), product.getКолТов());
        // Код единицы измерения
        statement.setString(DocumentStringsTable.UNIT_CODE.ordinal(), product.getОКЕИТов());
        // Цена за единицу
        statement.setBigDecimal(DocumentStringsTable.PRICE_PER_UNIT.ordinal(), product.getЦенаТов());
        // Стоимость товаров/услуг без налога
        statement.setBigDecimal(DocumentStringsTable.GOODS_SERVICES_COST_WITHOUT_TAX.ordinal(), product.getСтТовБезНДС());
        // Налог оплачиваемый покупателем
        if (product.getСумНал() != null) {
            statement.setBigDecimal(DocumentStringsTable.BUYER_TAX.ordinal(), product.getСумНал().getСумНДС());
        }
        // Стоимость товаров/услуг с налогом
        statement.setBigDecimal(DocumentStringsTable.GOODS_SERVICES_COST_WITH_TAX.ordinal(), product.getСтТовУчНал());
        // Сумма акциза
        if (product.getАкциз() != null) {
            statement.setBigDecimal(DocumentStringsTable.EXCISE_AMOUNT.ordinal(), product.getАкциз().getСумАкциз());
        }
        // Дата записи
        java.sql.Date date = new java.sql.Date(new Date().getTime());
        statement.setDate(DocumentStringsTable.RECORD_DATE.ordinal(), date);
        // Код страны производителя
        statement.setString(DocumentStringsTable.MANUFACTURE_COUNTRY_CODE.ordinal(), product.getСвТД().get(0).getКодПроисх());
        // Дата обновления строки
        statement.setDate(DocumentStringsTable.UPDATE_DATE.ordinal(), date);
        // Налоговая ставка
        statement.setString(DocumentStringsTable.TAX_RATE.ordinal(), product.getНалСт());

        return statement;
    }

    public PreparedStatement insertSigner(Файл.Документ.Подписант signer) throws SQLException {
        PreparedStatement statement = queryFactory.getInsertStatement(TableType.SIGNER);

        // ИП
        if (signer.getИП() != null) {
            Файл.Документ.Подписант.ИП signerIP = signer.getИП();

            // Тип подписанта
            statement.setString(SignersTable.SIGNER_TYPE.ordinal(), "ИП");
            // ИНН ФЛ
            statement.setString(SignersTable.INDIVIDUAL_INN.ordinal(), signerIP.getИННФЛ());
            // Реквизиты свидетельства о гос. регистрации
            statement.setString(SignersTable.REQUISITE_REGISTRATION.ordinal(), signerIP.getСвГосРегИП());
            // Фамилия
            statement.setString(SignersTable.SECOND_NAME.ordinal(), signerIP.getФИО().getФамилия());
            // Имя
            statement.setString(SignersTable.FIRST_NAME.ordinal(), signerIP.getФИО().getИмя());
            // Отчество
            statement.setString(SignersTable.LAST_NAME.ordinal(), signerIP.getФИО().getОтчество());
        }

        // ЮЛ
        if (signer.getЮЛ() != null) {
            Файл.Документ.Подписант.ЮЛ signerUL = signer.getЮЛ();
            // Тип подписанта
            statement.setString(SignersTable.SIGNER_TYPE.ordinal(), "ЮЛ");
            // ИНН ЮЛ
            statement.setString(SignersTable.ENTITY_INN.ordinal(), signerUL.getИННЮЛ());
            // Должность
            statement.setString(SignersTable.POSITION.ordinal(), signerUL.getДолжн());
            // Фамилия
            statement.setString(SignersTable.SECOND_NAME.ordinal(), signerUL.getФИО().getФамилия());
            // Имя
            statement.setString(SignersTable.FIRST_NAME.ordinal(), signerUL.getФИО().getИмя());
            // Отчество
            statement.setString(SignersTable.LAST_NAME.ordinal(), signerUL.getФИО().getОтчество());
        }

        // Дата вставки в таблицу
        java.sql.Date date = new java.sql.Date(new Date().getTime());
        statement.setDate(SignersTable.INSERTION_DATE.ordinal(), date);
        // Дата обновления строки
        statement.setDate(SignersTable.UPDATE_DATE.ordinal(), date);

        return statement;
    }

    public PreparedStatement insertXMLDocument(String xmlText) throws SQLException {
        PreparedStatement statement = queryFactory.getInsertStatement(TableType.XML_DOCUMENT);
        // Содержимое xml-файла
        statement.setString(XMLDocumentsTable.XML_FILE_CONTENT.ordinal(), xmlText);
        // Дата записи
        java.sql.Date date = new java.sql.Date(new Date().getTime());
        statement.setDate(XMLDocumentsTable.RECORD_DATE.ordinal(), date);

        return statement;
    }
}

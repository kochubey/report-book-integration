package logbook.intergation.exceptions;

public class MissingExchangeElement extends EtpmvException {

    private static final String MESSAGE_CODE = "1002";
    private static final String RESULT = "Отсутствует обязательный элемент exchange";
    private static final String DESCRIPTION = "В Body SOAP запроса отсутствует обязательный элемент exchange";

    public MissingExchangeElement() {
        super(MESSAGE_CODE, RESULT, DESCRIPTION);
    }
}

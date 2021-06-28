package logbook.intergation.exceptions;

public class MissingSessionIdAttribute extends EtpmvException{
    private static final String MESSAGE_CODE = "1005";
    private static final String RESULT = "Отсутствует обязательный атрибут sessionId";
    private static final String DESCRIPTION = "Отсутствует идентификатор сессии передачи сведений - sessionId";

    public MissingSessionIdAttribute() {
        super(MESSAGE_CODE, RESULT, DESCRIPTION);
    }
}

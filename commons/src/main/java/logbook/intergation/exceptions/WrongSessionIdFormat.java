package logbook.intergation.exceptions;

public class WrongSessionIdFormat extends EtpmvException{
    private static final String MESSAGE_CODE = "1009";
    private static final String RESULT = "Wrong sessionId format";
    private static final String DESCRIPTION = "Нарушен формат идентификатора сессии sessionId";

    public WrongSessionIdFormat() {
        super(MESSAGE_CODE, RESULT, DESCRIPTION);
    }
}

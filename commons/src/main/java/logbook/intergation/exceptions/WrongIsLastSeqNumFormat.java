package logbook.intergation.exceptions;

public class WrongIsLastSeqNumFormat extends EtpmvException{
    private static final String MESSAGE_CODE = "1013";
    private static final String RESULT = "isLast seqNum неверный формат";
    private static final String DESCRIPTION = "Ошибка формата значения isLast или seqNum";

    public WrongIsLastSeqNumFormat() {
        super(MESSAGE_CODE, RESULT, DESCRIPTION);
    }
}
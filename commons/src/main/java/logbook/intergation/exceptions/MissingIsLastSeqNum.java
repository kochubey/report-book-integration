package logbook.intergation.exceptions;

public class MissingIsLastSeqNum extends EtpmvException{
    private static final String MESSAGE_CODE = "1012";
    private static final String RESULT = "isLast или seqNum не указан";
    private static final String DESCRIPTION = "Ошибка: не указан один из двух элементов isLast и seqNum";

    public MissingIsLastSeqNum() {
        super(MESSAGE_CODE, RESULT, DESCRIPTION);
    }
}

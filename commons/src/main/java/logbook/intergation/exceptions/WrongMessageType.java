package logbook.intergation.exceptions;

public class WrongMessageType extends EtpmvException{
    private static final String MESSAGE_CODE = "1014";
    private static final String RESULT = "Wrong Message type";
    private static final String DESCRIPTION = "Ошибка в указанном типе элемента message";

    public WrongMessageType() {
        super(MESSAGE_CODE, RESULT, DESCRIPTION);
    }


}

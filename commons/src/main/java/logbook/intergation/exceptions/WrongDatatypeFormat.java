package logbook.intergation.exceptions;

public class WrongDatatypeFormat extends EtpmvException{
    private static final String MESSAGE_CODE = "1007";
    private static final String RESULT = "Wrong dataType format";
    private static final String DESCRIPTION = "Нарушен формат наменования запрошенного вида сведений dataType";

    public WrongDatatypeFormat() {
        super(MESSAGE_CODE, RESULT, DESCRIPTION);
    }
}

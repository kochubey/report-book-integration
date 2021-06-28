package logbook.intergation.exceptions;

public class MissingDatatypeAttribute extends EtpmvException{

    private static final String MESSAGE_CODE = "1004";
    private static final String RESULT = "Отсутствует обязательный атрибут dataType";
    private static final String DESCRIPTION = "Отсутствует наименование запрошенного вида сведений - dataType";

    public MissingDatatypeAttribute() {
        super(MESSAGE_CODE, RESULT, DESCRIPTION);
    }
}

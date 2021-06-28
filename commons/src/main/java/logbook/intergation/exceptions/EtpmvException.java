package logbook.intergation.exceptions;

public class EtpmvException extends Exception{

    private String code;
    private String result;
    private String desc;

    public EtpmvException(String code, String result, String desc) {
        super(desc);
        setCode(code);
        setDesc(desc);
        setResult(result);

    }

    public String getCode() { return code; }
    private void setCode(String code) { this.code = code; }
    public String getResult() { return result; }
    private void setResult(String result) { this.result = result; }
    public String getDesc() { return desc; }
    private void setDesc(String desc) { this.desc = desc; }
}

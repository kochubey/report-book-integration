package logbook.intergation.commons.datumnode;

public class Stat {
    public Stat(String uuid, Long code, String result, String description) {
        this.uuid = uuid;
        this.code = code;
        this.result = result;
        this.description = description;
    }

    private String uuid;

    private Long code;

    private String result;

    private String description;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

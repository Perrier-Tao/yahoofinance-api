package yahoofinance;

import static yahoofinance.SeverityEnum.ERROR;

/**
 * @author Perrier,TAO at 28/1/23
 */
public enum MessageEnum {
    NO_URL(1001, ERROR, "No URL provided");

    int code ;
    SeverityEnum severity;
    String message;

    MessageEnum(int code, SeverityEnum severity, String message) {
        this.code = code;
        this.severity = severity;
        this.message = message;
    }
}

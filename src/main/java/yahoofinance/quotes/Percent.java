package yahoofinance.quotes;

import java.math.BigDecimal;

public class Percent {
    private BigDecimal raw ;
    private String fmt ;

    //auto
    public BigDecimal getRaw() {
        return raw;
    }

    public void setRaw(BigDecimal raw) {
        this.raw = raw;
    }

    public String getFmt() {
        return fmt;
    }

    public void setFmt(String fmt) {
        this.fmt = fmt;
    }
}

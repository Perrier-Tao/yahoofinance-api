package yahoofinance.fi.fund;

import yahoofinance.quotes.Percent;

public class Holding {
    private String symbol;
    private String holdingName;
    private Percent holdingPercent;

    //auto
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getHoldingName() {
        return holdingName;
    }

    public void setHoldingName(String holdingName) {
        this.holdingName = holdingName;
    }

    public Percent getHoldingPercent() {
        return holdingPercent;
    }

    public void setHoldingPercent(Percent holdingPercent) {
        this.holdingPercent = holdingPercent;
    }
}

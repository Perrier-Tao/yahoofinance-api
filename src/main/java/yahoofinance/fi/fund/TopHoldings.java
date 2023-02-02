package yahoofinance.fi.fund;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import yahoofinance.quotes.Percent;
import yahoofinance.util.IJsonable;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TopHoldings implements IJsonable {
    private String symbol;
    private int maxAge;
    private Percent cashPosition;
    private Percent stockPosition;
    private Percent bondPosition;
    private Percent preferredPosition;
    private Percent convertiblePosition;
    private Percent otherPosition;

    private List<Holding> holdings;

    public static TopHoldings emptyOne(String symbol){
        TopHoldings th = new TopHoldings();
        th.symbol = symbol;
        return th;
    }

    // auto generated
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public Percent getCashPosition() {
        return cashPosition;
    }

    public void setCashPosition(Percent cashPosition) {
        this.cashPosition = cashPosition;
    }

    public Percent getStockPosition() {
        return stockPosition;
    }

    public void setStockPosition(Percent stockPosition) {
        this.stockPosition = stockPosition;
    }

    public Percent getBondPosition() {
        return bondPosition;
    }

    public void setBondPosition(Percent bondPosition) {
        this.bondPosition = bondPosition;
    }

    public Percent getPreferredPosition() {
        return preferredPosition;
    }

    public void setPreferredPosition(Percent preferredPosition) {
        this.preferredPosition = preferredPosition;
    }

    public Percent getConvertiblePosition() {
        return convertiblePosition;
    }

    public void setConvertiblePosition(Percent convertiblePosition) {
        this.convertiblePosition = convertiblePosition;
    }

    public Percent getOtherPosition() {
        return otherPosition;
    }

    public void setOtherPosition(Percent otherPosition) {
        this.otherPosition = otherPosition;
    }

    public List<Holding> getHoldings() {
        return holdings;
    }

    public void setHoldings(List<Holding> holdings) {
        this.holdings = holdings;
    }

}

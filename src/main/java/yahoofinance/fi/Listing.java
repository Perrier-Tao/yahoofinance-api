package yahoofinance.fi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import yahoofinance.util.IJsonable;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Security Listing describing where the Security be listed and currency and symbols
 *
 * @author Perrier,TAO at 29/1/23
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Listing implements IJsonable {
    // identifies
    private String isin ;       // e.g. LU0880133367
    private String symbol;      // e.g. Yahoo Finance Symbol ,"0P0000Y4WK.SI",

    private String shortname;   // e.g. "UBS (Lux) Equity Fund - China O",
    private String exchange;    // e.g. "SES"
    private String currency;

    // Asset Class
    private String quoteType;   // e.g. "MUTUALFUND",

    // display
    private String exchDisp;    // e.g. "Singapore"
    private String typeDisp;    // e.g. "Fund",
    private String longname;    // e.g. "UBS (Lux) Equity Fund - China Opportunity (USD) P SGD acc",

    // properties can be enriched based on above
    @JsonIgnore
    private AssetClassEnum assetClass;

    /**
     * Enrich based on property values after the instance created and set'ed
     */
    public void postEnrich(){
        if(isNotBlank(quoteType)) {
            this.assetClass = AssetClassEnum.value(quoteType);
        }
    }

    // auto generated
    //  -- getter only (e.g. set only via @postEnrich, so keep properties integrity)
    public AssetClassEnum getAssetClass() {
        return assetClass;
    }

    //  -- getter & setter
    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getQuoteType() {
        return quoteType;
    }

    public void setQuoteType(String quoteType) {
        this.quoteType = quoteType;
    }

    public String getExchDisp() {
        return exchDisp;
    }

    public void setExchDisp(String exchDisp) {
        this.exchDisp = exchDisp;
    }

    public String getTypeDisp() {
        return typeDisp;
    }

    public void setTypeDisp(String typeDisp) {
        this.typeDisp = typeDisp;
    }

    public String getLongname() {
        return longname;
    }

    public void setLongname(String longname) {
        this.longname = longname;
    }
}

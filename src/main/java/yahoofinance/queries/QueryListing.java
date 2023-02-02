package yahoofinance.queries;

import yahoofinance.YahooFinance;
import yahoofinance.fi.Listing;

import javax.validation.constraints.NotBlank;

/**
 * Get the Security Listing(market, symbol..) info from the given ISIN (or other Security Identify)
 * @author Perrier,TAO at 29/1/23
 */
public class QueryListing extends Query<Listing>{
    private static final String URL = YahooFinance.URL_SEARCH;

    private final String isin ;  //actual can be ISIN or Symbol

    public QueryListing(String isin) {
        super(assembleUrl(isin), "quotes", Listing.class);
        this.isin = isin;
    }

    @Override
    protected Listing emptyOrEnrich(Listing resultOrNull) {
        if(resultOrNull == null) {
            Listing empty = new Listing();
            empty.setIsin(isin);
            return empty ;
        }

        resultOrNull.setIsin(isin);
        return resultOrNull;
    }

    private static String assembleUrl(@NotBlank String isin) {
        //https://query1.finance.yahoo.com/v1/finance/search?q=LU0880133367&newsCount=0&listsCount=0
        return URL.replace("${isin}", isin);
    }
}

package yahoofinance.queries;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.Utils;
import yahoofinance.YahooFinance;
import yahoofinance.fi.fund.TopHoldings;

import java.util.HashMap;
import java.util.Map;

public class QueryQuoteSummary extends Query<TopHoldings> {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueryQuoteSummary.class);
    private static final String URL = YahooFinance.QUOTES_SUMMARY ;

    private final String symbol ;

    public QueryQuoteSummary(String symbol) {
        super(assembleUrl(symbol),"topHoldings", TopHoldings.class);
        this.symbol = symbol;
    }

    @Override
    protected TopHoldings emptyOrEnrich(TopHoldings th){
        if(th == null)
            return TopHoldings.emptyOne(symbol);

        th.setSymbol(symbol);
        return th;
    }

    private static String assembleUrl(String symbol) {
        //https://query2.finance.yahoo.com/v10/finance/quoteSummary/0P0000KXN4.SI?modules=topHoldings
        Map<String, String> params = new HashMap<>();
        params.put("modules", "topHoldings");
        return URL.replace("${symbol}", symbol) + "?" + Utils.getURLParameters(params);
    }
}

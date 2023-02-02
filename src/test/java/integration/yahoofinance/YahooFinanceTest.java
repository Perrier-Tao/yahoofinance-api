package yahoofinance;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import yahoofinance.fi.Listing;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.queries.Query;
import yahoofinance.queries.QueryListing;
import yahoofinance.queries.QueryQuoteSummary;
import yahoofinance.query2v8.HistQuotesQuery2V8Request;
import yahoofinance.fi.fund.TopHoldings;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class YahooFinanceTest {
    private static final String FUND_UBS_CHINA_OPP_USD = "0P0001JF2Y.F";
    private static final String FUND_UBS_CHINA_OPP_SGD = "0P0000Y4WK.SI";
    private static final String ISIN_FUND_UBS_CHINA_OPP_SGD = "LU0880133367";
    private static final String FUND_UBS_ASIA_COMSUM_SGD = "0P0000KXN4.SI";
    private static final String FUND_FSGG_ASIA_OPP_SGD = "0P00006OHX.SI";

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testQueryFundTopHoldings() throws IOException {
        Query<TopHoldings> testSubject = new QueryQuoteSummary(FUND_UBS_CHINA_OPP_SGD);
        TopHoldings holdings = testSubject.execute();

        assertNotNull("Unable to get Response", holdings);
        System.out.println(holdings.toJson());

        //assertEquals("symbol", FUND_UBS_ASIA_COMSUM_SGD, holdings.getSymbol());
        assertNotNull("cashPosition",holdings.getCashPosition());
        assertEquals("cashPosition is positive", 1, holdings.getCashPosition().getRaw().signum());
        assertTrue("portfolio not empty ", holdings.getHoldings()!=null && holdings.getHoldings().size()>0);
    }

    @Test
    public void testQueryListing() throws IOException {
        Query<Listing> testSubject = new QueryListing(ISIN_FUND_UBS_CHINA_OPP_SGD);
        Listing listing = testSubject.execute();

        assertNotNull("Unable to get Response", listing);
        System.out.println(listing.toJson());
        assertEquals("Symbol from ISIN", "0P0000Y4WK.SI", listing.getSymbol());

    }

    @Test
    public void testQueryFinChart(){
        HistQuotesQuery2V8Request finChart = new HistQuotesQuery2V8Request(FUND_UBS_CHINA_OPP_USD);
        try {
            List<HistoricalQuote> result = finChart.getResult();
            assertTrue("HistoricalQuote not empty", result != null && result.size() > 0);
            result.stream().forEach(System.out::println);
        } catch (IOException e) {
            fail("Unable to get HistoricalQuote of " + FUND_UBS_CHINA_OPP_USD);
        }
    }

    @Test
    public void testGetStock() {
        Stock stock = null;
        try {
            stock = YahooFinance.get(FUND_UBS_CHINA_OPP_USD, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BigDecimal price = stock.getQuote().getPrice();
        BigDecimal change = stock.getQuote().getChangeInPercent();
        BigDecimal peg = stock.getStats().getPeg();
        BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();

        stock.print();
    }
}
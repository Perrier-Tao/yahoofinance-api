package yahoofinance.queries;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.YahooFinance;
import yahoofinance.util.RedirectableRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Query to Yahoo Finance
 * @param <T> the expected result class
 */

public abstract class Query<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(Query.class);

    private static final int PROTOCOL_REDIRECT_LIMIT = 5;
    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final String url ;
    private final String rootName;
    private final Class<T> resultClazz;

    /**
     *
     * @param url
     *      Final assembled URL to Yahoo Finance
     * @param rootName
     *      Name of Root JSON node (from Yahoo Finance returned) your result Class maps to
     * @param resultClazz
     */
    protected Query(@NotBlank String url, @NotBlank String rootName, @NotNull Class<T> resultClazz) {
        this.url = url;
        this.rootName = rootName;
        this.resultClazz = resultClazz;
    }

    // All we need from you to make a query to Yahoo Finance API
    //-- ------------------------------------------------------------------------------------------
    /**
     * Give the chance to you to enrich the result, if input is null, give you chance to build a empty/default one
     */
    protected abstract T emptyOrEnrich(T resultOrNull);
    //-- [END]-------------------------------------------------------------------------------------

    /**
     * Parse Yahoo Finance response which is in JSON format to expected result Class
     */
    public T parseJson(JsonParser parser) {
        try {
            while (!parser.isClosed() && !rootName.equalsIgnoreCase(parser.getValueAsString())){
                parser.nextToken();
            }

            if(!parser.isClosed()) {
                JsonToken rootToken = parser.nextToken();

                // if the specified RootName is an Array in Yahoo Finance Response, we pick the 1st one from the Array
                if(JsonToken.START_ARRAY == rootToken) {
                    parser.nextToken();
                }

                T topHoldings = parser.readValueAs(resultClazz);
                return emptyOrEnrich(topHoldings);
            }
        } catch (IOException e) {
            LOGGER.error("Unable to parse JSON response of {} due to : {}", url, e.getMessage(), e);
        }

        return emptyOrEnrich(null);
    }

    /**
     * Send HTTP Request to Yahoo Finance
     */
    public T execute() throws IOException {
        LOGGER.info("Sending request: " + url);

        // Get JSON from Yahoo
        URL request = new URL(url);
        RedirectableRequest redirectableRequest = new RedirectableRequest(request, PROTOCOL_REDIRECT_LIMIT);
        redirectableRequest.setConnectTimeout(YahooFinance.CONNECTION_TIMEOUT);
        redirectableRequest.setReadTimeout(YahooFinance.CONNECTION_TIMEOUT);
        URLConnection connection = redirectableRequest.openConnection();

        // Process JSON response
        InputStreamReader is = new InputStreamReader(connection.getInputStream());
        JsonParser parser = OBJECT_MAPPER.getFactory().createParser(is);
        return parseJson(parser);
    }
}

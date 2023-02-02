package yahoofinance;

import org.junit.Assert;
import org.junit.Test;
import yahoofinance.fi.AssetClassEnum;

/**
 * Used by Developer verify JDK or Library usage and behaviors
 *
 * @author Perrier,TAO at 29/1/23
 */
public class DevLibUsage {

    @Test(expected = IllegalArgumentException.class)
    public void testEnum(){
        AssetClassEnum.valueOf("IF_NOT_EXST");
    }

    @Test
    public void testAssetClassEnum(){
        AssetClassEnum notExist = AssetClassEnum.value("IF_NOT_EXST");
        Assert.assertNull("null, no exception", notExist);
    }
}

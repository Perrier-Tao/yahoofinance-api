package yahoofinance.fi;

/**
 * @author Perrier,TAO at 29/1/23
 */
public enum AssetClassEnum {
    MUTUALFUND("Fund")
    ;

    String display;

    AssetClassEnum(String display) {
        this.display = display;
    }

    public static AssetClassEnum value(String name){
        try {
            return AssetClassEnum.valueOf(name);
        } catch (IllegalArgumentException e){
            return null;
        }
    }

}

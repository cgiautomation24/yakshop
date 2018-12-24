package utility;

public class Constants {

	static PropertiesConfigurator propertConfigurator = new PropertiesConfigurator();

	public static final String BASE_URL = propertConfigurator.getProperties().getProperty("baseURL");
	public static final String TestServer = "/testService";
	public static final String TestStockDay1 = "/stock/1";
	public static final String TestStockDay2 = "/stock/2";
	public static final String Herd = "/herd/1";
	public static final String ReloadHerd = "/reloadHerd";
	public static final String Order1 = "/order/1,%7B%22customer%22:%22fiuiu%22,%22order%22:%7B%22milk%22:10,%22skins%22:1%7D%7D";
	public static final String Order2 = "/order/2,%7B%22customer%22:%22fiuiu%22,%22order%22:%7B%22milk%22:20,%22skins%22:1%7D%7D";

	public static final String TestStockwithInvalidDay = "/stock/-2";
	public static final String TestServerUpwithInvalidPath = "/testServiceInvalid";

	public Constants() {
		// TODO Auto-generated constructor stub
	}
}

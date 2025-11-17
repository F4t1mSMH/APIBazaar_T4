package base_urls;

import org.testng.annotations.BeforeClass;
import utilities.ApiUtil;
import utilities.ApiUtilities;
import utilities.ConfigReader;

public class BazaarBaseUrl {


    public void loginAsCustomer() {
        ApiUtil.loginAndGetToken(
                ConfigReader.getCustomerEmail(),
                ConfigReader.getDefaultPassword()
        );
    }

    public void loginAsManager() {
        ApiUtil.loginAndGetToken(
                ConfigReader.getStoreManagerEmail(),
                ConfigReader.getDefaultPassword()
        );
    }

    public void loginAsAdmin() {
        ApiUtil.loginAndGetToken(
                ConfigReader.getAdminEmail(),
                ConfigReader.getDefaultPassword()
        );
    }
}
package base_urls;

import utilities.ApiUtil;
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

package co.proficiency.app.generic;

/**
 * Created by YCS01 on 26-05-2017.
 */

public interface IConstant {

    String API_BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/" ;

    String Data_API = "facts.json";

    // ConnectivityErrorCode
    int SUCCESS_START = 200;
    int SUCCESS_END = 300;
    int NETWORK_ERR = 0;
    int UNAUTHORIZED_ERR = 401;
    int SERVER_ERR_START = 500;
    int SERVER_ERR_END = 600;
    int CLIENT_ERR_START = 400;
    int CLIENT_ERR_END = 500;

    enum ResponseCode {
        SERVER_ERR(98),
        REQUEST_PARAM_NULL(99),
        SUCCESS(200),
        USER_ALERT_1(101),
        USER_ALERT_2(102),
        AUTH_TOKEN_EXPIRED(103);
        public int value;

        ResponseCode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}

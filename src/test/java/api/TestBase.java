package api;

import api.PojoClasses.AuthorizationResponse;
import api.PojoClasses.RegisterPlayerBody;
import api.enums.credentials.CredentialsEnum;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class TestBase {

    public static String accessToken;
    public static AuthorizationResponse authorizationResponse;
    public static RegisterPlayerBody registerPlayerResponse;

    @BeforeAll
    public static void beforeAll(){
        RestAssured.baseURI = CredentialsEnum.URL.getConstant();
    }

    /**
     * Send Post request with Basic Authorization
     * @param body
     * @return
     */
    public Response sendPostWithAuthToken(Object body){
        return given().log().all().auth().preemptive()
                .basic(CredentialsEnum.BASIC_AUTHORIZATION_VALUE.getConstant(), "")
                .accept(ContentType.ANY)
                .contentType("application/json")
                .body(body)
                .when().post(CredentialsEnum.PATH_OAUTH2_TOKEN.getConstant());
    }

    /**
     * Send Post request with Bearer Authorization
     * @param body
     * @return
     */
    public Response sendPostWithBearerToken(Object body){
        return given().log().all()
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/json")
                .body(body)
                .when().post(CredentialsEnum.PATH_PLAYERS.getConstant());
    }

    /**
     * Execute get method with Bearer Authorization, and get playerInfo
     * @param playerId
     * @return
     */
    public Response getUserProfile(String playerId) {
        return given().log().all()
                .header("Authorization","Bearer " + authorizationResponse.getAccessToken())
                .when().get("/v2/players/" + playerId);
    }

    /**
     * Generate random value
     * @param generate - generate value with special symbols
     * @return
     */
    public String generate(String generate){
        char[] val = "qwretyujfhns".toCharArray();
        StringBuilder stringBuilder = new StringBuilder(6);
        Random random = new Random();
        for (int i = 0; i<6;i++){
            char c = val[random.nextInt(val.length)];
            stringBuilder.append(c);
        }
        switch (generate){
            case "email":
                stringBuilder = stringBuilder.append("@mail.ru");
                break;
        }
        return stringBuilder.toString();
    }
}

package api;

import api.PojoClasses.AuthorizationBody;
import api.PojoClasses.AuthorizationPlayer;
import api.PojoClasses.AuthorizationResponse;
import api.PojoClasses.RegisterPlayerBody;
import api.enums.enumResponse.ResponseEnum;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApiTest extends TestBase {

    @Test
    @Order(1)
    public void getTokenTest(){
        AuthorizationBody authorizationBody = AuthorizationBody.builder()
                .grantType("client_credentials")
                .scope("guest:default")
                .build();

        Response getTokenResponse = sendPostWithAuthToken(authorizationBody);
        getTokenResponse.then().assertThat().statusCode(200);

        AuthorizationResponse responseToken = getTokenResponse.then().extract().as(AuthorizationResponse.class);

        assertAll("token", () -> assertNotNull(responseToken.getAccessToken()),
                () -> assertNotEquals("", responseToken.getAccessToken()),
                () -> assertTrue(responseToken.getAccessToken().startsWith("eyJ0")));

        TestBase.accessToken = String.valueOf(responseToken.getAccessToken());
    }

    @Test
    @Order(2)
    public void registeredPlayerTest() {
        RegisterPlayerBody registerPlayerBody = RegisterPlayerBody.builder()
                .username(generate("name"))
                .passwordChange("123123")
                .passwordRepeat("123123")
                .email(generate("email"))
                .name(generate("name"))
                .surname("surname")
                .currencyCode("RUB")
                .build();

        Response response = sendPostWithBearerToken(registerPlayerBody);

        System.out.println(response.then().extract().asString());
        response.then().assertThat().statusCode(201);
        TestBase.registerPlayerResponse = response.then().extract().as(RegisterPlayerBody.class);
        TestBase.registerPlayerResponse.setPasswordChange(registerPlayerBody.getPasswordChange());
    }

    @Test
    @Order(3)
    public void authorizationPlayerTest(){
        AuthorizationPlayer authorizationPlayer = AuthorizationPlayer.builder()
                .grantType("password")
                .username(registerPlayerResponse.getUsername())
                .password(registerPlayerResponse.getPasswordChange())
                .build();

        Response resp = sendPostWithAuthToken(authorizationPlayer);
        System.out.println(resp.then().extract().asString());

        resp.then().assertThat().statusCode(200);

        AuthorizationResponse authorizationResponse = resp.then().extract().as(AuthorizationResponse.class);

        assertAll("refreshToken",
                () -> assertNotNull(authorizationResponse.getRefreshToken()),
                () -> assertNotEquals("", authorizationResponse.getRefreshToken()),
                () -> assertNotNull(authorizationResponse.getAccessToken()),
                () -> assertNotEquals("",authorizationResponse.getAccessToken()));

        TestBase.authorizationResponse = authorizationResponse;
    }

    @Test
    @Order(4)
    public void getProfileTest(){
        Response response = getUserProfile(registerPlayerResponse.getId());

        RegisterPlayerBody rg = response.as(RegisterPlayerBody.class);

        System.out.println(response.then().extract().asString());
        response.then().assertThat().statusCode(200);

        assertAll("assertBody" ,
                () -> assertEquals(registerPlayerResponse.getId(),rg.getId()),
                () -> assertEquals(registerPlayerResponse.getUsername(),rg.getUsername()),
                () -> assertEquals(registerPlayerResponse.getEmail(),rg.getEmail()));
    }

    @Test
    @Order(5)
    public void getAnotherProfileTest(){
        String notExistPlayerId = "10224234";

        Response response = getUserProfile(notExistPlayerId);

        response.then().assertThat().statusCode(404);
        System.out.println(response.then().extract().asString());
        Assertions.assertEquals(ResponseEnum.BODY_FOR_GET_ANOTHER_PROFILE_TEST.getConstant(),
                response.then().extract().asString());
    }

}

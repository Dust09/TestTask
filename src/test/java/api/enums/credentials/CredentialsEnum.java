package api.enums.credentials;

import lombok.Getter;

public enum CredentialsEnum {
    BASIC_AUTHORIZATION_VALUE("front_2d6b0a8391742f5d789d7d915755e09e"),
    URL("http://test-api.d6.dev.devcaz.com/"),
    V2("/v2"),
    OAUTH2("/oauth2"),
    PLAYERS("/players"),
    TOKEN("/token"),
    PATH_PLAYERS(V2.getConstant() + PLAYERS.getConstant()),
    PATH_OAUTH2_TOKEN(V2.getConstant() + OAUTH2.getConstant() + TOKEN.getConstant());

    @Getter
    private String constant;

    CredentialsEnum(String constant){
        this.constant = constant;
    }
}

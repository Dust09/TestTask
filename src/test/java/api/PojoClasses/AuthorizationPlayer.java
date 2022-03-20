package api.PojoClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorizationPlayer {

    @JsonProperty(value = "grant_type")
    private String grantType;

    @JsonProperty(value = "username")
    private String username;

    @JsonProperty(value = "password")
    private String password;
}

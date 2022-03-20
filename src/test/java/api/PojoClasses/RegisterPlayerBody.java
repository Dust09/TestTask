package api.PojoClasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import lombok.Builder;
import lombok.Data;

import java.nio.charset.StandardCharsets;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterPlayerBody {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "username")
    private String username;

    @JsonProperty(value = "password_change")
    private String passwordChange;

    @JsonProperty(value = "password_repeat")
    private String passwordRepeat;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "surname")
    private String surname;

    @JsonProperty(value = "currency_code")
    private String currencyCode;

    @Builder(toBuilder = true)
    public RegisterPlayerBody(
            String username,
            String passwordChange,
            String passwordRepeat,
            String email,
            String name,
            String surname,
            String currencyCode
    ) {
        this.username = username;
        this.passwordChange = Base64.encode(passwordChange.getBytes(StandardCharsets.UTF_8));
        this.passwordRepeat = Base64.encode(passwordRepeat.getBytes(StandardCharsets.UTF_8));
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.currencyCode = currencyCode;
    }

    public RegisterPlayerBody(){}
}

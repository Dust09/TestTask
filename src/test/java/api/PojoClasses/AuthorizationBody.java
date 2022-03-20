package api.PojoClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthorizationBody {

    @JsonProperty(value = "grant_type")
    private String grantType;
    @JsonProperty(value = "scope")
    private String scope;
}

package api.enums.enumResponse;

import lombok.Getter;

public enum ResponseEnum {
    BODY_FOR_GET_ANOTHER_PROFILE_TEST("{\"name\":\"Not Found\",\"message\":\"Object not found: 10224234\",\"code\":0,\"status\":404}");

    @Getter
    String constant;

    ResponseEnum(String constant){
        this.constant = constant;
    }
}

package example.mvcdemo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {
    private int statusCode;
    private String message;
}

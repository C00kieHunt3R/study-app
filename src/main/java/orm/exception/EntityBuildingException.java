package orm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Cannot build entity")
public class EntityBuildingException extends Exception {
    public EntityBuildingException(String msg) {
        super(msg);
    }

}

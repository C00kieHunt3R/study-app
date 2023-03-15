package orm.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No such entity id")
public class EntityIdDoesNotExistException extends Exception{
    public EntityIdDoesNotExistException(String msg) {
        super(msg);
    }
}

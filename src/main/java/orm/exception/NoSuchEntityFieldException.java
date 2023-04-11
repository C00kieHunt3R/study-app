package orm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoSuchEntityFieldException extends RuntimeException{
    public NoSuchEntityFieldException(String entityName, String fieldName, String fieldValue) {
        super(String.format("Сущность [%s] с полем [%s]=[%s] не существует", entityName, fieldName, fieldValue));
    }
}

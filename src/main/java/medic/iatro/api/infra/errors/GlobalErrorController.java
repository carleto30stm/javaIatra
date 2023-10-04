package medic.iatro.api.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestControllerAdvice
public class GlobalErrorController {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity error400(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors().stream().map(DataValidationErrors:: new).toList();
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public  ResponseEntity error409(SQLIntegrityConstraintViolationException e){

        var errors = e.getMessage();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                List.of(new ConflictErrorDTO(errors))
        );
    }

    private record  DataValidationErrors(String msg, String error){
        public  DataValidationErrors(FieldError error){
            this(error.getDefaultMessage(),error.getField());
        }
    }
    public record ConflictErrorDTO( String message) {
        // No es necesario definir getters y setters, ya que los registros son inmutables.
    }

}

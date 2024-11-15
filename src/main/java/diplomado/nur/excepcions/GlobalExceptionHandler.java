
package diplomado.nur.excepcions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import diplomado.nur.models.dto.Errores;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
  
  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Errores notFoundException(NotFoundException exception) {
    return new Errores(exception.getMessage( ));
  }
  
  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Errores internalServerErrorException(BadRequestException exception) {
    return new Errores(exception.getMessage( ));
  }
  
}
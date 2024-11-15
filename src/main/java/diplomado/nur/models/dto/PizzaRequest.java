
package diplomado.nur.models.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class PizzaRequest {
  
  private String nombre;
  private List<String> ingredientes;
  private BigDecimal precio;
}
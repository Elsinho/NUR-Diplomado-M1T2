
package diplomado.nur.models.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class PedidoResponse {
  
  private List<PizzaResponse> pizzas;
  private String fecha;
  private BigDecimal total;
}

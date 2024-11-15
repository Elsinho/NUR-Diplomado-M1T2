
package diplomado.nur.models.dto;

import java.util.List;

import lombok.Data;

@Data
public class PedidoRequest {
  
  private List<Long> pizzaIds;
}
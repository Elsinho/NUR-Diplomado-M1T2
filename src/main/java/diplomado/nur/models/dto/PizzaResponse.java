
package diplomado.nur.models.dto;

import java.math.BigDecimal;
import java.util.List;

import diplomado.nur.models.entity.Pizza;
import lombok.Data;

@Data
public class PizzaResponse {
  
  private String nombre;
  private List<String> ingredientes;
  private BigDecimal precio;
  
  public PizzaResponse(Pizza pizza) {
    this.nombre = pizza.getNombre( );
    this.precio = pizza.getPrecio( );
    this.ingredientes = pizza.getIngredientes( );
  }
}

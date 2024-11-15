
package diplomado.nur.models.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import diplomado.nur.models.entity.Pizza;

public interface Promocion {
  
  BigDecimal aplicarPromocion(List<Pizza> pizzas,
                              LocalDate fechaPedido);
  String obtenerDescripcion( );
}

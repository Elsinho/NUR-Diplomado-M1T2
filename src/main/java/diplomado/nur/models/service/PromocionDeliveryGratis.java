
package diplomado.nur.models.service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import diplomado.nur.models.entity.Pizza;

public class PromocionDeliveryGratis implements
                                     Promocion {
  
  private static final BigDecimal COSTO_BASE_DELIVERY = new BigDecimal(5.0);
  
  @Override
  public BigDecimal aplicarPromocion(List<Pizza> pizzas,
                                     LocalDate fechaPedido) {
    return (fechaPedido.getDayOfWeek( ) == DayOfWeek.THURSDAY) ? BigDecimal.ZERO : COSTO_BASE_DELIVERY;
  }
  
  @Override
  public String obtenerDescripcion( ) { return "Delivery gratis"; }
}
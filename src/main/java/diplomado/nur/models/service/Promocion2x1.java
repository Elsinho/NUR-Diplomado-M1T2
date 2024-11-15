
package diplomado.nur.models.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import diplomado.nur.models.entity.Pizza;

public class Promocion2x1 implements
                          Promocion {
  
  @Override
  public BigDecimal aplicarPromocion(List<Pizza> pizzas,
                                     LocalDate fechaPedido) {
    DayOfWeek diaSemana = fechaPedido.getDayOfWeek( );
    BigDecimal total = pizzas.stream( )
                             .map(Pizza::getPrecio)
                             .reduce(BigDecimal.ZERO, BigDecimal::add);
    
    if (diaSemana == DayOfWeek.TUESDAY || diaSemana == DayOfWeek.WEDNESDAY) {
      return total.divide(BigDecimal.valueOf(2), RoundingMode.HALF_UP);
    }
    return total;
  }
  
  @Override
  public String obtenerDescripcion( ) { return "2x1 en pizzas"; }
}

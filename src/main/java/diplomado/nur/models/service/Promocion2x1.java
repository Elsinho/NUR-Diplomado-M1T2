
package diplomado.nur.models.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import diplomado.nur.excepcions.BadRequestException;
import diplomado.nur.models.entity.Pizza;

public class Promocion2x1 implements
                          Promocion {
  
  @Override
  public BigDecimal aplicarPromocion(List<Pizza> pizzas,
                                     LocalDate fechaPedido) {
    DayOfWeek diaSemana = fechaPedido.getDayOfWeek( );
    if (esPromo(diaSemana)) {
      validarCantidadPizzas(pizzas);
      validarPreciosIguales(pizzas);
      return calcularTotalPromo(pizzas);
    }
    return calcularTotalSinPromo(pizzas);
  }
  
  private boolean esPromo(DayOfWeek diaSemana) {
    return diaSemana == DayOfWeek.TUESDAY || diaSemana == DayOfWeek.WEDNESDAY;
  }
  
  private void validarCantidadPizzas(List<Pizza> pizzas) {
    if (pizzas.size( ) % 2 != 0) {
      throw new BadRequestException("Debe elegir una pizza más para aplicar la promoción 2x1.");
    }
  }
  
  private void validarPreciosIguales(List<Pizza> pizzas) {
    BigDecimal precio = pizzas.get(0)
                              .getPrecio( );
    boolean preciosIguales = pizzas.stream( )
                                   .allMatch(pizza -> pizza.getPrecio( )
                                                           .compareTo(precio) == 0);
    if (!preciosIguales) {
      throw new BadRequestException(
        "Todas las pizzas deben tener el mismo precio para aplicar la promoción 2x1.");
    }
  }
  
  private BigDecimal calcularTotalPromo(List<Pizza> pizzas) {
    BigDecimal total = pizzas.stream( )
                             .map(Pizza::getPrecio)
                             .reduce(BigDecimal.ZERO, BigDecimal::add);
    return total.divide(BigDecimal.valueOf(2), RoundingMode.HALF_UP);
  }
  
  private BigDecimal calcularTotalSinPromo(List<Pizza> pizzas) {
    return pizzas.stream( )
                 .map(Pizza::getPrecio)
                 .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
  
  @Override
  public String obtenerDescripcion( ) { return "2x1 en pizzas"; }
}

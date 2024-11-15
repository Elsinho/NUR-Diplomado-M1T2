
package diplomado.nur.models.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import diplomado.nur.models.entity.Pizza;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PromocionService {
  
  private List<Promocion> promociones;
  
  public PromocionService(List<Promocion> promociones) { this.promociones = promociones; }
  
  public BigDecimal calcularTotalConPromociones(List<Pizza> pizzas,
                                                LocalDate fechaPedido) {
    BigDecimal totalPizzas = pizzas.stream( )
                                   .map(Pizza::getPrecio)
                                   .reduce(BigDecimal.ZERO, BigDecimal::add);
    BigDecimal descuento = BigDecimal.ZERO;
    BigDecimal costoDelivery = BigDecimal.ZERO;
    log.info("Total pizzas: " + totalPizzas);
    log.info("Número de promociones: " + (promociones != null ? promociones.size( ) : "null"));
    for(Promocion promocion: promociones) {
      BigDecimal resultadoPromocion = promocion.aplicarPromocion(pizzas, fechaPedido);
      log.info("Resultado promoción: " + resultadoPromocion);
      if (promocion instanceof PromocionDeliveryGratis) {
        costoDelivery = costoDelivery.add(resultadoPromocion);
      }else {
        descuento = descuento.add(resultadoPromocion);
      }
      log.info("Descuento acumulado: " + descuento + ", Costo delivery acumulado: " + costoDelivery);
    }
    return totalPizzas.subtract(descuento)
                      .add(costoDelivery)
                      .setScale(2, RoundingMode.HALF_UP);
  }
  
  public List<String> obtenerDescripcionesPromocionesAplicadas(List<Pizza> pizzas,
                                                               LocalDate fechaPedido) {
    return promociones.stream( )
                      .filter(promocion -> promocion.aplicarPromocion(pizzas, fechaPedido)
                                                    .compareTo(BigDecimal.ZERO) > 0)
                      .map(Promocion::obtenerDescripcion)
                      .collect(Collectors.toList( ));
  }
}
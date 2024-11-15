
package diplomado.nur.models.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

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
    BigDecimal totalMonto = BigDecimal.ZERO;
    BigDecimal costoDelivery = BigDecimal.ZERO;
    for(Promocion promocion: promociones) {
      BigDecimal resultadoPromocion = promocion.aplicarPromocion(pizzas, fechaPedido);
      if (promocion instanceof PromocionDeliveryGratis) {
        costoDelivery = costoDelivery.add(resultadoPromocion);
      }else {
        totalMonto = totalMonto.add(resultadoPromocion);
      }
    }
    return totalMonto.add(costoDelivery)
                     .setScale(2, RoundingMode.HALF_UP);
  }
}
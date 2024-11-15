
package diplomado.nur.models.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diplomado.nur.models.dao.PedidoRepository;
import diplomado.nur.models.dto.PedidoResponse;
import diplomado.nur.models.dto.PizzaResponse;
import diplomado.nur.models.entity.Pedido;
import diplomado.nur.models.entity.Pizza;

@Service
public class PedidoService {
  
  @Autowired
  private PedidoRepository pedidoRepository;
  
  @Autowired
  private PromocionService promocionService;
  
  public PedidoResponse crearPedido(List<Pizza> pizzas) {
    // LocalDate fecha = LocalDate.now( );
    LocalDate fecha = LocalDate.of(2024, 11, 12);
    BigDecimal total = promocionService.calcularTotalConPromociones(pizzas, fecha);
    List<String> promocionesAplicadas = promocionService.obtenerDescripcionesPromocionesAplicadas(pizzas,
      fecha);
    Pedido pedido = new Pedido( );
    pedido.setPizzas(pizzas);
    pedido.setFecha(fecha);
    pedido.setTotal(total);
    pedido.setPromocionAplicada(promocionesAplicadas);
    pedidoRepository.save(pedido);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    List<PizzaResponse> lista = new ArrayList<>( );
    for(Pizza pizza: pizzas) {
      lista.add(new PizzaResponse(pizza));
    }
    PedidoResponse res = new PedidoResponse( );
    res.setFecha(fecha.format(formatter));
    res.setPizzas(lista);
    res.setTotal(total);
    return res;
  }
}
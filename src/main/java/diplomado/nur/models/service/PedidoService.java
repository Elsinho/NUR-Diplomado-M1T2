
package diplomado.nur.models.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diplomado.nur.excepcions.BadRequestException;
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
  private PizzaService pizzaService;
  @Autowired
  private PromocionService promocionService;
  
  public PedidoResponse crearPedido(List<Pizza> pizzas) {
    validarPizzas(pizzas);
    List<PizzaResponse> listaPizzas = obtenerPizzasResponses(pizzas);
    LocalDate fecha = LocalDate.now( );
    BigDecimal total = promocionService.calcularTotalConPromociones(pizzas, fecha);
    Pedido pedido = guardarPedido(pizzas, fecha, total);
    return pedidoResponse(pedido, listaPizzas);
  }
  
  private void validarPizzas(List<Pizza> pizzas) {
    if (pizzas == null || pizzas.isEmpty( )) {
      throw new BadRequestException("El pedido debe contener al menos una pizza.");
    }
  }
  
  private List<PizzaResponse> obtenerPizzasResponses(List<Pizza> pizzas) {
    List<PizzaResponse> responses = new ArrayList<>( );
    for(Pizza pizza: pizzas) {
      pizzaService.obtenerPizzaPorId(pizza.getId( ));
      responses.add(new PizzaResponse(pizza));
    }
    return responses;
  }
  
  private Pedido guardarPedido(List<Pizza> pizzas,
                               LocalDate fecha,
                               BigDecimal total) {
    Pedido pedido = new Pedido( );
    pedido.setPizzas(pizzas);
    pedido.setFecha(fecha);
    pedido.setTotal(total);
    return pedidoRepository.save(pedido);
  }
  
  private PedidoResponse pedidoResponse(Pedido pedido,
                                        List<PizzaResponse> listaPizzas) {
    PedidoResponse response = new PedidoResponse( );
    response.setFecha(pedido.getFecha( )
                            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    response.setPizzas(listaPizzas);
    response.setTotal(pedido.getTotal( ));
    return response;
  }
}
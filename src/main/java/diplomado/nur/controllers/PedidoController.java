
package diplomado.nur.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import diplomado.nur.models.dto.PedidoRequest;
import diplomado.nur.models.dto.PedidoResponse;
import diplomado.nur.models.entity.Pizza;
import diplomado.nur.models.service.PedidoService;
import diplomado.nur.models.service.PizzaService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
  
  @Autowired
  private PedidoService pedidoService;
  @Autowired
  private PizzaService pizzaService;
  
  @PostMapping("/crear")
  public ResponseEntity<PedidoResponse> crearPedido(@RequestBody PedidoRequest req) {
    List<Pizza> pizzas = req.getPizzaIds( )
                            .stream( )
                            .map(id -> pizzaService.obtenerPizzaPorId(id))
                            .collect(Collectors.toList( ));
    return ResponseEntity.status(HttpStatus.CREATED)
                         .body(pedidoService.crearPedido(pizzas));
  }
}
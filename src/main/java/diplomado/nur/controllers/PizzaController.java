
package diplomado.nur.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import diplomado.nur.models.dto.PizzaRequest;
import diplomado.nur.models.dto.PizzaResponse;
import diplomado.nur.models.entity.Pizza;
import diplomado.nur.models.service.PizzaService;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
  
  @Autowired
  private PizzaService pizzaServ;
  
  @PostMapping("/personalizada")
  public ResponseEntity<PizzaResponse> crearPizzaPersonalizada(@RequestBody PizzaRequest req) {
    Pizza pizzaCreada = pizzaServ.crearPizza(req, true);
    return ResponseEntity.status(HttpStatus.CREATED)
                         .body(new PizzaResponse(pizzaCreada));
  }
  
  @PostMapping("/predefinidas")
  public ResponseEntity<PizzaResponse> crearPizzaPredefinida(@RequestBody PizzaRequest req) {
    Pizza pizzaCreada = pizzaServ.crearPizza(req, false);
    return ResponseEntity.status(HttpStatus.CREATED)
                         .body(new PizzaResponse(pizzaCreada));
  }
  
  @GetMapping("/predefinidas")
  public ResponseEntity<List<PizzaResponse>> obtenerPizzasPredefinidas( ) {
    List<PizzaResponse> lista = new ArrayList<>( );
    List<Pizza> pizzasPredefinidas = pizzaServ.obtenerPizzasPredefinidas( );
    for(Pizza pizza: pizzasPredefinidas) {
      lista.add(new PizzaResponse(pizza));
    }
    return ResponseEntity.ok(lista);
  }
}
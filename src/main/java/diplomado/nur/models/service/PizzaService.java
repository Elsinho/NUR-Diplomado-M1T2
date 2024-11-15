
package diplomado.nur.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diplomado.nur.models.dao.PizzaRepository;
import diplomado.nur.models.dto.PizzaRequest;
import diplomado.nur.models.entity.Pizza;

@Service
public class PizzaService {
  
  @Autowired
  private PizzaRepository pizzaRepository;
  
  public List<Pizza> obtenerPizzasPredefinidas( ) { return pizzaRepository.findByPersonalizadaFalse( ); }
  
  public Pizza crearPizza(PizzaRequest req,
                          boolean personalizada) {
    Pizza pizza = new Pizza( );
    pizza.setNombre(req.getNombre( ));
    pizza.setPrecio(req.getPrecio( ));
    pizza.setIngredientes(req.getIngredientes( ));
    pizza.setPersonalizada(personalizada);
    return pizzaRepository.save(pizza);
  }
  
  public Pizza obtenerPizzaPorId(Long id) { return pizzaRepository.findById(id)
                                                                  .get( ); }
}

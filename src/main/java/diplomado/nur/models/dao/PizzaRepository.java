
package diplomado.nur.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import diplomado.nur.models.entity.Pizza;

@Repository
public interface PizzaRepository extends
                                 JpaRepository<Pizza, Long> {
  
  List<Pizza> findByPersonalizadaFalse( );
}
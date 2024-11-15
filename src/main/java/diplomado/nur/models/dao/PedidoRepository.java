
package diplomado.nur.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import diplomado.nur.models.entity.Pedido;

@Repository
public interface PedidoRepository extends
                                  JpaRepository<Pedido, Long> {}

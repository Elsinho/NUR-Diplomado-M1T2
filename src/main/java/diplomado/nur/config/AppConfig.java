
package diplomado.nur.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import diplomado.nur.models.service.Promocion;
import diplomado.nur.models.service.Promocion2x1;
import diplomado.nur.models.service.PromocionDeliveryGratis;

@Configuration
public class AppConfig {
  
  @Bean
  public List<Promocion> promociones( ) {
    List<Promocion> promociones = new ArrayList<>( );
    promociones.add(new Promocion2x1( ));
    promociones.add(new PromocionDeliveryGratis( ));
    return promociones;
  }
}
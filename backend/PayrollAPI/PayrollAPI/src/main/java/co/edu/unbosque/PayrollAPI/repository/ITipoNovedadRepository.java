package co.edu.unbosque.PayrollAPI.repository;

import co.edu.unbosque.PayrollAPI.entity.TipoNovedad;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITipoNovedadRepository extends CrudRepository<TipoNovedad, Integer> {

    @Query(
            value = "SELECT * FROM vw_tipo_novedad_activo",
            nativeQuery = true
    )
    List<TipoNovedad> vwTipoNovedadActivo();


}

package co.edu.unbosque.PayrollAPI.repository;

import co.edu.unbosque.PayrollAPI.model.dto.regular.CesantiaPagadaDTO;
import co.edu.unbosque.PayrollAPI.model.dto.regular.InteresCesantiaDTO;
import co.edu.unbosque.PayrollAPI.model.dto.regular.MensajeDTO;
import co.edu.unbosque.PayrollAPI.model.dto.regular.PrimaDTO;
import co.edu.unbosque.PayrollAPI.model.entity.Nomina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPrestacionesRepository extends JpaRepository<Nomina, Integer> {

    @Query(value = "EXEC sp_generar_prima_servicios :pn_id_periodo", nativeQuery = true)
    MensajeDTO spGenerarPrimaServicios(@Param("pn_id_periodo") Integer idPeriodo);

    @Query(value = "EXEC sp_generar_intereses_cesantias :pn_id_periodo", nativeQuery = true)
    MensajeDTO spGenerarInteresesCesantias(@Param("pn_id_periodo") Integer idPeriodo);

    @Query(value = "EXEC sp_registrar_cesantias_pagadas :pn_id_periodo", nativeQuery = true)
    MensajeDTO spRegistrarCesantiasPagadas(@Param("pn_id_periodo") Integer idPeriodo);

    @Query(value = "SELECT * FROM vw_primas_generadas WHERE id_periodo = :idPeriodo", nativeQuery = true)
    List<PrimaDTO> vwPrimasPorPeriodo(@Param("idPeriodo") Integer idPeriodo);

    @Query(value = "SELECT * FROM vw_intereses_cesantias WHERE id_periodo = :idPeriodo", nativeQuery = true)
    List<InteresCesantiaDTO> vwInteresesCesantiasPorPeriodo(@Param("idPeriodo") Integer idPeriodo);

    @Query(value = "SELECT * FROM vw_cesantias_pagadas WHERE id_periodo = :idPeriodo", nativeQuery = true)
    List<CesantiaPagadaDTO> vwCesantiasPagadasPorPeriodo(@Param("idPeriodo") Integer idPeriodo);


}

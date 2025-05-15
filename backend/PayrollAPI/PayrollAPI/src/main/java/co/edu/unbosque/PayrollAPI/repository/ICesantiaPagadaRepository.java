package co.edu.unbosque.PayrollAPI.repository;

import co.edu.unbosque.PayrollAPI.model.entity.CesantiaPagada;
import co.edu.unbosque.PayrollAPI.model.entity.Mensaje;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICesantiaPagadaRepository extends CrudRepository<CesantiaPagada, Integer> {

    @Query(
            value = "EXEC sp_registrar_cesantias_pagadas :pn_id_periodo",
            nativeQuery = true
    )
    Mensaje spRegistrarCesantiasPagadas(
            @Param("pn_id_periodo ") Integer pnIdPeriodo
    );

}

# Sistema de Gestión de Nómina y Prestaciones Sociales

**Autores:**  
- Miguel Ángel Alvarado Mendoza  
- David Santiago Camacho Rosales  
- Melanny Zuley Jutinico Corredor

---

## Descripción del Proyecto

Este sistema permite automatizar el proceso de liquidación de nómina para una empresa en Colombia, en cumplimiento con la normativa laboral vigente. Incluye funcionalidades para calcular salarios, deducciones obligatorias, aportes patronales y prestaciones sociales. Además, soporta el manejo de novedades laborales (incapacidades, licencias, etc.) y generación de reportes para auditoría y seguimiento.

---

## Entidades y Propósito en el Modelo

- `empleado`: almacena los datos de cada trabajador de la organización, incluyendo salario, cargo, afiliaciones y contrato.  
- `tipo_contrato`: clasifica el tipo de vínculo laboral (fijo, indefinido, aprendiz, etc.).  
- `nivel_riesgo_arl`: contiene los niveles de riesgo asignados por la ARL para el cálculo de aportes.  
- `periodo_nomina`: define los períodos de liquidación (mensual, quincenal) y su estado actual.  
- `concepto_nomina`: catálogo de conceptos devengados o deducidos (horas extra, salud, pensión, etc.).  
- `tipo_concepto`: distingue entre conceptos de tipo "DEVENGADO" o "DEDUCCIÓN".  
- `nomina`: tabla principal de la liquidación de cada empleado por período.  
- `detalle_nomina`: desglose de cada concepto aplicado a un empleado en su nómina.  
- `novedad`: registra eventos que afectan la nómina (incapacidades, licencias, ausencias).  
- `tipo_novedad`: catálogo de las novedades existentes en el sistema.  
- `tipo_aporte_patronal`: define los aportes que realiza el empleador (SENA, ICBF, etc.).  
- `aporte_patronal`: registros generados automáticamente para cada aporte por nómina.  
- `concepto_provision`: catálogo de conceptos que deben provisionarse (cesantías, primas, vacaciones).  
- `provision_prestaciones`: registro contable de las provisiones sociales por empleado.  
- `parametro_nomina`: valores numéricos o de texto que configuran el comportamiento del sistema.  
- `cesantias_pagadas`: almacena el histórico de pagos reales de cesantías.  
- `log_intento_modificacion_nomina`: auditoría de intentos de cambio en la nómina.  
- `log_novedad`: auditoría detallada sobre modificaciones o eliminaciones de novedades.  
- `log_estado_nomina`: registra cada cambio de estado de la nómina (ej. de GENERADA a CERRADA).



## Estructura del Proyecto y Archivos

### `ddl/` – Creación de Tablas

- `01_create_tables.sql`: Crea todas las tablas del modelo relacional normalizado. Incluye relaciones, claves primarias y foráneas.

---

### `dml/` – Carga de Datos Iniciales

- `insert_aux_tables.sql`: Inserta datos en tablas auxiliares como tipo de contrato, niveles de riesgo ARL, tipo de conceptos, EPS, AFP, etc.

---

### `functions/` – Funciones SQL

#### `functions/nomina/`  
- `fn_existe_nomina_por_periodo.sql`: Verifica si ya existe una nómina generada para un período.  
- `fn_obtener_nomina_empleado_periodo.sql`: Consulta la nómina de un empleado en un período específico.

#### `functions/novedades/`  
- `fn_dias_trabajados.sql`: Calcula los días efectivos trabajados considerando novedades.  
- `fn_empleado_tiene_novedades.sql`: Verifica si un empleado tiene novedades registradas en un período.

#### `functions/salario/`  
- `fn_salario_mensual_empleado.sql`: Calcula el salario mensual de un empleado, considerando condiciones contractuales.

---

### `procedures/` – Procedimientos Almacenados

#### `procedures/aportes_patronales/`  
- `sp_generar_aportes_patronales.sql`: Calcula los aportes que realiza el empleador (ARL, SENA, ICBF, etc.).

#### `procedures/deducciones/`  
- `sp_generar_deducciones_automaticas.sql`: Aplica las deducciones legales como salud, pensión y fondo de solidaridad.

#### `procedures/detalle_nomina/`  
- `sp_agregar_detalle_nomina.sql`: Agrega un concepto específico a la nómina de un empleado.  
- `sp_agregar_horas_extra.sql`: Agrega horas extra trabajadas.  
- `sp_listar_detalle_nomina_empleado.sql`: Lista los detalles de nómina de un empleado.

#### `procedures/empleados/`  
- `sp_buscar_empleado_por_id.sql`: Consulta datos de un empleado por su ID.

#### `procedures/nomina/`  
- `sp_buscar_periodo_por_id.sql`: Consulta un período por su ID.  
- `sp_cambiar_estado_periodo.sql`: Cambia el estado del período de nómina.  
- `sp_crear_nomina.sql`: Crea registros de nómina iniciales para los empleados.  
- `sp_crear_periodo.sql`: Registra un nuevo período de nómina.  
- `sp_generar_nomina_masiva.sql`: Genera toda la nómina para todos los empleados de un período.  
- `sp_liquidar_nomina.sql`: Calcula y liquida la nómina de un empleado.  
- `sp_liquidar_periodo.sql`: Liquida la nómina completa de un período.

#### `procedures/novedades/`  
- `sp_agregar_novedad.sql`: Inserta una novedad laboral (licencia, incapacidad, etc.).  
- `sp_aplicar_novedades.sql`: Aplica el impacto de las novedades a la nómina.  
- `sp_eliminar_novedad.sql`: Elimina una novedad.  
- `sp_listar_novedades_periodo.sql`: Muestra todas las novedades en un período.

#### `procedures/prestaciones/`  
- `sp_generar_intereses_cesantias.sql`: Calcula intereses sobre cesantías.  
- `sp_generar_prima_servicios.sql`: Calcula la prima de servicios por período.  
- `sp_registrar_cesantias_pagadas.sql`: Registra el pago de cesantías.

#### `procedures/provisiones/`  
- `sp_generar_provisiones.sql`: Registra provisiones contables por empleado.

---

### `triggers/` – Disparadores Automáticos

#### `triggers/detalle_nomina/`  
- `tbd_detalle_nomina_bloquear_eliminacion.sql`: Previene la eliminación si el período está cerrado.  
- `tbi_detalle_nomina_validar_estado.sql`: Valida el estado de la nómina antes de insertar conceptos.

#### `triggers/nomina/`  
- `tau_log_estado_nomina.sql`: Registra automáticamente los cambios de estado en la nómina (GENERADA, CERRADA, etc.).

#### `triggers/novedad/`  
- `tad_log_eliminacion_novedad.sql`: Crea un registro en el log al eliminar una novedad.  
- `trg_validar_eliminacion_novedad.sql`: Previene eliminación de novedades si ya fueron aplicadas.

---

### `views/` – Vistas de Consulta y Reportes

#### `views/empleados/`  
- `vw_empleado_info .sql`: Muestra la información completa del empleado para su visualización en el sistema.

#### `views/nomina/`  
- `vw_detalle_nomina_horas_extra.sql`: Visualiza horas extra liquidadas.  
- `vw_detalle_nomina_normal.sql`: Visualiza conceptos normales de devengado y deducción.  
- `vw_nomina_resumen.sql`: Muestra totales por empleado.  
- `vw_periodos_nomina_cerrados.sql`: Lista los períodos de nómina que han sido cerrados.  
- `vw_periodo_nomina_activo.sql`: Muestra el período activo actual.

#### `views/novedad/`  
- `vw_tipo_novedad_activo.sql`: Lista tipos de novedades activas en el sistema.

#### `views/prestaciones/`  
- `vw_cesantias_pagadas.sql`: Reporte de cesantías pagadas.  
- `vw_intereses_cesantias.sql`: Muestra los intereses calculados.  
- `vw_primas_generadas.sql`: Detalla las primas generadas por empleado.

#### `views/selectores/`  
- `vw_conceptos_devengados_deduccion.sql`: Lista conceptos de tipo devengado o deducción.  
- `vw_conceptos_horas_extra.sql`: Lista conceptos relacionados con horas extra.

---

## Recomendaciones Finales

- Ejecutar los scripts en orden: primero DDL, luego DML, funciones, procedimientos, triggers y vistas.
- Utilizar SQL Server Management Studio (SSMS) para ejecutar los scripts.
- Verificar que la base activa sea `GreedyGang`.
- Las vistas pueden ser utilizadas como fuente para reportes y dashboards.
- Los triggers pueden ser temporalmente desactivados si se requieren cargas masivas.

---

## Notas Finales

- Modelo relacional completamente normalizado en 3FN.
- Modular, escalable y preparado para integración con aplicaciones web.
- Cumple con la normativa laboral colombiana.
- Listo para uso académico o profesional.

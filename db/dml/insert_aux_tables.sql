INSERT INTO tipo_contrato (nombre) VALUES
('Indefinido'),
('Fijo'),
('Obra o labor'),
('Prestación de servicios');

INSERT INTO nivel_riesgo_arl (id_nivel, nombre, porcentaje) VALUES
(1, 'Muy Bajo', 0.522),
(2, 'Bajo', 1.044),
(3, 'Medio', 2.436),
(4, 'Alto', 4.350),
(5, 'Muy Alto', 6.960);

INSERT INTO tipo_concepto (nombre) VALUES
('DEVENGADO'),
('DEDUCCION');

INSERT INTO concepto_nomina (nombre, id_tipo_concepto, registro_manual, porcentaje, descripcion, activo) VALUES
('Sueldo Base', 1, 0, NULL, 'Salario básico mensual', 1),
('Auxilio de Transporte', 1, 0, NULL, 'Subsidio legal de transporte', 1),
('Horas Extra Diurna', 1, 1, 125.00, 'Horas extra trabajadas en el día', 1),
('Horas Extra Nocturna', 1, 1, 175.00, 'Horas extra trabajadas en la noche', 1),
('Recargo Nocturno', 1, 1, 135.00, 'Recargo por labor nocturna', 1),
('Horas Extra Dominical', 1, 1, 200.00, 'Horas extra trabajadas en domingo', 1),
('Salud', 2, 0, 4.00, 'Descuento salud empleado', 1),
('Pensión', 2, 0, 4.00, 'Descuento pensión empleado', 1),
('Fondo de Solidaridad', 2, 0, 1.00, 'Descuento fondo solidaridad pensional', 1),
('Embargo Judicial', 2, 1, NULL, 'Descuento por embargos legales', 1),
('Cooperativa', 2, 1, NULL, 'Descuento cooperativas', 1),
('Retefuente', 2, 1, NULL, 'Retención en la fuente', 1),
('Bono / Comision', 1, 1, NULL, 'Bono o comisión por ventas', 1),
('Prima de Servicios', 1, 0, NULL, 'Prima de servicios', 1),
('Intereses sobre Cesantías', 1, 0, NULL, 'Intereses sobre cesantías', 1),
('Otro Devengado', 1, 1, NULL, 'Otro concepto devengado', 1),
('Otro Deducción', 2, 1, NULL, 'Otro concepto deducción', 1);

INSERT INTO tipo_novedad (nombre, descripcion, activo) VALUES
('Incapacidad por enfermedad común', 'Pago del 66.67% del salario', 1),
('Incapacidad por accidente laboral', 'Pago del 100% vía ARL', 1),
('Licencia no remunerada', 'Suspensión de salario', 1),
('Vacaciones', 'Tiempo de descanso remunerado', 1),
('Licencia de maternidad', 'Pago del 100% por EPS', 1),
('Licencia de paternidad', 'Pago proporcional EPS', 1),
('Ausencia injustificada', 'No se paga salario esos días', 1);

INSERT INTO tipo_aporte_patronal (nombre, porcentaje, activo) VALUES
('Salud Patronal', 8.5, 1),
('Pensión Patronal', 12.0, 1),
('ARL', 0.0, 1), -- depende del riesgo, se ajusta en cálculos
('SENA', 2.0, 1),
('ICBF', 3.0, 1),
('Caja de Compensación', 4.0, 1);


INSERT INTO concepto_provision (nombre, activo) VALUES
('Prima de Servicios', 1),
('Cesantías', 1),
('Intereses sobre Cesantías', 1),
('Vacaciones', 1);


INSERT INTO parametro_nomina (nombre, valor_numerico, activo) VALUES
('SALARIO_MINIMO', 1300000, 1),
('AUXILIO_TRANSPORTE', 162000, 1),
('SMMLV_TOPE_AUXILIO', 2, 1),
('SALARIO_INTEGRAL', 18505500, 1),
('SMMLV_FONDO_SOLIDARIDAD', 4, 1);


CREATE DATABASE NominaBD;
GO

USE NominaBD;
GO


-- Tablas auxiliares
CREATE TABLE cargo
(
    id_cargo INT PRIMARY KEY IDENTITY(1,1),
    nombre_cargo NVARCHAR(100) NOT NULL

);


CREATE TABLE departamento
(
    id_departamento INT PRIMARY KEY IDENTITY(1,1),
    nombre_departamento NVARCHAR(100) NOT NULL
);

CREATE TABLE estado_civil
(
    id_estado_civil INT PRIMARY KEY IDENTITY(1,1),
    descripcion_estado_civil NVARCHAR(50) NOT NULL
);

CREATE TABLE arl
(
    id_arl INT PRIMARY KEY IDENTITY(1,1),
    nombre_arl NVARCHAR(100) NOT NULL
);

CREATE TABLE eps
(
    id_eps INT PRIMARY KEY IDENTITY(1,1),
    nombre_eps NVARCHAR(100) NOT NULL
);

CREATE TABLE fondo_pension
(
    id_fondo_pension INT PRIMARY KEY IDENTITY(1,1),
    nombre_fondo_pension NVARCHAR(100) NOT NULL
);

CREATE TABLE fondo_cesantias
(
    id_fondo_cesantias INT PRIMARY KEY IDENTITY(1,1),
    nombre_fondo_cesantias NVARCHAR(100) NOT NULL
);

CREATE TABLE tipo_contrato
(
    id_tipo_contrato INT PRIMARY KEY IDENTITY(1,1),
    descripcion_contrato NVARCHAR(100) NOT NULL
);

CREATE TABLE entidad_bancaria
(
    id_banco INT PRIMARY KEY IDENTITY(1,1),
    nombre_banco NVARCHAR(100) NOT NULL
);

CREATE TABLE estado_laboral
(
    id_estado_laboral INT PRIMARY KEY IDENTITY(1,1),
    descripcion_estado NVARCHAR(50) NOT NULL
);

CREATE TABLE nivel_riesgo
(
    id_nivel_riesgo INT PRIMARY KEY IDENTITY(1,1),
    descripcion_riesgo NVARCHAR(50),
    porcentaje_riesgo DECIMAL(5,2)
);

CREATE TABLE tipo_hora_extra
(
    id_tipo_hora_extra INT PRIMARY KEY IDENTITY(1,1),
    descripcion_hora_extra NVARCHAR(100),
    porcentaje_recargo DECIMAL(5,2)
);

CREATE TABLE tipo_incapacidad
(
    id_tipo_incapacidad INT PRIMARY KEY IDENTITY(1,1),
    descripcion_incapacidad NVARCHAR(100)
);

CREATE TABLE tipo_licencia
(
    id_tipo_licencia INT PRIMARY KEY IDENTITY(1,1),
    descripcion_licencia NVARCHAR(100)
);

CREATE TABLE empresa
(
    id_empresa INT PRIMARY KEY IDENTITY(1,1),
    nombre_empresa NVARCHAR(100),
    nit_empresa NVARCHAR(20),
    tipo_empresa NVARCHAR(50)
);

CREATE TABLE sucursal
(
    id_sucursal INT PRIMARY KEY IDENTITY(1,1),
    nombre_sucursal NVARCHAR(100),
    ciudad NVARCHAR(100),
    id_empresa INT,
    FOREIGN KEY (id_empresa) REFERENCES empresa(id_empresa)
);

-- Empleado
CREATE TABLE empleado
(
    id_empleado INT PRIMARY KEY IDENTITY(1,1),
    documento_identidad NVARCHAR(20),
    nombre_empleado NVARCHAR(100),
    id_cargo INT,
    id_departamento INT,
    id_empresa INT,
    id_sucursal INT,
    fecha_ingreso DATE,
    salario_basico DECIMAL(18,2),
    correo_electronico NVARCHAR(100),
    telefono_contacto NVARCHAR(20),
    id_estado_civil INT,
    id_eps INT,
    id_arl INT,
    id_fondo_pension INT,
    id_fondo_cesantias INT,
    fecha_nacimiento DATE,
    direccion_residencia NVARCHAR(200),
    id_tipo_contrato INT,
    es_salario_integral BIT,
    id_nivel_riesgo INT,
    cuenta_bancaria NVARCHAR(30),
    id_banco INT,
    id_estado_laboral INT,
    FOREIGN KEY (id_cargo) REFERENCES cargo(id_cargo),
    FOREIGN KEY (id_departamento) REFERENCES departamento(id_departamento),
    FOREIGN KEY (id_empresa) REFERENCES empresa(id_empresa),
    FOREIGN KEY (id_sucursal) REFERENCES sucursal(id_sucursal),
    FOREIGN KEY (id_estado_civil) REFERENCES estado_civil(id_estado_civil),
    FOREIGN KEY (id_eps) REFERENCES eps(id_eps),
    FOREIGN KEY (id_arl) REFERENCES arl(id_arl),
    FOREIGN KEY (id_fondo_pension) REFERENCES fondo_pension(id_fondo_pension),
    FOREIGN KEY (id_fondo_cesantias) REFERENCES fondo_cesantias(id_fondo_cesantias),
    FOREIGN KEY (id_tipo_contrato) REFERENCES tipo_contrato(id_tipo_contrato),
    FOREIGN KEY (id_nivel_riesgo) REFERENCES nivel_riesgo(id_nivel_riesgo),
    FOREIGN KEY (id_banco) REFERENCES entidad_bancaria(id_banco),
    FOREIGN KEY (id_estado_laboral) REFERENCES estado_laboral(id_estado_laboral)
);


-- Continuación de la base de datos NominaBD
CREATE TABLE estado_nomina
(
    id_estado INT PRIMARY KEY IDENTITY(1,1),
    nombre_estado NVARCHAR(20)
);

-- Tabla nomina
CREATE TABLE nomina
(
    id_nomina INT PRIMARY KEY IDENTITY(1,1),
    id_empleado INT,
    fecha_nomina DATE,
    salario_bruto DECIMAL(18,2),
    total_deducciones DECIMAL(18,2),
    salario_neto DECIMAL(18,2),
    id_estado INT,
    FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado),
    FOREIGN KEY (id_estado) REFERENCES estado_nomina(id_estado)
);

--'DEVENGADO', 'DEDUCCION', 'APORTE', 'PRESTACION', 'BONIFICACION', 'INCAPACIDAD', 'LICENCIA'
CREATE TABLE tipo_concepto_detalle_nomina
(
    id_concepto INT PRIMARY KEY IDENTITY(1,1),
    nombre_concepto NVARCHAR(50)
);


-- Tabla detalle_nomina
CREATE TABLE detalle_nomina
(
    id_detalle INT PRIMARY KEY IDENTITY(1,1),
    id_nomina INT,
    id_concepto INT,
    concepto NVARCHAR(100),
    valor DECIMAL(18,2),
    FOREIGN KEY (id_nomina) REFERENCES nomina(id_nomina),
    FOREIGN KEY (id_concepto) REFERENCES tipo_concepto_detalle_nomina(id_concepto)
);

--HORAS_EXTRA', 'RECARGO_NOCTURNO', 'DOMINICAL_FESTIVO', 'BONIFICACION', 'COMISION', 'AUXILIO_TRANSPORTE','AUXILIO_ALIMENTACION', 'OTRO'
CREATE TABLE tipo_pago_extra
(
    id_tipo_pago_extra INT PRIMARY KEY IDENTITY(1,1),
    nombre_tipo NVARCHAR(100)
);

-- Tabla pago_extra
CREATE TABLE pago_extra
(
    id_pago_extra INT PRIMARY KEY IDENTITY(1,1),
    id_empleado INT,
    id_tipo_pago_extra INT,
    valor_pago DECIMAL(18,2),
    FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado),
    FOREIGN KEY (id_tipo_pago_extra) REFERENCES tipo_pago_extra(id_tipo_pago_extra)
);

--'SALUD', 'PENSION', 'FONDO_SOLIDARIDAD','PRESTAMO_EMPRESA', 'COOPERATIVA', 'CUOTA_SINDICAL', 'OTRO'
CREATE TABLE tipo_deduccion
(
    id_tipo_deduccion INT PRIMARY KEY IDENTITY(1,1),
    nombre_tipo NVARCHAR(100)
);

-- Tabla deduccion
CREATE TABLE deduccion
(
    id_deduccion INT PRIMARY KEY IDENTITY(1,1),
    id_empleado INT,
    id_tipo_deduccion INT,
    valor_deduccion DECIMAL(18,2),
    FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado),
    FOREIGN KEY (id_tipo_deduccion) REFERENCES tipo_deduccion(id_tipo_deduccion)
);


-- Tabla horas_extra
CREATE TABLE horas_extra
(
    id_hora_extra INT PRIMARY KEY IDENTITY(1,1),
    id_empleado INT,
    id_tipo_hora_extra INT,
    cantidad_horas DECIMAL(10,2),
    valor_hora DECIMAL(18,2),
    total_pagado DECIMAL(18,2),
    FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado),
    FOREIGN KEY (id_tipo_hora_extra) REFERENCES tipo_hora_extra(id_tipo_hora_extra)
);

--'CESANTIAS', 'INTERES_CESANTIAS', 'PRIMA_SERVICIOS', 'VACACIONES', 'OTRO'
CREATE TABLE tipo_prestacion
(
    id_tipo_prestacion INT PRIMARY KEY IDENTITY(1,1),
    nombre_tipo NVARCHAR(100)
);


-- Tabla prestacion_social
CREATE TABLE prestacion_social
(
    id_prestacion INT PRIMARY KEY IDENTITY(1,1),
    id_empleado INT,
    id_tipo_prestacion INT,
    valor_prestacion DECIMAL(18,2),
    fecha_pago DATE,
    FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado),
    FOREIGN KEY (id_tipo_prestacion) REFERENCES tipo_prestacion(id_tipo_prestacion)
);

-- Tabla incapacidad
CREATE TABLE incapacidad
(
    id_incapacidad INT PRIMARY KEY IDENTITY(1,1),
    id_empleado INT,
    id_tipo_incapacidad INT,
    fecha_inicio DATE,
    fecha_fin DATE,
    FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado),
    FOREIGN KEY (id_tipo_incapacidad) REFERENCES tipo_incapacidad(id_tipo_incapacidad)
);

-- Tabla licencia
CREATE TABLE licencia
(
    id_licencia INT PRIMARY KEY IDENTITY(1,1),
    id_empleado INT,
    id_tipo_licencia INT,
    fecha_inicio DATE,
    fecha_fin DATE,
    FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado),
    FOREIGN KEY (id_tipo_licencia) REFERENCES tipo_licencia(id_tipo_licencia)
);

-- Parámetros (SMML,Auxilio Transporte,Días vacaciones, Intereses Cesantías %)
CREATE TABLE parametro_laboral
(
    id_parametro_laboral INT PRIMARY KEY IDENTITY(1,1),
    nombre_parametro NVARCHAR(100),
    valor_parametro DECIMAL(18,2),
    unidad NVARCHAR(20)
);


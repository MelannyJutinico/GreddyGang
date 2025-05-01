-- Tabla de Tipos de Contrato
CREATE TABLE tipo_contrato (
    id_tipo_contrato INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

-- Tabla de Niveles de Riesgo ARL
CREATE TABLE nivel_riesgo_arl (
    id_nivel INT PRIMARY KEY, -- 1 a 5
    nombre VARCHAR(50) NOT NULL,
    porcentaje DECIMAL(5,3) NOT NULL -- % del salario
);



-- Tabla de Empleados
CREATE TABLE empleado (
    id_empleado INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    cargo VARCHAR(100) NOT NULL,
    departamento VARCHAR(100) NOT NULL,
    salario_base DECIMAL(18,2) NOT NULL,
    recibe_auxilio_transporte BIT NOT NULL DEFAULT 1,
    fecha_ingreso DATE NOT NULL,
    activo BIT NOT NULL DEFAULT 1,
    nivel_riesgo INT NOT NULL,
    id_tipo_contrato INT NOT NULL,
    FOREIGN KEY (id_tipo_contrato) REFERENCES tipo_contrato(id_tipo_contrato),
    FOREIGN KEY (nivel_riesgo) REFERENCES nivel_riesgo_arl (id_nivel)
);

-- Tabla de Períodos de Nómina
CREATE TABLE periodo_nomina (
    id_periodo INT IDENTITY(1,1) PRIMARY KEY,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    estado VARCHAR(20) DEFAULT 'ABIERTO',
    fecha_creacion DATETIME DEFAULT GETDATE(),
    CONSTRAINT chk_estado_periodo CHECK (estado IN ('ABIERTO', 'EN_PROCESO', 'CERRADO', 'ANULADO'))
);

-- Tabla de Tipos de conceptos (Devengado/Deducción)
CREATE TABLE tipo_concepto (
    id_tipo_concepto INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL -- DEVENGADO o DEDUCCION
);

-- Tabla de Conceptos de Nómina
CREATE TABLE concepto_nomina (
    id_concepto INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    id_tipo_concepto INT NOT NULL, -- DEVENGADO o DEDUCCION
    registro_manual BIT NOT NULL DEFAULT 0, -- 0 = Automático, 1 = Usuario puede ingresar
    porcentaje DECIMAL(5,2) NULL, -- Aquí configuras el porcentaje sobre el sueldo / valor hora si aplica
    descripcion VARCHAR(255) NULL,
    activo BIT NOT NULL DEFAULT 1,
    FOREIGN KEY (id_tipo_concepto) REFERENCES tipo_concepto(id_tipo_concepto),
);



-- Tabla de Nómina (encabezado)
CREATE TABLE nomina (
    id_nomina INT IDENTITY(1,1) PRIMARY KEY,
    id_empleado INT NOT NULL,
    id_periodo INT NOT NULL,
    fecha_liquidacion DATE NOT NULL DEFAULT GETDATE(),
    total_devengado DECIMAL(18,2) DEFAULT 0,
    total_deduccion DECIMAL(18,2) DEFAULT 0,
    total_a_pagar DECIMAL(18,2) DEFAULT 0,
    estado VARCHAR(20) NOT NULL DEFAULT 'GENERADA',
    FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado),
    FOREIGN KEY (id_periodo) REFERENCES periodo_nomina(id_periodo),
    CONSTRAINT chk_estado_nomina CHECK (estado IN ('GENERADA', 'EN_PROCESO', 'LIQUIDADA', 'PAGADA'))
);

CREATE INDEX idx_nomina_id_periodo ON nomina (id_periodo);
CREATE INDEX idx_nomina_estado ON nomina (estado);


-- Tabla de Detalle de Nómina
CREATE TABLE detalle_nomina (
    id_detalle INT IDENTITY(1,1) PRIMARY KEY,
    id_nomina INT NOT NULL,
    id_concepto INT NOT NULL,
    cantidad DECIMAL(10,2) NULL, -- cantidad de horas (o NULL si no aplica)
    valor_unitario DECIMAL(18,2) NULL, -- valor por unidad (ej: valor de la hora ordinaria)
    valor_total DECIMAL(18,2) NOT NULL, -- el valor total calculado
    observaciones VARCHAR(255) NULL,
    FOREIGN KEY (id_nomina) REFERENCES nomina(id_nomina),
    FOREIGN KEY (id_concepto) REFERENCES concepto_nomina(id_concepto)
);

-- Tabla de Tipos de Novedad
CREATE TABLE tipo_novedad (
    id_tipo_novedad INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(200),
    activo BIT NOT NULL DEFAULT 1
);

-- Tabla de Novedades
CREATE TABLE novedad (
    id_novedad INT IDENTITY(1,1) PRIMARY KEY,
    id_empleado INT NOT NULL,
    id_tipo_novedad INT NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    porcentaje_pago DECIMAL(5,2) NOT NULL, -- Ej: 66.6%
    observacion VARCHAR(255),
    FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado),
    FOREIGN KEY (id_tipo_novedad) REFERENCES tipo_novedad(id_tipo_novedad)
);

-- Tabla de Tipos de Aporte Patronal
CREATE TABLE tipo_aporte_patronal (
    id_tipo_aporte INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    porcentaje DECIMAL(5,2) NOT NULL,
    activo BIT NOT NULL DEFAULT 1
);

-- Tabla de Aportes Patronales
CREATE TABLE aporte_patronal (
    id_aporte INT IDENTITY(1,1) PRIMARY KEY,
    id_nomina INT NOT NULL,
    id_tipo_aporte INT NOT NULL,
    cantidad DECIMAL(18,2) NOT NULL,
    FOREIGN KEY (id_nomina) REFERENCES nomina(id_nomina),
    FOREIGN KEY (id_tipo_aporte) REFERENCES tipo_aporte_patronal(id_tipo_aporte)
);

-- Tabla de Conceptos de Provisiones (Prima, Cesantías, Intereses, Vacaciones)
CREATE TABLE concepto_provision (
    id_concepto_provision INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    activo BIT NOT NULL DEFAULT 1
);


-- Tabla de Provisiones de Prestaciones Sociales
CREATE TABLE provision_prestaciones (
    id_provision INT IDENTITY(1,1) PRIMARY KEY,
    id_empleado INT NOT NULL,
    id_periodo INT NOT NULL,
    id_concepto_provision INT NOT NULL,
    cantidad DECIMAL(18,2) NOT NULL,
    fecha_registro DATETIME DEFAULT GETDATE() NOT NULL,
    FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado),
    FOREIGN KEY (id_periodo) REFERENCES periodo_nomina(id_periodo),
    FOREIGN KEY (id_concepto_provision) REFERENCES concepto_provision(id_concepto_provision)
);

-- Tabla de Parámetros de Nómina (para configuraciones generales)
CREATE TABLE parametro_nomina (
    id_parametro INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    valor_numerico DECIMAL(18,2) NULL,
    valor_texto VARCHAR(255) NULL,
    activo BIT NOT NULL DEFAULT 1
);

CREATE TABLE cesantias_pagadas (
    id_pago INT IDENTITY(1,1) PRIMARY KEY,
    id_empleado INT NOT NULL,
    id_periodo INT NOT NULL,
    salario_base DECIMAL(18,2) NOT NULL,
    dias_trabajados INT NOT NULL,
    valor_cesantias DECIMAL(18,2) NOT NULL,
    fecha_registro DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado),
    FOREIGN KEY (id_periodo) REFERENCES periodo_nomina(id_periodo)
);

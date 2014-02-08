-- Schema: cobros


-- DROP SCHEMA cobros;


CREATE SCHEMA cobros
 
AUTHORIZATION postgres;



-- Table: cobros.agente

-- 
DROP TABLE cobros.agente;


CREATE TABLE cobros.agente
(
  id_agente serial NOT NULL,
  cedula character varying(15) NOT NULL,
  usuario character varying(25) NOT NULL,
  "contraseña" character varying(25) NOT NULL,
  nombre character varying(25) NOT NULL,
  apellido character varying(25) NOT NULL,
  fecha_ingreso date,
  estatus character varying(1),
  tel1 character varying(25) NOT NULL,
  tel2 character varying(25),
  dir1 character varying(25) NOT NULL,
  dir2 character varying(25),
  id_zona integer,
  CONSTRAINT agente_pkey PRIMARY KEY (id_agente),
  CONSTRAINT agente_id_zona_fkey FOREIGN KEY (id_zona)
      REFERENCES cobros.zona (id_zona) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT agente_cedula_key UNIQUE (cedula),
  CONSTRAINT agente_usuario_key UNIQUE (usuario),
  CONSTRAINT agente_estatus_check CHECK (estatus::text = 'A'::text OR estatus::text = 'I'::text)
)
WITH (
  OIDS=FALSE
);

ALTER TABLE cobros.agente
  OWNER TO postgres;



-- Table: cobros.cuenta

-- DROP TABLE cobros.cuenta;

CREATE TABLE cobros.cuenta
(
  id_cuenta serial NOT NULL,
  cedula character varying(15) NOT NULL,
  nommbre character varying(25) NOT NULL,
  apellido character varying(25) NOT NULL,
  tel1 character varying(25) NOT NULL,
  tel2 character varying(25),
  dir1 character varying(25) NOT NULL,
  dir2 character varying(25),
  id_zona integer,
  fecha_ingreso date,
  no_contrato integer NOT NULL,
  fecha_contrato date,
  estatus character varying(1),
  id_empresa integer,
  monto_deuda numeric NOT NULL,
  CONSTRAINT cuenta_pkey PRIMARY KEY (id_cuenta),
  CONSTRAINT cuenta_id_empresa_fkey FOREIGN KEY (id_empresa)
      REFERENCES cobros.empresa (id_empresa) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT cuenta_id_zona_fkey FOREIGN KEY (id_zona)
      REFERENCES cobros.zona (id_zona) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT cuenta_cedula_key UNIQUE (cedula),
  CONSTRAINT cuenta_estatus_check CHECK (estatus::text = 'A'::text OR estatus::text = 'I'::text),
  CONSTRAINT cuenta_monto_deuda_check CHECK (monto_deuda > 0::numeric)
)
WITH (
  OIDS=FALSE
);

ALTER TABLE cobros.cuenta
  OWNER TO postgres;

-- Table: cobros.disposicion

-- DROP TABLE cobros.disposicion;

CREATE TABLE cobros.disposicion
(
  id_disposicion serial NOT NULL,
  descripcion character varying(40) NOT NULL,
  CONSTRAINT disposicion_pkey PRIMARY KEY (id_disposicion)
)
WITH (
  OIDS=FALSE
);

ALTER TABLE cobros.disposicion
  OWNER TO postgres;

-- Table: cobros.empresa

-- DROP TABLE cobros.empresa;

CREATE TABLE cobros.empresa
(
  id_empresa serial NOT NULL,
  nombre character varying(40) NOT NULL,
  CONSTRAINT empresa_pkey PRIMARY KEY (id_empresa)
)
WITH (
  OIDS=FALSE
);

ALTER TABLE cobros.empresa
  OWNER TO postgres;


-- Table: cobros.reminder

-- DROP TABLE cobros.reminder;

CREATE TABLE cobros.reminder
(
  id_reminder serial NOT NULL,
  descripcion character varying(40) NOT NULL,
  fecha_establecida time without time zone,
  fecha_a_aplicar timestamp without time zone,
  CONSTRAINT reminder_pkey PRIMARY KEY (id_reminder)
)
WITH (
  OIDS=FALSE
);

ALTER TABLE cobros.reminder
  OWNER TO postgres;


-- Table: cobros.track_cuenta

-- DROP TABLE cobros.track_cuenta;

CREATE TABLE cobros.track_cuenta
(
  id_track_cuenta serial NOT NULL,
  id_agente integer NOT NULL,
  fecha_hora date,
  id_cuenta integer NOT NULL,
  id_disposicion integer,
  id_reminder integer,
  CONSTRAINT track_cuenta_pkey PRIMARY KEY (id_track_cuenta, id_agente, id_cuenta),
  CONSTRAINT track_cuenta_id_agente_fkey FOREIGN KEY (id_agente)
      REFERENCES cobros.agente (id_agente) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT track_cuenta_id_cuenta_fkey FOREIGN KEY (id_cuenta)
      REFERENCES cobros.cuenta (id_cuenta) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT track_cuenta_id_disposicion_fkey FOREIGN KEY (id_disposicion)
      REFERENCES cobros.disposicion (id_disposicion) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT track_cuenta_id_reminder_fkey FOREIGN KEY (id_reminder)
      REFERENCES cobros.reminder (id_reminder) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

ALTER TABLE cobros.track_cuenta
  OWNER TO postgres;


-- Table: cobros.zona

-- DROP TABLE cobros.zona;

CREATE TABLE cobros.zona
(
  id_zona serial NOT NULL,
  descripcion character varying(40) NOT NULL,
  composicion character varying(40) NOT NULL,
  CONSTRAINT zona_pkey PRIMARY KEY (id_zona)
)
WITH (
  OIDS=FALSE
);

ALTER TABLE cobros.zona
  OWNER TO postgres;







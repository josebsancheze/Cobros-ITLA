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


INSERT INTO cobros.zona(
            id_zona, descripcion, composicion)
    VALUES (1, 'ZONA NORTE', 'Santiago, Moca, Salcedo, Sfm');
INSERT INTO cobros.zona(
            id_zona, descripcion, composicion)
    VALUES (2, 'ZONA SUR', 'Santo Domingo, Villa Alt., Azua, Spm');


CREATE TABLE cobros.empresa
(
  id_empresa serial NOT NULL,
  nombre character varying(40) NOT NULL,
  CONSTRAINT empresa_pkey PRIMARY KEY (id_empresa)
)
WITH (
  OIDS=FALSE
);

INSERT INTO cobros.empresa(
            id_empresa, nombre)
    VALUES (1, 'LA SIRENA');
INSERT INTO cobros.empresa(
            id_empresa, nombre)
    VALUES (2, 'PLAZA LAMA');
INSERT INTO cobros.empresa(
            id_empresa, nombre)
    VALUES (3, 'BRAVO');
INSERT INTO cobros.empresa(
            id_empresa, nombre)
    VALUES (4, 'CASA CUESTA');
INSERT INTO cobros.empresa(
            id_empresa, nombre)
    VALUES (5, 'DIST. CORRIPIO');
INSERT INTO cobros.empresa(
            id_empresa, nombre)
    VALUES (6, 'IKEA');

CREATE TABLE cobros.agente
(
  id_agente serial NOT NULL,
  cedula character varying(15) NOT NULL,
  usuario character varying(25) NOT NULL,
  "contraseña" character varying(25) NOT NULL,
  nombre character varying(25) NOT NULL,
  apellido character varying(25) NOT NULL,
  fecha_ingreso date,
  activo character varying(1),
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
  CONSTRAINT agente_estatus_check CHECK (activo::text = 'S'::text OR activo::text = 'N'::text)
)
WITH (
  OIDS=FALSE
);

INSERT INTO cobros.agente(
             cedula, usuario, contraseña, nombre, apellido, fecha_ingreso, 
            activo, tel1, tel2, dir1, dir2, id_zona)
    VALUES ('05500419709', 'esantiago', '123456', 'EDGAR', 'SANTIAGO', current_date, 'S', 
            '8095778585', '8095774141', 'C. Primera No. 15', 'Residencial Real', 1);



CREATE TABLE cobros.cuenta
(
  id_cuenta serial NOT NULL,
  cedula character varying(15) NOT NULL,
  nombre character varying(25) NOT NULL,
  apellido character varying(25) NOT NULL,
  tel1 character varying(25) NOT NULL,
  tel2 character varying(25),
  dir1 character varying(25) NOT NULL,
  dir2 character varying(25),
  id_zona integer,
  fecha_ingreso date,
  no_contrato integer NOT NULL,
  fecha_contrato date,
  activo character varying(1),
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
  CONSTRAINT cuenta_estatus_check CHECK (activo::text = 'S'::text OR activo::text = 'N'::text),
  CONSTRAINT cuenta_monto_deuda_check CHECK (monto_deuda > 0::numeric)
)
WITH (
  OIDS=FALSE
);

INSERT INTO cobros.cuenta(
             cedula, nombre, apellido, tel1, tel2, dir1, dir2, 
            id_zona, fecha_ingreso, no_contrato, fecha_contrato, activo, 
            id_empresa, monto_deuda)
    VALUES ('031-0754824-1', 'Juana Martina', 'Acosta Luna', '809-583-9458', '809-575-4895', 'Plaza Jardina Mod. 240', 'Los Jardines', 
            2, '2009/12/3', 12, '2008/12/3', 'S', 
            1, 18000);
INSERT INTO cobros.cuenta(
            id_cuenta, cedula, nombre, apellido, tel1, tel2, dir1, dir2, 
            id_zona, fecha_ingreso, no_contrato, fecha_contrato, activo, 
            id_empresa, monto_deuda)
    VALUES (3, '031-0755824-1', 'Laura Melissa', 'Lopez Gomez', '809-583-9485', '809-598-9823', 'Plaza Jardina Mod. 247', 'Los Jardines', 
            1, '2005/2/3', 24, '2002/12/3', 'S', 
            1, 13000);

CREATE TABLE cobros.agentescuentas
(
 id_agente integer,
 id_cuenta integer UNIQUE,
 PRIMARY KEY(id_agente, id_cuenta),
 CONSTRAINT agentescuentas_id_agente_fkey FOREIGN KEY (id_agente)
      REFERENCES cobros.agente (id_agente) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
 CONSTRAINT agentescuentas_id_cuenta_fkey FOREIGN KEY (id_cuenta)
      REFERENCES cobros.cuenta (id_cuenta) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE cobros.disposicion
(
  id_disposicion serial NOT NULL,
  descripcion character varying(40) NOT NULL,
  CONSTRAINT disposicion_pkey PRIMARY KEY (id_disposicion)
)
WITH (
  OIDS=FALSE
);

INSERT INTO cobros.disposicion(
            id_disposicion, descripcion)
    VALUES (1, 'LLAMADA SOLICITADA');
INSERT INTO cobros.disposicion(
            id_disposicion, descripcion)
    VALUES (2, 'NO DISPONIBLE');
INSERT INTO cobros.disposicion(
            id_disposicion, descripcion)
    VALUES (3, 'NO QUIERE PAGAR');
INSERT INTO cobros.disposicion(
            id_disposicion, descripcion)
    VALUES (4, 'ACUERDO DE PAGO');


    
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

INSERT INTO cobros.reminder(
            id_reminder, descripcion, fecha_establecida, fecha_a_aplicar)
    VALUES (1, 'No llamar despues de las 12:00pm', '2013-01-03 10:00:00.59', '2013-10-03 13:00:00.59');



CREATE TABLE cobros.track_cuenta
(
  id_track_cuenta serial NOT NULL,
  id_agente integer NOT NULL,
  fecha_hora timestamp without time zone,
  id_cuenta integer NOT NULL,
  id_disposicion integer,
  id_reminder integer,
  comentario character varying(25),
  revisado varchar(1) check(revisado = 'S' or revisado = 'N'),
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

INSERT INTO cobros.track_cuenta(
             id_agente, fecha_hora, id_cuenta, id_disposicion, 
            id_reminder, revisado)
    VALUES (1, '2014/02/06 13:15:52.02', 1, 4, 
            1,'N');



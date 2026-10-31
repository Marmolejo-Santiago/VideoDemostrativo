# escuela-api (Spring Boot)

Servicios REST para los cinco catalogos de la base `ESCUELA`: ALUMNO, MATERIA, PROFESOR, ALUMNO_MATERIA y PROFESOR_MATERIA.
Requiere Java 17, Maven y MySQL con la base creada desde `../sql/Escuela.sql`.

## Configuracion (sin contrasenas en el repo)

Las credenciales se leen de variables de entorno:

| Variable      | Default                                | Descripcion         |
|---------------|----------------------------------------|---------------------|
| `DB_URL`      | `jdbc:mysql://localhost:3306/ESCUELA`  | URL de MySQL        |
| `DB_USER`     | `root`                                 | Usuario             |
| `DB_PASSWORD` | (vacia)                                | Contrasena          |
| `SERVER_PORT` | `8081`                                 | Puerto (Tomcat usa 8080) |

## Compilar y ejecutar

```bash
cd springboot
mvn clean package -DskipTests

export DB_USER=root
export DB_PASSWORD='tu_password'
java -jar target/escuela-api-1.0.0.jar
```

En segundo plano (sigue vivo al cerrar la sesion SSH):

```bash
nohup java -jar target/escuela-api-1.0.0.jar > springboot.log 2>&1 &
tail -f springboot.log
```

Detener: `pkill -f escuela-api`

## Endpoints

| Catalogo          | Ruta base                |
|-------------------|--------------------------|
| ALUMNO            | `/api/alumnos`           |
| MATERIA           | `/api/materias`          |
| PROFESOR          | `/api/profesores`        |
| ALUMNO_MATERIA    | `/api/alumno-materias`   |
| PROFESOR_MATERIA  | `/api/profesor-materias` |

Cada ruta acepta: `GET /` (lista), `GET /{id}`, `POST /` (alta), `PUT /{id}` (cambio), `DELETE /{id}` (baja).

Codigos: `200` ok, `201` creado, `204` borrado, `400` datos invalidos o relacion inexistente,
`404` id no existe, `409` integridad referencial (por ejemplo, borrar un alumno que tiene materias).

## Ejemplos con curl (para el video)

```bash
U=http://localhost:8081/api

# ALUMNO
curl $U/alumnos
curl -X POST $U/alumnos -H 'Content-Type: application/json' -d '{"nombre":"LUIS","edad":21,"promedio":8.90}'
curl -X PUT  $U/alumnos/4 -H 'Content-Type: application/json' -d '{"nombre":"LUIS PEREZ","edad":22,"promedio":9.00}'
curl -X DELETE $U/alumnos/4

# MATERIA
curl -X POST $U/materias -H 'Content-Type: application/json' -d '{"nombre":"ETICA","creditos":4}'
curl -X PUT  $U/materias/6 -H 'Content-Type: application/json' -d '{"nombre":"ETICA PROFESIONAL","creditos":5}'
curl -X DELETE $U/materias/6

# PROFESOR
curl -X POST $U/profesores -H 'Content-Type: application/json' -d '{"nombre":"LUCIA SOTO","especialidad":"INGLES"}'
curl -X PUT  $U/profesores/5 -H 'Content-Type: application/json' -d '{"nombre":"LUCIA SOTO R","especialidad":"INGLES TECNICO"}'
curl -X DELETE $U/profesores/5

# ALUMNO_MATERIA (se envian solo los ids de alumno y materia)
curl -X POST $U/alumno-materias -H 'Content-Type: application/json' \
  -d '{"alumno":{"id":3},"materia":{"id":5},"calificacion":9.0}'
curl -X PUT  $U/alumno-materias/8 -H 'Content-Type: application/json' \
  -d '{"alumno":{"id":3},"materia":{"id":5},"calificacion":9.5}'
curl -X DELETE $U/alumno-materias/8

# PROFESOR_MATERIA
curl -X POST $U/profesor-materias -H 'Content-Type: application/json' \
  -d '{"profesor":{"id":2},"materia":{"id":5}}'
curl -X PUT  $U/profesor-materias/6 -H 'Content-Type: application/json' \
  -d '{"profesor":{"id":3},"materia":{"id":5}}'
curl -X DELETE $U/profesor-materias/6

# Integridad referencial: responde 409 (el alumno 1 tiene materias)
curl -i -X DELETE $U/alumnos/1
```

Los ids de los ejemplos suponen los datos iniciales de `Escuela.sql`; ajustalos a los que devuelva tu `GET`.

## Verificar la persistencia en MySQL

```bash
mysql -u root -p -e "SELECT * FROM ESCUELA.ALUMNO;"
```

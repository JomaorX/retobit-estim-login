# RetoBit: Estim 🏴‍☠️ Login

Tenemos nuestra app de **Estim** bastante avanzada. Durante el sprint anterior, mientras tú implementabas el hash del password, el resto del equipo han implementado toda la generación de tokens JWT. Solo falta la ruta de login que nos devuelve un token para poder acceder a las rutas protegidas de la API.


Puedes usar los siguientes usuarios (ya incluidos en la base de datos) para hacer pruebas en la aplicación:

| Usuario    | Email                  | Contraseña  | Rol            |
|------------|------------------------|-------------|----------------|
| user1      | user1@example.com      | Password123 | ROLE_USER      |
| admin1     | admin1@example.com     | Password123 | ROLE_ADMIN     |
| moderator1 | moderator1@example.com | Password123 | ROLE_MODERATOR |

## Fork del repositorio:

Haz un fork del repo.

Pulsa el botón Fork en github:

![fork](public/img1.png)

Esto creará una copia del repositorio en tu perfil de Github.

## Clona el repo en tu computadora

Ahora hay que descargarse el proyecto en tu computadora.

### 1. Asegúrate de que estás en la URL de TU copia del repositorio

Si la URL es esta: https://github.com/curso-java-marshall-bits/retobit-estim-login **NO ES CORRECTO**.

Donde aparece 'curso-java-marshall-bits' debería aparecer tu nombre de usuario. Haz click en tu perfil y mira tus
repositorios para comprobar si hiciste el fork. En caso contrario vuelve al paso anterior.

### 2. Pulsa en "code" para ver la URL del repositorio y cópiala

![clone](public/img2.png)

Para ello deberás abrir una terminal y navegar a la carpeta donde quieras añadir este retobit.

Utiliza el siguiente comando:

```commandline
git clone https://aqui.debes.pegar.la.url
```

**Nota: Después del 'git clone' debes pegar la url del repositorio. No pongas la que he puesto yo en el ejemplo 🤣*

Ahora se va a crear un nuevo directorio con el nombre del RetoBit.

### 3. ¡Ya puedes abrir este reto en IntelliJ!

# Instrucciones

## Tarea 1: Revisar el código existente

Ya conoces un poco la app, pero en esta ocasión se han añadido filtros de seguridad para proteger algunas rutas en el archivo `config/SecurityConfig.java`. Así que no podrás acceder a ciertas rutas de la API sin un token JWT. También verás que la lógica del token está implementada en el archivo `security/JwtUtil.java` y `security/JwtAuthenticationFilter.java`.

## Tarea 2: DTO para la respuesta del login

Cuando se haga login desde cualquier cliente (Postman, navegador, etc.) se espera que la respuesta sea un objeto JSON con el token JWT y algo de información.

Para ello, debes crear un DTO llamado `LoginResponseDTO` que contenga los campos `token`, `username`, `email` y `id`. Este DTO se usará para devolver la respuesta del login.
No olvides utilizar lombok para generar los getters y setters de este DTO.
Ejemplo de respuesta:

```json
{
  "username": "test",
  "email": "test@testing.com",
  "id": 1,
  "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJNYXJzaGFsbCBCaXRzIiwiaWF0IjoxNzU0OTkyNDc5LCJleHAiOjE3ODY1Mjg0NzksImF1ZCI6Imh0dHBzOi8vbWFyc2hhbGwtYml0cy5kZXYiLCJzdWIiOiJMb3MgbWVqb3JlcyBjdXJzb3MgZGVsIG11bmRvIG11bmRpYWwifQ.SKjs7sxDf66W_RY63iQxv8tm7acPeUJHkdTegLatINc"
}
```

## Tarea 3: Añadir método authenticateUser en UserService (Interfaz)

En el archivo `services/UserService.java` debes modificar el método `authenticateUser` que se encargará de autenticar al usuario. Este método recibirá el email y el password del usuario y devolverá un objeto `LoginResponseDTO`.

## Tarea 4: Implementar el método authenticateUser en UserServiceImpl

En el archivo `services/UserServiceImpl.java` debes implementar el método `authenticateUser` que has añadido en la interfaz `UserService`. Este método se encargará de buscar al usuario por su email, verificar que el password es correcto y generar un token JWT para el usuario autenticado.

Recuerda que el *return* debe ser un objeto `LoginResponseDTO` con el token JWT y la información del usuario.

## Tarea 5: DTO para el login

Para poder recibid un email y un password desde el cliente, debes crear un DTO llamado `LoginUserDTO` que contenga los campos `email` y `password`. Este DTO se usará para recibir los datos del usuario al hacer login.

## Tarea 6: Añadir ruta de login en UserController

En el archivo `controllers/UserController.java` debes añadir una nueva ruta para el login. Esta ruta será un POST a `/api/users/login` y recibirá un objeto `LoginUserDTO` en el cuerpo de la petición. La ruta debe devolver un objeto `LoginResponseDTO` con el token JWT y la información del usuario.

## Testing

Para comprobar si has realizado bien el ejercicio ejecuta los tests ubicados en **src/test**.
El test te indicará si has pasado con un tick verde ✅. En caso contrario verás el error.

Ejemplo:

![img.png](public/img3.png)

Pulsa en cualquiera de los tests que has fallado y mira el mensaje de la derecha.

- *Expected*: es el valor que el test estaba esperando.
- *Actual*: es el valor que tu reto está retornando.

## Solución

Si quieres ver una posible solución para el retobit que pasa todos los tests puedes mirar la rama *solution* dentro del
repositorio.

![rama solution](public/img4.png)

Ten en cuenta que hay muchas formas de resolver los ejercicios y la tuya puede pasar los tests iguales, pero ser
completamente distinta a la solución. No significa que la tuya esté mal. Compara los resultados y decide cuál te gusta
más o te parece mas legible.

## Entrega

Debes realizar una pull request para entregar el ejercicio. Abre el link del repositorio en github y haz click en la
pestaña *pull requests*.

Selecciona *New pull request*, *Create pull request*. Esto hará que yo pueda verlo y revisarlo en caso de que haya
fallado algún test para poder darte feedback.

Mucha suerte con el reto. Te mando un abrazo y ¡Sigamos desarrollando! 🫂

[marshall-bits.dev](http://marshall-bits.dev)

*Nota: Estos retos pertenecen al curso de Marcel Bosch de Java para desarrolladores junior. Cualquier uso fuera de este
contexto debe estar autorizado explícitamente. Si quieres usar estos ejercicios ponte en contacto conmigo a través de
mis redes sociales (visita mi página para [más información](http://marshall-bits.dev)).*

# App Testing

Esta es una Clean Architecture usando

- Coroutines
- MVVM
- Live Data
- Retrofit
- Room
- Shared Preference
- Navigation Components
- ViewBinding
- Testing
- Dependency Injection

# Architecture

- Se eligio esta Clean Architecture, ya que nos da un ambiente muy organizado donde podemos tener
  separado en una capa las peticiones a una api por ejemplo.
- Es sencilla de implementar como lo podemos ver en el siguiente diagrama.
- Se puede hacer testing de cada capa por separado, lo cual nos ahorra mucho tiempo al tener que
  compilar todo el proyecto.
- Al tener una Clean Architecture o una arquitectura por capas, podemos hacer uso de la inyeccion de
  dependencia, donde una capa necesita como parametro algun metodo o clase para acceder a sus
  funcion, pero no quiere decir que esta dependa unicamente de una metodo, alli es donde podemos
  hacer referencia a la inversion de dependencia, donde una clase debe poder compartir una interface
  con otra y asi tener los mismos metodos implementados, pero funcionar de manera diferente.

![Test Image 4](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

# Run App

- Descargar el Repositorio con los archivos fuentes.
- Abrir el archivo descargado en Android Studio y espera que se descarguen los paquetes.

# Screenshots

- El proyecto cuenta con 4 vistas - Login | Home Screen | Details | Favorites.

<img src="https://user-images.githubusercontent.com/45502428/149797437-39ab2190-0094-4b18-86e0-c6a7b7a07122.jpg" width="100px" /> 
<img src="https://user-images.githubusercontent.com/45502428/181161826-0f95b10e-16af-4ffa-98fd-def7af9fe44e.jpg" width="100px" /> 
<img src="https://user-images.githubusercontent.com/45502428/181161798-ada8d4b0-3be8-4d5f-adb0-8148ea576686.jpg" width="100px" />  
<img src="https://user-images.githubusercontent.com/45502428/181161811-a5586f46-3581-42d4-a38f-1b61531d1810.jpg" width="100px" /> 

# Flujo de la aplicacion

- (Splash Screen) Inicia un Pequeño splash con la imagen de kotlin.

- (Login) Al iniciar la app por completo nos aparecera un login donde tendremos que ingresar algunas
  credenciales -> ( username, password ).

    - El formulario del login nos hara algunas validaciones como, email en blanco, email no valido,
      contraseña en blanco, contraseña invalida ( igual o mayor a 6 caracteres ).
    - Nota: podemos acceder con una direccion de email valida y una contraseña, no tenemos un
      username o password en especificos. Queda a su libertad

- (Home) Al iniciar sesion correctamente nos redireccionara a la pantalla del Home donde nos
  mostrara un mensaje de bienvenida con nuestro username

    - En la vista del Home podemos apreciar una tabla con un listado de post, donde veremos una
      Imagen, un Titulo, votos promedios que tiene, una pequeña descripcion del post y la fecha que
      se creo la publicacion.
    - Encontraremos en la parte superior izquierda una boton donde podremos cerrar sesion, esto con
      la finalidad de borrar de nuestras preferencias de usuario el username con el que iniciamos
      sesion posteriormente.
    - En la parte superior derecha podemos apreciar un boton que nos podra rediriguir al apartado de
      favoritos.
    - Podemos visualizar cada post a detalle, solo debemos hacer click o clickear sobre cada uno de
      los elementos, esto nos mostrara una vista con el detalle del post un poco mas sencillo de
      visualizar.

- (Details) Al ingresar a la vista de detalle de un post podemos apreciar su contenido.

    - En la vista podemos visualizar en la parte superior izquierda una flecha que nos redirijira al
      apartado anterior.
    - En la parte superior derecha podemos apreciar una boton que nos redirige a la vista de
      favoritos.
    - Al final de la lectura podemos visualizar un boton donde podemos hacer click si asi lo
      deseamos, este nos agregara el post a nuestra lista de favoritos o destacados.

- (Favorites) Igual que como podemos apreciar la vista del Home podemos observar una tabla con la
  lista de algunos post o los post que nosotros hemos agregeado a la misma.

    - Podemos visualizar que tambien contamos con un boton de regreso a nuestra vista anterior.

# Manejo de datos

- Al iniciar sesion o realizar alguna accion que necesite mostrar datos dentro de la app debemos
  hacer consumo de los mismo, ya sea de una Dase de Datos (Room) o de algun servicio externo como lo
  puede ser un servicio REST.

- Al iniciar sesion lo primero que validamos es su conexion a internet, como podemos validar si el
  usuario tiene una conexion a internet hacemos una peticion a un servicio externo, luego de recibir
  una respuesta de exito, podemos almacenar los datos del usuario en nuestras preferencias de
  usuario, como lo es su username.

- Te preguntaras que hacemos con esos datos almacenados en nuestro dispositivo, recuerdas que
  mencione que hacemos una validacion con la conectividad de internet del usuario, alli es donde
  sucede la magia. Cuando el usuario no tenga acceso a internet podemos validar las credenciales de
  usuario, o bueno tan solo si el username exite o si ya el usuario ha iniciado sesion, el cual nos
  permite brindarle el funcionamiento de la app asi no este conectado a la red.

- Asi sucede con los posts, cuando se hace una llamada al servicio REST y obtenemos los datos, y el
  usuario acceda nuevamente al home, se obtendra nueva informacion y se limpiara la Base de Datos
  para no acumular datos no actualizados.

- ¿Que sucede cuando el usuario ha cerrado la app, este debera iniciar sesion nuevamente ? la
  respuesta es no, ya que hemos almacenado su username en las preferencias de usuario, cuando la app
  se inicia validamos que exista un username almacenado y asi pueda el usuario ingresar directamente
  al home y este no pasar nuevamente por la vista de login.

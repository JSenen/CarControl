# CARCONTROL
## Actividad Aprendizaje 1EVA - 2ºDAM - PROGRAMACION MULTIMEDIA DISPOSITIVOS MOVILES

![Java](https://img.shields.io/badge/Java-red?style=for-the-badge&logo=Java&logoColor=white)
![Android](https://img.shields.io/badge/androidstudio-green?style=for-the-badge&logo=androidstudio&logoColor=white)

***

Aplicación creada con el propósito de poder mantener un registro de vehículos por medio
de la base de datos **Room** de la biblioteca de persistencia de Android, con la ayuda de la dependencia de
**Loombok**.

La aplicación puede registrar varios aspectos de cada vehículo que le añadamos. Desde
una ficha inicial con los datos del vehículo, hasta un registro de los repostajes o las
revisiones que le vayamos efectuando, incluyendo la última localización del vehículo.
La aplicación se encuentra desarrollada para **2 idiomas**, inglés y español.

En el aspecto visual, se ha utilizado la librería **Material Design**, y para los mapas se ha usado
**MapBox**

Las funciones de la aplicación son las siguientes.

1.- PANTALLA INICIAL:
    En ella se visualiza por medio de Cards Views los vehiculos que tengamos registrados. Se visualiza matrícula
    del vehículo, marca, modelo, año de adquisición o antiguedad y kilometraje del mismo
    Desde la misma pantalla podemos realizr distintas funciones.
    Elimininar el vehiculo: Para lo que por medio de un dialogo emergente se nos pedira confirmación.
    Ir a la pantalla de modificación del vehículo.
    Registrar un respostaje desde la pantalla de registro de respostajes
    Registrar un mantenimiento desde la pantalla de registro de mantenimiento
    Ir a la pantalla de registro de la posición de aparcamiento del vehículo
    Ver un listado completo de repostajes
    Ver un lstado completo de mantenimientos
    Ir al mapa y ver el último aparcamiento del vehículo.
    Ir, desde el "action bar" al posicionamiento GPS.
    Abrir la aplicación fotografica del dispositivo y registrar una foto en la galería.

2.- PANTALLA AÑADIR VEHICULO
    Desde esta pantalla el usuario puede dar de alta un nuevo vehículo. En la misma pantalla puede 
    **realizar una fotografia** del mismo o **usar una ya grabada en la galería** del dispositivo como
    imagen del mismo.

3.- PANTALLA MODDIFICACIÓN VEHÍCULO.
    Si el usuario lo desea, desde esta pantalla puede realizar modificaciónes sobre ciertos datos del
    vehículo, o cambiar la imagen que se nos muestra del mismo

4.- PANTALLA AÑADIR REPOSTAJE
    En cada ocasión que el usuario repueste el vehículo, puede añadirlo en esta pantalla. Los kilometros 
    del vehículo se iran añadiendo al total del mismo y se modificaran en la pantalla inicial

5.- PANTALLA AÑADIR MANTENIMIENTO   
    Ofrece la posibilidad de ir registrando los mantenimientos del vehículo, se pueden introducir ciertos
    datos y se ofrece una serie de **checkbox** con las operaciones más habituales.
    Al igual que ocurre con los respostajes, los kilometros se van modificando automáticamente.

6.- PANTALLA PARKING.
    El usuario puede marcar su posición en el momento de aparcar el vehículo, y asi poder localizarlo 
    al ir a recogerlo mediante la siguiente función que se lo facilita. La posición del aparcamiento viene
    indicada por la última posición del dispositivo (GPS) o si lo desea el usuario, el mismo puede puntear 
    en el mapa donde desea posicionarla.

7.- PANTALLA LISTADO REPOSTAJES
    Nos ofrece un listado de todos los repostajes realizados

8.- PANTALLA LISTADO MANTENIMIENTOS
    Muestra en pantalla el listado de mantenimientos realizados.

9.- PANTALLA PARKING.
    Nos ofrece sobre un mapa la posición marcada como ultimo aparcamiento del vehículo.

Durante las operaciones habituales en la aplicación, la misma nos **mensajes emergentes** de confirmación del
proceso o **dialogos** en los que se nos preguntará sobre la confirmación de la misma.

Para la introducción de fechas en distintos formularios, se ofrece al usuario un fragmento de **calendario emergente** y que
así le resulte más cómodo la introducción de las mismas.

***

### Requisitos (1 pto cada uno, obligatorios

1. La aplicación contará con, al menos, 7 Activities, utilizando controles ImageView,
TextView, Button, CheckBox y RecyclerView para recoger y presentar información
en pantalla y se hará, como mínimo, en dos idiomas. ✅
2. Se deberán usar Bases de datos para almacenar información. El usuario deberá ser
capaz de registrar, modificar, eliminar y visualizar en un RecyclerView esa
información con un adaptador personalizado (Un CRUD completo). El modelo de
datos de la aplicación estará compuesto, al menos, de 3 clases. ✅
3. La aplicación contará con un menú de opciones o ActionBar desde donde se podrá
acceder a las acciones que el usuario pueda realizar en cada Activity. ✅
4. Añadir alguna función que interactúe con otras aplicaciones del dispositivo (cámara,
contactos, . . .) ✅
5. Se mostrará información útil para la aplicación en un mapa (GoogleMaps o
MapBox) de forma que el usuario pueda interactuar con el mismo para llevar a cabo
alguna acción de utilidad para la aplicación ✅

### tras funcionalidades (1 pto cada una)

6. Utilizar diálogos siempre que sea necesario (al modificar o eliminar información,
por ejemplo) ✅
7. Utiliza la herramienta Git (y GitHub) durante todo el desarrollo de la aplicación.
Utiliza el gestor de Issues para los problemas/fallos que vayan surgiendo ✅
8. Utilizar el GPS del dispositivo para realizar alguna función sobre el mapa ✅
9. Añadir un menú de preferencias con al menos 3 opciones que modifiquen el
   comportamiento de la aplicación. Este menú estará siempre disponible en el
   ActionBar
10. Diseñar algunos layouts para otras posiciones de la pantalla (portrait/landscape)
11. Utilizar imágenes como atributos de algún objeto (y almacenarlo en la base de
   datos) ✅
12. Emplear Fragments en el diseño de alguna de las Activities de la aplicación ✅
13. Utilizar Material Design para personalizar el diseño de la aplicación ✅
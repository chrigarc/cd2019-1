# Juego de las vaquitas
Desarrollar un algoritmo eficiente para el juego de las vacas
El juego consiste en la siguiente:
1. Dada una granja de tamaña nxn se reparte el pasto por casillas i, j. Todas las casillas tienen un nivel de pasto diferente aleatorio
2. Los jugadores desarrollan su algoritmo respetando las siguientes reglas
  * Las vacas solo tienen las acciones: comer, no hacer nada, moverse y reproducirse
  * Cada vaca solo tiene información de su posición actual y las 8 casillas a su alrededor, desconocen por completo el estado del resto de la granja y demás vacas.
  * Por cada ciclo la vaca gasta algo de su energía
  * Si la vaca se queda sin energia muere.
  * Al iniciar el juego se colocan en la granja una cantidad aleatoria de vacas de cada jugador sobre la granja
  * El ganador del juego es quien sobrevivan sus vaquitas.

## Maven
Es una herramienta de software para la gestión y construcción de proyectos Java creada por Jason van Zyl, de Sonatype, en 2002. Es similar en funcionalidad a Apache Ant (y en menor medida a PEAR de PHP y CPAN de Perl), pero tiene un modelo de configuración de construcción más simple, basado en un formato XML. Estuvo integrado inicialmente dentro del proyecto Jakarta pero ahora ya es un proyecto de nivel superior de la Apache Software Foundation.

Maven utiliza un Project Object Model (POM) para describir el proyecto de software a construir, sus dependencias de otros módulos y componentes externos, y el orden de construcción de los elementos. Viene con objetivos predefinidos para realizar ciertas tareas claramente definidas, como la compilación del código y su empaquetado.

Una característica clave de Maven es que está listo para usar en red. El motor incluido en su núcleo puede dinámicamente descargar plugins de un repositorio, el mismo repositorio que provee acceso a muchas versiones de diferentes proyectos Open Source en Java, de Apache y otras organizaciones y desarrolladores. Este repositorio y su sucesor reorganizado, el repositorio Maven 2, pugnan por ser el mecanismo de facto de distribución de aplicaciones en Java, pero su adopción ha sido muy lenta. Maven provee soporte no solo para obtener archivos de su repositorio, sino también para subir artefactos al repositorio al final de la construcción de la aplicación, dejándola al acceso de todos los usuarios. Una caché local de artefactos actúa como la primera fuente para sincronizar la salida de los proyectos a un sistema local.

## Material

1. Todo el codigo del juego será proporcionado de forma adjunta
2. El alumno debe crear una clase que implemente la interfaz Clow.java
3. Su clase debe estar dentro de la carpeta clows del proyecto
4. En clase se dará más detalle de cómo obtener funciona el programa
5. Para la competencia se requiere que la siguiente sesión de laboratorio el alumno tenga su clase implementada y enviarla al ayudante
6. Pueden realizar pruebas con sus compañeros libremente para conocer la efectividad de su algoritmo
7. La competencia será tipo torneo de uno contra uno y otra competencia de todos contra todos.
8. La calificación de la práctica dependerá del lugar que quedaron en la competencia

## Ejecución del programa

**Se requiere tener instalado Maven**

* Compilar
```sh
$ mvn compile
```
* Ejecución
```sh
$ mvn "-Dexec.args=-classpath %classpath main.MainApp" -Dexec.executable=/usr/bin/java org.codehaus.mojo:exec-maven-plugin:1.2.1:exec
```
**Deben sustituir RUTA_JAVA por la ruta java de su computadora**

# Bibliografía
https://www.oracle.com/technetwork/articles/java/javareflection-1536171.html


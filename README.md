# Kalunga
Este repositorio contiene el código relacionado desarrollado para una aplicación móvil que permitira: controlar, gestionar y enviar dineros en pesos colombianos (COP) a bolivares (BOL)

## Tabla de contenido
1. [Información general](#general-info)
2. [Tecnología](#tecnologies)
3. [Arquitectura](#architecture)
4. [Estrategía de desarrollo](#strategy)
5. [Flujo de trabajo](#flow)
6. [Metodología de proyecto](#methodology)
7. [Funcionalidades de la aplicación](#features)
8. [Versión en producción](#versión)
9. [Instalación del proyecto](#installation)
10. [Colaboradores](#collaborators)

<a name="general-info"></a> 
### Información general
Este desarrollo móvil estará compuesto por 4 historias de usuario épicas:
 - HU00 - Core de la aplicación móvil
 - HU01 - Aplicación móvil para rol de superUsuario.
 - HU02 - Aplicación móvil para rol de administrador.
 - HU03 - Aplicación móvil para rol de terceros.
 - HU04 - Aplicación móvil para rol de usuarios.

<a name="tecnologies"></a> 
### Tecnología
Para el desarrollo de está aplicación móvil se empleará el lenguaje de programación KOTLIN v1.5.0 y su IDE de desarrollo es nativo (Android Studio 4.2.1)

<a name="architecture"></a> 
### Arquitectura
Como arquitectura de software para está aplicación se empleará MVVM + clean con el fin de soportar escalabilidad y mantenimiento del software de forma sencilla, a continuación se mencionana algunas de las ventajas de utilizar una arquitectura limpia:
 * El código está desacoplado.
 * La estructura del paquete es aún más fácil de navegar.
 * El proyecto es aún más fácil de mantener.
 * Se pueden agregar nuevas funciones rápidamente.

***Nota:** Para mayor información puede visualizar la WIKI del proyecto*


<a name="strategy"></a> 
### Estrategía de desarrollo
Como estrategía de desarrollo emplearemos TDD (Test Driven Development) que consiste en escribir primero las pruebas (generalmente unitarias), después escribir el código fuente que pase la prueba satisfactoriamente y, por último, refactorizar el código escrito. A continuación se describen algunas ventajas de utilizar TDD:
 * Genera confianza gracias a tener pruebas que garantizan el correcto funcionamiento del código.
 * Mejora el diseño y la calidad del código
 * Evita escribir código que no se va a utilizar
 * Desarrollo más rápido y permite la optimización y evolución del código al transcurrir el tiempo.

***Nota:** Para mayor información puede visualizar la WIKI del proyecto*

# Taller 03: Sistema de Gestión de Magos y Hechizos

## Descripción del Proyecto
Este repositorio contiene el desarrollo del **Taller 03** para la asignatura de Programación orientada a objetos (ITI - ICCI - ICI). 

El sistema simula la gestión de un mundo dominado por la magia, donde existen Magos que poseen un repertorio de Hechizos elementales (Fuego, Agua, Planta y Tierra). La aplicación provee un **Panel de Administrador** para gestionar las entidades y un **Panel de Analista** que genera reportes y ránkings de poder basados en fórmulas matemáticas específicas de cada elemento.

## Características Técnicas y Arquitectura
El proyecto fue desarrollado en Java aplicando los pilares de la **Programación Orientada a Objetos (POO)** y buenas prácticas de Ingeniería de Software:

* **Arquitectura de Capas:** El código está estrictamente separado aislando el punto de entrada o Vista (`App.java`), la Lógica (`Sistema` y `SistemaImp`), y las entidades del modelo de datos (`dominio`).
* **Herencia y Clases Abstractas:** Implementación de una clase abstracta padre `Hechizo` con un método abstracto `calcularPuntuacion()`, heredada por subclases específicas (`HechizoFuego`, `HechizoAgua`, etc.) que aplican sus propios atributos únicos.
* **Interfaces y Polimorfismo:** Uso de la interfaz `Sistema` para definir el contrato de la lógica, y aplicación de polimorfismo puro (y `instanceof`) para el casteo dinámico de objetos durante la ejecución y la reescritura de archivos.
* **Persistencia de Datos Robusta:** Lectura inicial y guardado permanente del estado del sistema mediante lectura (`Scanner`) y sobreescritura (`BufferedWriter`) de archivos planos (`Magos.txt` y `Hechizos.txt`), controlando excepciones con bloques `try-catch`.
* **Colecciones y Ránkings:** Uso de `ArrayList` interconectados (catálogo global vs. mochila de magos) y expresiones Lambda con `Collections.sort()` para la generación dinámica del Top 10 de Hechizos y Top 3 de Magos.

## Estructura del Proyecto
* `datos/`: Contiene los archivos `.txt` que actúan como base de datos persistente.
* `src/dominio/`: Entidades del modelo de negocio, incluyendo la estructura de herencia de los Hechizos.
* `src/logica/`: Motor del sistema, compuesto por la interfaz `Sistema`, su implementación `SistemaImp`, y el menú de consola `App.java`.
* `Diagrama de Dominio.pdf`: Abstracción conceptual del problema.
* `Diagrama de clase.pdf`: Plano técnico UML de la arquitectura del software.

## Autor
* **Lukas Cortés Alfaro** - Estudiante de Ingeniería Civil en Computación e Informática (UCN).
* GitHub: [@ToshiLuk](https://github.com/ToshiLuk)

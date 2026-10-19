# NearSpot

NearSpot es una aplicación de escritorio desarrollada en Java cuyo objetivo es facilitar la búsqueda, localización y valoración de comercios, emprendimientos y prestadores de servicios locales.

La plataforma permite a los usuarios encontrar prestadores según diferentes criterios, consultar información detallada, visualizar calificaciones y reseñas y acceder a información relacionada con su ubicación y actividad.

Actualmente el proyecto se encuentra en una primera etapa de desarrollo y utiliza datos simulados almacenados en memoria. La integración con una base de datos será incorporada en una etapa posterior.

---

## Objetivo

Desarrollar un sistema de información orientado a la gestión, localización y valoración de la oferta comercial y de servicios locales.

NearSpot busca centralizar información de distintos actores económicos y permitir a los usuarios realizar búsquedas según criterios como:

- Rubro.
- Ubicación.
- Tipo de prestador.
- Calificación.
- Nombre.

---

## Tecnologías utilizadas

El proyecto utiliza las siguientes tecnologías:

- Java 21
- JavaFX
- FXML
- Maven
- Programación Orientada a Objetos
- Git
- GitHub

---

## Arquitectura del proyecto

El proyecto se encuentra organizado en diferentes paquetes con el objetivo de separar responsabilidades.

```text
src/main/java/com/nearspot/
│
├── Main.java
│
├── controller/
│   ├── LoginController.java
│   ├── InicioController.java
│   └── DetallePrestadorController.java
│
├── data/
│   └── DatosPrueba.java
│
├── model/
│   ├── Prestador.java
│   ├── Comercio.java
│   ├── Emprendimiento.java
│   ├── PrestadorServicio.java
│   ├── Usuario.java
│   ├── Rol.java
│   ├── Rubro.java
│   ├── Ubicacion.java
│   ├── Zona.java
│   ├── Resena.java
│   └── Calificacion.java
│
├── service/
│   ├── LoginService.java
    └── PrestadorService.java



src/main/resources/
│
└── views/
    ├── login.fxml
    ├── inicio.fxml
    └── detallePrestador.fxml
```

## Requisitos
Para ejecutar el proyecto se necesita:
- Java JDK 21
- Maven
- Git
- Visual Studio Code o cualquier IDE compatible con Java
Para Visual Studio Code se recomienda instalar: Extension Pack for Java

1. Clonar el repositorio
git clone https://github.com/olanciotti/nearspot.git

2. Ingresar a la carpeta:
cd NearSpot

3. Compilar:
mvn clean compile

4. Ejecutar la aplicación:
mvn javafx:run

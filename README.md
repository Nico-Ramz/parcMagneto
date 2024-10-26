# 🧬 Parcial 1 - Desarrollo de Software

![Java](https://img.shields.io/badge/Java-11-orange) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5.0-brightgreen) ![Database](https://img.shields.io/badge/Database-H2-blue) ![Render](https://img.shields.io/badge/Hosting-Render-purple)

Esta API permite detectar si un humano es mutante mediante el análisis de una secuencia de ADN enviada en un formato JSON. La API está hosteada en Render, donde se han configurado los servidores necesarios para manejar el tráfico esperado.

---

## 📌 Descripción del proyecto

El objetivo de esta API es analizar secuencias de ADN y determinar si pertenecen a un mutante. Para ello, se reciben cadenas de ADN en un formato específico y se aplican algoritmos para identificar patrones característicos de mutantes.

---

## 🌐 Endpoints

URL : `https://parcmagneto-1.onrender.com/`

URL para base de datos: `https://parcmagneto-1.onrender.com/h2-console/`

### 🚀 Detectar mutante
- **Método**: `POST`
- **URL**: `https://parcmagneto-1.onrender.com/mutant`

### 📈 Obtener estadísticas
- **Método**: `GET`
- **URL**: `https://parcmagneto-1.onrender.com/stats`

---

## 📥 Request

El cliente debe enviar una solicitud HTTP `POST` con el cuerpo en formato JSON utilizando la siguiente estructura:


{
  "dna": [
    "TACAAC",
    "TTGGGC",
    "ACTGAA",
    "ATTTAT",
    "TCTAGG",
    "TTAGCT"
  ]
}

## Donde:

"dna" es un array de strings, cada string representa una fila de la secuencia de ADN.


## 📤 Response

La respuesta será un código HTTP indicando si la secuencia es mutante o no:

- **200 OK**: La secuencia pertenece a un mutante.
- **403 Forbidden**: La secuencia pertenece a un humano normal.

---

## 🛠 Tecnologías utilizadas

- **Lenguaje**: Java
- **Framework**: Spring Boot
- **Base de Datos**: H2
- **Hospedaje**: Render


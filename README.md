# 🏥 Sistema de Gestión de Citas Médicas

Sistema para la gestión de citas médicas en instituciones de salud. Desarrollado con Spring Boot y una interfaz web moderna con enfoque en experiencia de usuario.

## 📋 Tabla de Contenidos

- [Características](#características)
- [Tecnologías](#tecnologías)
- [Requisitos Previos](#requisitos-previos)
- [Instalación](#instalación)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [API Endpoints](#api-endpoints)

## ✨ Características

### 🎯 Gestión de Citas Médicas (Módulo Principal)
- ✅ **Registro de nuevas citas** - Formulario intuitivo con validaciones
- ✅ **Visualización de citas** - Tabla interactiva con toda la información
- ✅ **Reprogramación de citas** - Cambio de fecha y hora en tiempo real
- ✅ **Marcado de citas atendidas** - Control del estado de las consultas
- ✅ **Cancelación de citas** - Con confirmación de seguridad
- ✅ **Filtros avanzados** - Por estado, fecha, paciente y médico
- ✅ **Búsqueda en tiempo real** - Búsqueda instantánea por paciente o médico
- ✅ **Dashboard de estadísticas** - Visualización de métricas clave

### 📊 Características Adicionales
- 📝 Gestión de pacientes
- 👨‍⚕️ Gestión de médicos
- 🎨 Interfaz moderna y responsive
- 🚀 Operaciones en tiempo real sin recargar página
- 💾 Base de datos SQLite integrada (sin configuración externa)
- 🔔 Notificaciones visuales elegantes

## 🛠️ Tecnologías

### Backend
- **Java 17+** - Lenguaje de programación
- **Spring Boot 3.x** - Framework principal
- **Spring Data JPA** - Persistencia de datos
- **Spring Web** - API REST
- **SQLite** - Base de datos embebida (se crea automáticamente)
- **Gradle** - Gestión de dependencias y construcción
- **Lombok** - Reducción de código boilerplate

### Frontend
- **HTML5** - Estructura semántica
- **Tailwind CSS 3.x** (CDN) - Estilos modernos y responsive
- **JavaScript Vanilla** - Lógica del cliente sin frameworks
- **Fetch API** - Consumo de servicios REST

## 📦 Requisitos Previos

Antes de comenzar, asegúrate de tener instalado:

```bash
# Java Development Kit (JDK) 17 o superior
java -version

# Gradle (opcional, el proyecto incluye Gradle Wrapper)
gradle -version

# Git
git --version
```

**Nota:** No necesitas instalar ni configurar ninguna base de datos. SQLite se crea automáticamente en la primera ejecución.

## 🚀 Instalación

### 1. Clonar el repositorio

```bash
git clone https://github.com/syderkkk/DAW-EVAL02-IM.git
cd DAW-EVAL02-IM
```

### 2. Verificar la configuración (Opcional)

El archivo `src/main/resources/application.properties` ya está configurado:

```properties
# Configuración del servidor
server.port=8080

# Configuración de SQLite
spring.datasource.url=jdbc:sqlite:hospital.db
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect

# Configuración de JPA (auto-genera las tablas)
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Configuración CORS (permite acceso desde el frontend)
spring.web.cors.allowed-origins=*
spring.web.cors.allowed-methods=GET,POST,PUT,PATCH,DELETE,OPTIONS
```

### 3. Construir el proyecto

```bash
# Linux/Mac
./gradlew build

# Windows
gradlew.bat build
```

### 4. Ejecutar el proyecto

```bash
# Linux/Mac
./gradlew bootRun

# Windows
gradlew.bat bootRun
```

El servidor estará disponible en: `http://localhost:8080`

**La base de datos SQLite (`hospital.db`) se creará automáticamente en la raíz del proyecto en la primera ejecución.**

### Flujo de Uso

1. **Registrar Pacientes y Médicos** (si no existen)
2. **Crear una Nueva Cita:**
    - Click en tab "Nueva Cita"
    - Selecciona paciente y médico
    - Elige fecha y hora
    - Opcionalmente agrega un motivo
    - Click en "Guardar Cita"

3. **Gestionar Citas:**
    - En tab "Lista de Citas" verás todas las citas
    - Usa los filtros para buscar citas específicas
    - Acciones disponibles:
        - ✏️ **Editar** - Reprogramar fecha/hora
        - ✅ **Atender** - Marcar como atendida
        - ❌ **Cancelar** - Cancelar la cita

4. **Ver Estadísticas:**
    - Tab "Estadísticas" muestra:
        - Total de citas
        - Citas programadas
        - Citas atendidas
        - Citas canceladas
        - Gráfico de distribución



**Nota:** También puedes usar herramientas como [DB Browser for SQLite](https://sqlitebrowser.org/) para visualizar y manipular los datos.

## 📁 Estructura del Proyecto

```
sistema-citas-medicas/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── hospital/
│   │   │           ├── controller/
│   │   │           │   ├── CitaController.java
│   │   │           │   ├── PacienteController.java
│   │   │           │   └── MedicoController.java
│   │   │           ├── model/
│   │   │           │   ├── Cita.java
│   │   │           │   ├── Paciente.java
│   │   │           │   └── Medico.java
│   │   │           ├── repository/
│   │   │           │   ├── CitaRepository.java
│   │   │           │   ├── PacienteRepository.java
│   │   │           │   └── MedicoRepository.java
│   │   │           ├── service/
│   │   │           │   ├── CitaService.java
│   │   │           │   ├── PacienteService.java
│   │   │           │   └── MedicoService.java
│   │   │           ├── config/
│   │   │           │   └── DataLoader.java
|   |   |           |   └── WebConfig.java
│   │   │           └── HospitalApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   │           └── index.html # Frontend principal
│   └── test/
│       └── java/
│           └── com/
│               └── hospital/
│                   └── HospitalApplicationTests.java
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── build.gradle               # Configuración de Gradle
├── gradlew                    # Gradle Wrapper (Linux/Mac)
├── gradlew.bat               # Gradle Wrapper (Windows)
├── settings.gradle
├── hospital.db               # Base de datos SQLite (se crea automáticamente)
├── .gitignore
└── README.md
```

## 🔌 API Endpoints

### 📅 Citas (Módulo Principal)

| Método | Endpoint | Descripción | Body Example |
|--------|----------|-------------|--------------|
| GET | `/api/citas` | Listar todas las citas | - |
| GET | `/api/citas/{id}` | Obtener cita por ID | - |
| POST | `/api/citas` | Crear nueva cita | Ver abajo ⬇️ |
| PATCH | `/api/citas/{id}/reprogramar?fecha={fecha}&hora={hora}` | Reprogramar cita | - |
| PATCH | `/api/citas/{id}/atender` | Marcar cita como atendida | - |
| PATCH | `/api/citas/{id}/cancelar` | Cancelar cita | - |
| DELETE | `/api/citas/{id}` | Eliminar cita permanentemente | - |

**Body para crear cita:**
```json
{
  "paciente": {
    "idPaciente": 1
  },
  "medico": {
    "idMedico": 1
  },
  "fecha": "2025-10-25",
  "hora": "09:00",
  "motivo": "Consulta general",
  "estado": "programada"
}
```

### 👥 Pacientes

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/pacientes` | Listar todos los pacientes |
| GET | `/api/pacientes/activos` | Listar solo pacientes activos |
| GET | `/api/pacientes/{id}` | Obtener paciente por ID |
| POST | `/api/pacientes` | Crear nuevo paciente |
| PUT | `/api/pacientes/{id}` | Actualizar paciente |
| DELETE | `/api/pacientes/{id}` | Eliminar paciente (soft delete) |

### 👨‍⚕️ Médicos

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/medicos` | Listar todos los médicos |
| GET | `/api/medicos/activos` | Listar solo médicos activos |
| GET | `/api/medicos/{id}` | Obtener médico por ID |
| POST | `/api/medicos` | Crear nuevo médico |
| PUT | `/api/medicos/{id}` | Actualizar médico |
| DELETE | `/api/medicos/{id}` | Eliminar médico (soft delete) |

### Error: "Port 8080 already in use"

```bash
# Opción 1: Cambiar puerto en application.properties
server.port=8081

# Opción 2: Liberar el puerto
# Linux/Mac
lsof -ti:8080 | xargs kill -9

# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F
```

### Frontend no se conecta al backend

- Verifica que el backend esté corriendo en `http://localhost:8080`
- Revisa la consola del navegador (F12) para ver errores CORS
- Asegúrate que la URL de API en `index.html` sea correcta:
  ```javascript
  const API_URL = 'http://localhost:8080/api';
  ```

### Base de datos corrupta

```bash
# Elimina la base de datos y deja que se recree
rm hospital.db
./gradlew bootRun
```

## 👥 Autor

- **Italo Mendoza** 

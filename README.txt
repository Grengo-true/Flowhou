# Flowhou Game - README

## Descripción del Proyecto

Flowhou Game es un juego 2D de estilo bullet-hell desarrollado con libGDX. El jugador controla un personaje que debe disparar y evitar enemigos que aparecen en oleadas. El proyecto implementa patrones de diseño como Singleton, Template Method, Strategy y Abstract Factory.

## Requisitos del Sistema

1. **Java Development Kit (JDK)**
   - Versión mínima: JDK 8
   - Descargar desde: https://www.oracle.com/java/technologies/downloads/

2. **Eclipse IDE**
   - Con plugin Buildship Gradle Integration
   - Descargar desde: https://www.eclipse.org/downloads/

## Instalación

1. Descargar el proyecto como ZIP desde el repositorio
2. Extraer en una carpeta local (ej: `C:\proyectos\flowhou-game`)

## Ejecución del Proyecto

### Usando Eclipse IDE

1. **Abrir Eclipse**

2. **Importar el proyecto:**
   - Ir a `File` → `Import`
   - Seleccionar `Gradle` → `Existing Gradle Project`
   - Click en `Next`

3. **Seleccionar directorio:**
   - Click en `Browse`
   - Navegar a la carpeta donde se extrajo el proyecto
   - Click en `Finish`

4. **Esperar sincronización:**
   - Eclipse descargará las dependencias automáticamente
   - Esto puede tomar 2-5 minutos la primera vez
   - Observar la barra de progreso en la esquina inferior derecha

5. **Ejecutar el juego:**
   - En el explorador de proyectos, click a run configurations.
   - Java Application - Flowhou-lwjgl3
   - Main Class flowhou.game.lwjgl3.Lwjgl3Launcher
   - Arguments - Working Directory - Other ${workspace_loc:Flowhou-parent/assets}
   - Run


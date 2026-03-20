# Animal Match - Juego de Memoria 🦊

¡Bienvenido a **Animal Match**! Una aplicación Android moderna diseñada para entrenar tu memoria mientras te diviertes encontrando parejas de animales. 

Este proyecto fue desarrollado utilizando las últimas tecnologías de Android, siguiendo patrones de diseño limpios y una interfaz de usuario atractiva basada en una paleta de colores azul y blanco.

## 🚀 Características

- **Diseño Moderno**: Interfaz limpia con degradados, elevaciones y una experiencia de usuario fluida.
- **Validación de Datos**: Sistema de ingreso de usuario con validaciones en tiempo real.
- **Mecánica de Juego**:
    - 16 tarjetas (8 parejas de animales).
    - Límite de 16 intentos para completar el tablero.
    - Sistema de puntuación dinámico.
- **Navegación Segura**: Implementación de *Type-Safe Navigation* de Jetpack Compose.
- **Feedback Visual**: Diálogo de instrucciones interactivo y barras de progreso para los intentos restantes.

## 🛠️ Tecnologías Utilizadas

- **Kotlin**: Lenguaje de programación principal.
- **Jetpack Compose**: Para la construcción de la interfaz de usuario declarativa.
- **MVVM (Model-View-ViewModel)**: Arquitectura para separar la lógica de negocio de la UI.
- **StateFlow & MutableState**: Para el manejo reactivo del estado del juego.
- **Kotlinx Serialization**: Para la navegación segura entre pantallas.
- **Material Design 3**: Componentes y sistema de diseño moderno.

## 📱 Pantallas

1.  **Home (Inicio)**: Bienvenida al usuario, validación de nombre y acceso a las instrucciones del juego.
2.  **Game (Juego)**: El tablero principal de 4x4 donde ocurre la magia.
3.  **Results (Resultados)**: Pantalla final que muestra si ganaste o perdiste, tu puntaje y la opción de revancha.

## 🎮 Reglas del Juego

- El objetivo es encontrar las **8 parejas** escondidas.
- Tienes un máximo de **16 intentos**. Por cada par incorrecto, se resta un intento.
- El juego termina si te quedas sin intentos o si encuentras todas las parejas.
- ¡Tu puntaje final reflejará tu precisión en el tablero!

## 📦 Estructura del Proyecto

- `core/`: Componentes reutilizables, utilidades y configuración de navegación.
- `features/`: Lógica y UI dividida por funcionalidades (Home, Game, Results).
- `ui/theme/`: Definición de colores, tipografías y tema global de la app.

---

# WeatherApp

[![Kotlin](https://img.shields.io/badge/kotlin-2.1.0-blueviolet?logo=kotlin&label=kotlin)](http://kotlinlang.org) [![Gradle](https://img.shields.io/badge/gradle-8.7.3-02303a?logo=gradle&logoColor=1bacca&label=gradle)](https://developer.android.com/studio/releases/gradle-plugin)

### Descripción

Aplicación Android en Kotlin utilizando [MVVM](https://developer.android.com/jetpack/guide?hl=es-419#recommended-app-arch) (Model - View - ViewModel). Algunos de los componentes y librerías utilizados en este proyecto son los siguientes:

Android y [Jetpack](https://developer.android.com/jetpack?hl=es-419):

* [Jetpack Compose](https://developer.android.com/compose)
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata?hl=es-419)
* [Flow](https://developer.android.com/kotlin/flow?hl=es-419)
* [Coroutines](https://developer.android.com/kotlin/coroutines?hl=es-419)
* [Dagger/Hilt](https://developer.android.com/training/dependency-injection/hilt-android?hl=es-419)
* [Room](https://developer.android.com/training/data-storage/room?hl=es-419)

Librerías externas:

* [Coil](https://github.com/coil-kt/coil)
* [Retrofit](https://square.github.io/retrofit/)
* [Gson](https://github.com/google/gson)

### Capturas de pantalla

<img src="/docs/img1.png" height="480"> <img src="/docs/img2.png" height="480"> <img src="/docs/img3.png" height="480">

<img src="/docs/img4.png" height="480">

### Video Demo

N/A

### Guía de estilos

En el diseño de la app se siguieron los lineamientos de [Material Design](https://material.io/).

### Descarga del proyecto y otros recursos

El proyecto puedes clonarlo o descargarlo si lo deseas.

### Compilación y ejecución

Para compilar y ejecutar este proyecto, sigue estos pasos:

1.  **Clona el repositorio:**  
    git clone https://github.com/rolandoselvera/WeatherApp.git
    cd WeatherApp

2. **Abre el proyecto en Android Studio:**
* Asegúrate de tener instalado Android Studio con una versión compatible con el proyecto ([Android Studio Koala](https://developer.android.com/studio/releases/past-releases/as-koala-release-notes) o superior).
* Abre el proyecto y sincroniza Gradle si es necesario. **IMPORTANTE:**
    * La versión de Java utilizada para compilar el proyecto es la 17.
    * La versión de JDK Gradle que se utilizó en el proyecto es la 21.0.3. Puedes configurar esta opción en  `File > Settings > Build, Execution, Deployment > Build Tools > Gradle > Gradle JDK` y seleccionar esa versión o descargarla en ese menú.

3. **Configura la clave API:**
* Obtén tu clave de API gratuita en [WeatherAPI](https://www.weatherapi.com/docs/).
* Abre el archivo `local.properties` que se encuentra en el directorio raíz del proyecto. Si no existe, puedes crearlo con ese mismo nombre.
* Añade la siguiente línea, reemplazando `YOUR_API_KEY` con tu clave de API:
    * API_KEY=YOUR_API_KEY

4. **Ejecutar la aplicación:**
* Conecta un dispositivo Android físico o inicia un emulador.
* Haz clic en el botón **Run** o usa el atajo **Shift + F10** para compilar y ejecutar la aplicación.

### Contacto del desarrollador

Cualquier duda o comentario, [contáctame vía email](mailto:rolando.selvera3@gmail.com).

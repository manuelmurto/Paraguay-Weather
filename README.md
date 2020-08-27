# Paraguay-Weather

Autor: Manuel Murto

Esta aplicación fue desarrollada en Android Studio (4.0.1) con Java, compilado con el SDK 29.



Features que funciona:
  -Efectivamente, al inicio de la App se listan las 5 ciudades requeridas en el punto (a.)
  -Seleccionando una ciudad, se pueden ver los detalles actuales de la temperatura en esa ciudad.
  -Pantalla "detalle de temperatura": Entre los detalles de temperatura se lista lo siguiente: Nombre de la ciudad y pais, temperatura actual, sensacion termica, temperatura minima y temperatura maxima
  -Por defecto las temperaturas se muestran en Celsius.
  -Selector de unidades: en la parte inferior de la pantalla "Detalle de temperatura" tenemos un spinner que nos permite seleccionar en que unidad queremos ver la temperatura
  -Se puede visualizar la temperatura (obtenida desde la API de openweathermap) en Celsius, Kelvin y Fahrenheit.
  -Esto se puede repetir para cada uno de las 5 ciudades.
  -Finalmente, la api key no se sube al repositorio de manera plana. Se encuentra incluido en el gradle build:app.
  
Features que no funcionan y problemas:
  -No se listan todas las ciudades del Paraguay que contiene la api de OpenWeather.
  -Error de truncamiento al realizar las conversiones de unidades de temperatura.
  -Al ingresar a la patanlla de "Detalle de clima", los datos demoran 1 a 1,5 segs en cargar.
  -

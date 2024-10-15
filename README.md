
<h1 align="center"> CONVERSOR DE MONEDAS </h1>
<h2>DESCRIPCIÓN</h2>
Este proyecto es una aplicación de consola en Java que permite convertir montos de una moneda a otra utilizando la tasa de cambio actual obtenida de una API externa.
El usuario puede seleccionar las monedas a convertir y la cantidad, y el sistema devolverá el valor convertido.
<h2>CARACTERISTICAS</h2>

•	Soporte para múltiples monedas - Consulta de tasas de cambio actualizadas desde una API externa. <br>
•	Manejo de errores en caso de tasas de cambio no disponibles o datos inválidos <br>
•	Interfaz de usuario sencilla mediante consola<br>
<h2>REQUISITOS</h2>
Para poder ejecutar este proyecto, necesitas tener instalados los siguientes programas y librerías:<br>
Java 11 o superior<br>
Gson Librería para convertir objetos JSON en Java <br>
HttpClient: Incluido en Java 11 para realizar solicitudes HTTP<br>

<h2>API UTILIZADA</h2>
Este proyecto utiliza la API de Exchange Rate API para obtener tasas de cambio de divisas actualizadas. Asegúrate de contar con una clave de API válida. Las tasas de cambio se almacenan temporalmente para minimizar consultas repetidas.

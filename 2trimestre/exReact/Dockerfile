# Usa una imagen de Java (JDK 17)
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo .jar al contenedor
COPY tu-archivo.jar app.jar

# Expone el puerto (si tu app usa uno, por ejemplo 8080)
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]

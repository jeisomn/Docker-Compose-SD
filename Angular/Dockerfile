# Utilizar una imagen base de Node.js con una versión compatible con Angular CLI
FROM node:latest AS build

# Establecer el directorio de trabajo
WORKDIR /Angular

# Copiar los archivos de la aplicación Angular
COPY package.json package-lock.json ./

# Instalar las dependencias
RUN npm install

COPY . .

# Construir la aplicación Angular
RUN npm run build --prod

# Usar una imagen base de Nginx para servir la aplicación
FROM nginx:alpine

# Copiar los archivos de construcción desde la etapa anterior
COPY --from=build Angular/dist/prueba /usr/share/nginx/html

# Exponer el puerto en el que Nginx servirá la aplicación
EXPOSE 80

# Iniciar Nginx
CMD ["nginx", "-g", "daemon off;"]

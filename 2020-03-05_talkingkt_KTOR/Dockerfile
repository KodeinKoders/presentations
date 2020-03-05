# BUILD
FROM adoptopenjdk/openjdk11:alpine AS build
MAINTAINER Romain Boisselle <romain@boisselle.dev>

RUN mkdir -p /app
COPY . /app
WORKDIR /app
RUN ./gradlew build -x test

# DEPLOY
FROM nginx:alpine
EXPOSE 80
COPY --from=build /app/build/web/ /usr/share/nginx/html/
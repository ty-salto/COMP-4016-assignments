FROM node:20.17.0
MAINTAINER ty-salto
# COPY package*.json .
COPY . .
RUN npm install
# COPY . .
ENTRYPOINT ["node", "index.js"]
EXPOSE 8080
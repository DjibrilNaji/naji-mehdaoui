# WIP

FROM node:20

WORKDIR /app
COPY ../../front/package*.json ./
RUN npm install
COPY . .
EXPOSE 3000
CMD npm run dev
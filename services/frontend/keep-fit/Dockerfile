FROM node:13.12.0-alpine

WORKDIR /app
COPY package.json .
COPY package-lock.json .
ENV PATH /app/node_modules/.bin:$PATH
RUN npm install

COPY . .

CMD ["npm", "start"]

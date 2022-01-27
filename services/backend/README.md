# Backend services

# Description 

> Before running the Application stack and creating the Docker images please check each image requirements.

This folder contains backend services implementation. It also contains the Docker compose file to create the Application stack.

Before running Docker compose configuration, build the Docker images from corresponding Dockerfiles.

Start the docker compose stack with the following command.
```
    docker-compose up
or
    docker-compose up -d
```

Running docker compose CLI command with -d parameter, starts the process in detached mode.

Consider that you need to run this command in the terminal, under the _services/backend_ folder.

For building the Docker images you need to run this command under each service folder.

```
    docker build -t <container_name> .
```
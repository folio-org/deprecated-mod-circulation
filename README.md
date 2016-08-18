# circulation

This documentation currently only includes information about running the dockerfile

### Running via docker

Build:

`docker build --build-arg git_user=<git user> --build-arg  git_password=<git password> -t "apis" .`

Run

`docker run -it -p 8081:8081  apis`


Note in AWS you will need to open port 8081 in a security group

To access the running container: (replace the id below with the id of the running container - can be found by running `docker ps`)

`docker exec -t -i 2fc63df52141 /bin/sh `

The container will

- pull an alpine + java docker image
- download and install maven
- clone the lsp-apis-impl repo
- download filebeat to push logs to a central logstash
- copy the supervisord config file into the container
- run the supervisord - this will use the supervisord.conf file to run the circ and filebeat processes


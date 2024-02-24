FROM maven:3-openjdk-15-slim
# Download dependencies
# Execute the java application
# Reload the java application at file system changes
# Wait for mongo db readiness before starting the application


#inotify è un servizio del kernel linux che ci permette di rilevare variazioni nel file
#system ed inotify-tools sono command line programs che forniscono l'interfaccia ad inotify
RUN apt-get -y update && apt-get -y install inotify-tools wget gnupg2 && \
    wget -qO - https://www.mongodb.org/static/pgp/server-4.4.asc | apt-key add - && \
    echo "deb http://repo.mongodb.org/apt/debian buster/mongodb-org/4.4 main" | \
    tee /etc/apt/sources.list.d/mongodb-org-4.4.list && apt-get -y update && \
    apt-get -y install mongodb-org-shell && apt-get clean && rm -rf /var/cache/apt/*

WORKDIR /app

#copio tutto quello che c'è nella docker/root, nella root stessa del container
# così che mi replico il file system per come lo voglio
COPY docker/root/ /
RUN chmod 755 /usr/local/bin/*.sh

# Download dependencies
# copio il file delle dipendenze ed eseguo il package-manager per risolverle
COPY pom.xml ./
RUN mvn dependency:resolve

VOLUME /app

#ENTRYPOINT: instruction used to configure how the container will run. We need to specify a command and parameters.
ENTRYPOINT ["sh", "-c" ]
CMD ["/usr/local/bin/reloader.sh"]

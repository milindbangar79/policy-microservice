FROM centos
RUN yum install -y java
VOLUME /tmp
ADD policyMicroservice-0.0.1-SNAPSHOT.jar custservice.jar
ENTRYPOINT ["java","-jar","-Dserver.port=8080","/custservice.jar"]
EXPOSE 8080
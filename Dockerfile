FROM centos:7
RUN yum install -y java
VOLUME /tmp
ADD ./target/policyservice-0.0.1-SNAPSHOT.jar policyservice.jar
ENTRYPOINT ["java","-jar","-Dserver.port=8080","/policyservice.jar"]
EXPOSE 8080
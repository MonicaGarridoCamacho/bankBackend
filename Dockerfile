FROM jenkins/jenkins:lts-jdk11
USER root
RUN apt update
USER jenkins
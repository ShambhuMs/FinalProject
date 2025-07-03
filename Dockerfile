# Stage 1: Build the WAR with Maven
FROM maven:3.8.1-openjdk-8 AS builder

WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Build the WAR
RUN mvn clean package

# Stage 2: Prepare Tomcat and deploy the WAR
FROM tomcat:9.0.38-jdk8-openjdk

# Remove default apps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the WAR file from the builder stage
COPY --from=builder /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Expose the port
EXPOSE 8080

# Set JVM memory options to avoid memory limit issues (adjust as needed)
ENV JAVA_OPTS="-Xms128m -Xmx256m"

# Start Tomcat
CMD ["catalina.sh", "run"]

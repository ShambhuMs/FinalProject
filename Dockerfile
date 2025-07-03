# Use Tomcat 9.0.38 with Java 8
FROM tomcat:9.0.38-jdk8-openjdk

# Clean default apps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy your WAR file into ROOT.war
COPY target/FinalProject.war /usr/local/tomcat/webapps/ROOT.war

# Expose port for Render
EXPOSE 8080

# Run Tomcat
CMD ["catalina.sh", "run"]
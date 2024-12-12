# Base image for Java application
FROM openjdk:17-jdk AS app-build

# Set build argument for JAR file
ARG JAR_FILE=target/*.jar

# Copy the JAR file to the image
COPY ${JAR_FILE} app.jar

# Add Microsoft SQL Server setup (version 2019)
FROM mcr.microsoft.com/mssql/server:2019-latest AS sql-server

# Set environment variables for MSSQL
ENV ACCEPT_EULA=Y
ENV SA_PASSWORD=YourStrong@Password123  # Default SA password
ENV MSSQL_TCP_PORT=1434

# Copy custom SQL script to create user
COPY init-sql.sql /docker-entrypoint-initdb.d/

# Expose SQL Server port
EXPOSE 1434

# Combine both stages
FROM openjdk:17-jdk

# Copy the application from the first stage
COPY --from=app-build app.jar app.jar

# Copy the SQL Server setup
COPY --from=sql-server /opt /opt

# Set environment variables for MSSQL in the final stage
ENV ACCEPT_EULA=Y
ENV SA_PASSWORD=YourStrong@Password123
ENV MSSQL_TCP_PORT=1434

# Expose the application and SQL Server ports
EXPOSE 8080 1434

# Set the entry point for the application
ENTRYPOINT ["java", "-jar", "/app.jar"]

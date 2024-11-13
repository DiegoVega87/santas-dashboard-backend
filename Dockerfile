FROM eclipse-temurin:17


ENV DATABASE_URL jdbc:mysql://localhost:3306/dashboard
ENV DATABASE_USERNAME team2
ENV DATABASE_PASSWORD 123456
ENV DATABASE_PLATFORM org.hibernate.dialect.MySQL57Dialect
ENV DATABASE_DRIVER com.mysql.cj.jdbc.Driver

#Previamente realizar un mvn clean package
COPY target/santas-dashboard-backend-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
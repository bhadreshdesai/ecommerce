package bdd.demo.ecommerce;

import org.testcontainers.utility.DockerImageName;

public class Constants {
    public static final DockerImageName MYSQL_57_IMAGE = DockerImageName.parse("mysql:5.7");

    public static final DockerImageName MONGODB_42_IMAGE = DockerImageName.parse("mongo:4.2");
}

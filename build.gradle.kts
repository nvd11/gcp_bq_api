plugins {
    // to use Gradle built-in java plugin
    /**
     * By applying the java plugin, you gain the following features and tasks:
     * Default source code directory: src/main/java is the default directory for Java source code. You can place your Java source code in this directory.
     * Default resource directory: src/main/resources is the default directory for project resources in a Java project. You can place the required resource files, such as configuration files or property files, in this directory.
     * Default compilation task: The compileJava task is used to compile Java source code.
     * Default test task: The test task is used to execute Java unit tests.
     * Default JAR task: The jar task is used to build an executable JAR file.
     * Default documentation task: The javadoc task is used to generate Java documentation.
     */
    java

    // to use Spring Boot plugin, for  to support spring boot project's build and management
    id("org.springframework.boot") version "3.3.0"

    // to use Spring Dependency Management plugin, for managing dependencies
    id("io.spring.dependency-management") version "1.0.11.RELEASE"

    // it's for publishing the project to Maven repository (Local or Remote)
    id("maven-publish")

    id("io.freefair.lombok") version "8.6"



}

publishing {
    publications {
        create<MavenPublication>("library") {
            groupId = "com.home"
            artifactId = "bq-api-service"
            version = "0.0.1-SNAPSHOT"
            from(components["java"])
        }
    }
}

// TO define the repositories to use, please use maven repo instead of gradle repo, much faster in China
repositories {
    // to use local maven repository
    // if the library exists in the local maven repository, it will be used
    // else it will be downloaded from the remote maven repository into gradle cache folder ~/.gradle/caches
    mavenLocal()
    mavenCentral()
}

// TO define the group, version
group = "com.home"
version = "0.0.1-SNAPSHOT"

// TO define the Java version to use
java {
    sourceCompatibility = JavaVersion.VERSION_17
}

// let compileOnly configuration extends from annotationProcessor configuration
// then, the dependencies in annotationProcessor configuration will be available in compileOnly configuration
// will be excluded from the final jar file
configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}


var googleApiVersion= "1.32.1"
var springCloudGcpVersion= "1.2.8.RELEASE"
var openFeignVersion= "3.1.9"
var feignHttpClientVersion= "11.6"
var springBootVersion= "3.3.0"

// referring https://docs.gradle.org/current/userguide/declaring_repositories.html
// To define the dependencies to use, please use implementation instead of compile, compile is deprecated
dependencies {
    // Spring Boot Starter Web, for building web applications
    implementation("org.springframework.boot:spring-boot-starter-web")

    // compileOnly is used for dependencies that are necessary to compile the project, but should not be included in the final jar file
    // equivalent to <scope>provided</scope> in Maven
    // by default the version number is not specified, it will be controlled by the plugin io.spring.dependency-management
    // do not use single !, otherwise it will cause the error:
    //   > Could not resolve all files for configuration ':compileClasspath'.
    //   > Could not find org.projectlombok:lombok:1.18.32!.
    // !! means force to use the specified version and ignore any conflict, if not found, throw an error
    compileOnly("org.projectlombok:lombok")
    testCompileOnly("org.projectlombok:lombok")

    // for gradle to recognize the lombok annotation, we have to add the lombok annotation processor
    annotationProcessor("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")


    // Spring Boot Starter Test, for testing Spring Boot applications
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // actuator, the monitoring and management of the application
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // google api client
    // for to call Google BigQuery/Bucket.. API
    implementation("com.google.api-client:google-api-client:${googleApiVersion}")

    implementation("com.google.cloud:google-cloud-bigquery")


    // spring cloud gcp starter bigquery
    // for to call Google BigQuery API
    // for bigQuery library, it cannot be managed by plugin io.spring.dependency-management, we have to specify the version number
    implementation("org.springframework.cloud:spring-cloud-gcp-starter-bigquery:${springCloudGcpVersion}!!")

    // open feign, for to call REST API
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:${openFeignVersion}!!")

    // feign-httpclient, for to use HttpClient in Feign
    implementation("io.github.openfeign:feign-httpclient:${feignHttpClientVersion}!!")

    //slf4j, for logging
    implementation("org.springframework.boot:spring-boot-starter-logging")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:${springBootVersion}")
    }
}

tasks.test {
    useJUnitPlatform()
}


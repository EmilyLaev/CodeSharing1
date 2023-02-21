package platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CodeSharingPlatform {

    public static void main(String[] args) {
        SpringApplication.run(CodeSharingPlatform.class, args);
    }

}

    buildscript {
        apply plugin: 'hyperskill'

        repositories {
            mavenCentral()
        }
        dependencies {
            classpath "org.springframework.boot:spring-boot-gradle-plugin:$hs.spring.bootVersion"
            classpath "io.spring.gradle:dependency-management-plugin:$hs.spring.dependencyManagementVersion"
        }
    }

    apply plugin: 'java'
        apply plugin: 'org.springframework.boot'
        apply plugin: 'io.spring.dependency-management'

        repositories {
        mavenCentral()
        }

        sourceSets.main.resources.srcDirs = ["src/resources"]

        dependencies {
        implementation 'org.springframework.boot:spring-boot-starter'
        implementation 'org.springframework.boot:spring-boot-starter-actuator'
        implementation 'org.springframework.boot:spring-boot-starter-web'
        }
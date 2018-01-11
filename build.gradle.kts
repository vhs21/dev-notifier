import org.junit.platform.gradle.plugin.JUnitPlatformExtension
import org.junit.platform.gradle.plugin.JUnitPlatformPlugin

buildscript {
    repositories {
        jcenter()
    }

    dependencies {
        classpath("org.junit.platform:junit-platform-gradle-plugin:1.0.1")
    }
}

plugins {
    kotlin("jvm") version ("1.1.51")

    id("org.jenkins-ci.jpi") version ("0.22.0")
}
apply<JUnitPlatformPlugin>()

repositories {
    jcenter()
}

val kotlinVersion by project
val thymeleafVersion by project
val javaxMailVersion by project
val junitVersion by project

val jenkinsMailerPluginVersion by project
val jenkinsCredentialsPluginVersion by project
val jenkinsWorkflowStepsAPIPluginVersion by project

val jenkinsCoreVersion by project

dependencies {
    compile(kotlin("stdlib-jre8", "${kotlinVersion}"))
    compile("com.sun.mail:javax.mail:${javaxMailVersion}")
    compile("org.thymeleaf:thymeleaf:${thymeleafVersion}")
    compile("org.jenkins-ci.main:jenkins-core:${jenkinsCoreVersion}")
    compile("javax.servlet:javax.servlet-api:3.0.1")

    jenkinsPlugins("org.jenkins-ci.plugins.workflow:workflow-api:2.16@jar")
    jenkinsPlugins("org.jenkins-ci.plugins.workflow:workflow-job:2.16@jar")
    jenkinsPlugins("org.jenkins-ci.plugins:mailer:$jenkinsMailerPluginVersion@jar")
    jenkinsPlugins("org.jenkins-ci.plugins:credentials:$jenkinsCredentialsPluginVersion@jar")
    jenkinsPlugins("org.jenkins-ci.plugins.workflow:workflow-step-api:$jenkinsWorkflowStepsAPIPluginVersion@jar")

    testCompile("org.powermock:powermock-module-junit4:1.6.5")
    testCompile("org.powermock:powermock-api-mockito:1.6.5")
    testCompile("junit:junit:4.4")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

jenkinsPlugin {
    displayName = "Jarvis"
    shortName = "jarvis"
    gitHubUrl = "https://github.com/madhead/jarvis"

    coreVersion = jenkinsCoreVersion as String?
    compatibleSinceVersion = coreVersion
    fileExtension = "jpi"
    pluginFirstClassLoader = false
}

task<Wrapper>("wrapper") {
    gradleVersion = "4.3"
    distributionType = Wrapper.DistributionType.ALL
}
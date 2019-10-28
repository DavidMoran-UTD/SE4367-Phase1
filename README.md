# SE4367-Phase1
 Use ASM byte-code manipulation framework [1] to build an automated coverage collection tool that can capture the statement coverage for the program under test. Then, apply your tool to 10 real-world Java projects (>1000 lines of code) with JUnit tests (>50 tests) from GitHub [2] to collect the statement coverage for its JUnit tests. Note that your tool should (1) use Java Agent [3] to perform on-the-fly code instrumentation, (2) be able to store the coverage for each test method in the file system, and (3) be integrated with the Maven build system [4] so that your tool can be triggered by simply typing “mvn test” after changing the pom.xml file of the project under test. More implementation details are shown in the appendix. 
There are 4 steps that need to be completed in order for the project to be able to run successfully. 
The first thing we need to do is run "mvn clean" and "mvn install" on the project.
Next, we need to add our project as a dependency to the project we want to gather the code coverage for. 
1. We first need to add the jar file from our project as a dependency in the pom.xml of the target project that we want to gather statement coverage for. 
```xml
<dependency>
    <groupId>CodeCoverage</groupId>
    <artifactId>code-coverage</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

2. We then need to add the agent and the listener to the plug ins.  Simply add the following code to pom.xml and replace [your-agent-jar] with the absolute path to your Java agent jar file, and [your-project-name] with the name of the project.  

```xml
         <plugin>
            <!-- https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-surefire-plugin -->
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <configuration>
                <argLine>-javaagent:[your-agent-jar]=[name-of-project]=[your-project-name]</argLine>
                <properties>
                    <property>
                        <name>listener</name>
                        <value>org.joda.time.Listener</value>
                    </property>
                </properties>
            </configuration>
        </plugin>
```



We are now ready to test.  We simply need to run the command "mvn test", and the program should automatically collect code coverage for the project.  The coverage data will be output in a file called "stmt-cov.txt".  The statement coverage for all of the test methods will be included in this file.  The test methods will have [TEST] as a prefix on the line, and the other lines are the will be the methods covered by that test method. 

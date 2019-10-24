# SE4367-Phase1
 Use ASM byte-code manipulation framework [1] to build an automated coverage collection tool that can capture the statement coverage for the program under test. Then, apply your tool to 10 real-world Java projects (>1000 lines of code) with JUnit tests (>50 tests) from GitHub [2] to collect the statement coverage for its JUnit tests. Note that your tool should (1) use Java Agent [3] to perform on-the-fly code instrumentation, (2) be able to store the coverage for each test method in the file system, and (3) be integrated with the Maven build system [4] so that your tool can be triggered by simply typing “mvn test” after changing the pom.xml file of the project under test. More implementation details are shown in the appendix. 
There are 4 steps that need to be completed in order for the project to be able to run successfully. 
1. We first need to add the agent and the listener to the plug ins.  Simply add the following code to pom.xml, and replace [your-agent's-path.jar] with the absolute path to the java agent for the project we are testing coverage on, and replace [your-JUnit-listener] with the name of the JUnit lister for the project we are testing coverage on.
             <myxml>
             <plugin>
             <groupId>org.apache.maven.plugins</groupId>
             <artifactId>maven-surefire-plugin</artifactId>
             <configuration>
             <argLine>-javaagent:[your-agent's-path.jar]</argLine>
             <properties>
             <property>
             <name>listener</name>
             <value>[your-JUnit-listener]</value>
             </property>
             </properties>
             </configuration>
             </plugin>
             </myxml>
2. We then need to add the agent.jar file as a dependency so it can be used by the Listener.  Replace [your-agent.jar] with the name of your agent's jar file and update the version if necessary. 
             <myxml>
             <dependency>
             <artifactId>TestCompetition.JavaAgent</artifactId>
             <groupId>[your-agent.jar]</groupId>
             <version>1.0</version>
             <scope>system</scope>
             <systemPath>${basedir}/[your-agent.jar]</systemPath>
             </dependency>
             </myxml>
3. We then need to add the asm library to the dependencies as it is used throughout the project.  
             <myxml>
             <dependency>
             <groupId>org.ow2.asm</groupId>
             <artifactId>asm</artifactId>
             <version>5.0.3</version>
             </dependency>
             </myxml>
4. Finally, we need to add the JUnit package as a dependency
             <myxml>
             <dependency>
             <groupId>junit</groupId>
             <artifactId>junit</artifactId>
             <version>4.11</version>
             <scope>test</scope>
             </dependency>
             </myxml>
We are now ready to test.  We simply need to run the command "mvn test", and the program should automatically collect code coverage for the project.  The coverage data will be output in a file called "stmt-cov.txt".  The statement coverage for all of the test methods will be included in this file.  The test methods will have [TEST] as a prefix on the line, and the other lines are the will be the methods covered by that test method. 

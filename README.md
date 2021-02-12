# RoboFlask

### IntelliJ build component:
```xml
<component name="RunManager">
    <configuration name="Debug" type="JarApplication">
        <option name="JAR_PATH" value="$PROJECT_DIR$/target/RoboFlask-1.0.jar" /> <!--CHANGE THIS DEPENDING ON VERSION/NAME IN SPECIFIED POM.XML-->
        <option name="ALTERNATIVE_JRE_PATH" />
        <method v="2">
            <option name="Maven.BeforeRunTask" enabled="true" file="$PROJECT_DIR$/pom.xml" goal="clean package" />
        </method>
    </configuration>
</component>
```
Paste this into your `.idea/workspace.xml` replacing the current component named **RunManager** or adding it under the `<project>` tag.
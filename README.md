**How to compile Class in cmd:**
  1. javac -d out Main.java gui/*.java
  2. java -cp out Main

**How to make a JAR File:**
  1. jar cfm BinaryConverter.jar MANIFEST.MF -C out .

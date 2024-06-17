echo "Running Build Batch File"
set PATH=C:\Program Files\Java\jdk-17\bin;%PATH%
set JAVA_HOME=C:\Program Files\Java\jdk-17\bin;%JAVA_HOME%

REM go back to parent folder
cd.. 
REM enter source file
cd src
REM compile code
javac Flowers.java
java Flowers

javap -verbose Flowers.class

javap -cp ./bin Flowers > doc\ByteCode.txt

PAUSE
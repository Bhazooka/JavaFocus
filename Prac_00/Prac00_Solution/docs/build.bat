@ECHO OFF
REM Mr. A. Maganlal
REM Computer Science 2A 2023
REM Set path for JDK
SET JAVA_HOME="C:\jdk-17"
SET PATH=%JAVA_HOME%\bin;%PATH%;

REM Set Variables
cd ..
set PRAC_BIN=.\bin
set PRAC_DOCS=.\docs
set PRAC_SRC=.\src

REM Clean old class files in bin
del %PRAC_BIN%\*.class

REM Compiling code
ECHO Trying to compile
javac -sourcepath %PRAC_SRC% -cp "%PRAC_BIN%;%PRAC_LIB%" -d %PRAC_BIN% %PRAC_SRC%\Flowers.java
PAUSE

REM Running code
ECHO Trying to run
java -classpath %PRAC_BIN%;%PRAC_LIB% Flowers
PAUSE

REM Dissasembling class
ECHO Trying to dissasemble
javap -c %PRAC_BIN%\Flowers.class > %PRAC_DOCS%\ByteCode.txt

REM Go back to docs folder
cd %PRAC_DOCS%

PAUSE

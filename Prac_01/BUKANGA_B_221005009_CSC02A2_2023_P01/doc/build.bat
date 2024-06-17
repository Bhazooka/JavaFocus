@ECHO OFF
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
javac -sourcepath %PRAC_SRC% -cp "%PRAC_BIN%;%PRAC_LIB%" -d %PRAC_BIN% %PRAC_SRC%\Main.java
PAUSE

REM Run code
ECHO Trying to run
java -classpath %PRAC_BIN%;%PRAC_LIB% Main
PAUSE

REM JAVADOC
javadoc -sourcepath %PRAC_SRC% -classpath %PRAC_BIN%;%PRAC_LIB% -use -version -author -d %PRAC_DOCS%\JavaDoc -subpackages acsse

REM Go back to docs folder
cd %PRAC_DOCS%

PAUSE

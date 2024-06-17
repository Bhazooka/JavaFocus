@ECHO OFF

REM Good batch file coding practice
SETLOCAL enabledelayedexpansion

REM Set the Java Path
SET JAVA_HOME="C:\jdk-17"
SET PATH=%JAVA_HOME%;%PATH%

REM SET FOLDER VARIABLES
set ERRMSG=
SET PRAC_BIN=.\bin
SET PRAC_DOCS=.\docs
SET PRAC_LIB=.\lib\*
SET PRAC_SRC=.\src

CD ..

REM Clean all class files from the bin folder
:CLEAN
ECHO CLEANING PROJECT
DEL /S %PRAC_BIN%\*.class
RMDIR /Q /S %PRAC_DOCS%\JavaDoc
IF /I "%ERRORLEVEL%" NEQ "0" (
	ECHO !!! Error CLEANING Project !!!
)

REM Compile project 
:COMPILE
ECHO Attempting to Compile
javac -sourcepath %PRAC_SRC% -cp "%PRAC_BIN%;%PRAC_LIB%" -d %PRAC_BIN% %PRAC_SRC%\Main.java
IF /I "%ERRORLEVEL%" NEQ "0" (
	SET ERRMSG=!!! ERROR COMPILING !!!
	GOTO ERROR
)

REM Generate Java Docs
:JAVADOC
ECHO Generating Java Doc for Project
REM javadoc -sourcepath %PRAC_SRC% -cp %PRAC_BIN%;%PRAC_LIB% -use -version -author -d %PRAC_DOCS%\JavaDoc -subpackages acsse
javadoc -sourcepath %PRAC_SRC% -cp %PRAC_BIN%;%PRAC_LIB% -use -version -author -d %PRAC_DOCS%\JavaDoc -subpackages acsse
IF /I "%ERRORLEVEL%" NEQ "0" (
	ECHO !!! Error generating JavaDoc for project !!!
	REM GOTO ERROR
)

REM Run the Project
:RUN
ECHO Running the Project
java -cp %PRAC_BIN%;%PRAC_LIB% Main
IF /I "%ERRORLEVEL%" NEQ "0" (
	SET ERRMSG=!!! ERROR RUNNING PROJECT !!!
	GOTO ERROR
)
GOTO END

REM If something went wrong, error
:ERROR
ECHO %ERRMSG%

REM The end. We move back to the docs folder
:END
ECHO END
CD %PRAC_DOCS%
PAUSE
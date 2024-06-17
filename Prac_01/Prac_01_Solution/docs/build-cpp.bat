@ECHO off
CLS

REM Good batch file coding practice.
SETLOCAL enabledelayedexpansion
ECHO Setting C++ Path
REM SET C++ Compiler path here if needed

REM Move to correct folder.
echo Build script set to run in Project folder
cd ..

REM Variables for batch
set PRAC_BIN=.\bin
set PRAC_DOCS=.\docs
set PRAC_SRC=.\src

REM Clean old executable
:CLEAN
echo ~~~ Cleaning project ~~~
DEL /S %PRAC_BIN%\Main.exe
IF /I "%ERRORLEVEL%" NEQ "0" (
    echo ~~! Error cleaning project !~~
)

REM Compile CPP project
:COMPILE
echo ~~~ Compiling project ~~~
g++ %PRAC_SRC%\main.cpp %PRAC_SRC%\turtle.cpp -o %PRAC_BIN%\Main.exe
IF /I "%ERRORLEVEL%" NEQ "0" (
    set ERRMSG=~~! Error compiling project !~~
    GOTO ERROR
)

REM Run project by running Main.
:RUN
echo ~~~ Running project ~~~
%PRAC_BIN%\Main.exe
IF /I "%ERRORLEVEL%" NEQ "0" (
    set ERRMSG=~~! Error running project !~~
    GOTO ERROR
)
GOTO END

REM Something went wrong, display error.
:ERROR
echo ~~! Fatal error with project !~~
echo %ERRMSG%

REM Move back to docs folder and wait.
:END
echo ~~~ End ~~~
cd %PRAC_DOCS%
pause
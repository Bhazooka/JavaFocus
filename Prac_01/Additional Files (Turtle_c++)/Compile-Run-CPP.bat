@echo off
echo Setting C++ Path
REM SET C++ Compiler path here if needed
echo Delete old exe
del Main.exe
echo Compiling C++ code
g++ main.cpp turtle.cpp -o Main.exe
echo Running C++ code
pause
cls
Main.exe
pause
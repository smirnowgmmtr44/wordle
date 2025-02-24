del /S /Q %~dp0out\*.class

::for /d %%i in ("%~dp0resource\*") do (
	::javac -cp %~dp0classes -d %~dp0  %%i\*.java		
::)
javac -cp %~dp0 -d %~dp0  %~dp0src\storage\*.java
javac -cp %~dp0 -d %~dp0  %~dp0src\logic\*.java
javac -cp %~dp0 -d %~dp0  %~dp0src\view\*.java



javac -cp %~dp0 -d %~dp0  %~dp0src\*.java

java -cp %~dp0 out.WordleApp
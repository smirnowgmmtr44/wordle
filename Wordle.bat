del /S /Q %~dp0out\*.class
javac -cp %~dp0 -d %~dp0  %~dp0src\storage\*.java  %~dp0src\logic\*.java  %~dp0src\view\*.java %~dp0src\*.java
java -cp %~dp0 out.WordleApp
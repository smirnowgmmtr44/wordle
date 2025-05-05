del /S /Q .\out\*.class
javac -d .\out -cp .\lib\* .\src\storage\*.java  .\src\logic\*.java  .\src\view\*.java .\src\*.java
java -cp .\lib\*;.\out  WordleApp
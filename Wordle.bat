del /S /Q .\out\*.class
javac -d .\out  .\src\storage\*.java  .\src\logic\*.java  .\src\view\*.java .\src\*.java
java -cp .\out  WordleApp
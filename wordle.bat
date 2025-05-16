del /S /Q .\out\*.class
javac -d .\out -cp .\lib\* .\src\storage\*.java  .\src\logic\enums\*.java .\src\logic\exception\*.java .\src\logic\model\*.java  .\src\view\*.java .\src\*.java
java -cp .\lib\*;.\out  WordleApp
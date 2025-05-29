del /S /Q .\out\*.class
del /S /Q Wordle.jar
javac -d .\out -cp .\lib\* .\src\logic\storage\*.java .\src\storage\*.java   .\src\logic\enums\*.java .\src\logic\model\*.java  .\src\view\*.java .\src\*.java
jar cvfm Wordle.jar manifest.txt lib -C out .
java -jar Wordle.jar
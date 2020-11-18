all: run

clean:
	rm -f out/Main.jar out/Algorithm.jar

out/Main.jar: out/parcs.jar src/Main.java src/Result.java
	@javac -cp out/parcs.jar src/Main.java src/Result.java
	@jar cf out/Main.jar -C src Main.class -C src Result.class
	@rm -f src/Main.class src/Result.class

out/Algorithm.jar: out/parcs.jar src/Algorithm.java src/Result.java
	@javac -cp out/parcs.jar src/Algorithm.java src/Result.java
	@jar cf out/Algorithm.jar -C src Algorithm.class -C src Result.class
	@rm -f src/Algorithm.class src/Result.class

build: out/Main.jar out/Algorithm.jar

run: out/Main.jar out/Algorithm.jar
	@cd out && java -cp 'parcs.jar:Main.jar' Main

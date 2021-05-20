package= nz.ac.auckland.softeng281.a4
bin = bin

ifeq ($(OS),Windows_NT)
	src = src\main\java
	src_test = src\test\java
	classpath = $(bin);lib\junit-4.13.1.jar;lib\hamcrest-core-1.3.jar
	build_path = src\main\java\$(package_path)\*.java src\test\java\$(package_path)\*.java
	package_path = nz\ac\auckland\softeng281\a4
	clean_command = rd /s /q
	find = where
else
	src = src/main/java
	src_test = src/test/java
	classpath = $(bin):lib/junit-4.13.1.jar:lib/hamcrest-core-1.3.jar
	build_path = src/main/java/$(package_path)/*.java src/test/java/$(package_path)/*.java
	package_path = nz/ac/auckland/softeng281/a4
	clean_command = rm -rfv
	find = which
endif

all: dependencies clean build run

dependencies:
	$(find) java javac

clean:
	$(clean_command) $(bin)

build: clean
	javac -cp $(classpath) -d $(bin) $(build_path)

test-nodes: clean build
	java -cp $(classpath) org.junit.runner.JUnitCore $(package).NodesStackAndQueueTest

test-edges: clean build
	java -cp $(classpath) org.junit.runner.JUnitCore $(package).EdgesLinkedListTest

test-graph: clean build
	java -cp $(classpath) org.junit.runner.JUnitCore $(package).GraphTest

test-all: clean build
	java -cp $(classpath) org.junit.runner.JUnitCore $(package).AllTests

run: clean build 
	java -cp $(classpath) $(package).GraphControl

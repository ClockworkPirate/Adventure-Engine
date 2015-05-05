LIB = -cp .:lib/kxml2-2.3.0.jar:lib/xstream-1.4.2.jar
SRC = src/com/guerrieri/mud/

all: base char cont inv io nav ui

base:
	javac $(LIB) $(SRC)*.java

char: base cont inv nav
	javac $(LIB) $(SRC)character/*.java

cont: base char nav ui
	javac $(LIB) $(SRC)control/*.java
	
lex: base
	javac $(LIB) $(SRC)control/lexicon/*.java

inv: base nav
	javac $(LIB) $(SRC)inventory/*.java

io: inv nav
	javac $(LIB) $(SRC)io/*.java

nav: base cont
	javac $(LIB) $(SRC)navigation/*.java

ui:
	javac $(LIB) $(SRC)ui/*.java

clean:
	rm $(SRC)*.class
	rm $(SRC)character/*.class
	rm $(SRC)control/*.class
	rm $(SRC)inventory/*.class
	rm $(SRC)io/*.class
	rm $(SRC)navigation/*.class
	rm $(SRC)ui/*.class
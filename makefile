LIB = -cp .:lib/kxml2-2.3.0.jar:lib/xstream-1.4.2.jar
SRC = src/com/mguerrieri/mud/

all: base char cont inv io nav ui

base:
	javac $(LIB) $(SRC)*.java

char: base cont inv nav
	javac $(LIB) $(SRC)character/*.java

cont: base char nav ui
	javac $(LIB) $(SRC)control/*.java

inv: base nav
	javac $(LIB) $(SRC)inventory/*.java

io:
	javac $(LIB) $(SRC)io/*.java

nav: base io cont
	javac $(LIB) $(SRC)navigation/*.java

ui:
	javac $(LIB) $(SRC)ui/*.java

build: Lexer.java Parser.java sym.java
	javac -cp ./cup.jar data/*.java *.java

Lexer.java: Lexer.lex
	jflex $<

Parser.java: Parser.cup
	cup -parser Parser -package progp_s4 $<

sym.java: Parser.java

clean:
	rm -f sym.java Parser.java Lexer.java *.class data/*.class

run:
	java -cp ./cup.jar:.. progp_s4.Main input.in
	convert out.svg out.pdf
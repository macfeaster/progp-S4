// Lexer.lex
// Part of the KTH course DD1361 Programming Paradigms lab S4
// Authors: Johan Callvik and Mauritz Zachrisson

package progp_s4;

import java.lang.System;
import java_cup.runtime.Symbol;
import progp_s4.sym;

%%
%cup
%class Lexer

%%

[%].*$ { /* VASK */ }
[\r]+|[\n]+|(\r\n)+|[ \t\f]+ { /* VASK */ }
DOWN { return new Symbol(sym.DOWN); }
UP { return new Symbol(sym.UP); }
FORW { return new Symbol(sym.FORW); }
BACK { return new Symbol(sym.BACK); }
LEFT { return new Symbol(sym.LEFT); }
RIGHT { return new Symbol(sym.RIGHT); }
COLOR { return new Symbol(sym.COLOR); }
REP { return new Symbol(sym.REP); }
\" { return new Symbol(sym.QUOTE); }
[0-9]+ { return new Symbol(sym.NUMBER, new Integer(yytext())); }
[#][0-9A-F]{6} { return new Symbol(sym.HEX, new String(yytext())); }
[+] { return new Symbol(sym.PLUS); }
[-] { return new Symbol(sym.MINUS); }
[*] { return new Symbol(sym.TIMES); }
[/] { return new Symbol(sym.DIV); }
[.] { return new Symbol(sym.DOT); }
"(" { return new Symbol(sym.LPAREN); }
")" { return new Symbol(sym.RPAREN); }
[=] { return new Symbol(sym.EQUALS); }
[a-zA-Z][_0-9a-zA-Z]* { return new Symbol(sym.VAR, new String(yytext())); }

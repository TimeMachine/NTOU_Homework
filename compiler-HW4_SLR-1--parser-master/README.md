compiler-HW4_SLR-1--parser
==========================
Description

We have introduced the algorithms for 1) the FIRST function, 2) the FOLLOW
function, 3) constructing LR(0) automaton, 4) creating LR(1) parsing table,
and 5) running a LR(1) parser. Please write a SLR parser that accepts or
rejects an input token sequence based on a given grammar. You have to write
several components to accomplish this homework, including:


   1. The FIRST function.
   2. The FOLLOW function.
   3. The LR(0) automaton generator, include the CLOSURE and the GOTO
   function.
   4. The LR(1) parsing table generator.
   5. The SLR(1) parser.

The program must read inputs from a file, e.g., *input.txt*, which defines
the grammar and the test cases. The grammar part includes definitions to
the terminals, the non-terminals, the productions, and the start symbol.
Each definition is prefixed with a keyword, as shown below:

Grammar definitionPrefixComments#Terminalsterm:Non-terminalsnon-term:
Productionsprod:Start symbolstart:

In contrast, test cases are not prefixed with any keyword. The test cases
are always placed in the end of file. You do not need to worry about the
situation that a test case is appeared before a grammar is completely
defined.

The input file provided below contains two test cases. There is not a
restriction on the number of test cases in an input file.
------------------------------

Input:

# this is a comment
# non terminals
non-term: E
non-term: T
non-term: F
# terminals
term: *
term: +
term: (
term: )
term: id
# productions
prod: E : E + T
prod: E : T
prod: T : T * F
prod: T : F
prod: F : ( E )
prod: F : id
# the start symbol
start: E
# test cases
id + id
id * * id


Output:
--------------------------------
Production(1): E -> E + T
Production(2): E -> T
Production(3): T -> T * F
Production(4): T -> F
Production(5): F -> ( E )
Production(6): F -> id
Production(7): S' -> E
------------------------------------------------------------
FIRST(E) = { '(' 'id' }
FIRST(F) = { '(' 'id' }
FIRST(S') = { '(' 'id' }
FIRST(T) = { '(' 'id' }
------------------------------------------------------------
FOLLOW(E) = { '$' ')' '+' }
FOLLOW(F) = { '$' ')' '*' '+' }
FOLLOW(S') = { '$' }
FOLLOW(T) = { '$' ')' '*' '+' }
------------------------------------------------------------
# Start production = S' -> . E
# Item0 [0x884e208] created
# LR(0) automata: 12 states created.
------------------------------------------------------------
 STATE | (   )   *   +   id  $   | E   F   S'  T
-------+-------------------------+-----------------
     0 | s1              s5      | 2   3       4
     1 | s1              s5      | 6   3       4
     2 |             s7      acc |
     3 |     r4  r4  r4      r4  |
     4 |     r2  s8  r2      r2  |
     5 |     r6  r6  r6      r6  |
     6 |     s9      s7          |
     7 | s1              s5      |     3       10
     8 | s1              s5      |     11
     9 |     r5  r5  r5      r5  |
    10 |     r1  s8  r1      r1  |
    11 |     r3  r3  r3      r3  |
-------+-------------------------+-----------------
------------------------------------------------------------
Input = id + id
Item0, IN=id:   shift id, push Item5
Item5, IN=+:    reduce F -> id, pop 1 item(s), push Item3
Item3, IN=+:    reduce T -> F, pop 1 item(s), push Item4
Item4, IN=+:    reduce E -> T, pop 1 item(s), push Item2
Item2, IN=+:    shift +, push Item7
Item7, IN=id:   shift id, push Item5
Item5, IN=$:    reduce F -> id, pop 1 item(s), push Item3
Item3, IN=$:    reduce T -> F, pop 1 item(s), push Item10
Item10, IN=$:   reduce E -> E + T, pop 3 item(s), push Item2
Parser accept the input
------------------------------------------------------------
Input = id * * id
Item0, IN=id:   shift id, push Item5
Item5, IN=*:    reduce F -> id, pop 1 item(s), push Item3
Item3, IN=*:    reduce T -> F, pop 1 item(s), push Item4
Item4, IN=*:    shift *, push Item8
Item8, IN=*:    no action defined
Parser reject the input
------------------------------------------------------------
package BrickWork.test;

/**
 * Class with no implementations holding some text inputs to use in the console
 * it is not called/ instanced or extended nowhere in the program.
 */
public class Test {

/*
correct
2 4
1 1 2 2
3 3 4 4

correct 2
4 4
1 1 3 4
2 5 3 4
2 5 6 6
7 7 8 8

4 8
1	2	2	12	5	7	7	16
1	10	10	12	5	15	15	16
9	9	3	4	4	8	8	14
11	11	3	13	13	6	6	14


4 4
1 1 3 4
2 5 3 4
2 5 6 6
7 7

3 pli brick
4 4
1 1 2 4
3 3 2 4
5 5 2 6
7 7 8 8

with incorrect brick
4 4
1 1 2 4
3 3 2 4
5 5 1 6
7 7 8 8

with wrong dimensions
4 4
1 1 2 4
3 3 2 4
5 5 1 6 7
7 7 8 8


Rows = n + (n - 1)
even normal components(* 01 ) 1 space after
even last component(  03 *\n) two spaces before
odd normal components(*    )
odd last component(* ** *\n) two spaces before


   0    1    2    3         second layer
0  1 2  3 4  5 6  7 8       whole structure

* ** * ** * ** * ** *
* 01 * 02 * 03   03 *  0
*    *    * ** * ** *  1
* 01 * 02 * 04   04 *  2
* ** * ** * ** * ** *  3
* 05 * 06 * 07 * 08 *  4
*    *    *    *    *  5
* 05 * 06 * 07 * 08 *  6
* ** * ** * ** * ** *

- -- - -- - -- - -- -
- 01 - 02 - 03   03 -
-    -    - -- - -- -
- 01 - 02 - 04   04 -
- -- - -- - -- - -- -
- 05 - 06 - 07 - 08 -
-    -    -    -    -
- 05 - 06 - 07 - 08 -
- -- - -- - -- - -- -
4 4
1 1 3 4
2 5 3 4
2 5 6 6
7 7 8 8

1 2 3 3
1 2 4 4
5 6 7 8
5 6 7 8

 */
}

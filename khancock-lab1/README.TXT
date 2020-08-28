Kim Hancock

1. How might one pick game sizes (number of coins) such that one has a 50% chance of three
coins, a 25% chance of four coins, a 12.5% chance of five coins, and so on?

Create a random Boolean while loop, so that the loop adds one to the number of coins
when the Boolean returns false
    ex:
    int coinNum = 3;
    while (r.nextBoolean() == false) {
        coinNum++;
    }

The chances of getting the first random boolean false is 50%, the chance of getting 
two false in a row is 25%, etc.

2. Does your method of generating the starting configuration guarantee that the game is not
an immediate win? If not, how might you change your approach to enforce that guarantee
(without just adding some blank spaces to the left of an existing configuration)?

Yes; the first position on the board a coin can go is position 2, as the position array
only holds integers greater than or equal to 2.

3. A similar game, called Welter’s Game (after C. P. Welter, who analyzed the game), allows
the coins to pass each other. Would this rule modification change your implementation
significantly? Why or why not?

It would modify it significantly as it would reorder the position array; the program would have to
store coin numbers in a different way. Right now the numbers in the position array represent the order the 
board is filled and the positions the coins are on. If coins could jump the method which prints the board based off the
array would have to print a higher number before lower numbers. Now, when the value of the position array matches the
position on the board, the next consecutive number is printed, the number representing a coin. Thus, an entirely new
way of representing the board would have to be created.

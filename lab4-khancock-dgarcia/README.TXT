    
    
    public boolean solve() {
        // conditional called success -- if moves on stack == number of blanks
        // while not success
            // get next move
            // if legal, make move
            // else (illegal), pop last move, reset array so move wasnt made, reset current mve to changed last move
            // if current move is legal, can add to stack
        for (int row = 0; row <= 8; row++) {
            for (int col = 0; col <= 8; col++) {
                if (puzzleToSolve.getCurrentIndex(row, col) == 0) {
                    SudokuMove myMove = addToDeque(row, col);
                    while(myMove == null) {
                        
                        SudokuMove lastMove = collectionOfMoves.pop();
                        //System.out.println(collectionOfMoves);
                        puzzleToSolve.resetToZero(lastMove.getI(), lastMove.getJ());
                        addToDeque(lastMove.getI(), lastMove.getJ());
             
                    }
                        //couldnt find number that works, clear number before and recurse
      
                }
            }
        }
        return true;
    }    

    
    private SudokuMove addToDeque(int row, int col) {
        SudokuMove myMove;
        int nextNum = puzzleToSolve.pickNum(row, col);
        if (nextNum != 0) {
            //puzzleToSolve.assignSpaceValue(row, col,nextNum );
                myMove = new SudokuMove(nextNum, row, col);
                
                collectionOfMoves.push(myMove);
                System.out.println(collectionOfMoves);
                //addToDeque(myMove.getI(), myMove.getJ());
                return myMove;
          /**  
        for(int i = 1; i < 10; i++) {
            if(puzzleToSolve.isMoveValid(row, col, i)) {
                puzzleToSolve.assignSpaceValue(row, col, i);
                myMove = new SudokuMove(i, row, col);
                collectionOfMoves.push(myMove);
                //System.out.println(collectionOfMoves);
                return myMove;
            }
        }
        **/
    }
    System.out.println(collectionOfMoves);
    //puzzleToSolve.resetToZero(row, col);
        return null;

    }
    
}
/*
                    SudokuMove attemptedMove = addToDeque(i, j);
                    
                    while(attemptedMove == null) {
                        // grab indices of item to pop off and call addToDeque again
                        SudokuMove goBack = collectionOfMoves.pop();
                        puzzleToSolve.resetToZero(i,j);
                        int a = goBack.getI();
                        int b = goBack.getJ();
                        

                        attemptedMove = addToDeque(a,b);
                        // PROBLEM HERE ^^^
                        //when we try addToDeque, it calls pickNum
                        //pickNum chooses the smallest possible value in 1-9 and places that num
                        //addToDeque pushes that onto the linked list
                        //this public solve method checks there are no possible moves for the space
                        //pops the move we just placed off
                        //changes attemptedMove to addToDeque
                        //BUT BUT BUT addToDeque just goes to pickNum again, which chooses the SAME NUM
                        //thus, the move we just popped off is pushed on again
                        //need to figure out how to keep track of which values were already tried
                        //HINT: the newNum value of the SudokuMove object...
                        
                    }
                    */
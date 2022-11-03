/*Move
* @author       Sanaa Kapur
* @version      May 28 2021
 * Description:       
* Data type that stores all information for a move such as userRow/col and newRow/col as well as evaluation
* */
public class Move {
    
    private int userRow;
    private int userCol;
    private int newRow;
    private int newCol;
    private double evaluation; 
    
    public Move(int ur, int uc, int nr, int nc) {
	userRow = ur;
	userCol = uc;
	newRow = nr;
	newCol = nc;
    }
    public void setUserRow(int val ) { userRow = val; }
    public void setNewRow(int val ) { newRow = val; }
    public void setUserCol(int val ) { userCol = val; }
    public void setNewCol(int val ) { newCol = val; }
    public void setEval(double val ) { evaluation = val; }
    
    
    public int getUserRow() { return userRow; }
    public int getNewRow() { return newRow; }
    public int getUserCol() { return userCol; }
    public int getNewCol() { return newCol; }
    
    public String toString() { return "Move: userRow:" + userRow + " userCol:" + userCol + " newRow:" + newRow + " newCol:"  + newCol;}
}

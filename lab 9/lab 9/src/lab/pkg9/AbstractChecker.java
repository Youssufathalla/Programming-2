/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab.pkg9;

/**
 *
 * @author Lenovo
 */

public abstract class AbstractChecker implements Runnable {

    protected int[][] board;
    protected DuplicateResult result;
    protected Checker checker;

    public AbstractChecker(int[][] board, DuplicateResult result) {
        this.board = board;
        this.result = result;
        this.checker = new Checker();
    }

    @Override
    public abstract void run();

}

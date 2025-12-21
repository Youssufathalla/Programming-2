package lab10;
interface Controllable {
    boolean[] getCatalog();
    int[][] getGame(char level) throws Exception;
    void driveGames(String sourcePath) throws Exception;
    boolean[][] verifyGame(int[][] game);
    int[][] solveGame(int[][] game) throws Exception;
    void logUserAction(UserAction userAction) throws Exception;
}

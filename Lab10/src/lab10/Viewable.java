package lab10;
interface Viewable {
    Catalog getCatalog();
    Game getGame(String level) throws Exception;
    void driveGames(Game sourceGame) throws Exception;
    String verifyGame(Game game);
    int[] solveGame(Game game) throws Exception;
    void logUserAction(String userAction) throws Exception;
}

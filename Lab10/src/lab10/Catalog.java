public class Catalog {
    private boolean current;  // True if there is a game in progress, False otherwise
    private boolean allModesExist;  // True if there is at least one game available for each difficulty, False otherwise

    // Constructor to initialize the game state
    public Catalog(boolean current, boolean allModesExist) {
        this.current = current;
        this.allModesExist = allModesExist;
    }

    // Check if there is an unfinished game
    public boolean hasUnfinishedGame() {
        return current;  // Return whether the game is in progress
    }

    // Check if there is at least one game available for each difficulty
    public boolean hasGamesInDifficultyFiles() {
        return allModesExist;  // Return whether all modes exist (easy, medium, and hard)
    }

    // Method to update the game state
    public void setGameState(boolean current, boolean allModesExist) {
        this.current = current;
        this.allModesExist = allModesExist;
    }
}

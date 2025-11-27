package lab.pkg9;
public class Checker {

    // This method safely checks duplicates in an array of 9 values.
    // RETURNS:
    //   int[]{pos1, pos2}  → if a duplicate exists (1-based positions)
    //   null               → if NO duplicates
    //
    // Missing or invalid values (not 1–9) are ignored safely.
    public int[] findDuplicatePositions(int[] values) {

        int[] firstIndex = new int[10]; // indexes for values 1–9
        java.util.Arrays.fill(firstIndex, -1);

        for (int i = 0; i < values.length; i++) {

            int number = values[i];

            // IGNORE missing/invalid values
            if (number < 1 || number > 9) {
                continue;
            }

            // First time seeing this number
            if (firstIndex[number] == -1) {
                firstIndex[number] = i;
            }
            else {
                // Duplicate found → return positions (convert to 1-based)
                return new int[] { firstIndex[number] + 1, i + 1 };
            }
        }

        // no duplicates
        return null;
    }
}

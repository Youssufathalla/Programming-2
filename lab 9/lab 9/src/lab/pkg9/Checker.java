package lab.pkg9;
public class Checker {


    public int[] findDuplicatePositions(int[] values) {

        int[] firstIndex = new int[10];
        java.util.Arrays.fill(firstIndex, -1);

        for (int i = 0; i < values.length; i++) {

            int number = values[i];

         
            if (number < 1 || number > 9) {
                continue;
            }

      
            if (firstIndex[number] == -1) {
                firstIndex[number] = i;
            }
            else {
           
                return new int[] { firstIndex[number] + 1, i + 1 };
            }
        }

    
        return null;
    }
}

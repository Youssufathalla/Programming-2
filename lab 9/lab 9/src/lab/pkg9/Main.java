package lab.pkg9;
public class Main {
   
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: java -jar app.jar <file.csv> <mode>");
            return;
        }

        String path = args[0];
        int mode = Integer.parseInt(args[1]); 

        int[][] board = BoardReader.read(path);
        Verifier verifier = new Verifier(board);

        String result = verifier.run(mode);
        System.out.print(result);
    }
}

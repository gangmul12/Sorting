import java.io.*;
import java.io.IOException;
public class numGen {
	public static void main(String[] args) throws IOException {
       BufferedWriter output = new BufferedWriter(new FileWriter("out.txt"));
        for(int i=1; i<1000001; i++) {
            Integer k = i;
            output.write(k.toString());
            output.newLine();
        }
        output.close();
    }

}




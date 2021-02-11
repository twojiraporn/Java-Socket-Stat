import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    private static Socket socket;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Please select the number : \n" + "1 (Mean)\n" +
                "2 (Med)\n" +
                "3 (Mode)\n" +
                "4 (SD)\n" +
                "5 (Deviation)\n" +
                "6 (Factorial)\n");

        int operation = sc.nextInt();
        String all = "";
        System.out.println("Please fill value and end with \"end\".");

        String input = sc.next();
        int checkFirst = 0;

        while (!input.equals("end")) {
            if (checkFirst == 0) {
                all += input;
                checkFirst++;
            } else {
                all += " ";
                all += input;
            }
            input = sc.next();
        }

        String message = "/"+Integer.toString(operation)+"/"+ all+"/";
        //System.out.println(message);

        try {
            socket = new Socket("127.0.0.1", 8999);

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);


            String sendMessage = message + "\n";
            bw.write(sendMessage);
            bw.flush();
            System.out.println();
            System.out.println("Message sent to the server : " + sendMessage);

            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String msg = br.readLine();
            System.out.println("Answer from server : " + msg);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Wrong Input");
            }
        }
    }
}
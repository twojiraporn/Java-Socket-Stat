import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    private static Socket socket;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        try {
            while(true) {
                String message = "";

                System.out.println("Please select the number : \n" + "1 (Mean)\n" +
                        "2 (Median)\n" +
                        "3 (Mode)\n" +
                        "4 (Standard Deviation)\n" +
                        "5 (Variance)\n" +
                        "6 (Factorial)\n" +
                        "0 (Exit)\n" );

                int operation = sc.nextInt();

                while (operation < 0 | operation > 6) {
                    System.out.println("You can select only 0 to 6.");
                    System.out.println("Please select the number : \n" + "1 (Mean)\n" +
                            "2 (Median)\n" +
                            "3 (Mode)\n" +
                            "4 (Standard Deviation)\n" +
                            "5 (Variance)\n" +
                            "6 (Factorial)\n" +
                            "0 (Exit)\n" );
                    operation = sc.nextInt();
                }

                if (operation >= 1 && operation <= 5) {
                    String all = "";
                    System.out.println("Please fill value and end with \"end\".");

                    String input = sc.next();
                    int checkFirst = 0;

                    while (!input.equals("end"))  {
                        if (checkFirst == 0) {
                            all += input;
                            checkFirst++;
                        } else {
                            all += " ";
                            all += input;
                        }
                        input = sc.next();
                    }
                    message = "/" + Integer.toString(operation) + "/" + all + "/";
                } else if (operation == 6) {
                    System.out.println("Please fill only single value.");
                    String input = sc.next();
                    input += " ";
                    message = "/" + Integer.toString(operation) + "/" + input + "/";
                } else if (operation >= 7) {
                    message = "/" + Integer.toString(operation) + "/" + 0 + "/";
                }
                else if(operation == 0){
                    System.out.println("You already exit");
                    message = "/" + Integer.toString(operation) + "/" + 0 + "/";
                    socket = new Socket("127.0.0.1", 8999);

                    OutputStream os = socket.getOutputStream();
                    OutputStreamWriter osw = new OutputStreamWriter(os);
                    BufferedWriter bw = new BufferedWriter(osw);

                    String sendMessage = message + "\n";
                    bw.write(sendMessage);
                    bw.flush();
                    break;
                }
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
                System.out.println("Answer from server : " + msg +"\n");
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
//                e.printStackTrace();
 //               System.out.println("You already exit");
            }
        }
    }
}
import java.io.*;
import java.math.BigInteger;
import java.net.*;

public class Server {

    private static Socket socket;

    public static void main(String[] args) {
        double answer = 0;
        BigInteger fact = BigInteger.ZERO;
        int count = 0;
        try {
            ServerSocket serverSocket = new ServerSocket(8999);
            System.out.println("Server Started and listening to the port 8999\n");
            while (true) {
                socket = serverSocket.accept();
                if(count == 0){
                    System.out.println("Client connected.");
                    count++;
                }
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String clientMassage = br.readLine();
                String receivedFromClient[] = clientMassage.split("/");
                System.out.println("All number that receive from client : " + receivedFromClient[2]);
                System.out.println("Request operation from client is : " + receivedFromClient[1]);
                int operation = Integer.valueOf(receivedFromClient[1]);
                String[] arrOfInput = receivedFromClient[2].split(" ");

                if (operation >= 1 && operation <= 6) {
                    Calculator cal = new Calculator();
                    if (operation == 1) {
                        answer = cal.avg(arrOfInput);
                    } else if (operation == 2) {
                        answer = cal.mean(arrOfInput);
                    } else if (operation == 3) {
                        answer = cal.mode(arrOfInput);
                    } else if (operation == 4) {
                        answer = cal.standardDeviation(arrOfInput);
                    } else if (operation == 5) {
                        answer = cal.deviation(arrOfInput);
                    } else if (operation == 6) {
                        fact = cal.factorial(arrOfInput);
                    }

                    String returnMessage = "";

                    if (operation >= 1 && operation <= 5) {
                        returnMessage = Double.toString(answer);
                    } else if (operation == 6) {
                        returnMessage = fact.toString();
                    }
                    OutputStream os = socket.getOutputStream();
                    OutputStreamWriter osw = new OutputStreamWriter(os);
                    BufferedWriter bw = new BufferedWriter(osw);
                    bw.write(returnMessage + "\n");
                    System.out.println("Answer sent to the client : " + returnMessage + "\n");
                    bw.flush();
                } else if(operation>=7 || operation<0 ){
                    String returnMessage = "Invalid operation.";
                    OutputStream os = socket.getOutputStream();
                    OutputStreamWriter osw = new OutputStreamWriter(os);
                    BufferedWriter bw = new BufferedWriter(osw);
                    bw.write(returnMessage + "\n");
                    System.out.println("Answer sent to the client : " + returnMessage + "\n");
                    bw.flush();
                }
                else if(operation == 0){
                    count = 0;
                    String returnMessage = "Client exit.";
                    OutputStream os = socket.getOutputStream();
                    OutputStreamWriter osw = new OutputStreamWriter(os);
                    BufferedWriter bw = new BufferedWriter(osw);
                    bw.write(returnMessage + "\n");
                    System.out.println("Answer sent to the client : " + returnMessage + "\n");
                    bw.flush();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                String returnMessage = "Client exit.";
                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write(returnMessage + "\n");
                System.out.println("Answer sent to the client : " + returnMessage + "\n");
                bw.flush();
                socket.close();
            } catch (Exception e) {
                System.out.println("Sever shut down");
            }
        }
    }
}

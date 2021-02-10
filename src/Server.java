import java.io.*;
import java.net.*;

public class Server {

    private static Socket socket;

    public static void main(String[] args) {
        double answer = 0;
        try {
            ServerSocket serverSocket = new ServerSocket(8999);
            System.out.println("Server Started and listening to the port 8999\n");
            while (true) {
                socket = serverSocket.accept();
                System.out.println("Client connected.");
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String clientMassage = br.readLine();
                String receivedFromClient[]= clientMassage.split("/");
                System.out.println("All number that receive from client  : " + receivedFromClient[2]);
                System.out.println("Request operation from client is : " + receivedFromClient[1]);
                int operation = Integer.valueOf(receivedFromClient[1]);
                String[] arrOfInput = receivedFromClient[2].split(" ");


                if(operation>=1 && operation<=5){
                    Calculator cal = new Calculator();
                    if(operation == 1 ){
                        answer = cal.avg(arrOfInput);
                    }
                    else if(operation == 2 ){
                        answer = cal.mean(arrOfInput);
                    }
                    else if(operation == 3 ){
                        answer = cal.mode(arrOfInput);
                    }
                    else if(operation == 4 ){
                        answer = cal.standardDeviation(arrOfInput);
                    }
                    else if(operation == 5 ){
                        answer = cal.deviation(arrOfInput);
                    }
                    String returnMessage = Double.toString(answer);
                    OutputStream os = socket.getOutputStream();
                    OutputStreamWriter osw = new OutputStreamWriter(os);
                    BufferedWriter bw = new BufferedWriter(osw);
                    bw.write(returnMessage + "\n");
                    System.out.println("Answer sent to the client is " + returnMessage);
                    bw.flush();
                    System.out.println("Calculated Success.");
                }

                else{
                    String returnMessage = "Wrong Input";
                    OutputStream os = socket.getOutputStream();
                    OutputStreamWriter osw = new OutputStreamWriter(os);
                    BufferedWriter bw = new BufferedWriter(osw);
                    bw.write(returnMessage + "\n");
                    System.out.println("Answer sent to the client is " + returnMessage);
                    bw.flush();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                System.out.println("Client Disconnected");
            } catch (Exception e) {
                System.out.println("hello");
            }
        }
    }
}
package com.emailserver.pop3;

import java.io.*;
import java.net.*;
import java.util.*;

public class Pop3Server {
    private int port;
    private ServerSocket serverSocket;
    private List<Email> emails;

    public Pop3Server(int port) {
        this.port = port;
        this.emails = new ArrayList<>();
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("POP3 Server started on port " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                handleClient(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            out.println("+OK POP3 server ready");

            String line;
            while ((line = in.readLine()) != null) {
                if (line.startsWith("USER")) {
                    out.println("+OK User accepted");
                } else if (line.startsWith("PASS")) {
                    out.println("+OK Password accepted");
                } else if (line.startsWith("LIST")) {
                    out.println("+OK " + emails.size() + " messages");
                    for (int i = 0; i < emails.size(); i++) {
                        out.println((i + 1) + " " + emails.get(i).getSize());
                    }
                    out.println(".");
                } else if (line.startsWith("RETR")) {
                    int msgNum = Integer.parseInt(line.split(" ")[1]) - 1;
                    if (msgNum >= 0 && msgNum < emails.size()) {
                        out.println("+OK " + emails.get(msgNum).getSize() + " octets");
                        out.println(emails.get(msgNum).getContent());
                        out.println(".");
                    } else {
                        out.println("-ERR No such message");
                    }
                } else if (line.startsWith("DELE")) {
                    int msgNum = Integer.parseInt(line.split(" ")[1]) - 1;
                    if (msgNum >= 0 && msgNum < emails.size()) {
                        emails.remove(msgNum);
                        out.println("+OK Message deleted");
                    } else {
                        out.println("-ERR No such message");
                    }
                } else if (line.equals("QUIT")) {
                    out.println("+OK Goodbye");
                    break;
                } else {
                    out.println("-ERR Unknown command");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addEmail(Email email) {
        emails.add(email);
    }

    public static void main(String[] args) {
        Pop3Server server = new Pop3Server(110);
        server.start();
    }

    public static class Email {
        private String content;
        private int size;

        public Email(String content) {
            this.content = content;
            this.size = content.length();
        }

        public String getContent() {
            return content;
        }

        public int getSize() {
            return size;
        }
    }
}
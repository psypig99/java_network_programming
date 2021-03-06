package edu.kosta.network.io;

import java.io.*;
import java.lang.reflect.Field;

/**
 * Created by sejun on 17. 2. 20.
 */
public class IOTest {

    public static void main(String[] args) throws IOException {
        String homedir = System.getProperty("user.home");
        System.out.println(homedir);
        String filePath = homedir +File.separator + "test.txt";
        File file = new File(filePath);

        if(file.exists()){
            file.createNewFile();
        }

        FileOutputStream fos = new FileOutputStream(file);

        String message = "hello world\n";
        byte[] messageBytes = message.getBytes();


//        한 바이트씩 읽는다고 함.
//        for(int i = 0 ; i < messageBytes.length; i++){
//            fos.write(messageBytes[i]);
//        }
//        fos.write(messageBytes);

//        FileInputStream fis = new FileInputStream(file);
//        while(true){
//            int readByte = fis.read();
//            if(readByte == -1){
//                break;
//            }
//            System.out.println((char)readByte);
//        }

//        int readByte = 0;
//        while((readByte = fis.read()) != -1){
//            System.out.println((char)readByte);
//        }

//        byte[] buf = new byte[3];
//        int readByte = 0;
//        if((readByte = fis.read(buf)) != -1){
//            System.out.print(new String(buf));
//        }

        FileOutputStream fos2 = new FileOutputStream(file);

        BufferedOutputStream bos2 = new BufferedOutputStream(fos);

        String message2 = "hello world\n";
        byte[] messageBytes2 = message.getBytes();
        long startTime = System.currentTimeMillis();
        for(int i = 0; i <10000000; i++){
//            fos.write(messageBytes);
            bos2.write(messageBytes2);
        }
        long endTime = System.currentTimeMillis();

        System.out.println(endTime-startTime +"ms ....");


        FileInputStream fis2 = new FileInputStream(file);
        BufferedInputStream bis2 = new BufferedInputStream(fis2);
        long startTime2 = System.currentTimeMillis();
        byte[] buf = new byte[3];
        int readByte = 0;
        if((readByte = fis2.read(buf)) != -1){
            System.out.print(new String(buf));
        }
        long endTime2 = System.currentTimeMillis();

        System.out.println(endTime2-startTime2 +"ms ....");

        /*
        * BufferedOuputStream의 경우에는 마지막에 내용이 전부 차지 않으면 write를 하지 않으므로
        * 반드시 flush를 이용해서 마지막 내용까지 write를 해줘야 함.
        * */
    }
}

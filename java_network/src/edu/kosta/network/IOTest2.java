package edu.kosta.network;

import java.io.*;

/**
 * Created by sejun on 17. 2. 20.
 */
public class IOTest2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String homedir = System.getProperty("user.home");
        String filePath = homedir + File.separator + "test.txt";
        File file = new File(filePath);

        if(file.exists()){
            file.createNewFile();
        }

//        FileOutputStream fos = new FileOutputStream(file);
//        BufferedOutputStream bos = new BufferedOutputStream(fos);
//        DataOutputStream dos = new DataOutputStream(bos);
//
//        dos.writeBoolean(true);
//        dos.writeInt(100000000);
//        dos.writeUTF("hello world");
//        dos.flush();
//
//        FileInputStream fis = new FileInputStream(file);
//        BufferedInputStream bis = new BufferedInputStream(fis);
//        DataInputStream dis = new DataInputStream(bis);
//
//        System.out.println(dis.readBoolean());
//        System.out.println(dis.readInt());
//        System.out.println(dis.readUTF());

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(new Person("Hong", 34));
        oos.writeObject(new Person("KIM", 32));

        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Person person1 = (Person) ois.readObject();
        Person person2 = (Person) ois.readObject();

        System.out.println(person1.toString());
        System.out.println(person2.toString());




    }
}

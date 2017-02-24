package edu.kosta.chatSample.core.message;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

public class MessageBody {
    //
    private String contents;
    
    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
    
    public byte[] toBytes() throws IOException {
        //
        byte[] fullBytes = null;
        if (contents != null) {
            byte[] contentsBytes = contents.getBytes("UTF-8");
            int bodyLength = contentsBytes.length;
            
            ByteOutputStream bos = new ByteOutputStream(bodyLength + 4);
            DataOutputStream dos = new DataOutputStream(bos);
            
            dos.writeInt(bodyLength);
            dos.write(contentsBytes);
            fullBytes = bos.getBytes();
            dos.close();
            
        } else {
            fullBytes = new byte[4];
            Arrays.fill(fullBytes, (byte) 0);
        }
        
        return fullBytes;
    }
}

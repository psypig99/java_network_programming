package edu.kosta.network.framework.message.json;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.google.gson.Gson;

import edu.kosta.network.framework.message.BlockMessage;
import edu.kosta.network.framework.message.MessageWriter;

public class JsonMessageWriter implements MessageWriter {

	private DataOutputStream dos;
	private Gson gson;
	
	public JsonMessageWriter(OutputStream os) {
		this.dos = new DataOutputStream(os);
		this.gson = new Gson();
	}
	
	@Override
	public void write(BlockMessage message) throws IOException {
		String jsonMessage = gson.toJson(message);
		System.out.println("write json:" + jsonMessage);
		dos.writeUTF(jsonMessage);
	}

}

package edu.kosta.chatSample.core.message;

public enum MessageType {
    //
    // Client <-> Server
    JoinRoom(10),
    SimpleMessage(20),
    SecretMessage(21),
    ParticipantList(30),
    
    // Client -> Server
    ExitRoom(90),
    
    // Server -> Client
    AddParticipant(40),
    RemoveParticipant(41),
    ErrorMessage(99);
    
    final int typeCode;
    
    private MessageType(int typeCode) {
        this.typeCode = typeCode;
    }
    
    public byte getTypeCode() {
        return (byte) typeCode;
    }

    public static MessageType findBy(byte messageType) {
        //
        for (MessageType type : values()) {
            if (type.getTypeCode() == messageType) {
                return type;
            }
        }
        
        return null;
    }
}

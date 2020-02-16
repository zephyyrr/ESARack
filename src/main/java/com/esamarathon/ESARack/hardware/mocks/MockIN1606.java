package com.esamarathon.ESARack.hardware.mocks;

import java.util.logging.Logger;
import java.io.IOException;
import java.net.UnknownHostException;
import com.esamarathon.ESARack.hardware.IN1606;

public class MockIN1606 extends IN1606 {

    private Logger logger;
    public MockIN1606(String addr, int port) {
        super(addr, port);
        logger = Logger.getLogger("MockIN1606");
    }

    @Override
    public boolean testConnection() {
        return true;
    }

    @Override
    public String ConnectAndSendCommand(String command) throws IOException, InterruptedException {
        return "142";
    }
}
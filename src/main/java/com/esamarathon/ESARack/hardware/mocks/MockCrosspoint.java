package com.esamarathon.ESARack.hardware.mocks;

import java.util.logging.Logger;
import java.io.IOException;
import java.net.UnknownHostException;
import com.esamarathon.ESARack.hardware.Crosspoint;

public class MockCrosspoint extends Crosspoint {

    private Logger logger;
    public MockCrosspoint(String addr, int port) {
        super(addr, port);
        logger = Logger.getLogger("MockCrosspoint");
    }

    public boolean testConnection() {
        return true;
    }

    public String ConnectAndSendCommand(String command) throws IOException, InterruptedException {
        return "";
    }
}
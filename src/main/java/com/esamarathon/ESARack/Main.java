package com.esamarathon.ESARack;

import java.util.logging.Logger;

import com.esamarathon.ESARack.hardware.*;
import com.esamarathon.ESARack.hardware.mocks.*;
import com.esamarathon.ESARack.web.API;

import jssc.SerialPort;

public class Main {

	public static void main(String[] args) {
		Logger logger = Logger.getLogger(Main.class.getCanonicalName());
		logger.info("Starting server...");

		String mode = System.getenv("ESARACK_MODE");
		if (mode.equals("demo")) {
			demoMode(logger);
			return;
		}

		VP50 vp50 = new VP50(new SerialPort("/dev/ttyUSB0"));
		OSSC ossc = new OSSC();
		
		logger.info("Setting up Crosspoint connection..");
		Crosspoint crosspoint = new Crosspoint("192.168.0.21", 23);
		logger.info("Setting up IN1606 connection..");
		IN1606 in1606 = new IN1606("192.168.0.20", 23);
		
		logger.info("Testing Crosspoint connection..");
		crosspoint.testConnection();
		logger.info("Testing IN1606 connection..");
		in1606.testConnection();
		logger.info("Devices are good to go.");
		new API(vp50, ossc, crosspoint, in1606);
	}

	public static void demoMode(Logger logger) {
		logger.info("Starting in Demo mode. No features will work.");

		VP50 vp50 = new VP50(new SerialPort("/dev/ttyUSB0"));
		OSSC ossc = new OSSC();

		logger.info("Setting up Crosspoint connection..");
		Crosspoint crosspoint = new MockCrosspoint("192.168.0.21", 23);
		logger.info("Setting up IN1606 connection..");
		IN1606 in1606 = new MockIN1606("192.168.0.20", 23);
		
		logger.info("Testing Crosspoint connection..");
		crosspoint.testConnection();
		logger.info("Testing IN1606 connection..");
		in1606.testConnection();
		logger.info("Devices are good to go.");
		
		new API(vp50, ossc, crosspoint, in1606);
	}

}

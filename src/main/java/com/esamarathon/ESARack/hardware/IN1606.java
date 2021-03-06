package com.esamarathon.ESARack.hardware;

import java.io.IOException;
import java.util.logging.Logger;

import com.esamarathon.ESARack.hardware.enums.AutoImageType;

/**
 * Implementation for controlling a Extron IN1606.
 * @author zephyyrr
 *
 */
public class IN1606 extends Extron implements InputSwitch {
	
	public static final char ESC = 0x1b;
	
	public IN1606(String addr, int port) {
		logger = Logger.getLogger("IN1606");
		this.ip = addr;
		this.port = port;
	}

	@Override
	public String getInput() throws IOException, InterruptedException {
		String resp = ConnectAndSendCommand("!");
		return resp;
	}

	@Override
	public void setInput(String input) throws IOException, InterruptedException {
		if (input.matches("^[1-6]$")) {
			ConnectAndSendCommand(input + "!");
		} else {
			throw new IllegalArgumentException("Invalid input. Must be number 1-6");
		}
	}
	
	public void executeAutoMode(AutoImageType type) throws IllegalArgumentException, IOException, InterruptedException {
		if (type == null) throw new IllegalArgumentException("type can not be null");
		ConnectAndSendCommand(Integer.toString(type.getMode()) + "*A");
	}
	
	public int getWidth() throws IOException, InterruptedException {
		String resp = ConnectAndSendCommand(ESC +"HSIZ\r");
		return Integer.parseInt(resp);
	}
	
	public void setWidth(int width) throws IOException, InterruptedException {
		width = Math.min(width, 4096);
		width = Math.max(width, 10);
		String sWidth = Integer.toString(width);
		ConnectAndSendCommand(ESC + sWidth + "HSIZ\r");
	}
	
	public int getHeight() throws IOException, InterruptedException {
		String resp = ConnectAndSendCommand(ESC +"VSIZ\r");
		return Integer.parseInt(resp);
	}
	
	public void setHeight(int height) throws IOException, InterruptedException {
		height = Math.min(height, 4096);
		height = Math.max(height, 10);
		String sHeight = Integer.toString(height);
		ConnectAndSendCommand(ESC + sHeight + "VSIZ\r");
	}
	
	public int getHorizontalShift() throws IOException, InterruptedException {
		String resp = ConnectAndSendCommand(ESC +"HCTR\r");
		return Integer.parseInt(resp);
	}
	
	public void setHorizontalShift(int shift) throws IOException, InterruptedException {
		shift = Math.min(shift, 2048);
		shift = Math.max(shift, -2048);
		String sShift = Integer.toString(shift);
		ConnectAndSendCommand(ESC + sShift + "HCTR\r");
	}
	
	public int getVerticalShift() throws IOException, InterruptedException {
		String resp = ConnectAndSendCommand(ESC +"VCTR\r");
		return Integer.parseInt(resp);
	}
	
	public void setVerticalShift(int shift) throws IOException, InterruptedException {
		shift = Math.min(shift, 2048);
		shift = Math.max(shift, -2048);
		String sShift = Integer.toString(shift);
		ConnectAndSendCommand(ESC + sShift + "VCTR\r");
	}
	
	/*
	public int getContrast(int contrast) {
		//TODO Auto-generated method stub
		return 0;
	}
	
	public void setContrast(int contrast) {
		//TODO Auto-generated method stub
	}
	
	public int getBrightness(int contrast) {
		//TODO Auto-generated method stub
		return 0;
	}
	
	public void setBrightness(int contrast) {
		//TODO Auto-generated method stub
	}
	*/
	

}

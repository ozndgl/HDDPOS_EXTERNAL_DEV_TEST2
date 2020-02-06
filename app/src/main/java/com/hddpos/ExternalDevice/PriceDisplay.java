package com.hddpos.ExternalDevice;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import android.serialport.SerialPort;

//客显

public class PriceDisplay {

	private SerialPort mSerialPort = null;
	private OutputStream mOutputStream = null;
	private int custdisp_port;

	public PriceDisplay(int custdisp_port) {
		this.custdisp_port = custdisp_port;
	}

	// 清屏
	public boolean clean() {
		if (custdisp_port <= 0)
			return false;

		try {
			openSerialPort();
			mOutputStream.write(new byte[] { 0x1b, 0x40 });
			mOutputStream.flush();
			closeSerialPort();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean write(int type, String price) {

		/*
		 * TYPE 1、单价 2、总价 3、付款额 4、找零
		 */
		if (custdisp_port <= 0)
			return false;

		try {
			openSerialPort();

			byte[] tem = price.getBytes("gbk");
			byte[] data = new byte[tem.length + 4];
			data[0] = 0x1b;
			data[1] = 0x51;
			data[2] = 0x41;
			data[data.length - 1] = 0x0d;
			System.arraycopy(tem, 0, data, 3, tem.length);
			System.out.println("command=" + Arrays.toString(data));

			// 显示价格
			mOutputStream.write(data);
			// 指定类型指示灯
			mOutputStream
					.write(new byte[] { 0x1b, 0x73, (byte) (0x30 + type) });

			mOutputStream.flush();
			closeSerialPort();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	private void openSerialPort() throws SecurityException, IOException {

		if (mSerialPort == null) {
			/* Open the serial port */
			String path = "/dev/ttyS" + (custdisp_port - 1);
			int baudrate = 2400;
			mSerialPort = new SerialPort(new File(path), baudrate);
			mOutputStream = mSerialPort.getOutputStream();
		}
	}

	private void closeSerialPort() {
		if (mSerialPort != null) {
			mSerialPort.close();
			mSerialPort = null;
			mOutputStream = null;
		}
	}

	private int integer(String str) {
		try {
			return Integer.valueOf(str);
		} catch (Exception e) {
			return 1;
		}
	}

}

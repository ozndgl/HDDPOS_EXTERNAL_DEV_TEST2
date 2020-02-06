package com.hddpos.ExternalDevice;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import android.serialport.SerialPort;
import android.util.Log;

public class ElectronicSays {
	private SerialPort mSerialPort = null;
	private OutputStream mOutputStream = null;
	private InputStream mInputStream = null;
	public static boolean isBusy = false;
	int electronicSays_port ;
	

	public ElectronicSays(int electronicSays_port) {
		super();
		// TODO Auto-generated constructor stub
		this.electronicSays_port = electronicSays_port;
		Log.e("ss", "electronicSays_port:"+electronicSays_port);
	}

	public interface ClickItemCallback {
		public void OnItemClick(String data);
	}

	public void setExit() {
		closeSerialPort();
	}

	// 动态取重
	public void getDynamicWeight(final int mode,
			final ClickItemCallback clickItemCallback) {
		if (isBusy || electronicSays_port <= 0)
			return;
		isBusy = true;

		new Thread() {
			public void run() {
				try {
					try {
						openSerialPort();
					} catch (Exception e) {
						clickItemCallback.OnItemClick("-1");
					}

					byte[] Weight = null;

					while (true) {

						int num = mInputStream.available();

						
						if (num >=24 ) {
							Log.e("ss", "num:"+num);
							Weight = new byte[num];
							
							Log.e("ss", "sss:"+Arrays.toString(Weight));
							
							mInputStream.read(Weight);

							String weight = handleData(Weight);
							Log.e("ss", "weight:"+weight);
						    
							clickItemCallback.OnItemClick(weight);

						}

						Thread.sleep(100);
					}
				} catch (Exception e) {
				}
			};
		}.start();
	}
	
	public static int bytesToInt(byte src) {
		return (src & 0xFF);
	}

	public static String jieshi(int a) {
		String ret;
		switch (a) {
			case 0xee:
				ret = "0";
				break;
			case 0xfe:
				ret = "0.";
				break;
			case 0x0a:
				ret = "1";
				break;
			case 0x1a:
				ret = "1.";
				break;
			case 0x67:
				ret = "2";
				break;
			case 0x77:
				ret = "2.";
				break;
			case 0x4f:
				ret = "3";
				break;
			case 0x5f:
				ret = "3.";
				break;
			case 0x8b:
				ret = "4";
				break;
			case 0x9b:
				ret = "4.";
				break;
			case 0xcd:
				ret = "5";
				break;
			case 0xdd:
				ret = "5.";
				break;
			case 0xed:
				ret = "6";
				break;
			case 0xfd:
				ret = "6.";
				break;
			case 0x0e:
				ret = "7";
				break;
			case 0x1e:
				ret = "7.";
				break;
			case 0xef:
				ret = "8";
				break;
			case 0xff:
				ret = "8.";
				break;
			case 0xcf:
				ret = "9";
				break;
			case 0xdf:
				ret = "9.";
				break;
			case 0xaf:
				ret = "A";
				break;
			case 0xe9:
				ret = "B";
				break;
			case 0xe4:
				ret = "C";
				break;
			case 0x6b:
				ret = "D";
				break;
			case 0xe5:
				ret = "E";
				break;
			case 0xa5:
				ret = "F";
				break;
			case 0x68:
				ret = "U";
				break;
			case 0x69:
				ret = "O";
				break;
			case 0x01:
				ret = "-";
				break;
			case 0x11:
				ret = "-.";
				break;
			case 0x29:
				ret = "N";
				break;
			case 0xe0:
				ret = "L";
				break;
			case 0x41:
				ret = "=";
				break;
			case 0x00:
				ret = " ";
				break;
			case 0x10:
				ret = " .";
				break;
			default:
				ret = "X";
				break;
		}
		return ret;
	}

	public String handleData(byte[] data) {
		if (bytesToInt(data[0]) == 0x04 && bytesToInt(data[1]) == 0x02
				&& bytesToInt(data[2]) == 0x08 && bytesToInt(data[3]) == 0x20
				&& bytesToInt(data[20]) == 0x06 && bytesToInt(data[21]) == 0x82
				&& bytesToInt(data[22]) == 0x48 && bytesToInt(data[23]) == 0x28) {

			String Str = "";
			String weightStr = "";

			for (int i = 3; i < 20; i++) {

				Str = bytesToHexString(data[i]);
				switch (i) {
					case 4:// 去皮
						weightStr = jieshi(Integer.valueOf(Str, 16));
						if (weightStr.indexOf(".") > 0) {
							weightStr = weightStr.substring(0, 1);
						} else
							break;
					case 5:
						weightStr += jieshi(Integer.valueOf(Str, 16));

						break;
					case 6:
						weightStr += jieshi(Integer.valueOf(Str, 16));
						break;
					case 7:
						weightStr += jieshi(Integer.valueOf(Str, 16));
						break;
					case 8:// 零位
						if (jieshi(Integer.valueOf(Str, 16)).indexOf(".") > 0) {

							weightStr += jieshi(Integer.valueOf(Str, 16))
									.substring(0, 1);
						} else {
							weightStr += jieshi(Integer.valueOf(Str, 16));
						}

						break;

					default:
						break;
				}

			}
			return weightStr;

		}
		return  "0.00";
	}

	private void openSerialPort() throws SecurityException, IOException {

		if (mSerialPort == null) {
			/* Open the serial port */
			String path = "/dev/ttyS" + electronicSays_port;
			int baudrate = 9600;
			Log.e("sss",path+","+baudrate);
			mSerialPort = new SerialPort(new File(path), baudrate);
			mOutputStream = mSerialPort.getOutputStream();
			mInputStream = mSerialPort.getInputStream();
		}
	}

	private void closeSerialPort() {
		if (mSerialPort != null) {

			try {
				mOutputStream.close();
				mInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			mSerialPort.close();
			mSerialPort = null;
			mOutputStream = null;
			mInputStream = null;
		}
		isBusy = false;
	}
	
	public static String bytesToHexString(byte src) {
		StringBuilder stringBuilder = new StringBuilder("");
		int v = src & 0xFF;
		String hv = Integer.toHexString(v);
		if (hv.length() < 2) {
			stringBuilder.append(0);
		}
		stringBuilder.append(hv);
		return stringBuilder.toString();
	}
}

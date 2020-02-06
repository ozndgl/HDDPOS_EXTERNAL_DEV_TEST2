package com.hddpos.ExternalDevice;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.util.Log;

import com.scpos.hddpos_external_dev_test.ConversionNumber;
import com.scpos.hddpos_external_dev_test.R;

//读卡器
public class UsbIcCard {

	// USB刷卡
	private final byte REQUEST_MODE[] = { 0x01 };// idel模式0x00,所有卡0x01
	private final byte LOADKEY_MODE = 0x00;// 0,0套密码,1,1套密码，2，2套密码。3，3不允许
	private final byte AUTHENTICATION_MODE = 0x00;// 0,0套密码,1,1套密码，2，2套密码。3，MCM-ROM密码验证
	private final byte SECNR = 0x01;// 扇区
	private final byte ADDRESS[] = { (byte) (SECNR * 4),
			(byte) (SECNR * 4 + 1), (byte) (SECNR * 4 + 2),
			(byte) (SECNR * 4 + 3) };// 块地址。
	private final byte BEEPTIME = (byte) 0x05;// 蜂鸣时长
	// 有效传输密码和用户密码为6 字节，但通讯时按8 字节发送，最后两个字节为任意值。
	// public final byte[] TKEY={0x7d,0x3e,(byte) 0x9f,0x4f,(byte) 0x95,(byte)
	// 0x95,0x33,(byte) 0x79};//传输密码
	public final byte[] NKEY = { (byte) 0xff, (byte) 0xff, (byte) 0xff,
			(byte) 0xff, (byte) 0xff, (byte) 0xff };// 下载到RAM中的密码

	private final String ACTION_USB_PERMISSION = "com.scpos.hddpos.USB_PERMISSION";
	public final static String DEFPWD = "FFFFFF";
	public final static int TIMEOUT = 10000;

	// 自增长序号起始位
	private int sqr = 0;
	// 接收数据
	byte[] byte2 = new byte[64];

	private UsbManager usbManager;
	private UsbDevice usbDevice;
	private PendingIntent pendingIntent;
	private UsbInterface usbInterface;
	private UsbDeviceConnection connection;
	private Context context;

	// 校验数组
	byte replenish[] = { 0x1a, 0x2f, 0x45, 0x64, (byte) 0x8e, (byte) 0x9a,
			(byte) 0xa1, (byte) 0xb5, (byte) 0xf6, 0x7e, (byte) 0xf9,
			(byte) 0xbf, (byte) 0x91, 0x34, 0x67, (byte) 0x80 };

	String[] libusb_error_name = { "函数调用成功", "在操作区域内无卡", "卡片CRC错误", "数值溢出",
			"验证不成功", "卡片奇偶校验错误", "通讯错误", "", "放冲突过程中读系列号错误", "", "卡片没有通过验证" };

	// 构造方法
	public UsbIcCard(Context context) {
		this.context = context;
		// TODO Auto-generated constructor stub
		usbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
		pendingIntent = PendingIntent.getBroadcast(context, 0, new Intent(
				ACTION_USB_PERMISSION), 0);

	}

	// 判断读卡器是否连接
	public boolean deviceIsConnection() {

		usbDevice = null;
		// 取连接到设备上的USB设备集合
		HashMap<String, UsbDevice> map = usbManager.getDeviceList();
		// 遍历集合取指定的USB设备
		Log.e("deviceIsConnection", "map:" + map.size());
		for (UsbDevice device : map.values()) {
			Log.e("deviceIsConnection", "VendorId:" + device.getVendorId()
					+ ",ProductId:" + device.getProductId());
			// usb 读卡器
			/*
			 * if (4292 == device.getVendorId() && 33485 ==
			 * device.getProductId()) {// 明华读卡器 // pid,vid usbDevice = device; }
			 */

			if (1241 == device.getVendorId() && 46388 == device.getProductId()) {// 明华读卡器
				// pid,vid
				usbDevice = device;
			}
		}
		return usbDevice == null ? false : true;
	}

	// 判断读卡器有权操作
	public boolean hasPermission() {
		if (usbManager.hasPermission(usbDevice)) {
			return true;
		} else {
			IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
			context.registerReceiver(mUsbReceiver, filter);
			// 没有权限询问用户是否授予权限
			usbManager.requestPermission(usbDevice, pendingIntent); // 该代码执行后，系统弹出一个对话框，
			// 询问用户是否授予程序操作USB设备的权限
			return false;
		}
	}

	// 判断卡是否放上
	public boolean CardIsReady() {
		boolean b = false;
		try {

			if (connection == null) {
				usbInterface = usbDevice.getInterface(0);
				connection = usbManager.openDevice(usbDevice);
				connection.claimInterface(usbInterface, true);
			}
			// 0x45 将卡片置于暂停状态
			byte tmp45[] = getBccList((byte) 0x45, (byte) 0x00, null);
			if (getRespone(connection, tmp45) == 0) {
				// 41
				byte tmp41[] = getBccList((byte) 0x41, (byte) 0x01,
						REQUEST_MODE);
				if (getRespone(connection, tmp41) == 0) {
					b = true;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			connection = null;
		}
		return b;
	}

	// 密码校验
	public boolean PwdCheck(String pwd) {
		boolean b = false;
		try {

			byte[] tem;
			if (pwd == null || pwd.length() != 6)
				return b;

			if (pwd.equalsIgnoreCase(DEFPWD))
				tem = NKEY;
			else {
				tem = pwd.getBytes("gbk");
			}

			// 42防冲突
			byte tmp42[] = getBccList((byte) 0x42, (byte) 0x05,
					new byte[] { 0x00 });
			if (getRespone(connection, tmp42) == 0) {

				// 43防冲突选择卡片
				byte tm[] = new byte[6];
				tm[0] = byte2[5];
				tm[1] = byte2[6];
				tm[2] = byte2[7];
				tm[3] = byte2[8];
				byte tmp43[] = getBccList((byte) 0x43, (byte) 0x04, tm);
				if (getRespone(connection, tmp43) == 0) {

					byte[] m1 = new byte[8];
					m1[0] = AUTHENTICATION_MODE;
					m1[1] = ADDRESS[0];
					System.arraycopy(tem, 0, m1, 2, tem.length);

					// 0x5f ？？ //匹配旧密码
					byte tmp5f[] = getBccList((byte) 0x5f, (byte) 0x08, m1);
					if (getRespone(connection, tmp5f) == 0) {
						b = true;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			connection = null;
		}
		return b;

	}

	// 清除
	public boolean emptyCard(String psd) {
		boolean b = false;
		try {
			byte[] tmp47 = getBccWriteList((byte) 0x47, (byte) 0x11,
					ADDRESS[0], new byte[] { 0x00 });
			byte[] tmp47_2 = getBccWriteList((byte) 0x47, (byte) 0x11,
					ADDRESS[1], new byte[] { 0x00 });
			if (getRespone(connection, tmp47) == 0) {
				if (getRespone(connection, tmp47_2) == 0) {

					if (modifyPass(psd, DEFPWD)) {
						b = true;
						// 蜂鸣
						beep();
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqr = 0;
			connection = null;
		}
		return b;

	}

	// 发卡
	public boolean writeCard(String cardno) {

		if (cardno == null || cardno.equals(""))
			return false;

		boolean b = false;

		try {
			if (cardno.length() > 0 && cardno.length() <= 16) {
				// 0x47 将数据写入卡中的某一块
				byte[] dd = getBccWriteList((byte) 0x47, (byte) 0x11,
						ADDRESS[0], cardno.getBytes());

				if (getRespone(connection, dd) == 0) {
					beep();
					b = true;
				}
			} else if (cardno.length() > 16 && cardno.length() <= 32) {
				String editString1 = cardno.substring(0, 16);
				String editString2 = cardno.substring(16);
				byte[] dd = getBccWriteList((byte) 0x47, (byte) 0x11,
						ADDRESS[0], editString1.getBytes());
				byte[] dd2 = getBccWriteList((byte) 0x47, (byte) 0x11,
						ADDRESS[1], editString2.getBytes());
				if (getRespone(connection, dd) == 0
						&& getRespone(connection, dd2) == 0) {
					beep();
					b = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			sqr = 0;
			connection = null;
		}
		return b;
	}

	// 读卡
	public String readCard() {
		// TODO Auto-generated method stub
		String s = "";
		try {

			byte tmp46[] = getBccList((byte) 0x46, (byte) 0x01,
					new byte[] { ADDRESS[0] });
			if (getRespone(connection, tmp46) == 0) {
				s = ConversionNumber.Bytes2HexString(byte2);
				s = s.substring(10, 42);
				byte tmp46_2[] = getBccList((byte) 0x46, (byte) 0x01,
						new byte[] { ADDRESS[1] });
				if (getRespone(connection, tmp46_2) == 0) {
					String s1 = ConversionNumber.Bytes2HexString(byte2);
					s1 = s1.substring(10, 42);
					s = s + s1;

					String dataString = "";
					for (int i = 0; i < s.length(); i = i + 2) {
						String temp = s.substring(i, i + 2);
						if (!"00".equals(temp))
							dataString += temp;
					}
					s = ConversionNumber.HexString2String(dataString);
					beep();
					return s;
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqr = 0;
			connection = null;
		}
		return null;

	}

	// 修改密码
	public boolean modifyPass(String oldPwd, String newPwd) {
		try {
			byte[] oldPwds, newPwds;
			if (oldPwd == null || newPwd == null || oldPwd.length() != 6
					|| newPwd.length() != 6)
				return false;
			if (oldPwd.equalsIgnoreCase(DEFPWD))
				oldPwds = NKEY;
			else
				oldPwds = oldPwd.getBytes("gbk");

			if (newPwd.equalsIgnoreCase(DEFPWD))
				newPwds = NKEY;
			else
				newPwds = newPwd.getBytes("gbk");

			return modifyPass(oldPwds, newPwds);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		}
	}

	// 修改密码
	private boolean modifyPass(byte[] oldPwd, byte[] newPwd) {

		boolean b = false;
		try {

			if (connection == null) {
				usbInterface = usbDevice.getInterface(0);
				connection = usbManager.openDevice(usbDevice);
				connection.claimInterface(usbInterface, true);
			}

			// 0x4e 将RF系统关闭一段时间
			byte tmp4e[] = getBccList((byte) 0x4e, (byte) 0x02, new byte[] {
					0x03, 0x00 });
			if (getRespone(connection, tmp4e) == 0) {
				// 41
				byte tmp41[] = getBccList((byte) 0x41, (byte) 1, REQUEST_MODE);
				if (getRespone(connection, tmp41) == 0) {

					// 42防冲突
					byte tmp42[] = getBccList((byte) 0x42, (byte) 0x05,
							new byte[] { 0x00 });
					if (getRespone(connection, tmp42) == 0) {
						// 43防冲突选择卡片
						byte tm[] = new byte[6];
						tm[0] = byte2[5];
						tm[1] = byte2[6];
						tm[2] = byte2[7];
						tm[3] = byte2[8];
						byte tmp43[] = getBccList((byte) 0x43, (byte) 0x04, tm);
						if (getRespone(connection, tmp43) == 0) {

							byte[] m1 = new byte[8];
							m1[0] = AUTHENTICATION_MODE;
							m1[1] = ADDRESS[0];
							System.arraycopy(oldPwd, 0, m1, 2, oldPwd.length);

							// 0x5f ？？ //匹配旧密码
							byte tmp5f[] = getBccList((byte) 0x5f, (byte) 0x08,
									m1);
							if (getRespone(connection, tmp5f) == 0) {

								// 0x46 读出卡中某一块的16 个字节数据
								byte tmp46[] = getBccList((byte) 0x46,
										(byte) 0x01, new byte[] { ADDRESS[3] });
								if (getRespone(connection, tmp46) == 0) {
									String s = ConversionNumber
											.Bytes2HexString(byte2);
									s = s.substring(10, 42);
									System.out.println("s=" + s);
								}

								byte[] m2 = new byte[] { newPwd[0], newPwd[1],
										newPwd[2], newPwd[3], newPwd[4],
										newPwd[5], (byte) 0xff, 0x07,
										(byte) 0x80, 0x69, (byte) 0xff,
										(byte) 0xff, (byte) 0xff, (byte) 0xff,
										(byte) 0xff, (byte) 0xff };

								// 0x47 将数据写入卡中的某一块 //在0xff, 0xff, 0xff , 0xff,
								// 0xff, 0xff 的基础上修改密码
								byte tmp47[] = getBccWriteList((byte) 0x47,
										(byte) 0x11, ADDRESS[3], m2);
								if (getRespone(connection, tmp47) == 0) {

									// 0x45 将卡片置于暂停状态
									byte tmp45[] = getBccList((byte) 0x45,
											(byte) 0x00, null);
									if (getRespone(connection, tmp45) == 0) {
										b = true;
									}

								}

							}
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			connection = null;
		}
		return b;

	}

	// 蜂鸣
	public void beep() {
		if (connection != null) {
			byte tmp10_1[] = getBccList((byte) 0x36, (byte) 0x02,
					new byte[] { BEEPTIME });
			connection.controlTransfer(0x21, 0x09, 0x306, 0, tmp10_1,
					tmp10_1.length, 2000);
		}
	}

	public int getRespone(UsbDeviceConnection connection, byte[] tmp) {
		if (connection.controlTransfer(0x21, 0x09, 0x306, 0, tmp, tmp.length,
				1000) <= 0) {
			System.err.println("err:write");
			return -1;
		}

		// 后期优化，应返回byte2,对byte2的N位标记判断操作
		if (connection.controlTransfer(0xa1, 0x01, 0x306, 0, byte2,
				byte2.length, 1000) <= 0) {
			System.err.println("err:read");
			return -1;
		}
		if ((byte2[3] & 0xff) != 0)
			System.out.println("errId=" + (byte2[3] & 0xff));

		return byte2[3] & 0xff;
	}

	public String getErrString() {
		int id = byte2[3] & 0xff;
		if (id > 0 && id < libusb_error_name.length) {
			return libusb_error_name[id];
		}
		return context.getString(R.string.member_card_err1);
	}

	public byte[] getBccList(byte fuction, byte length, byte[] original) {
		int dataLenght = original == null ? 0 : original.length;
		if (dataLenght < length)
			dataLenght = length;

		byte[] req = new byte[255];
		byte xor = 0x00;
		req[0] = 0x06;
		req[1] = 0x02;
		req[2] = (byte) sqr;
		req[3] = fuction;
		req[4] = length;
		if (original != null) {
			for (int i = 0; i < original.length; i++) {
				req[5 + i] = original[i];
			}
			for (int j = original.length; j < length; j++) {
				req[5 + j] = 0x00;
			}
		}
		for (int k = 2; k < req.length - 3; k++) {
			xor = (byte) (xor ^ req[k]);
		}
		xor = (byte) ((xor + replenish[xor & 0xf]) & 0xff);
		req[5 + length] = xor;
		req[6 + length] = 0x10;
		req[7 + length] = 0x03;
		sqr++;
		if (sqr == 256) {
			sqr = 0;
		}
		return req;

	}

	// 写入
	public byte[] getBccWriteList(byte fuction, byte length, byte area,
			byte[] original) {
		int dataLenght = original == null ? 0 : original.length;
		if (dataLenght < length)
			dataLenght = length;
		// byte[] req=new byte[dataLenght+9];
		byte[] req = new byte[255];
		byte xor = 0x00;
		req[0] = 0x06;
		req[1] = 0x02;
		req[2] = (byte) sqr;
		req[3] = fuction;
		req[4] = length;
		req[5] = area;

		for (int i = 0; i < original.length; i++) {
			req[6 + i] = original[i];
		}
		for (int j = original.length; j < length - 1; j++) {
			req[6 + j] = 0x00;
		}
		for (int k = 2; k < req.length - 3; k++) {
			xor = (byte) (xor ^ req[k]);
		}
		xor = (byte) ((xor + replenish[xor & 0xf]) & 0xff);
		req[5 + length] = xor;
		req[6 + length] = 0x10;
		req[7 + length] = 0x03;
		sqr++;
		if (sqr == 256) {
			sqr = 0;
		}
		return req;

	}

	// //密码写入
	// public byte[] getBccMiMaList(byte fuction,byte length,byte mode,byte
	// area,byte[] original){
	// int dataLenght= original==null?0:original.length;
	// byte[] req=new byte[dataLenght+10];
	// byte xor=0x00;
	// req[0]=0x06;
	// req[1]=0x02;
	// req[2]=(byte) sqr;
	// req[3]=fuction;
	// req[4]=length;
	// req[5]=mode;
	// req[6]=area;
	// for(int i=0;i<dataLenght;i++){
	// req[7+i]=original[i];
	// }
	// for(int j=dataLenght;j<length-1;j++){
	// req[7+j]=0x00;
	// }
	// for(int k=2;k<req.length-3;k++){
	// xor=(byte) (xor^req[k]);
	// }
	// xor=(byte) ((xor+replenish[xor&0xf])&0xff);
	// req[5+length]=xor;
	// req[6+length]=0x10;
	// req[7+length]=0x03;
	// sqr++;
	// if(sqr==256){
	// sqr=0;
	// }
	// return req;
	//
	// }

	private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (ACTION_USB_PERMISSION.equals(action)) {
				synchronized (this) {
					usbDevice = (UsbDevice) intent
							.getParcelableExtra(UsbManager.EXTRA_DEVICE);
					if (intent.getBooleanExtra(
							UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
						if (usbDevice != null) {
							context.unregisterReceiver(mUsbReceiver);
							// readCard();
						}
					} else {
					}
				}
			}
		}
	};

}

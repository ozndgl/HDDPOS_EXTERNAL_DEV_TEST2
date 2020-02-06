package com.hddpos.ExternalDevice;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbConstants;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.util.Log;

//条码打印机
public class BarcodePrinter {

	public interface ClickItemCallback {
		public void OnItemClick(int what, Object obj);
	}

	UsbInterface myInterface;

	private String TAG = "BarcodePrinter";

	private String PVStr = "";
	private Context mContext;
	private UsbManager usbManager;
	private UsbDevice usbDevice;
	private PendingIntent pendingIntent;
	private static final String ACTION_USB_PERMISSION = "com.scpos.hddpos.USB_PERMISSION";
	private StringBuilder sb = null;
	private ClickItemCallback callback;
	private boolean isFirst = true;
	// DENSITY 浓度 0-15，SPEED打印速度 1.5,2.0,3.0,4.0，GAP:间距0-25，HOME 位置校验 ，SOUND
	// 3,200 蜂鸣
	// textL 文本左边距 ，textT 文本顶部距 ，textGap 文本之间的间隔，textScal 文本的放大倍数
	private int width = 40, height = 30, density = 15, speed = 4, gap = 2,
			textL = 20, textT = 20, textGap = 10, textScalX = 1, textScalY = 1;

	public BarcodePrinter(String pStr, Context mContext,
			ClickItemCallback callback) {
		super();
		// TODO Auto-generated constructor stub
		PVStr = pStr;
		this.mContext = mContext;
		usbManager = (UsbManager) mContext
				.getSystemService(Context.USB_SERVICE);
		pendingIntent = PendingIntent.getBroadcast(mContext, 0, new Intent(
				ACTION_USB_PERMISSION), 0);
		sb = new StringBuilder();
		this.callback = callback;
	}

	public void setSize(int width, int height) {// 设置 标签宽高
		this.width = width;
		this.height = height;
	}

	public void setDensity(int density) {// 设置 标签打印浓度
		this.density = density;
	}

	public void setGap(int gap) {// 设置 标签间隔
		this.gap = gap;
	}

	public void setTextGap(int textGap) {// 设置 文本垂直间距
		this.textGap = textGap;
	}

	public void setTextL(int textL) {// 设置 文本左间距
		this.textL = textL;
	}

	public void setTextT(int textT) {// 设置 文本上间距
		this.textT = textT;
	}

	public void setTextScal(int textScal) {// 设置 文本放大倍数
		this.textScalX = textScal;
		this.textScalY = textScal;
	}

	public void reset() {// 设置 默认配置
		width = 30;
		height = 40;
		density = 15;
		speed = 4;
		gap = 2;
		textL = 20;
		textT = 20;
		textGap = 20;
		textScalX = 1;
		textScalY = 1;
	}

	public void setTitle(String s, int scalX, int scalY) {// 设置标题

		sb.append("TEXT " + textL + "," + textT + ",\"TSS24.BF2\",0," + scalX
				+ "," + scalY + ",\"" + s + "\"\n");
		textT += (24 * scalY + textGap);
	}

	public void write(String s) {// 累加。。。

		sb.append("TEXT " + textL + "," + textT + ",\"TSS24.BF2\",0,"
				+ textScalX + "," + textScalY + ",\"" + s + "\"\n");
		textT += (24 * textScalY + textGap);
	}

	public void setBarcode(String s, int height) {// 设置一维条形码。。。

		sb.append("BARCODE " + textL + "," + textT + ",\"128M\"," + height
				+ ",1,0,2,2,\"" + s + "\"\n");
		textT += (24 * textScalY + textGap);
	}

	public void commit() {// 执行

		// TODO Auto-generated method stub
		try {
			// 取连接到设备上的USB设备集合
			HashMap<String, UsbDevice> map = usbManager.getDeviceList();
			// 遍历集合取指定的USB设备
			for (UsbDevice device : map.values()) {
				// VendorID 和 ProductID 十进制
				// usb 条码打印机

				String PID_VID = PVStr;

				int startPos = PID_VID.indexOf("[");
				int Pos = PID_VID.indexOf(",");
				int endPos = PID_VID.indexOf("]");

				if (startPos > 0 && startPos < Pos && Pos < endPos) {

					if (PID_VID.substring(startPos + 1, Pos).toString()
							.equals("" + device.getVendorId())
							&& PID_VID.substring(Pos + 1, endPos).toString()
									.equals("" + device.getProductId())) {
						usbDevice = device;
					}

				}
			}
			// 程序是否有操作设备的权限
			if (usbManager.hasPermission(usbDevice)) {
				exectPrint();
			} else {

				IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
				mContext.registerReceiver(mUsbReceiver, filter);

				// 没有权限询问用户是否授予权限
				usbManager.requestPermission(usbDevice, pendingIntent); // 该代码执行后，系统弹出一个对话框，
																		// 询问用户是否授予程序操作USB设备的权限
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 打印机没开启，或者线没有接好。
			callback.OnItemClick(-1, null);
		}
	}

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
						if (usbDevice != null && isFirst) {
							isFirst = false;
							mContext.unregisterReceiver(mUsbReceiver);
							exectPrint();

						}
					} else {
						Log.d("denied", "permission denied for device "
								+ usbDevice);
					}
				}
			}
		}
	};

	public void exectPrint() {

		UsbEndpoint outEndpoint = null;
		UsbInterface usbInterface = usbDevice.getInterface(0);
		for (int i = 0; i <= usbInterface.getEndpointCount(); i++) {
			if (usbInterface.getEndpoint(i).getDirection() == UsbConstants.USB_DIR_OUT) {
				outEndpoint = usbInterface.getEndpoint(i); // 读数据节点
				break;
			}
		}
		// USBEndpoint为读写数据所需的节点
		UsbDeviceConnection connection = usbManager.openDevice(usbDevice);

		if (connection != null && connection.claimInterface(usbInterface, true)) {

			if (!"".equals(sb.toString().trim())) {
				// 发送数据
				byte tmp[] = null;
				try {

					String s = "SIZE " + width + " mm," + height + " mm\n"
							+ "GAP " + gap + " mm,0 mm\n" + "REFERENCE 0,0\n"
							+ "SPEED " + speed + "\n" + "DENSITY " + density
							+ "\n" + "SET PEEL OFF\n" + "SET CUTTER OFF\n"
							+ "SET PARTIAL_CUTTER OFF\n" + "SET TEAR ON\n"
							+ "DIRECTION 1\n" + "SHIFT 0\n" + "OFFSET 0 mm\n"
							+ "CLS\n"// 清楚image的缓存
							+ sb.toString() + "PRINT 1,1\n";

					System.out.println("s=" + sb.toString());

					tmp = s.getBytes("GBK");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();

				}

				int out = connection.bulkTransfer(outEndpoint, tmp, tmp.length,
						10000);
				Log.e("MyThread3", "out:" + out);
				connection.close();
				if (out < 0)
					callback.OnItemClick(-1, null);// 打印机接受出现异常。
			} else
				connection.close();

		} else
			Log.e("denied", "<---open error --->");

	}

}

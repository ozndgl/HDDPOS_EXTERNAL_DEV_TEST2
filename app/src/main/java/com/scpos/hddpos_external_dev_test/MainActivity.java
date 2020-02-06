package com.scpos.hddpos_external_dev_test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hddpos.ExternalDevice.BarcodePrinter;
import com.hddpos.ExternalDevice.Constant;
import com.hddpos.ExternalDevice.ElectronicSays;
import com.hddpos.ExternalDevice.PriceDisplay;
import com.hddpos.ExternalDevice.Printer;
import com.hddpos.ExternalDevice.UsbIcCard;

public class MainActivity extends Activity implements OnClickListener {

	private Button printTest, shuakaqi, kexian, jijiacheng, usbIcCard,
			tiaomadayinji, scanTest;
	private Handler myHandler = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		Constant.M_CONTEXT = MainActivity.this;

		changeLangue();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		printTest = (Button) findViewById(R.id.button1);
		shuakaqi = (Button) findViewById(R.id.button2);
		kexian = (Button) findViewById(R.id.button3);
		jijiacheng = (Button) findViewById(R.id.button4);
		usbIcCard = (Button) findViewById(R.id.button5);
		tiaomadayinji = (Button) findViewById(R.id.button6);
		scanTest = (Button) findViewById(R.id.button7);

		printTest.setOnClickListener(this);
		shuakaqi.setOnClickListener(this);
		kexian.setOnClickListener(this);
		jijiacheng.setOnClickListener(this);
		usbIcCard.setOnClickListener(this);
		tiaomadayinji.setOnClickListener(this);
		scanTest.setOnClickListener(this);

		myHandler = new Handler() {
			public void handleMessage(android.os.Message msg) {

				switch (msg.what) {
				case 1:
					Toast.makeText(MainActivity.this, "" + msg.obj,
							Toast.LENGTH_LONG).show();
					break;
				default:
					new AlertDialog.Builder(MainActivity.this).setTitle("INFO")
							.setMessage(msg.obj + "")
							.setNegativeButton("OK", null).show();
					break;
				}

			};
		};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void changeLangue() {

		Configuration config1;
		DisplayMetrics dm1;
		String languType = null;
		String able = getResources().getConfiguration().locale.getCountry();

		if (able.equals("CN")) {
			languType = "chinese";
		} else if (able.equals("TW")) {
			languType = "traditionalChinese";
		} else {
			languType = "english";
		}
		config1 = getResources().getConfiguration();
		dm1 = getResources().getDisplayMetrics();

		if ("chinese".equals(languType)) {
			config1.locale = Locale.SIMPLIFIED_CHINESE;
		} else if ("traditionalChinese".equals(languType)) {
			config1.locale = Locale.TRADITIONAL_CHINESE;
		} else {
			config1.locale = Locale.US;
		}
		getResources().updateConfiguration(config1, dm1);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (arg0.getId() == printTest.getId()) {

			View view = LayoutInflater.from(MainActivity.this).inflate(
					R.layout.print_test, null);
			final Spinner tab2_spinner1 = (Spinner) view
					.findViewById(R.id.tab2_spinner1);
			final Spinner tab2_spinner2 = (Spinner) view
					.findViewById(R.id.tab2_spinner7);
			final Spinner tab2_spinner3 = (Spinner) view
					.findViewById(R.id.serialSelect);
			final Spinner tab2_spinner4 = (Spinner) view
					.findViewById(R.id.serialrateSet);
			final EditText ip = (EditText) view.findViewById(R.id.input_ip);
			final Button frontPrintTest = (Button) view
					.findViewById(R.id.frontPrintTest);
			final Button qianxiangTest = (Button) view
					.findViewById(R.id.qianxiangTest);

			tab2_spinner1
					.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> arg0,
								View arg1, int arg2, long arg3) {
							// TODO Auto-generated method stub
							System.out.println(arg2);
							switch (arg2) {
							case 0:// 网口
								ip.setEnabled(true);
								tab2_spinner3.setEnabled(false);
								tab2_spinner4.setEnabled(false);
								break;
							case 1:// usb
								ip.setEnabled(false);
								tab2_spinner3.setEnabled(false);
								tab2_spinner4.setEnabled(false);
								break;
							case 2:// 串口
								ip.setEnabled(false);
								tab2_spinner3.setEnabled(true);
								tab2_spinner4.setEnabled(true);
								break;
							}
						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
						}
					});

			frontPrintTest.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub

					final ProgressDialog proDialog = android.app.ProgressDialog.show(
							MainActivity.this,
							MainActivity.this.getString(R.string.new12),
							MainActivity.this.getString(R.string.new13));

					switch (tab2_spinner1.getSelectedItemPosition()) {
					case 0:// 网口
						final String ipStr = ip.getText().toString();
						if (ipStr != null && ipStr.length() > 0) {
							new Thread() {
								public void run() {
									new Printer(ipStr, new Printer.CallBack() {

										@Override
										public void onFailure(String err) {
											// TODO Auto-generated method stub
											System.err.println(err);
											Message msg = myHandler
													.obtainMessage();
											msg.obj = MainActivity.this
													.getString(R.string.new14);
											msg.sendToTarget();
										}

										@Override
										public void onSuccess(Printer printer) {
											// TODO Auto-generated method stub
											printer.writeHex("1B40");// 清除打印缓冲区数据，打印模式被设为上电时的默认值模式
											printer.writeHex("1B6101");// 选择字符对齐模式（居中对齐）
											printer.writeHex("1C5701");// (设置倍高背宽)
											// printer.write("Net Export Printer Test,success!!");
											printer.write(0 + ",测试!!");
											printer.writeHex("0A");// 打印并换行
											printer.write(1 + ",测试!!");
											printer.writeHex("0A");// 打印并换行
											printer.write(2 + ",测试!!");
											printer.writeHex("0A");// 打印并换行
											printer.writeHex("1B6408");// 打印并走纸,06
																		// 行
											printer.writeHex("1D5601");// 切纸
										}
									});
									proDialog.dismiss();
								};
							}.start();
						} else
							proDialog.dismiss();

						break;
					case 1:// USB
						new Thread() {
							public void run() {
								new Printer(MainActivity.this,
										new Printer.CallBack() {

											@Override
											public void onFailure(String err) {
												// TODO Auto-generated method
												// stub
												System.err.println(err);
												Message msg = myHandler
														.obtainMessage();
												msg.obj = MainActivity.this
														.getString(R.string.new15);
												msg.sendToTarget();
											}

											@Override
											public void onSuccess(
													Printer printer) {
												// TODO Auto-generated method
												// stub
												
												try {
													printer.writeHex("1B40");// 清除打印缓冲区数据，打印模式被设为上电时的默认值模式
													printer.writeHex("1B6101");// 选择字符对齐模式（居中对齐）
													printer.writeHex("1C5701");// (设置倍高背宽)
													printer.write(0 + ",测试!!");
													printer.writeHex("0A");// 打印并换行
													printer.write(1 + ",测试!!");
													printer.writeHex("0A");// 打印并换行
													printer.write(2 + ",测试!!");
													printer.writeHex("0A");// 打印并换行
													printer.writeMapHex(printer.getPrintPictureCmd(getImageFromAssetsFile(MainActivity.this,
																	"print.jpg")));
												} catch (Exception e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
												

												/*
												 * // 打印并走纸,06 // 行
												 * printer.writeHex("1D5601");//
												 * 切纸
												 */}
										});

								proDialog.dismiss();
							};
						}.start();

						break;
					case 2:// 串口
						new Thread() {
							public void run() {
								new Printer(tab2_spinner3
										.getSelectedItemPosition(),
										tab2_spinner4.getSelectedItem()
												.toString(),
										new Printer.CallBack() {

											@Override
											public void onFailure(String err) {
												// TODO Auto-generated method
												// stub
												System.err.println(err);
												Message msg = myHandler
														.obtainMessage();
												msg.obj = MainActivity.this
														.getString(R.string.new16);
												msg.sendToTarget();
											}

											@Override
											public void onSuccess(
													Printer printer) {
												// TODO Auto-generated method
												// stub
												Message msg = new Message();
												msg.what = 1;
												msg.obj = MainActivity.this
														.getString(R.string.new30);
												myHandler.sendMessage(msg);

												printer.writeHex("1B40");// 清除打印缓冲区数据，打印模式被设为上电时的默认值模式
												printer.writeHex("1B6101");// 选择字符对齐模式（居中对齐）
												printer.writeHex("1C5701");// (设置倍高背宽)
												printer.writeHex("1B4501");// 字体加粗

												printer.write("打印测试!!");
												printer.writeHex("0A");

												printer.writeHex("1C5700");// (取消设置倍高背宽)
												printer.writeHex("1B6100");// 选择字符对齐模式（左对齐）

												for (int i = 0; i < 20; i++) {
													printer.write("测试顺序:"
															+ (i++));
													printer.writeHex("0A");// 打印并换行
												}
											}
										});
								proDialog.dismiss();
							};
						}.start();
						break;
					}
				}
			});

			qianxiangTest.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					final ProgressDialog proDialog = android.app.ProgressDialog.show(
							MainActivity.this,
							MainActivity.this.getString(R.string.new12),
							MainActivity.this.getString(R.string.new13));

					switch (tab2_spinner1.getSelectedItemPosition()) {
					case 0:// 网口
						final String ipStr = ip.getText().toString();
						if (ipStr != null && ipStr.length() > 0) {
							new Thread() {
								public void run() {
									new Printer(ipStr, new Printer.CallBack() {

										@Override
										public void onFailure(String err) {
											// TODO Auto-generated method stub
											System.err.println("网口:" + err);
											Message msg = myHandler
													.obtainMessage();
											msg.obj = MainActivity.this
													.getString(R.string.new17);
											msg.sendToTarget();
										}

										@Override
										public void onSuccess(Printer printer) {
											// TODO Auto-generated method stub
											printer.writeHex("1B70002828");// 打印指令
										}
									});
									proDialog.dismiss();
								};
							}.start();
						} else
							proDialog.dismiss();

						break;
					case 1:// USB
						new Thread() {
							public void run() {
								new Printer(MainActivity.this,
										new Printer.CallBack() {

											@Override
											public void onFailure(String err) {
												// TODO Auto-generated method
												// stub
												System.err
														.println("USB:" + err);
												Message msg = myHandler
														.obtainMessage();
												msg.obj = MainActivity.this
														.getString(R.string.new18);
												msg.sendToTarget();
											}

											@Override
											public void onSuccess(
													Printer printer) {
												// TODO Auto-generated method
												// stub
												printer.writeHex("1B70002828");// 打印指令
											}
										});

								proDialog.dismiss();
							};
						}.start();

						break;
					case 2:// 串口
						new Thread() {
							public void run() {
								new Printer(tab2_spinner3
										.getSelectedItemPosition(),
										tab2_spinner4.getSelectedItem()
												.toString(),
										new Printer.CallBack() {

											@Override
											public void onFailure(String err) {
												// TODO Auto-generated method
												// stub
												Log.e("串口", "err:" + err);
												System.err.println("串口:" + err);
												Message msg = myHandler
														.obtainMessage();
												msg.obj = MainActivity.this
														.getString(R.string.new16);
												msg.sendToTarget();
											}

											@Override
											public void onSuccess(
													Printer printer) {
												// TODO Auto-generated method
												// stub
												Log.e("串口", "串口打印测试11");
												printer.writeHex("1B70001B1B");// 打印指令
												Message msg = myHandler
														.obtainMessage();
												msg.obj = MainActivity.this
														.getString(R.string.new30);
												msg.sendToTarget();
											}
										});
								proDialog.dismiss();
							};
						}.start();
						break;
					}
				}
			});
			new AlertDialog.Builder(MainActivity.this)
					.setTitle("PRINT_TEST")
					.setView(view)
					.setNegativeButton(
							MainActivity.this.getString(R.string.public_cancel),
							null).show();

		} else if (arg0.getId() == shuakaqi.getId()) {
			new AlertDialog.Builder(MainActivity.this)
					.setTitle("ReadCardMachine_TEST")
					.setMessage(MainActivity.this.getString(R.string.new20))
					.setNegativeButton(
							MainActivity.this.getString(R.string.public_cancel),
							null)
					.setPositiveButton(
							MainActivity.this
									.getString(R.string.public_confirm),
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {
									// TODO Auto-generated method stub
									((InputMethodManager) MainActivity.this
											.getSystemService("input_method"))
											.showInputMethodPicker();
								}
							}).show();

		} else if (arg0.getId() == kexian.getId()) {

			View view = LayoutInflater.from(MainActivity.this).inflate(
					R.layout.price_display, null);
			final Spinner tab2_spinner4_2 = (Spinner) view
					.findViewById(R.id.tab2_spinner4_2);
			final Button button1 = (Button) view.findViewById(R.id.button1);
			final Button button2 = (Button) view.findViewById(R.id.button2);

			button1.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					final ProgressDialog proDialog = android.app.ProgressDialog.show(
							MainActivity.this,
							MainActivity.this.getString(R.string.new12),
							MainActivity.this.getString(R.string.new13));

					new Thread() {
						public void run() {

							PriceDisplay priceDisplay = new PriceDisplay(
									tab2_spinner4_2.getSelectedItemPosition());
							if (!priceDisplay.write(1, "100.00")) {
								Message msg = myHandler.obtainMessage();
								msg.obj = MainActivity.this
										.getString(R.string.new19);
								msg.sendToTarget();
							}
							proDialog.dismiss();
						};
					}.start();

				}
			});
			button2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					final ProgressDialog proDialog = android.app.ProgressDialog.show(
							MainActivity.this,
							MainActivity.this.getString(R.string.new12),
							MainActivity.this.getString(R.string.new13));

					new Thread() {
						public void run() {

							PriceDisplay priceDisplay = new PriceDisplay(
									tab2_spinner4_2.getSelectedItemPosition());
							if (!priceDisplay.clean()) {
								Message msg = myHandler.obtainMessage();
								msg.obj = MainActivity.this
										.getString(R.string.new19);
								msg.sendToTarget();
							}

							proDialog.dismiss();

						};
					}.start();

				}
			});

			new AlertDialog.Builder(MainActivity.this)
					.setTitle("PriceDisplay_TEST")
					.setView(view)
					.setNegativeButton(
							MainActivity.this.getString(R.string.public_cancel),
							null).show();

		} else if (arg0.getId() == jijiacheng.getId()) {

			View view = LayoutInflater.from(MainActivity.this).inflate(
					R.layout.electronic_say, null);
			final Spinner tab2_spinner5_1 = (Spinner) view
					.findViewById(R.id.tab2_spinner5_1);
			final Spinner tab2_spinner5_2 = (Spinner) view
					.findViewById(R.id.tab2_spinner5_2);
			final TextView textView2 = (TextView) view
					.findViewById(R.id.textView2);
			final Button button1 = (Button) view.findViewById(R.id.button1);

			button1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub

					float xdpi = getResources().getDisplayMetrics().xdpi;
					float ydpi = getResources().getDisplayMetrics().ydpi;

					Log.e("jijiacheng", "xdpi:" + xdpi + ",ydpi:" + ydpi);

					final ProgressDialog proDialog = android.app.ProgressDialog.show(
							MainActivity.this,
							MainActivity.this.getString(R.string.new12),
							MainActivity.this.getString(R.string.new13));

					ElectronicSays electronicSays = new ElectronicSays(
							tab2_spinner5_2.getSelectedItemPosition());
					electronicSays.getDynamicWeight(
							tab2_spinner5_1.getSelectedItemPosition(),
							new ElectronicSays.ClickItemCallback() {
								@Override
								public void OnItemClick(final String data) {
									// TODO Auto-generated method stub

									proDialog.dismiss();

									if ("-1".equals(data)) {
										Message msg = myHandler.obtainMessage();
										msg.obj = MainActivity.this
												.getString(R.string.new19);
										msg.sendToTarget();

										runOnUiThread(new Runnable() {

											@Override
											public void run() {
												// TODO Auto-generated method
												// stub
												textView2.setText("0.00");
											}
										});
										return;
									} else if ("-2".equals(data)) {
										Message msg = myHandler.obtainMessage();
										msg.obj = MainActivity.this
												.getString(R.string.new31);
										msg.sendToTarget();
									}

									runOnUiThread(new Runnable() {

										@Override
										public void run() {
											// TODO Auto-generated method stub
											textView2.setText(data + "");

										}
									});

								}
							});
				}
			});
			new AlertDialog.Builder(MainActivity.this)
					.setTitle("ElectronicSays_TEST")
					.setView(view)
					.setNegativeButton(
							MainActivity.this.getString(R.string.public_cancel),
							null).show();

		} else if (arg0.getId() == usbIcCard.getId()) {

			final ProgressDialog proDialog = android.app.ProgressDialog.show(
					MainActivity.this,
					MainActivity.this.getString(R.string.new12),
					MainActivity.this.getString(R.string.new21));

			new Thread() {
				public void run() {

					UsbIcCard usbIcCard = new UsbIcCard(MainActivity.this);

					int count = 0;
					System.out.println("start");
					while (true) {
						count++;
						if (count > 10) {
							Message msg = myHandler.obtainMessage();
							msg.obj = MainActivity.this
									.getString(R.string.new22);
							msg.sendToTarget();
							break;
						}

						if (usbIcCard.deviceIsConnection()) {
							if (usbIcCard.hasPermission()) {
								if (usbIcCard.CardIsReady()) {
									if (usbIcCard.PwdCheck("FFFFFF")) {

										boolean isOk = false;
										String data;
										if ((data = usbIcCard.readCard()) != null) {
											Message msg = myHandler
													.obtainMessage();
											msg.obj = MainActivity.this
													.getString(R.string.new23);
											msg.sendToTarget();
											isOk = true;
										}
										if (!isOk) {// 发送错误信息

											Message msg = myHandler
													.obtainMessage();
											msg.obj = usbIcCard.getErrString();
											msg.sendToTarget();
										}

										break;

									} else {
										Message msg = myHandler.obtainMessage();
										msg.obj = MainActivity.this
												.getString(R.string.new24);
										msg.sendToTarget();
										break;
									}
								}
							} else {
								break;
							}

						} else {
							Message msg = myHandler.obtainMessage();
							msg.obj = MainActivity.this
									.getString(R.string.new25);
							msg.sendToTarget();
							break;
						}

						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}

					System.out.println("over");
					proDialog.dismiss();
				};
			}.start();

		} else if (arg0.getId() == tiaomadayinji.getId()) {

			View view = LayoutInflater.from(MainActivity.this).inflate(
					R.layout.barcode_printer, null);
			final Spinner barcodeSpinner = (Spinner) view
					.findViewById(R.id.barcode_spinner);

			ArrayAdapter<String> adapterS = new ArrayAdapter<String>(
					MainActivity.this, android.R.layout.simple_spinner_item);

			UsbManager usbManager = (UsbManager) MainActivity.this
					.getSystemService(Context.USB_SERVICE);
			HashMap<String, UsbDevice> map = usbManager.getDeviceList();

			for (UsbDevice device : map.values()) {
				adapterS.add("VId_PId[" + device.getVendorId() + ","
						+ device.getProductId() + "]");

			}

			adapterS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			barcodeSpinner.setAdapter(adapterS);

			final Button buttonTest = (Button) view
					.findViewById(R.id.barcodePrintTest);

			buttonTest.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub

					new Thread() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							super.run();
							String reStr = barcodeSpinner.getSelectedItem()
									.toString() + "";

							for (int i = 0; i < 10; i++) {

								BarcodePrinter barcodePrinter = new BarcodePrinter(
										reStr, MainActivity.this,
										new BarcodePrinter.ClickItemCallback() {

											@Override
											public void OnItemClick(int what,
													Object obj) {
												// TODO Auto-generated method
												// stub

												Message msg = myHandler
														.obtainMessage();
												msg.obj = MainActivity.this
														.getString(R.string.new26);
												msg.sendToTarget();

											}
										});

								barcodePrinter.setTitle("  " + "点菜单", 2, 2);

								barcodePrinter.commit();

							}
						}

					}.start();

					/*
					 * String reStr =
					 * barcodeSpinner.getSelectedItem().toString() + "";
					 * 
					 * Log.e("佳博","reStr："+reStr);
					 * 
					 * final ProgressDialog proDialog =
					 * android.app.ProgressDialog.show( MainActivity.this,
					 * MainActivity.this.getString(R.string.new12),
					 * MainActivity.this.getString(R.string.new13));
					 * 
					 * BarcodePrinter barcodePrinter = new BarcodePrinter(reStr,
					 * MainActivity.this, new BarcodePrinter.ClickItemCallback()
					 * {
					 * 
					 * @Override public void OnItemClick(int what, Object obj) {
					 * // TODO Auto-generated method stub Message msg =
					 * myHandler.obtainMessage(); msg.obj = MainActivity.this
					 * .getString(R.string.new26); msg.sendToTarget(); } });
					 * barcodePrinter.setTitle("BarcodePrinter", 2, 2);
					 * barcodePrinter.write("TEST");
					 * barcodePrinter.write("TEST");
					 * barcodePrinter.write("TEST"); barcodePrinter.commit();
					 * proDialog.dismiss();
					 */

				}
			});

			new AlertDialog.Builder(MainActivity.this)
					.setTitle("PriceDisplay_TEST")
					.setView(view)
					.setNegativeButton(
							MainActivity.this.getString(R.string.public_cancel),
							null).show();

		} else if (arg0.getId() == scanTest.getId()) {
			View view = LayoutInflater.from(MainActivity.this).inflate(
					R.layout.scan_test, null);

			final EditText editText = (EditText) view
					.findViewById(R.id.scan_barcode);

			editText.setInputType(InputType.TYPE_NULL);
			editText.setOnKeyListener(new OnKeyListener() {

				@Override
				public boolean onKey(View arg0, int keyCode, KeyEvent event) {
					// TODO Auto-generated method stub
					// 按回车键 或者，小键盘回车键 响应
					if ((keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_NUMPAD_ENTER)
							&& event.getAction() == KeyEvent.ACTION_DOWN) {

						final String text = editText.getText().toString()
								.trim();
						if (text != null && !text.equals("")) {

						}
					}
					return false;
				}
			});

			editText.setFocusable(true);
			editText.requestFocus();

			new AlertDialog.Builder(MainActivity.this)
					.setTitle("Scan_TEST")
					.setView(view)
					.setNegativeButton(
							MainActivity.this.getString(R.string.public_cancel),
							null).show();
		}
	}
	Bitmap getImageFromAssetsFile(Context mContext,
			String fileName) {
		Bitmap image = null;
		AssetManager am = mContext.getResources().getAssets();
		try {
			InputStream is = am.open(fileName);
			image = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;

	}

}

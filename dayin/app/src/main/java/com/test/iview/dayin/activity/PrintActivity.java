package com.test.iview.dayin.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.test.iview.dayin.R;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.utils.BlueSAPI;
import com.test.iview.dayin.utils.PrinterImageUtils;
import com.test.iview.dayin.utils.ToastUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Javen Wong 完成打印功能的Activity
 */
public class PrintActivity extends BaseActivity {
	@BindView(R.id.home_add)
	ImageView homeAdd;
	@BindView(R.id.common_txt)
	TextView commonTxt;
	@BindView(R.id.common_title)
	TextView commonTitle;
	@BindView(R.id.back)
	ImageView back;

	private EditText etMAC;
	private EditText toPrintText;
	private Handler handler;
	private ImageView bImageView;
	private PrintActivity context = null;
	private String address ;

	@Override
	public void initView(@Nullable Bundle savedInstanceState) {

		address = getIntent().getStringExtra("address");
		context = this;
		commonTitle.setText(R.string.dy_add_device);
		homeAdd.setVisibility(View.GONE);
		homeAdd.setImageResource(R.drawable.printer);
		commonTxt.setVisibility(View.GONE);

		verifyStoragePermissions(this);
		etMAC = (EditText) findViewById(R.id.EditText01);
		toPrintText = (EditText) findViewById(R.id.EditText02);
		Button btnSelect = (Button) findViewById(R.id.Button01);
		Button btnTestPrint = (Button) findViewById(R.id.btn_print_visitor);
		Button bENA8 = (Button) findViewById(R.id.ENA8);
		Button bENA13 = (Button) findViewById(R.id.ENA13);
		Button bCODE39 = (Button) findViewById(R.id.CODE39);
		Button bCODE128 = (Button) findViewById(R.id.CODE128);
		Button btnCharacterPrint = (Button) findViewById(R.id.Button04);
		Button btnAsciiPrint = (Button) findViewById(R.id.Button05);
		Button btnCurvePrint = (Button) findViewById(R.id.Button06);
		bImageView = (ImageView) findViewById(R.id.bitmap);
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		bImageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				bImageView.setVisibility(View.GONE);
			}
		});

		if (null != address){
			etMAC.setText(address);
		}
		/**
		 * 打开蓝牙打印机选择界面
		 */
		btnSelect.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PrintActivity.this,
						BTDeviceListActivity.class);
				startActivityForResult(intent, 0);
			}
		});

		/**
		 * 连接打印机
		 */
		Button bconn = (Button) findViewById(R.id.btn_print_connect);
		bconn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showLoadingDialog();
				final String deviceid = etMAC.getText().toString();
				if (null == deviceid ||deviceid.length() == 0){
					ToastUtils.showShort("请选择蓝牙设备");
					return;
				}
				String pwd = "0000";

				int res = BlueSAPI.getInstance().openPrinter(deviceid, pwd, new BlueSAPI.CallBack() {
					@Override
					public void predo() {
						showLoadingDialog();
					}

					@Override
					public void success() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								hideLaodingDialog();
								ToastUtils.showShort("打印机连接成功");
								finish();

							}
						});
					}

					@Override
					public void errors() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								hideLaodingDialog();
								ToastUtils.showShort("打印机连接失败");
							}
						});
					}
				});


			}
		});

		/**
		 * 打印2维码
		 */
		btnTestPrint.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				printContent(4);
			}
		});

		/**
		 * 打印ENA8条形码
		 */
		bENA8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				printContent(0);
			}
		});

		/**
		 * 打印ENA13条形码
		 */
		bENA13.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				printContent(1);
			}
		});

		/**
		 * 打印CODE39条形码
		 */
		bCODE39.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				printContent(2);
			}
		});

		/**
		 * 打印CODE128条形码
		 */
		bCODE128.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				printContent(3);
			}
		});

		/**
		 * 打印图片
		 */
		btnCharacterPrint.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				printContent(5);
			}
		});

		/**
		 * 打印Ascii码
		 */
		btnAsciiPrint.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				printContent(6);
			}
		});

		/**
		 * 打印文本框输入内容
		 */
		btnCurvePrint.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				printContent(7);
			}
		});

		handler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
					case 0:
						Builder builder = new Builder(PrintActivity.this);
						builder.setTitle("提示");
						builder.setMessage("和蓝牙打印机通讯时发生错误！\n"
								+ msg.getData().getString("error"));
						builder.setPositiveButton("确定", null);
						builder.show();
						break;
					case 1:
						builder = new Builder(PrintActivity.this);
						builder.setTitle("提示");
						builder.setMessage("打印完成！");
						builder.setPositiveButton("确定", null);
						builder.show();
						break;
				}
			}
		};

	}

	@Override
	public void initData() {

	}

	@Override
	public int initLayout() {
		return R.layout.print_page;
	}


	/**
	 * 打印
	 * @param type 打印内容类型
	 */
	private void printContent(final int type) {
		if (etMAC.getText().toString().equals("")) {
			Toast.makeText(PrintActivity.this, "请选择一个蓝牙打印机先！",
					Toast.LENGTH_SHORT).show();
		} else {
			// 打印至蓝牙打印机
			final ProgressDialog pd = new ProgressDialog(PrintActivity.this);
			pd.setTitle("提示");
			pd.setMessage("正在打印，请稍候……");
			pd.show();

			/**
			 * 初始化打印机 ，带格式的数据打印完成后一定要设置回去否则以后打印的文字都会带次格式
			 */
			BlueSAPI.getInstance().initPrinter();

			// //新开线程 打印
			new Thread() {
				public void run() {
					try {
						int rtn = 0;
						switch (type) {
						case 0: {		
							String code ="12345678";
							rtn = BlueSAPI.getInstance().PrintOnedimCode(200,50,10,code,0);
						};
							break;
						case 1: {							
							String code ="12345678";
							rtn = BlueSAPI.getInstance().PrintOnedimCode(200,50,10,code,1);
						}
							break;
						case 2: {							
							String code ="1234567";
							rtn = BlueSAPI.getInstance().PrintOnedimCode(200,50,10,code,2);
						}
							break;
						case 3: {
							String code ="123456789012";
							rtn = BlueSAPI.getInstance().PrintOnedimCode(200,50,10,code,3);
						}
							break;
						case 4: {
							rtn = BlueSAPI.getInstance().PrintTwoCode("http://www.baidu.com");
						}
							break;
						case 5: {

//							AssetManager am = getResources().getAssets();
//							try {
//								InputStream is = am.open("1214.png");
//								final Bitmap bmplogo = BitmapFactory.decodeStream(is);
//
//								btapi.PrintImage(bmplogo);
//								is.close();
//								runOnUiThread(new Runnable() {
//									@Override
//									public void run() {
//										bImageView.setVisibility(View.VISIBLE);
//										bImageView.setImageBitmap(bmplogo);
//									}
//								});
//							} catch (IOException e) {
//								e.printStackTrace();
//							}



							Intent intent = new Intent(Intent.ACTION_PICK,
									MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
							startActivityForResult(intent, 2);


							}
							break;
						case 6: {
							rtn = BlueSAPI.getInstance().PrintByte(new byte[] { 0x0A, 0x20, 0x21,
									0x22, 0x23, 0x24, 0x25, 0x26, 0x27, 0x28,
									0x29, 0x2A, 0x2B, 0x2C, 0x2D, 0x2E, 0x2F,
									0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36,
									0x37, 0x38, 0x39, 0x3A, 0x3B, 0x3C, 0x3D,
									0x3E, 0x3F, 0x40, 0x41, 0x42, 0x43, 0x44,
									0x45, 0x46, 0x47, 0x48, 0x49, 0x4A, 0x4B,
									0x4C, 0x4D, 0x4E, 0x4F, 0x50, 0x51, 0x52,
									0x53, 0x54, 0x55, 0x56, 0x57, 0x58, 0x59,
									0x5A, 0x5B, 0x5C, 0x5D, 0x5E, 0x5F, 0x60,
									0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67,
									0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E,
									0x6F, 0x70, 0x71, 0x72, 0x73, 0x74, 0x75,
									0x76, 0x77, 0x78, 0x79, 0x7A, 0x7B, 0x7C,
									0x7D, 0x7E, 0x7F, 0x0A, 0x0A });
						}
							break;
						case 7: {							
							rtn = BlueSAPI.getInstance().PrintByte(toPrintText.getText().toString().getBytes("GBK"));
						
						}
							break;
						default:
							break;
						}
//						rtn = BlueSAPI.getInstance().PrintLn();
						if(rtn == 0)
						{
						// 提示 打印完成
						handler.sendEmptyMessage(1);
						}
						else
						{
							Message msg = Message.obtain();
							msg.what = 0;
							msg.getData().putString("error", "" + rtn);
//							handler.sendMessage(msg);
						}

					} catch (IOException e) {
						// 提示 打印时 出错
						Message msg = Message.obtain();
						msg.what = 0;
						msg.getData().putString("error", e.getMessage());
						handler.sendMessage(msg);

					} finally {
						pd.dismiss();

					}
				}
			}.start();
		}
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == 0 && resultCode == RESULT_OK) {
			/**
			 * 保存选择打印机地址
			 */
			etMAC.setText(data.getStringExtra(BTDeviceListActivity.EXTRA_DEVICE_ADDRESS));

		}

		if (requestCode == 2 && null != data.getData()) {
//              int s = BitmapUtil.getBitmapBytes(MediaStore.Images.Media.getBitmap(getContentResolver(),data.getData()));
			Bitmap bmplogo = null;
//			AssetManager am = getResources().getAssets();
//			try {
//				InputStream is = am.open("logo.png");
//				bmplogo = BitmapFactory.decodeStream(is);
//				is.close();
			try {
				bmplogo = MediaStore.Images.Media.getBitmap(getContentResolver(),data.getData());
//				bImageView.setVisibility(View.VISIBLE);
//				bImageView.setImageBitmap(convertGreyImgByFloyd(toGrayScale(zoomImg(bmplogo,380))));
//				bImageView.setImageBitmap(printimage(bmplogo));
			} catch (IOException e) {
				e.printStackTrace();
			}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}

//			String path = getPathByUri(this,data.getData());
//			Bitmap mp = convertGreyImgByFloyd(convertToBlackWhite(getSmallBitmap(this,path)));
			Bitmap mp = PrinterImageUtils.getSmallBitmap(getPathByUri(this,data.getData()));
			bImageView.setVisibility(View.VISIBLE);
			bImageView.setImageBitmap(mp);
			Log.e("PrintImageBitmap:",mp.getWidth() + "," + mp.getHeight() + "," + mp.getByteCount()
									+ "," + mp.getRowBytes());
			BlueSAPI.getInstance().PrintImage(mp,false);
//			BitmapUtil.createScaledBitmap(this,data.getData(),374, new BitmapUtil.MyCallback() {
//				@Override
//				public void onPrepare() {
//
//				}
//
//				@Override
//				public void onSucceed(final Object object) {
//					PrintActivity.this.runOnUiThread(new Runnable() {
//						@Override
//						public void run() {
//						Bitmap bb =	convertGreyImgByFloyd(toGrayScale((Bitmap)object));
//							Log.e("PrintImageBitmap:",bb.getWidth() + "," + bb.getHeight() + "," + bb.getByteCount()
//									+ "," + bb.getRowBytes());
//							bImageView.setVisibility(View.VISIBLE);
//							bImageView.setImageBitmap(bb);
////							btapi.PrintImage(bb);
//						}
//					});
//
//				}
//
//				@Override
//				public void onError() {
//
//				}
//			});

//			Bitmap mp = getCompressBitmap(convertGreyImgByFloyd(toGrayScale(zoomImg(bmplogo,380))));
//			Log.e("PrintImageBitmap:",mp.getWidth() + "," + mp.getHeight() + "," + mp.getByteCount()
//			+ "," + mp.getRowBytes());
//			bImageView.setVisibility(View.VISIBLE);
//			bImageView.setImageBitmap(mp);
//			btapi.PrintImage(mp);




//			btapi.PrintImage(convertGreyImgByFloyd(convertToBlackWhite(getResizedBitmap(bmplogo,380))));


//			BitmapUtil.createScaledBitmap(this,data.getData(),350, new BitmapUtil.MyCallback() {
//				@Override
//				public void onPrepare() {
//
//				}
//
//				@Override
//				public void onSucceed(final Object object) {
//					PrintActivity.this.runOnUiThread(new Runnable() {
//						@Override
//						public void run() {
//							btapi.PrintImage((Bitmap)object);
//						}
//					});
//
//				}
//
//				@Override
//				public void onError() {
//
//				}
//			});

		}

	}


	public Bitmap getCompressBitmap(Bitmap bit){
		       ByteArrayOutputStream bos=new ByteArrayOutputStream();
		       bit.compress(Bitmap.CompressFormat.JPEG, 40, bos);//参数100表示不压缩
		       byte[] bytes = bos.toByteArray();
		       return getimg(Base64.encodeToString(bytes, Base64.DEFAULT));
		    }

	public Bitmap getimg(String str){
		byte[] bytes;
		bytes= Base64.decode(str, 0);
		return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
	}



	byte getPix(int[] var1, int var2, int var3, int var4) {
		return (byte)var1[var3 * var4 + var2];
	}

	public Bitmap printimage(Bitmap var1) {
		int var10 = var1.getHeight();
		int var11 = var1.getWidth();
		int[] var12 = new int[var1.getByteCount()];
		var1.getPixels(var12, 0, var11, 0, 0, var11, var10);
		int[] var13 = new int[9];
		byte[] var14 = new byte[9];

		int var3;
		for(var3 = 0; var3 < 9; ++var3) {
			var14[var3] = (byte)var3;
		}

		for(int var6 = 0; var6 < var10 - 2; var6 += 3) {
			for(int var7 = 0; var7 < var11 - 2; var7 += 3) {
				int var4 = 0;

				byte var2;
				int var5;
				for(var3 = 0; var3 < 9; ++var3) {
					var13[var3] = 255 - (this.getPix(var12, var3 % 3 + var7, var3 / 3 + var6, var11) + this.getPix(var12, var7 + 1 + var3 % 3, var3 / 3 + var6, var11) + this.getPix(var12, var7 + 2 + var3 % 3, var3 / 3 + var6, var11)) / 3;
					var4 += var13[var3];
					var5 = (int)(Math.random() % 8.0D + 1.0D);
					var2 = var14[0];
					var14[0] = var14[var5];
					var14[var5] = var2;
				}

				var4 /= 234;

				for(var3 = 0; var3 < 9; ++var3) {
					var12[var7 + 0 + var3 % 3 + (var3 / 3 + var6) * var11] = 255;
					var12[var7 + 1 + var3 % 3 + (var3 / 3 + var6) * var11] = 255;
					var12[var7 + 2 + var3 % 3 + (var3 / 3 + var6) * var11] = 255;
				}

				int var8 = var4;
				if (var4 >= 10) {
					var8 = 9;
				}

				for(var3 = 0; var3 < var8; ++var3) {
					var4 = var3;

					int var9;
					for(var5 = var3 + 1; var5 < 9; var4 = var9) {
						var9 = var4;
						if ((double)var13[var14[var5]] > (double)var13[var14[var4]] + Math.random() % 26.0D) {
							var9 = var5;
						}

						++var5;
					}

					var5 = var4;
					if (var4 >= 9) {
						var5 = 8;
					}

					var2 = var14[var5];
					var14[var5] = var14[var3];
					var14[var3] = var2;
					var12[var7 + 0 + var2 % 3 + (var2 / 3 + var6) * var11] = 0;
					var12[var7 + 1 + var2 % 3 + (var2 / 3 + var6) * var11] = 0;
					var12[var7 + 2 + var2 % 3 + (var2 / 3 + var6) * var11] = 0;
				}
			}
		}
		var1 = Bitmap.createBitmap(var11, var10, Bitmap.Config.RGB_565);
		var1.setPixels(var12, 0, var11, 0, 0, var11, var10);
		return var1;
	}



	        public static Bitmap convertToBlackWhiteLogo(Bitmap var0) {
            int var3 = var0.getWidth();
            int var4 = var0.getHeight();
            int[] var6 = new int[var3 * var4];
            var0.getPixels(var6, 0, var3, 0, 0, var3, var4);

            for(int var1 = 0; var1 < var4; ++var1) {
                for(int var2 = 0; var2 < var3; ++var2) {
                    int var5 = var6[var3 * var1 + var2];
                    var5 = (int)((double)((16711680 & var5) >> 16) * 0.3D + (double)(('\uff00' & var5) >> 8) * 0.59D + (double)(var5 & 255) * 0.11D);
                    var6[var3 * var1 + var2] = var5 | var5 << 16 | -16777216 | var5 << 8;
                }
            }

            var0 = Bitmap.createBitmap(var3, var4, Bitmap.Config.RGB_565);
            var0.setPixels(var6, 0, var3, 0, 0, var3, var4);
            return ThumbnailUtils.extractThumbnail(var0, 120, 120);
        }

	public static String getPathByUri(Context context, Uri uri) {
		Activity activity = null;
		if (context instanceof Activity) {
			activity = (Activity) context;
		}

		if (activity == null) {
			return null;
		}

		String path = null;
		if (uri == null) {
			return path;
		}
		if ("content".equals(uri.getScheme())) {
			String[] projection = {MediaStore.Images.Media.DATA};
			Cursor cursor = activity.managedQuery(uri, projection, null, null, null);
			int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			path = cursor.getString(column_index);
		} else if ("file".equals(uri.getScheme())) {
			path = uri.getPath();
		}
		return path;
	}


	public Bitmap getResizedBitmap(Bitmap image, int maxSize){
		int width = image.getWidth();
		int height = image.getHeight();

		float bitmapRatio =(float)width /(float)height;
		if(bitmapRatio> 1){
			width = maxSize;
			height =(int)(width / bitmapRatio);
		} else {
			height = maxSize;
			width =(int)(height * bitmapRatio);
		}

		return Bitmap.createScaledBitmap(image,width,height,true);

	}

	/**
	 * 计算图片的缩放值
	 *
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			// Calculate ratios of height and width to requested height and
			// width
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);

			// Choose the smallest ratio as inSampleSize value, this will
			// guarantee
			// a final image with both dimensions larger than or equal to the
			// requested height and width.
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}

		return inSampleSize;
	}




	/**
	 * 根据路径获得图片并压缩返回bitmap用于显示
	 *
	 * @param
	 * @return
	 */
	public static Bitmap getSmallBitmap(Context context , String filePath) {

		final BitmapFactory.Options options = new BitmapFactory.Options();
		 options.inJustDecodeBounds = true;
		 BitmapFactory.decodeFile(filePath, options);
		 options.inSampleSize = calculateInSampleSize(options, 380, 560);//自定义一个宽和高
		 options.inJustDecodeBounds = false;
		 return BitmapFactory.decodeFile(filePath, options);
	}




	public static Bitmap convertToBlackWhite(Bitmap var0) {
            int var3 = var0.getWidth();
            int var4 = var0.getHeight();
            int[] var6 = new int[var3 * var4];
            var0.getPixels(var6, 0, var3, 0, 0, var3, var4);

            int var1;
            int var2;
            for(var1 = 0; var1 < var4; ++var1) {
                for(var2 = 0; var2 < var3; ++var2) {
                    int var5 = var6[var3 * var1 + var2];
                    var5 = (int)((double)((16711680 & var5) >> 16) * 0.3D + (double)(('\uff00' & var5) >> 8) * 0.59D + (double)(var5 & 255) * 0.11D);
                    var6[var3 * var1 + var2] = var5 | var5 << 16 | -16777216 | var5 << 8;
                }
            }

            var0 = Bitmap.createBitmap(var3, var4, Bitmap.Config.RGB_565);
            var0.setPixels(var6, 0, var3, 0, 0, var3, var4);
            var2 = (int)(384.0F / (float)var3 * (float)var4);
            var1 = var2;
            if (var2 == 682) {
                var1 = 681;
            }

            return ThumbnailUtils.extractThumbnail(var0, 384, var1);
        }


	@Override
	protected void onDestroy() {

//		BlueSAPI.getInstance().closePrinter();
		super.onDestroy();
	}



	private static final int REQUEST_EXTERNAL_STORAGE = 1;
	private static String[] PERMISSIONS_STORAGE = {
			"android.permission.READ_EXTERNAL_STORAGE",
			"android.permission.WRITE_EXTERNAL_STORAGE" };


	public static void verifyStoragePermissions(Activity activity) {

		try {
			//检测是否有写的权限
			int permission = ActivityCompat.checkSelfPermission(activity,
					"android.permission.WRITE_EXTERNAL_STORAGE");
			if (permission != PackageManager.PERMISSION_GRANTED) {
				// 没有写的权限，去申请写的权限，会弹出对话框
				ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public Bitmap convertGreyImgByFloyd(Bitmap img) {


		int width = img.getWidth(); //获取位图的宽  
		int height = img.getHeight(); //获取位图的高  


		int[] pixels = new int[width * height]; //通过位图的大小创建像素点数组  


		img.getPixels(pixels, 0, width, 0, 0, width, height);
		int[] gray=new int[height*width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int grey = pixels[width * i + j];
				int red = ((grey & 0x00FF0000 ) >> 16);
				gray[width*i+j]=red;
			}
		}


		int e=0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int g=gray[width*i+j];
				if (g>=127) {
					pixels[width*i+j]=0xffffffff;
					e=g-255;


				}else {
					pixels[width*i+j]=0xff000000;
					e=g-0;
				}

				// 误差传递
//            int a = (e * 7) / 16;
//			int b = (e * 1) / 16;
//            int c = (e * 5) / 16;
//            int d = (e * 3) / 16;
//
//				if((i != (height-1)) && (j != 0) && (j != (width - 1)))
//            {
//				gray[width*i+j + 1] = a;
//				gray[width*(i + 1)+j + 1] = b;
//				gray[width*(i + 1)+j ] = c;
//				gray[width*(i + 1)+j - 1] = d;
////                cvMat.at<uint8_t>(i+0,j+1) = saturated_add(cvMat.at<uint8_t>(i+0,j+1),a);
////                cvMat.at<uint8_t>(i+1,j+1) = saturated_add(cvMat.at<uint8_t>(i+1,j+1),b);
////                cvMat.at<uint8_t>(i+1,j+0) = saturated_add(cvMat.at<uint8_t>(i+1,j+0),c);
////                cvMat.at<uint8_t>(i+1,j-1) = saturated_add(cvMat.at<uint8_t>(i+1,j-1),d);
//            }

//				if (j<width-1&&i<height-1) {
				if((i != (height-1)) && (j != 0) && (j != (width - 1))){


//右边像素处理
					gray[width*i+j+1]+=7*e/16;
//下
					gray[width*(i+1)+j]+=5*e/16;
//右下
					gray[width*(i+1)+j+1]+=e/16;
					gray[width*(i+1)+j-1]+=2*e/16;
				}
//				}else if (j==width-1&&i<height-1) {//靠右或靠下边的像素的情况
////下方像素处理
//					gray[width*(i+1)+j]+=5*e/16;
//				}else if (j<width-1&&i==height-1) {
////右边像素处理
//					gray[width*(i)+j+1]+=7*e/16;
//				}
			}


		}


		Bitmap mBitmap= Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
		mBitmap.setPixels(pixels, 0, width, 0, 0, width, height);

		return mBitmap;
	}


	private static Bitmap toGrayScale(Bitmap bmpOriginal) {
		int width, height;
		height = bmpOriginal.getHeight();
		width = bmpOriginal.getWidth();

		Bitmap bmpGrayScale = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
		Canvas c = new Canvas(bmpGrayScale);
		Paint paint = new Paint();
		ColorMatrix cm = new ColorMatrix();
		cm.setSaturation(0);
		ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
		paint.setColorFilter(f);
		c.drawBitmap(bmpOriginal, 0, 0, paint);
		return bmpGrayScale;
	}

	public static Bitmap zoomImg(Bitmap bm, int newWidth ){
		// 获得图片的宽高
		int width = bm.getWidth();
		int height = bm.getHeight();
		float zoomer = width/height;

		float reqWidth = newWidth;
		float reqHeight = newWidth * height/width;
//		width/newWidth = height/newHeight
//				newWidth/newHeight = zoomer;
		// 计算缩放比例
		float scaleWidth = ((float) reqWidth) / width;
		float scaleHeight = ((float) reqHeight) / height;
		// 取得想要缩放的matrix参数
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		// 得到新的图片
		Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
		Log.e("newbm::",newbm.getWidth() + "," + newbm.getHeight());
		return newbm;
	}

}

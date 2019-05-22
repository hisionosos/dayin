package com.test.iview.dayin.view.word;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.Toast;
import com.test.iview.dayin.R;

@SuppressLint({ "SetJavaScriptEnabled", "NewApi" })
public class ShowWordActivity extends Activity {

	private WebView mOfficeWebview;
	private String mOfficeHtmlPath;
	public static String mSdcardPath = null;
	private String htmlPath = null;
	private String filePath = null;

	FR fr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.office_show_content);
		mOfficeWebview = (WebView) findViewById(R.id.webview);
		// ֧��JavaScript
		mOfficeWebview.getSettings().setJavaScriptEnabled(true);
		// ���ÿ���֧������(˫���Լ��϶�)
		mOfficeWebview.getSettings().setSupportZoom(true);
		mOfficeWebview.getSettings().setBuiltInZoomControls(true);
		// ����ʾWebView���Ű�ť(3.0���ϰ汾)
		mOfficeWebview.getSettings().setDisplayZoomControls(false);
		// �������������
		mOfficeWebview.getSettings().setUseWideViewPort(true);
		// ����Ӧ��Ļ
		mOfficeWebview.getSettings().setLayoutAlgorithm(
				LayoutAlgorithm.SINGLE_COLUMN);
		mOfficeWebview.getSettings().setLoadWithOverviewMode(true);
		WebSettings settings = mOfficeWebview.getSettings();
		settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		mSdcardPath = Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		filePath = this.getIntent().getExtras().getString("filePath");

		if (filePath.endsWith(".doc")) {
			Toast.makeText(ShowWordActivity.this, "doc", Toast.LENGTH_SHORT)
					.show();
			if (!isExistsDoc(filePath)) {
				try {
					Log.e("�ļ�������", "HTML·�� ��" + mOfficeHtmlPath + "| "
							+ filePath);
					// String officeName = mOfficeHtmlPath.substring(
					// mOfficeHtmlPath.indexOf("0") + 1,
					// mOfficeHtmlPath.length());
					String[] mStrPath = mOfficeHtmlPath.split("/");
					String officeName = mStrPath[mStrPath.length - 1];
					htmlPath = mSdcardPath + File.separator + "gdemm"
							+ File.separator + officeName;
					WordToHtml.convert2Html(filePath, htmlPath);
				} catch (TransformerException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				}
			}
			mOfficeWebview.loadUrl("file://" + htmlPath);
			// FR fr = new FR(filePath);
			// mOfficeWebview.loadUrl(fr.returnPath);
		}
		if (filePath.endsWith(".docx")) {
			Toast.makeText(ShowWordActivity.this, "docx", Toast.LENGTH_SHORT)
					.show();
			fr = new FR(filePath);
			mOfficeWebview.loadUrl(fr.returnPath);
			// readDocx();
		}
		if (filePath.endsWith(".xls")) {
			Toast.makeText(ShowWordActivity.this, "xls", Toast.LENGTH_SHORT)
					.show();
			fr = new FR(filePath);
			mOfficeWebview.loadUrl(fr.returnPath);
		}
		if (filePath.endsWith(".xlsx")) {
			Toast.makeText(ShowWordActivity.this, "xlsx", Toast.LENGTH_SHORT)
					.show();
			fr = new FR(filePath);
			mOfficeWebview.loadUrl(fr.returnPath);
		}
		if (filePath.endsWith(".pptx")) {
			Toast.makeText(ShowWordActivity.this, "pptx", Toast.LENGTH_SHORT)
					.show();
			fr = new FR(filePath);
			mOfficeWebview.loadUrl(fr.returnPath);
		}
		// Log.v("====================", htmlPath);
		// Log.e("HTMl�ı����ݣ�", getHtmlString("file://" + htmlPath));
		// String text = getHtmlString("file://" + htmlPath);
		// System.out.println(text);
		// String t = text.replace("vnf", "<a href = 'http://www.baidu.com'>" +
		// "vnf" + "</a>");
		// Log.e("HTMl�ı����ݣ�" ,"========================" + t);
		// File file = new File(htmlPath);
		// print(file,t);

	}

	public boolean isExistsDoc(String path) {
		mOfficeHtmlPath = path.replace(".doc", ".html");
		File file = new File(mOfficeHtmlPath);
		if (file.exists()) {
			return true;
		}
		return false;
	}

	public boolean isExistsDocx(String path) {
		mOfficeHtmlPath = path.replace(".docx", ".html");
		File file = new File(mOfficeHtmlPath);
		if (file.exists()) {
			return true;
		}
		return false;
	}

	// /**
	// * ��ȡdocx�ĵ�
	// */
	// @SuppressWarnings("deprecation")
	// private void readDocx() {
	// try {
	// final LoadFromZipNG loader = new LoadFromZipNG();
	// WordprocessingMLPackage wordMLPackage = (WordprocessingMLPackage) loader
	// .get(new FileInputStream(filePath));
	// String IMAGE_DIR_NAME = "images";
	// String baseURL = this
	// .getDir(IMAGE_DIR_NAME, Context.MODE_WORLD_READABLE)
	// .toURL().toString();
	// System.out.println(baseURL);
	// ConversionImageHandler conversionImageHandler = new FileImageHandler(
	// IMAGE_DIR_NAME, baseURL, false, this);
	// HtmlExporterNonXSLT withoutXSLT = new HtmlExporterNonXSLT(
	// wordMLPackage, conversionImageHandler);
	// String html = XmlUtils.w3CDomNodeToString(withoutXSLT.export());
	// mOfficeWebview.loadDataWithBaseURL(baseURL, html, "text/html",
	// null, null);
	// } catch (Exception e) {
	// e.printStackTrace();
	// finish();
	// }
	// }

	/**
	 * ��ȡhtml�е�����
	 * 
	 * @param
	 * @return
	 */
//	public String getHtmlString(String urlString) {
//		try {
//			URL url = null;
//			url = new URL(urlString);
//			URLConnection ucon = null;
//			ucon = url.openConnection();
//			InputStream instr = null;
//			instr = ucon.getInputStream();
//			BufferedInputStream bis = new BufferedInputStream(instr);
//			ByteArrayBuffer baf = new ByteArrayBuffer(500);
//			int current = 0;
//			while ((current = bis.read()) != -1) {
//				baf.append((byte) current);
//			}
//			return EncodingUtils.getString(baf.toByteArray(), "UTF-8");
//		} catch (Exception e) {
//			return "";
//		}
//
//	}

	public void print(File file, String str) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(file, false);
			bw = new BufferedWriter(fw);
			bw.write(str);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ɾ��HTML�ļ�
	 * 
	 * @param fileName
	 */
	public static void delFile(String fileName) {
		File file = new File(fileName);
		if (file.isFile()) {
			file.delete();
		}
		file.exists();
	}

	public void RecursionDeleteFile(File file) {
		if (file.isFile()) {
			file.delete();
			return;
		}
		if (file.isDirectory()) {
			File[] childFile = file.listFiles();
			if (childFile == null || childFile.length == 0) {
				file.delete();
				return;
			}
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { // ���µ������BACK��ͬʱû���ظ�

			deleteDir();
			finish();
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	public static void deleteDir() {
		String SDPATH = Environment.getExternalStorageDirectory()
				+ "/gdemm/aaa/";
		File dir = new File(SDPATH);
		if (dir == null || !dir.exists() || !dir.isDirectory())
			return;

		for (File file : dir.listFiles()) {
			if (file.isFile())
				file.delete(); // ɾ�������ļ�
			else if (file.isDirectory())
				deleteDir(); // �ݹ�ķ�ʽɾ���ļ���
		}
		dir.delete();// ɾ��Ŀ¼����
	}
}

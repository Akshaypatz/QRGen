package com.akshaylabs.QRGen;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Controller
public class QRController {

	@GetMapping({"/", "/generateQR"})
	public String generateQR(Model model) {

		return "hello";

	}

	@PostMapping({"/computeQRwithAmount"})
	public String generateQRwithAmount(HttpServletRequest httpServletRequest,
			Model model) throws Exception {
		String fname = httpServletRequest.getParameter("fname");
		String lname = httpServletRequest.getParameter("lname");
		String product = httpServletRequest.getParameter("product");
		String amount = httpServletRequest.getParameter("amount");
		String extradets = "Customer : " + fname + "  " + lname + " paying "
				+ amount + " for " + product;
		System.out.println(extradets);
		byte[] qrstring = getQRString(amount);
		String b64qr = Base64.getEncoder().encodeToString(qrstring);
		model.addAttribute("qrstring", b64qr);
		model.addAttribute("extradets", extradets);
		return "qr";

	}

	@PostMapping({"/computeQRwithoutAmount"})
	public String generateQRwithoutAmount(Model model) throws Exception {

		byte[] qrstring = getQRString();
		String b64qr = Base64.getEncoder().encodeToString(qrstring);
		model.addAttribute("qrstring", b64qr);
		return "qr";

	}

	private byte[] getQRString(String amount)
			throws WriterException, IOException {
		return getFinalQRString(amount);
	}
	private byte[] getQRString() throws WriterException, IOException {

		return getFinalQRString("1000");
	}

	private byte[] getFinalQRString(String finalString)
			throws WriterException, IOException {
		String finalEstring = "akshay" + "_ " + finalString;
		return getQRCodeImage(finalEstring, 250, 250);
	}

	public static byte[] getQRCodeImage(String text, int width, int height)
			throws WriterException, IOException {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE,
				width, height);

		ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
		MatrixToImageConfig con = new MatrixToImageConfig(0xFF000002,
				0xFFFFC041);

		MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream,
				con);
		byte[] pngData = pngOutputStream.toByteArray();
		return pngData;
	}

}

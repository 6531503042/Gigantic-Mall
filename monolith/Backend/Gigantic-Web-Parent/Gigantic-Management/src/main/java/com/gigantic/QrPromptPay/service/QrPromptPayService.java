package com.gigantic.QrPromptPay.service;

import com.gigantic.QrPromptPay.QRPaymentUtils;
import com.google.zxing.WriterException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;

@Service
public class QrPromptPayService {

    public String createPromptPayQRCode() {
        try {
            QRPaymentUtils qrPaymentUtils = new QRPaymentUtils.Builder()
                    .dynamicQR()
                    .creditTransfer()
                    .mobileNumber("0947044119")
                    .amount(new BigDecimal("100.00"))
                    .build();

                    //Generate QR
                    String qrCodeBase64 = qrPaymentUtils.drawToBase64(300, 300);
                    return qrCodeBase64;
        } catch (WriterException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

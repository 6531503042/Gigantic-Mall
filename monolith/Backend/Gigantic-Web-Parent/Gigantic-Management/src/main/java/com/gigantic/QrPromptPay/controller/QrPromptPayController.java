package com.gigantic.QrPromptPay.controller;

import com.gigantic.QrPromptPay.service.QrPromptPayService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qr-prompt-pay")
public class QrPromptPayController {

    private final QrPromptPayService qrPromptPayService;

    public QrPromptPayController(QrPromptPayService qrPromptPayService) {
        this.qrPromptPayService = qrPromptPayService;
    }

    @RequestMapping("/create-qr-code")
    public String createQrCode() {
        return qrPromptPayService.createPromptPayQRCode();
    }
}

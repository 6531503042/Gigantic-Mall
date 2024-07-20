package com.gigantic.QrPromptPay;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;


import static com.gigantic.QrPromptPay.Constants.*;

public class QRPaymentUtils {

    private static final DecimalFormat MONEY_FORMAT = new DecimalFormat("0.00");

    private final Integer paymentField;
    private final String usageType;
    private final String acquirerId;
    private final BigDecimal amount;
    private final String currencyCode;
    private final String countryCode;
    private String billerId;
    private String mobileNumber;
    private String nationalId;
    private String eWalletId;
    private String ref1;
    private String ref2;
    private String ref3;
    private OutputType outputType;

    private QRPaymentUtils(Builder builder) {
        if (builder.selectPromptPayTypeBuilder.selectPromptPayType instanceof Builder.SelectPromptPayTypeBuilder.CreditTransferBuilder) {
            this.paymentField = CREDIT_TRANSFER_DATA_FIELD_ID;
            this.acquirerId = CREDIT_TRANSFER_ACQUIRER_ID;
            Builder.SelectPromptPayTypeBuilder.CreditTransferBuilder creditTransferBuilder = (Builder.SelectPromptPayTypeBuilder.CreditTransferBuilder) builder.selectPromptPayTypeBuilder.selectPromptPayType;
            this.mobileNumber = creditTransferBuilder.mobileNumber;
            this.nationalId = creditTransferBuilder.nationalId;
            this.eWalletId = creditTransferBuilder.eWalletId;
            this.amount = creditTransferBuilder.amount;
            this.outputType = OutputType.PROMPTPAY;
        } else {
            this.paymentField = BILL_PAYMENT_DATA_FIELD_ID;
            this.acquirerId = BILL_PAYMENT_DATA_ACQUIRER_ID;
            Builder.SelectPromptPayTypeBuilder.BillPaymentBuilder billPaymentBuilder = (Builder.SelectPromptPayTypeBuilder.BillPaymentBuilder) builder.selectPromptPayTypeBuilder.selectPromptPayType;
            this.billerId = billPaymentBuilder.billerId;
            this.ref1 = billPaymentBuilder.ref1;
            this.ref2 = billPaymentBuilder.ref2;
            this.ref3 = billPaymentBuilder.ref3;
            this.amount = billPaymentBuilder.amount;
            this.outputType = builder.outputType;
        }
        this.usageType = builder.usageType;
        this.currencyCode = builder.currencyCode;
        this.countryCode = builder.countryCode;
    }

    public String generateContent() {
        switch (outputType) {
            case OutputType.BOT3:
                return generateBOT();
            case OutputType.PROMPTPAY:
            default:
                return generatePromptPayQR();
        }
    }

    private static ByteArrayOutputStream generateQRCodeImage(String text, int width, int height)
            throws IOException, WriterException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);
        return byteArrayOutputStream;
    }

    private String generateBOT() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("|");
        stringBuilder.append(billerId);
        stringBuilder.append("\n");
        stringBuilder.append(ref1);
        stringBuilder.append("\n");
        if (ref2 != null) {
            stringBuilder.append(ref2);
        }
        stringBuilder.append("\n");
        if (amount != null) {
            stringBuilder.append(amount.multiply(oneHundred).setScale(0, RoundingMode.DOWN));
        } else {
            stringBuilder.append(0);
        }
        return stringBuilder.toString();
    }

    private String generatePromptPayQR() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(generateField(0, PAYLOAD_FORMAT_INDICATOR));
        stringBuilder.append(generateField(1, usageType));

        StringBuilder content = new StringBuilder(generateField(0, acquirerId));
        if (paymentField == 29) {
            if (mobileNumber != null) {
                if (mobileNumber.startsWith("0")) {
                    mobileNumber = mobileNumber.substring(1);
                }
                content.append(generateField(1, "00" + DEFAULT_COUNTRY_CODE_TEL + mobileNumber));
            } else if (nationalId != null) {
                content.append(generateField(2, nationalId));
            } else if (eWalletId != null) {
                content.append(generateField(3, eWalletId));
            }
        } else if (paymentField == 30) {
            content.append(generateField(1, billerId));
            content.append(generateField(2, ref1));
            if (ref2 != null) {
                content.append(generateField(3, ref2));
            }
        }
        stringBuilder.append(generateField(paymentField, content.toString()));

        stringBuilder.append(generateField(53, currencyCode));
        if (amount != null) {
            stringBuilder.append(generateField(54, MONEY_FORMAT.format(amount)));
        }

        stringBuilder.append(generateField(58, countryCode));
        if (ref3 != null) {
            stringBuilder.append(generateField(62, generateField(7, ref3)));
        }

        stringBuilder.append("6304");
        stringBuilder.append(Helper.crc16((stringBuilder.toString()).getBytes()));
        return stringBuilder.toString();
    }

    public enum OutputType {
        BOT3, PROMPTPAY
    }

    @Override
    public String toString() {
        return generateContent();
    }

    private String generateField(int fieldId, String content) {
        return String.format("%02d", fieldId) + String.format("%02d", content.length()) + content;
    }

    public void draw(int width, int height, File file) throws IOException, WriterException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(generateQRCodeImage(generateContent(), width, height).toByteArray());
        }
    }

    public String drawToBase64(int width, int height) throws IOException, WriterException {
        byte[] imageData = generateQRCodeImage(generateContent(), width, height).toByteArray();
        return new String(Base64.encodeBase64(imageData));
    }

    public byte[] drawToByteArray(int width, int height) throws IOException, WriterException {
        return generateQRCodeImage(generateContent(), width, height).toByteArray();
    }

    public static class Builder {
        private String usageType;
        protected String currencyCode = DEFAULT_CURRENCY_CODE;
        protected String countryCode = DEFAULT_COUNTRY_CODE;
        protected SelectPromptPayTypeBuilder selectPromptPayTypeBuilder = new SelectPromptPayTypeBuilder();
        protected OutputType outputType = OutputType.PROMPTPAY;

        public Builder currencyCode(String currencyCode) {
            validateNumeric("Currency Code", currencyCode);
            validateLength("Currency Code", currencyCode, 3);
            this.currencyCode = currencyCode;
            return this;
        }

        public Builder countryCode(String countryCode) {
            validateLength("Country Code", countryCode, 2);
            this.countryCode = countryCode.toUpperCase();
            return this;
        }

        public SelectPromptPayTypeBuilder staticQR() {
            this.usageType = STATIC_QR_CODE;
            this.outputType = OutputType.PROMPTPAY;
            return selectPromptPayTypeBuilder;
        }

        public SelectPromptPayTypeBuilder dynamicQR() {
            this.usageType = DYNAMIC_QR_CODE;
            this.outputType = OutputType.PROMPTPAY;
            return selectPromptPayTypeBuilder;
        }

        public SelectPromptPayTypeBuilder bot() {
            this.outputType = OutputType.BOT3;
            return selectPromptPayTypeBuilder;
        }

        public interface BillPaymentBuilderBillerId {
            BillPaymentBuilderRef1 billerId(String billerId);
        }

        public interface BillPaymentBuilderRef1 {
            BillPaymentBuilderOptionalDetail ref1(String ref1);
        }

        public interface BillPaymentBuilderOptionalDetail extends BuildReady {
            BillPaymentBuilderOptionalDetail amount(BigDecimal amount);

            BillPaymentBuilderOptionalDetail ref2(String ref2);

            BillPaymentBuilderOptionalDetail ref3(String ref3);
        }

        public interface CreditTransferBuilderIdentifier {
            CreditTransferBuilderAmount mobileNumber(String mobileNumber);

            CreditTransferBuilderAmount nationalId(String nationalId);

            CreditTransferBuilderAmount eWalletId(String eWalletId);
        }

        public interface CreditTransferBuilderAmount extends BuildReady {
            BuildReady amount(BigDecimal amount);
        }

        public interface BuildReady {
            QRPaymentUtils build();
        }

        interface SelectPromptPayType {

        }

        public class SelectPromptPayTypeBuilder {
            protected SelectPromptPayType selectPromptPayType;

            public BillPaymentBuilderBillerId billPayment() {
                BillPaymentBuilder billPaymentBuilder = new BillPaymentBuilder();
                this.selectPromptPayType = billPaymentBuilder;
                return billPaymentBuilder;
            }

            public CreditTransferBuilderIdentifier creditTransfer() {
                if (outputType != OutputType.PROMPTPAY) {
                    throw new IllegalStateException("Credit Transfer is only reserved for PromptPay QR.");
                }
                CreditTransferBuilder creditTransferBuilder = new CreditTransferBuilder();
                this.selectPromptPayType = creditTransferBuilder;
                return creditTransferBuilder;
            }

            protected class CreditTransferBuilder implements SelectPromptPayType, CreditTransferBuilderIdentifier, CreditTransferBuilderAmount {
                private String mobileNumber;
                private String nationalId;
                private String eWalletId;
                private BigDecimal amount;

                @Override
                public BuildReady amount(BigDecimal amount) {
                    this.amount = amount.setScale(2, RoundingMode.HALF_DOWN);
                    return this;
                }

                @Override
                public CreditTransferBuilderAmount mobileNumber(String mobileNumber) {
                    validateNumeric("Mobile Number", mobileNumber);
                    validateLength("Mobile Number", mobileNumber, 10);
                    this.mobileNumber = mobileNumber;
                    return this;
                }

                @Override
                public CreditTransferBuilderAmount nationalId(String nationalId) {
                    validateNumeric("National ID", nationalId);
                    validateLength("National ID", nationalId, 13);
                    this.nationalId = nationalId;
                    return this;
                }

                @Override
                public CreditTransferBuilderAmount eWalletId(String eWalletId) {
                    this.eWalletId = eWalletId;
                    return this;
                }

                @Override
                public QRPaymentUtils build() {
                    return new QRPaymentUtils(Builder.this);
                }
            }

            protected class BillPaymentBuilder implements SelectPromptPayType, BillPaymentBuilderBillerId, BillPaymentBuilderRef1, BillPaymentBuilderOptionalDetail {
                private String billerId;
                private String ref1;
                private String ref2;
                private String ref3;
                private BigDecimal amount;

                @Override
                public BillPaymentBuilderOptionalDetail ref1(String ref1) {
                    this.ref1 = ref1;
                    return this;
                }

                @Override
                public BillPaymentBuilderOptionalDetail amount(BigDecimal amount) {
                    this.amount = amount.setScale(2, RoundingMode.HALF_DOWN);
                    return this;
                }

                @Override
                public BillPaymentBuilderOptionalDetail ref2(String ref2) {
                    this.ref2 = ref2;
                    return this;
                }

                @Override
                public BillPaymentBuilderOptionalDetail ref3(String ref3) {
                    this.ref3 = ref3;
                    return this;
                }

                @Override
                public BillPaymentBuilderRef1 billerId(String billerId) {
                    this.billerId = billerId;
                    return this;
                }

                @Override
                public QRPaymentUtils build() {
                    return new QRPaymentUtils(Builder.this);
                }
            }
        }

        private void validateLength(String label, String value, int length) {
            if (value.length() != length) {
                throw new IllegalArgumentException(
                        String.format("%s length must be %d characters long", label, length));
            }
        }

        private void validateNumeric(String label, String value) {
            if (!value.chars().allMatch(Character::isDigit)) {
                throw new IllegalArgumentException(String.format("%s must be numeric", label));
            }
        }
    }
}

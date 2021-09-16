package com.example.getandsetResults.service;

import com.example.getandsetResults.model.order.OrderResponse;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Service
public class ConverterService {

    public byte[] createReport(OrderResponse orderResponse) throws IOException {

        int emptyRes = countOfEmptyResult(orderResponse);
        if (emptyRes > 0) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PdfWriter pdfWriter = new PdfWriter(byteArrayOutputStream);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);
            pdfDocument.setDefaultPageSize(PageSize.A4);

            float col = PageSize.A4.getWidth() / 2;
            float[] columnWidth = {col, col};

            PdfFont pdfFont = PdfFontFactory.createFont();

            Table headerMeta = new Table(columnWidth);
            headerMeta.setBackgroundColor(new DeviceRgb(46, 139, 87))
                    .setFont(pdfFont).
                    setFontColor(Color.WHITE)
                    .setBorder(Border.NO_BORDER);

            headerMeta.addCell(new Cell()
                    .add("RESULT")
                    .setFontSize(24)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setBorder(Border.NO_BORDER));
            headerMeta.addCell(new Cell().add("Private clinic\nAll rights reserved\n More info on website\n pClinic.com.ua")
                    .setFontSize(8)
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setBorder(Border.NO_BORDER)
                    .setPaddings(15, 15, 15, 0));

            document.add(headerMeta);

            Table userInfo = new Table(columnWidth);
            userInfo.setFontSize(8).setMarginTop(5f).setMarginBottom(5f);

            userInfo.addCell(new Cell()
                    .add("Surname: " + orderResponse.surname_() +
                            "\nName: " + orderResponse.name_() + " ")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setBorder(Border.NO_BORDER));
            userInfo.addCell(new Cell().
                    add("Date: " + orderResponse.time().toString().substring(0, 10) +
                            "\nTime: " + orderResponse.time().toString().substring(11, 16) + " ")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setBorder(Border.NO_BORDER));

            document.add(userInfo);

            float columnRes = PageSize.A4.getWidth() / 3;
            float[] columnResWidth = {columnRes, columnRes, columnRes};

            Table resultTable = new Table(columnResWidth);
            resultTable.setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(8);
            resultTable.addCell(new Cell().add("Analysis name").setBold());
            resultTable.addCell(new Cell().add("Result").setBold());
            resultTable.addCell(new Cell().add("Description").setBold());

            for (int i = 0; i < orderResponse.analysisInOrderList().size(); i++) {
                if (orderResponse.analysisInOrderList().get(i).result() != 0.0) {
                    resultTable.addCell(new Cell().add(orderResponse.analysisInOrderList().get(i).name()));
                    resultTable.addCell(new Cell().add(String.valueOf(orderResponse.analysisInOrderList().get(i).result())));
                    resultTable.addCell(new Cell().add(orderResponse.analysisInOrderList().get(i).description()));
                }
            }
            document.add(resultTable);
            document.close();
            return byteArrayOutputStream.toByteArray();
        }
        return null;
    }

    private int countOfEmptyResult(OrderResponse orderResponse) {
        int count = 0;
        for (int i = 0; i < orderResponse.analysisInOrderList().size(); i++) {
            if (orderResponse.analysisInOrderList().get(i).result() != 0.0) {
                count++;
            }
        }
        return count;
    }
}

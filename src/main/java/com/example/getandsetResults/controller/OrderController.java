package com.example.getandsetResults.controller;

import com.example.getandsetResults.AppException;
import com.example.getandsetResults.model.order.AnalysisRequest;
import com.example.getandsetResults.model.order.OrderResponse;
import com.example.getandsetResults.service.ConverterService;
import com.example.getandsetResults.service.IOrderService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private IOrderService orderService;
    private ConverterService converterService;

    @Autowired
    public OrderController(IOrderService iOrderService_,
    ConverterService converterService_){
        this.orderService = iOrderService_;
        this.converterService = converterService_;
    }

    @GetMapping("/myOrders")
    public OrderResponse getOrderResult(@RequestParam Long idOrder){
        return orderService.find(idOrder)
                .orElseThrow(() -> AppException.orderDoesNotExist(idOrder));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/order")
    public void update(@Valid @RequestBody AnalysisRequest analysisRequest){

        orderService.update(analysisRequest);
    }

    @GetMapping("")
    public List<OrderResponse> findOrderbyClinic(@RequestParam Long clinicId,
                                  @RequestParam boolean finished){
        return orderService.findAllByClinicAndFinished(clinicId, finished);
    }

    @GetMapping("myOrders/download")
    @ResponseBody
    @CrossOrigin
    public ResponseEntity<InputStreamResource> download(@RequestParam Long idOrder) throws IOException {
      var order = orderService.find(idOrder)
              .orElseThrow(()-> AppException.orderDoesNotExist(idOrder));
      var pdfBytes = converterService.createReport(order);
        if(pdfBytes == null) {
            return ResponseEntity.status(202).header(HttpHeaders.RETRY_AFTER).body(null);
        }
            String fileName = "UserInfo.pdf";
            MediaType mediaType = MediaType.parseMediaType("application/pdf");
            File file = new File(fileName);

            FileUtils.writeByteArrayToFile(file, pdfBytes);
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                    .contentType(mediaType)
                    .contentLength(file.length())
                    .body(resource);
    }
}

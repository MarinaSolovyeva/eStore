package com.example.estote.entityDetail;

import java.util.List;

public class ReceiptFormModelWrapper {
    /**
     * The class is necessary to serialize all goods of receipt to POJO
    */
    private List<ReceiptFormModel> receiptFormModels;

    public List<ReceiptFormModel> getReceiptFormModel() {
        return receiptFormModels;
    }

    public void ReceiptFormModels(List<ReceiptFormModel> receiptFormModels) {
        this.receiptFormModels = receiptFormModels;
    }
}


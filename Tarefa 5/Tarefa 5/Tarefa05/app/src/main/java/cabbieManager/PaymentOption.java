package cabbieManager;

public enum PaymentOption {

    CREDIT_CARD("Cartão de Crédito", 1.06f),
    CASH("Dinheiro", 1.0f),
    DEBIT_CARD("Cartão de Débito", 1.04f),
    VOUCHER("Voucher", 1.03f),
    PIX("Pix", 1.01f);


    private final String name;
    private final float fee;

    PaymentOption(String name, float fee) {
        this.name = name;
        this.fee = fee;
    }

    /**
     * Returns the PaymentOption corresponding to the given name, or null if none is found.
     * @param name The name of the payment option to find.
     * @return The PaymentOption with the given name, or null if none is found.
     */
    public static PaymentOption valueOfName(String name) {
        for (PaymentOption paymentOption : PaymentOption.values()) {
            if (paymentOption.name.equals(name)) {
                return paymentOption;
            }
        }
        return null;
    }



    /**
     * Calculates the payment fee for a given value.
     *
     * @param value The value to calculate the fee for.
     * @return The calculated payment fee.
     */

    float calculatePaymentFee(float value) {
        return value * fee;
    }
    


}
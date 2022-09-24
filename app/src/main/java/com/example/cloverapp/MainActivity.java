package com.example.cloverapp;


import android.accounts.Account;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.clover.connector.sdk.v3.PaymentConnector;
import com.clover.sdk.util.CloverAccount;
import com.clover.sdk.v3.connector.ExternalIdUtils;
import com.clover.sdk.v3.connector.IPaymentConnectorListener;
import com.clover.sdk.v3.remotepay.AuthResponse;
import com.clover.sdk.v3.remotepay.CapturePreAuthResponse;
import com.clover.sdk.v3.remotepay.CloseoutResponse;
import com.clover.sdk.v3.remotepay.ConfirmPaymentRequest;
import com.clover.sdk.v3.remotepay.ManualRefundResponse;
import com.clover.sdk.v3.remotepay.PreAuthResponse;
import com.clover.sdk.v3.remotepay.ReadCardDataResponse;
import com.clover.sdk.v3.remotepay.RefundPaymentResponse;
import com.clover.sdk.v3.remotepay.RetrievePaymentResponse;
import com.clover.sdk.v3.remotepay.RetrievePendingPaymentsResponse;
import com.clover.sdk.v3.remotepay.SaleRequest;
import com.clover.sdk.v3.remotepay.SaleResponse;
import com.clover.sdk.v3.remotepay.TipAdded;
import com.clover.sdk.v3.remotepay.TipAdjustAuthResponse;
import com.clover.sdk.v3.remotepay.VaultCardResponse;
import com.clover.sdk.v3.remotepay.VerifySignatureRequest;
import com.clover.sdk.v3.remotepay.VoidPaymentRefundResponse;
import com.clover.sdk.v3.remotepay.VoidPaymentResponse;







/*
2) To get or set any Clover data, go through Clover’s Connector classes,
   such as InventoryConnector.
*/

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the PaymentConnector with a listener
        final PaymentConnector paymentConnector = initializePaymentConnector();

        Button saleButton = findViewById(R.id.saleButton);
        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set up a SaleRequest
                System.out.println("Ya Inicie continua");
                SaleRequest saleRequest = setupSaleRequest();
                paymentConnector.sale(saleRequest);
            }
        });
    }

    private SaleRequest setupSaleRequest() {
        // Create a new SaleRequest and populate the required request fields
        SaleRequest saleRequest = new SaleRequest();
        saleRequest.setExternalId(ExternalIdUtils.generateNewID()); //required, but can be any string
        saleRequest.setAmount(1000L);

        return saleRequest;
    }

    private PaymentConnector initializePaymentConnector() {
        // Get the Clover account that will be used with the service; uses the GET_ACCOUNTS permission
        Account cloverAccount = CloverAccount.getAccount(this);
        // Set your RAID as the remoteApplicationId
        String remoteApplicationId = "SWDEFOTWBD7XT.6W3D67YDX8GN3";

        //Implement the interface
        IPaymentConnectorListener paymentConnectorListener = new IPaymentConnectorListener() {
            @Override
            public void onDeviceDisconnected() {

            }

            @Override
            public void onDeviceConnected() {

            }

            @Override
            public void onPreAuthResponse(PreAuthResponse response) {
                
            }

            @Override
            public void onAuthResponse(AuthResponse response) {

            }

            @Override
            public void onTipAdjustAuthResponse(TipAdjustAuthResponse response) {

            }

            @Override
            public void onCapturePreAuthResponse(CapturePreAuthResponse response) {

            }

            @Override
            public void onVerifySignatureRequest(VerifySignatureRequest request) {

            }

            @Override
            public void onConfirmPaymentRequest(ConfirmPaymentRequest request) {

            }

            @Override
            public void onSaleResponse(SaleResponse response) {
                String result;
                if(response.getSuccess()) {
                    result = "Sale was successful";
                } else {
                    result = "Sale was unsuccessful" + response.getReason() + ":" + response.getMessage();
                }
                Toast.makeText(getApplication().getApplicationContext(), result, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onManualRefundResponse(ManualRefundResponse response) {

            }

            @Override
            public void onRefundPaymentResponse(RefundPaymentResponse response) {

            }

            @Override
            public void onTipAdded(TipAdded tipAdded) {

            }

            @Override
            public void onVoidPaymentResponse(VoidPaymentResponse response) {

            }

            @Override
            public void onVaultCardResponse(VaultCardResponse response) {

            }

            @Override
            public void onRetrievePendingPaymentsResponse(RetrievePendingPaymentsResponse retrievePendingPaymentResponse) {

            }

            @Override
            public void onReadCardDataResponse(ReadCardDataResponse response) {

            }

            @Override
            public void onCloseoutResponse(CloseoutResponse response) {

            }

            @Override
            public void onRetrievePaymentResponse(RetrievePaymentResponse response) {

            }

            @Override
            public void onVoidPaymentRefundResponse(VoidPaymentRefundResponse response) {

            }
        };

        // Implement the other IPaymentConnector listener methods

        // Create the PaymentConnector with the context, account, listener, and RAID
        return new PaymentConnector(this, cloverAccount, paymentConnectorListener, remoteApplicationId);
    }


}


package com.example.tomek.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etBillAmount)
    EditText etBillAmount;
    @BindView(R.id.tvTipPercent)
    TextView tvTipPercent;
    @BindView(R.id.tvTipTotal)
    TextView tvTipTotal;
    @BindView(R.id.tvBillTotalAmount)
    TextView tvBillTotalAmount;
    @BindView(R.id.etChoosePercent)
    EditText etChoosePercent;

    float tipPercentage = 0;
    float tipTotal = 0;
    float finalBillAmount = 0;
    float regularTipPercent = 10;
    float goodTipPercent = 15;
    float excellentTipPercent = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setTipValues();
    }

    private void setTipValues() {
        tvTipPercent.setText(getString(R.string.main_msg_tippercent, tipPercentage));
        tvTipTotal.setText(getString(R.string.main_msg_tiptotal, tipTotal));
        tvBillTotalAmount.setText(getString(R.string.main_msg_billtotalresult, finalBillAmount));
    }

    public void calculateValues(float tipInputPercentage) {
        if (!TextUtils.isEmpty(etBillAmount.getText().toString())) {
            tipPercentage = tipInputPercentage;
            tipTotal = Float.valueOf(etBillAmount.getText().toString()) * (tipPercentage / 100);
            finalBillAmount = Float.valueOf(etBillAmount.getText().toString()) + tipTotal;
            setTipValues();
        } else {
            etBillAmount.setError("Field cannot be empty");
        }
    }

    @OnClick({R.id.ibRegularService, R.id.ibGoodService, R.id.ibExcellentService})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ibRegularService:
                tipPercentage = regularTipPercent;
                break;
            case R.id.ibGoodService:
                tipPercentage = goodTipPercent;
                break;
            case R.id.ibExcellentService:
                tipPercentage = excellentTipPercent;
                break;
        }
        calculateValues(tipPercentage);
    }

    @OnTextChanged(R.id.etBillAmount)
    public void onTextChangedBillAmount() {
        calculateValues(tipPercentage);
    }

    @OnTextChanged(R.id.etChoosePercent)
    public void onTextChangedPercent(){
        if(!TextUtils.isEmpty(etChoosePercent.getText().toString())) {
            tipPercentage = Float.valueOf(etChoosePercent.getText().toString());
            calculateValues(tipPercentage);
        }
        else{
            etChoosePercent.setError("Field cannot be empty");
        }
    }
}

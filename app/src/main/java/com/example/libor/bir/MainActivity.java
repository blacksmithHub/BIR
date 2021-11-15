package com.example.libor.bir;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    public Spinner spinStatus,spinPeriod;
    public EditText txtSalary,txtSSS,txtPH,txtPG;
    public Button btnCal;
    public int statusPosition, periodPosition;
    public float EE, ES, basicSalary, SSS, PH, PG, taxableIncome, basicSalary1,deduct, nearestAmount, percentage, taxAmount, excess, netIncome, pagibig;
    public String salary, sss, pg, ph;
    public AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinStatus = (Spinner)findViewById(R.id.spinStatus);
        spinPeriod = (Spinner)findViewById(R.id.spinPeriod);
        txtSalary = (EditText) findViewById(R.id.editTxtSalary);
        txtSSS = (EditText) findViewById(R.id.editTxtSSS);
        txtPH = (EditText) findViewById(R.id.editTxtPhilHealth);
        txtPG = (EditText) findViewById(R.id.editTxtPagibig);
        btnCal = (Button) findViewById(R.id.btnCalculate);
        txtSalary.requestFocus();
        alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        pagibig = 100.00f;
        txtSalary.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                onChange();
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(txtSalary.length() != 0 && txtSSS.length() != 0 && txtPH.length() != 0 && txtPG.length() != 0) {
                    btnCal.setEnabled(true);
                } else {
                    if(txtSalary.getText().length() == 0) {
                        txtSalary.setError("This field cannot be blank");
                    }
                    btnCal.setEnabled(false);
                }
            }
        });
        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if(txtSSS.getText().length() == 0) {
                 txtSSS.setError("This field cannot be blank"); return;
                 }
                 if(txtPH.getText().length() == 0) {
                 txtPH.setError("This field cannot be blank"); return;
                 }
                 if(txtPG.getText().length() == 0) {
                 txtPG.setError("This field cannot be blank"); return;
                 }
                if (periodPosition == 0) {
                    monthly();
                } else if (periodPosition == 1) {
                    semiMonthly();
                }
            }
        });
        spinStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                statusPosition = i;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinPeriod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                periodPosition = i; onChange();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    public void onChange() {
        if(txtSalary.length() == 0) {
            txtPG.setText(""); txtPH.setText(""); txtSSS.setText(""); return;
        } else {
            sss(); philhealth();
        if(periodPosition == 0) {
                txtSSS.setText("" + EE);
                txtPH.setText("" + ES);
                txtPG.setText("" + pagibig);
        } else if (periodPosition == 1) {
                txtSSS.setText("" + EE / 2);
                txtPH.setText("" + ES / 2);
                txtPG.setText("" + pagibig / 2);
            }
        }
    }
    public void convertion() {
        pg = txtPG.getText().toString();
        PG = Float.parseFloat(pg);
        ph = txtPH.getText().toString();
        PH = Float.parseFloat(ph);
        sss = txtSSS.getText().toString();
        SSS = Float.parseFloat(sss);
        taxableIncome = basicSalary - SSS - PH - PG;
        deduct = SSS + PH + PG;
    }
    public void monthly() {
        convertion();
        if(statusPosition == 0 || statusPosition == 1) {
            if(taxableIncome < 4167.00) {
                nearestAmount = 1.00f; percentage = 0.0f; taxAmount = 0.0f;
            } else if (taxableIncome >= 4167.00 && taxableIncome < 5000.00) {
                nearestAmount = 4167.00f; percentage = 0.05f; taxAmount = 0.0f;
            } else if (taxableIncome >= 5000.00 && taxableIncome < 6667.00) {
                nearestAmount = 5000.00f; percentage = 0.1f; taxAmount = 41.67f;
            } else if (taxableIncome >= 6667.00 && taxableIncome < 10000.00) {
                nearestAmount = 6667.00f; percentage = 0.15f; taxAmount = 208.33f;
            } else if (taxableIncome >= 10000.00 && taxableIncome < 15833.00) {
                nearestAmount = 10000.00f; percentage = 0.2f; taxAmount = 708.33f;
            } else if (taxableIncome >= 15833.00 && taxableIncome < 25000.00) {
                nearestAmount = 15833.00f; percentage = 0.25f; taxAmount = 1875.33f;
            } else if (taxableIncome >= 25000.00 && taxableIncome < 45833.00) {
                nearestAmount = 25000.00f; percentage = 0.3f; taxAmount = 4166.67f;
            } else if (taxableIncome >= 45833.00) {
                nearestAmount = 45833.00f; percentage = 0.32f; taxAmount = 10416.67f;
            } else {
                nearestAmount = 0.0f; percentage = 0.0f; taxAmount = 0.0f;
            }
        }
        else if (statusPosition == 2 || statusPosition == 6) {
            if(taxableIncome < 6250.00) {
                nearestAmount = 1.00f; percentage = 0.0f; taxAmount = 0.0f;
            } else if (taxableIncome >= 6250.00 && taxableIncome < 7083.00) {
                nearestAmount = 6250.00f; percentage = 0.05f; taxAmount = 0.0f;
            } else if (taxableIncome >= 7083.00 && taxableIncome < 8750.00) {
                nearestAmount = 7083.00f; percentage = 0.1f; taxAmount = 41.67f;
            } else if (taxableIncome >= 8750.00 && taxableIncome < 12083.00) {
                nearestAmount = 8750.00f; percentage = 0.15f; taxAmount = 208.33f;
            } else if (taxableIncome >= 12083.00 && taxableIncome< 17917.00) {
                nearestAmount = 12083.00f; percentage = 0.2f; taxAmount = 708.33f;
            } else if (taxableIncome >= 17917.00 && taxableIncome < 27083.00) {
                nearestAmount = 17917.00f; percentage = 0.25f; taxAmount = 1875.33f;
            } else if (taxableIncome >= 27083.00 && taxableIncome < 47917.00) {
                nearestAmount = 27083.00f; percentage = 0.3f; taxAmount = 4166.67f;
            } else if (taxableIncome >= 47917.00) {
                nearestAmount = 47917.00f; percentage = 0.32f; taxAmount = 10416.67f;
            }
        }
        else if (statusPosition == 3 || statusPosition == 7) {
            if(taxableIncome < 8333.00) {
                nearestAmount = 1.00f; percentage = 0.0f; taxAmount = 0.0f;
            } else if (taxableIncome >= 8333.00 && taxableIncome < 9167.00) {
                nearestAmount = 8333.00f; percentage = 0.05f; taxAmount = 0.0f;
            } else if (taxableIncome >= 9167.00 && taxableIncome < 10833.00) {
                nearestAmount = 9167.00f; percentage = 0.1f; taxAmount = 41.67f;
            } else if (taxableIncome >= 10833.00 && taxableIncome < 14167.00) {
                nearestAmount = 10833.00f; percentage = 0.15f; taxAmount = 208.33f;
            } else if (taxableIncome >= 14167.00 && taxableIncome < 20000.00) {
                nearestAmount = 14167.00f; percentage = 0.2f; taxAmount = 708.33f;
            } else if (taxableIncome >= 20000.00 && taxableIncome < 29167.00) {
                nearestAmount = 20000.00f; percentage = 0.25f; taxAmount = 1875.33f;
            } else if (taxableIncome >= 29167.00 && taxableIncome < 50000.00) {
                nearestAmount = 29167.00f; percentage = 0.3f; taxAmount = 4166.67f;
            } else if (taxableIncome >= 50000.00) {
                nearestAmount = 50000.00f; percentage = 0.32f; taxAmount = 10416.67f;
            }
        }
        else if (statusPosition == 4 || statusPosition == 8) {
            if(taxableIncome < 10417.00) {
                nearestAmount = 1.00f; percentage = 0.0f; taxAmount = 0.0f;
            } else if (taxableIncome >= 10417.00 && taxableIncome < 11250.00) {
                nearestAmount = 10417.00f; percentage = 0.05f; taxAmount = 0.0f;
            } else if (taxableIncome >= 11250.00 && taxableIncome < 12197.00) {
                nearestAmount = 11250.00f; percentage = 0.1f; taxAmount = 41.67f;
            } else if (taxableIncome >= 12197.00 && taxableIncome < 16250.00) {
                nearestAmount = 12197.00f; percentage = 0.15f; taxAmount = 208.33f;
            } else if (taxableIncome >= 16250.00 && taxableIncome < 22083.00) {
                nearestAmount = 16250.00f; percentage = 0.2f; taxAmount = 708.33f;
            } else if (taxableIncome >= 22083.00 && taxableIncome < 31250.00) {
                nearestAmount = 22083.00f; percentage = 0.25f; taxAmount = 1875.33f;
            } else if (taxableIncome >= 31250.00 && taxableIncome < 52083.00) {
                nearestAmount = 31250.00f; percentage = 0.3f; taxAmount = 4166.67f;
            } else if (taxableIncome >= 52083.00) {
                nearestAmount = 52083.00f; percentage = 0.32f; taxAmount = 10416.67f;
            }
        }
        else if (statusPosition == 5 || statusPosition == 9) {
            if(taxableIncome < 12500.00) {
                nearestAmount = 1.00f; percentage = 0.0f; taxAmount = 0.0f;
            } else if (taxableIncome >= 12500.00 && taxableIncome < 13333.00) {
                nearestAmount = 12500.00f; percentage = 0.05f; taxAmount = 0.0f;
            } else if (taxableIncome >= 13333.00 && taxableIncome < 15000.00) {
                nearestAmount = 13333.00f; percentage = 0.1f; taxAmount = 41.67f;
            } else if (taxableIncome >= 15000.00 && taxableIncome < 18333.00) {
                nearestAmount = 15000.00f; percentage = 0.15f; taxAmount = 208.33f;
            } else if (taxableIncome >= 18333.00 && taxableIncome < 24167.00) {
                nearestAmount = 18333.00f; percentage = 0.2f; taxAmount = 708.33f;
            } else if (taxableIncome >= 24167.00 && taxableIncome < 33333.00) {
                nearestAmount = 24167.00f; percentage = 0.25f; taxAmount = 1875.33f;
            } else if (taxableIncome >= 33333.00 && taxableIncome < 54167.00) {
                nearestAmount = 33333.00f; percentage = 0.3f; taxAmount = 4166.67f;
            } else if (taxableIncome >= 54167.00) {
                nearestAmount = 54167.00f; percentage = 0.32f; taxAmount = 10416.67f;
            }
        }
        alert();
    }
    public void semiMonthly() {
        convertion();
        if(statusPosition == 0 || statusPosition == 1) {
            if( taxableIncome < 2083.00) {
                nearestAmount = 1.00f; percentage = 0.0f; taxAmount = 0.0f;
            } else if ( taxableIncome >= 2083.00 &&  taxableIncome < 2500.00) {
                nearestAmount = 2083.00f; percentage = 0.05f; taxAmount = 0.0f;
            } else if ( taxableIncome >= 2500.00 &&  taxableIncome < 3333.00) {
                nearestAmount = 2500.00f; percentage = 0.1f; taxAmount = 20.83f;
            } else if ( taxableIncome >= 3333.00 &&  taxableIncome < 5000.00) {
                nearestAmount = 3333.00f; percentage = 0.15f; taxAmount = 104.17f;
            } else if ( taxableIncome >= 5000.00 &&  taxableIncome < 7917.00) {
                nearestAmount = 5000.00f; percentage = 0.2f; taxAmount = 354.17f;
            } else if ( taxableIncome >= 7917.00 &&  taxableIncome < 12500.00) {
                nearestAmount = 7917.00f; percentage = 0.25f; taxAmount = 937.50f;
            } else if ( taxableIncome >= 12500.00 &&  taxableIncome < 22917.00) {
                nearestAmount = 12500.00f; percentage = 0.3f; taxAmount = 2083.33f;
            } else if ( taxableIncome >= 22917.00) {
                nearestAmount = 22917.00f; percentage = 0.32f; taxAmount = 5083.33f;
            } else {
                nearestAmount = 0.0f; percentage = 0.0f; taxAmount = 0.0f;
            }
        }
        else if (statusPosition == 2 || statusPosition == 6) {
            if( taxableIncome < 3125.00) {
                nearestAmount = 1.00f; percentage = 0.0f; taxAmount = 0.0f;
            } else if ( taxableIncome >= 3125.00 &&  taxableIncome < 3542.00) {
                nearestAmount = 3125.00f; percentage = 0.05f; taxAmount = 0.0f;
            } else if ( taxableIncome >= 3542.00 &&  taxableIncome < 4375.00) {
                nearestAmount = 3542.00f; percentage = 0.1f; taxAmount = 20.83f;
            } else if ( taxableIncome >= 4375.00 &&  taxableIncome < 6042.00) {
                nearestAmount = 4375.00f; percentage = 0.15f; taxAmount = 104.17f;
            } else if ( taxableIncome >= 6042.00 &&  taxableIncome < 8958.00) {
                nearestAmount = 6042.00f; percentage = 0.2f; taxAmount = 354.17f;
            } else if ( taxableIncome >= 8958.00 &&  taxableIncome < 13542.00) {
                nearestAmount = 8958.00f; percentage = 0.25f; taxAmount = 937.50f;
            } else if ( taxableIncome >= 13542.00 &&  taxableIncome < 23958.00) {
                nearestAmount = 13542.00f; percentage = 0.3f; taxAmount = 2083.33f;
            } else if ( taxableIncome >= 23958.00) {
                nearestAmount = 23958.00f; percentage = 0.32f; taxAmount = 5083.33f;
            } else {
                nearestAmount = 0.0f; percentage = 0.0f; taxAmount = 0.0f;
            }
        }
        else if (statusPosition == 3 || statusPosition == 7) {
            if( taxableIncome < 4167.00) {
                nearestAmount = 1.00f; percentage = 0.0f; taxAmount = 0.0f;
            } else if ( taxableIncome >= 4167.00 &&  taxableIncome < 4583.00) {
                nearestAmount = 4167.00f; percentage = 0.05f; taxAmount = 0.0f;
            } else if ( taxableIncome >= 4583.00 &&  taxableIncome < 5417.00) {
                nearestAmount = 4583.00f; percentage = 0.1f; taxAmount = 20.83f;
            } else if (taxableIncome >= 5417.00 && taxableIncome < 7083.00) {
                nearestAmount = 5417.00f; percentage = 0.15f; taxAmount = 104.17f;
            } else if (taxableIncome >= 7083.00 && taxableIncome < 10000.00) {
                nearestAmount = 7083.00f; percentage = 0.2f; taxAmount = 354.17f;
            } else if (taxableIncome >= 10000.00 && taxableIncome < 14583.00) {
                nearestAmount = 10000.00f; percentage = 0.25f; taxAmount = 937.50f;
            } else if (taxableIncome >= 14583.00 && taxableIncome < 25000.00) {
                nearestAmount = 14583.00f; percentage = 0.3f; taxAmount = 2083.33f;
            } else if (taxableIncome >= 25000.00) {
                nearestAmount = 25000.00f; percentage = 0.32f; taxAmount = 5083.33f;
            } else {
                nearestAmount = 0.0f; percentage = 0.0f; taxAmount = 0.0f;
            }
        }
        else if (statusPosition == 4 || statusPosition == 8) {
            if(taxableIncome < 5208.00) {
                nearestAmount = 1.00f; percentage = 0.0f; taxAmount = 0.0f;
            } else if (taxableIncome >= 5208.00 && taxableIncome < 5625.00) {
                nearestAmount = 5208.00f; percentage = 0.05f; taxAmount = 0.0f;
            } else if (taxableIncome >= 5625.00 && taxableIncome < 6458.00) {
                nearestAmount = 5625.00f; percentage = 0.1f; taxAmount = 20.83f;
            } else if (taxableIncome >= 6458.00 && taxableIncome < 8125.00) {
                nearestAmount = 6458.00f; percentage = 0.15f; taxAmount = 104.17f;
            } else if (taxableIncome >= 8125.00 && taxableIncome < 11042.00) {
                nearestAmount = 8125.00f; percentage = 0.2f; taxAmount = 354.17f;
            } else if (taxableIncome >= 11042.00 && taxableIncome < 15625.00) {
                nearestAmount = 11042.00f; percentage = 0.25f; taxAmount = 937.50f;
            } else if (taxableIncome >= 15625.00 && taxableIncome < 26042.00) {
                nearestAmount = 15625.00f; percentage = 0.3f; taxAmount = 2083.33f;
            } else if (taxableIncome >= 26042.00) {
                nearestAmount = 26042.00f; percentage = 0.32f; taxAmount = 5083.33f;
            } else {
                nearestAmount = 0.0f; percentage = 0.0f; taxAmount = 0.0f;
            }
        }
        else if (statusPosition == 5 || statusPosition == 9) {
            if(taxableIncome < 6250.00) {
                nearestAmount = 1.00f; percentage = 0.0f; taxAmount = 0.0f;
            } else if (taxableIncome >= 6250.00 && taxableIncome < 6667.00) {
                nearestAmount = 6250.00f; percentage = 0.05f; taxAmount = 0.0f;
            } else if (taxableIncome >= 6667.00 && taxableIncome < 7500.00) {
                nearestAmount = 6667.00f; percentage = 0.1f; taxAmount = 20.83f;
            } else if (taxableIncome >= 7500.00 && taxableIncome < 9167.00) {
                nearestAmount = 7500.00f; percentage = 0.15f; taxAmount = 104.17f;
            } else if (taxableIncome >= 9167.00 && taxableIncome < 12083.00) {
                nearestAmount = 9167.00f; percentage = 0.2f; taxAmount = 354.17f;
            } else if (taxableIncome >= 12083.00 && taxableIncome < 16667.00) {
                nearestAmount = 12083.00f; percentage = 0.25f; taxAmount = 937.50f;
            } else if (taxableIncome >= 16667.00 && taxableIncome < 27083.00) {
                nearestAmount = 16667.00f; percentage = 0.3f; taxAmount = 2083.33f;
            } else if (taxableIncome >= 27083.00) {
                nearestAmount = 27083.00f; percentage = 0.32f; taxAmount = 5083.33f;
            } else {
                nearestAmount = 0.0f; percentage = 0.0f; taxAmount = 0.0f;
            }
        }
        alert();
    }
    public void alert() {
        excess = (taxableIncome - nearestAmount) * percentage + taxAmount;
        netIncome = (taxableIncome - excess);
        alertDialog.setTitle("BIR Tax Calculator");
        alertDialog.setMessage("Total Deductions: " + deduct + "\n\nTaxable Income: " + taxableIncome + "\n\nWithholding Tax: " + excess + "\n\nNet Income: " + netIncome);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Close", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }); alertDialog.show();
    }
    public float convert() {
        salary = txtSalary.getText().toString();
        basicSalary = Float.parseFloat(salary);
        if(periodPosition == 0) {
            return basicSalary;
        } else if (periodPosition == 1) {
            basicSalary1 = basicSalary * 2;  return basicSalary1;
        }
        return basicSalary;
    }
    public float sss() {
        convert();
        if(periodPosition == 0)
        {
            if (basicSalary <= 1249.99 && basicSalary >= 1000.00) {
                EE = 36.30f; return EE;
            } else if (basicSalary <= 1749.99 && basicSalary >= 1250.00) {
                EE = 54.50f; return EE;
            } else if (basicSalary <= 2249.99 && basicSalary >= 1750.00) {
                EE = 72.70f; return EE;
            } else if (basicSalary <= 2749.99 && basicSalary >= 2250.00) {
                EE = 90.80f; return EE;
            } else if (basicSalary <= 3249.99 && basicSalary >= 2750.00) {
                EE = 109.00f; return EE;
            } else if (basicSalary <= 3749.99 && basicSalary >= 3250.00) {
                EE = 127.20f; return EE;
            } else if (basicSalary <= 4249.99 && basicSalary >= 3750.00) {
                EE = 145.30f; return EE;
            } else if (basicSalary <= 4749.99 && basicSalary >= 4250.00) {
                EE = 163.50f; return EE;
            } else if (basicSalary <= 5249.99 && basicSalary >= 4750.00) {
                EE = 181.70f; return EE;
            } else if (basicSalary <= 5749.99 && basicSalary >= 5250.00) {
                EE = 199.80f; return EE;
            } else if (basicSalary <= 6249.99 && basicSalary >= 5750.00) {
                EE = 218.00f; return EE;
            } else if (basicSalary <= 6749.99 && basicSalary >= 6250.00) {
                EE = 236.20f; return EE;
            } else if (basicSalary <= 7249.99 && basicSalary >= 6750.00) {
                EE = 254.30f; return EE;
            } else if (basicSalary <= 7749.99 && basicSalary >= 7250.00) {
                EE = 272.50f; return EE;
            } else if (basicSalary <= 8249.99 && basicSalary >= 7750.00) {
                EE = 290.70f; return EE;
            } else if (basicSalary <= 8749.99 && basicSalary >= 8250.00) {
                EE = 308.80f; return EE;
            } else if (basicSalary <= 9249.99 && basicSalary >= 8750.00) {
                EE = 327.00f; return EE;
            } else if (basicSalary <= 9749.99 && basicSalary >= 9250.00) {
                EE = 345.20f; return EE;
            } else if (basicSalary <= 10249.99 && basicSalary >= 9750.00) {
                EE = 363.30f; return EE;
            } else if (basicSalary <= 10749.99 && basicSalary >= 10250.00) {
                EE = 381.50f; return EE;
            } else if (basicSalary <= 11249.99 && basicSalary >= 10750.00) {
                EE = 399.70f; return EE;
            } else if (basicSalary <= 11749.99 && basicSalary >= 11250.00) {
                EE = 417.80f; return EE;
            } else if (basicSalary <= 12249.99 && basicSalary >= 11750.00) {
                EE = 436.00f; return EE;
            } else if (basicSalary <= 12749.99 && basicSalary >= 12250.00) {
                EE = 454.20f; return EE;
            } else if (basicSalary <= 13249.99 && basicSalary >= 12750.00) {
                EE = 472.30f; return EE;
            } else if (basicSalary <= 13749.99 && basicSalary >= 13250.00) {
                EE = 490.50f; return EE;
            } else if (basicSalary <= 14249.99 && basicSalary >= 13750.00) {
                EE = 508.70f; return EE;
            } else if (basicSalary <= 14749.99 && basicSalary >= 14250.00) {
                EE = 526.80f; return EE;
            } else if (basicSalary <= 15249.99 && basicSalary >= 14750.00) {
                EE = 545.00f; return EE;
            } else if (basicSalary <= 15749.99 && basicSalary >= 15250.00) {
                EE = 563.20f; return EE;
            } else if (basicSalary >= 15750.00) {
                EE = 581.30f; return EE;
            } else {
                EE = 00.00f; return EE;
            }
        }
        else if (periodPosition == 1) {
            basicSalary1 = basicSalary * 2;
            if (basicSalary1 <= 1249.99 && basicSalary1 >= 1000.00) {
                EE = 36.30f; return EE;
            } else if (basicSalary1 <= 1749.99 && basicSalary1 >= 1250.00) {
                EE = 54.50f; return EE;
            } else if (basicSalary1 <= 2249.99 && basicSalary1 >= 1750.00) {
                EE = 72.70f; return EE;
            } else if (basicSalary1 <= 2749.99 && basicSalary1 >= 2250.00) {
                EE = 90.80f; return EE;
            } else if (basicSalary1 <= 3249.99 && basicSalary1 >= 2750.00) {
                EE = 109.00f; return EE;
            } else if (basicSalary1 <= 3749.99 && basicSalary1 >= 3250.00) {
                EE = 127.20f; return EE;
            } else if (basicSalary1 <= 4249.99 && basicSalary1 >= 3750.00) {
                EE = 145.30f; return EE;
            } else if (basicSalary1 <= 4749.99 && basicSalary1 >= 4250.00) {
                EE = 163.50f; return EE;
            } else if (basicSalary1 <= 5249.99 && basicSalary1 >= 4750.00) {
                EE = 181.70f; return EE;
            } else if (basicSalary1 <= 5749.99 && basicSalary1 >= 5250.00) {
                EE = 199.80f; return EE;
            } else if (basicSalary1 <= 6249.99 && basicSalary1 >= 5750.00) {
                EE = 218.00f; return EE;
            } else if (basicSalary1 <= 6749.99 && basicSalary1 >= 6250.00) {
                EE = 236.20f; return EE;
            } else if (basicSalary1 <= 7249.99 && basicSalary1 >= 6750.00) {
                EE = 254.30f; return EE;
            } else if (basicSalary1 <= 7749.99 && basicSalary1 >= 7250.00) {
                EE = 272.50f; return EE;
            } else if (basicSalary1 <= 8249.99 && basicSalary1 >= 7750.00) {
                EE = 290.70f; return EE;
            } else if (basicSalary1 <= 8749.99 && basicSalary1 >= 8250.00) {
                EE = 308.80f; return EE;
            } else if (basicSalary1 <= 9249.99 && basicSalary1 >= 8750.00) {
                EE = 327.00f; return EE;
            } else if (basicSalary1 <= 9749.99 && basicSalary1 >= 9250.00) {
                EE = 345.20f; return EE;
            } else if (basicSalary1 <= 10249.99 && basicSalary1 >= 9750.00) {
                EE = 363.30f; return EE;
            } else if (basicSalary1 <= 10749.99 && basicSalary1 >= 10250.00) {
                EE = 381.50f; return EE;
            } else if (basicSalary1 <= 11249.99 && basicSalary1 >= 10750.00) {
                EE = 399.70f; return EE;
            } else if (basicSalary1 <= 11749.99 && basicSalary1 >= 11250.00) {
                EE = 417.80f; return EE;
            } else if (basicSalary1 <= 12249.99 && basicSalary1 >= 11750.00) {
                EE = 436.00f; return EE;
            } else if (basicSalary1 <= 12749.99 && basicSalary1 >= 12250.00) {
                EE = 454.20f; return EE;
            } else if (basicSalary1 <= 13249.99 && basicSalary1 >= 12750.00) {
                EE = 472.30f; return EE;
            } else if (basicSalary1 <= 13749.99 && basicSalary1 >= 13250.00) {
                EE = 490.50f; return EE;
            } else if (basicSalary1 <= 14249.99 && basicSalary1 >= 13750.00) {
                EE = 508.70f; return EE;
            } else if (basicSalary1 <= 14749.99 && basicSalary1 >= 14250.00) {
                EE = 526.80f; return EE;
            } else if (basicSalary1 <= 15249.99 && basicSalary1 >= 14750.00) {
                EE = 545.00f; return EE;
            } else if (basicSalary1 <= 15749.99 && basicSalary1 >= 15250.00) {
                EE = 563.20f; return EE;
            } else if (basicSalary1 >= 15750.00) {
                EE = 581.30f; return EE;
            } else {
                EE = 00.00f; return EE;
            }
        }
        return EE;
    }
    public float philhealth() {
        convert();
        if(periodPosition == 0) {
            if (basicSalary <= 8999.99 && basicSalary >= 199.00) {
                ES = 100.00f; return ES;
            } else if (basicSalary <= 9999.99 && basicSalary >= 9000.00) {
                ES = 112.50f; return ES;
            } else if (basicSalary <= 10999.99 && basicSalary >= 10000.00) {
                ES = 125.00f; return ES;
            } else if (basicSalary <= 11999.99 && basicSalary >= 11000.00) {
                ES = 137.50f; return ES;
            } else if (basicSalary <= 12999.99 && basicSalary >= 12000.00) {
                ES = 150.00f; return ES;
            } else if (basicSalary <= 13999.99 && basicSalary >= 13000.00) {
                ES = 162.50f; return ES;
            } else if (basicSalary <= 14999.99 && basicSalary >= 14000.00) {
                ES = 175.00f; return ES;
            } else if (basicSalary <= 15999.99 && basicSalary >= 15000.00) {
                ES = 187.50f; return ES;
            } else if (basicSalary <= 16999.99 && basicSalary >= 16000.00) {
                ES = 200.00f; return ES;
            } else if (basicSalary <= 17999.99 && basicSalary >= 17000.00) {
                ES = 212.50f; return ES;
            } else if (basicSalary <= 18999.99 && basicSalary >= 18000.00) {
                ES = 225.00f; return ES;
            } else if (basicSalary <= 19999.99 && basicSalary >= 19000.00) {
                ES = 237.50f; return ES;
            } else if (basicSalary <= 20999.99 && basicSalary >= 20000.00) {
                ES = 250.00f; return ES;
            } else if (basicSalary <= 21999.99 && basicSalary >= 21000.00) {
                ES = 262.50f; return ES;
            } else if (basicSalary <= 22999.99 && basicSalary >= 22000.00) {
                ES = 275.00f; return ES;
            } else if (basicSalary <= 23999.99 && basicSalary >= 23000.00) {
                ES = 287.50f; return ES;
            } else if (basicSalary <= 24999.99 && basicSalary >= 24000.00) {
                ES = 300.00f; return ES;
            } else if (basicSalary <= 25999.99 && basicSalary >= 25000.00) {
                ES = 312.50f; return ES;
            } else if (basicSalary <= 26999.99 && basicSalary >= 26000.00) {
                ES = 325.00f; return ES;
            } else if (basicSalary <= 27999.99 && basicSalary >= 27000.00) {
                ES = 337.50f; return ES;
            } else if (basicSalary <= 28999.99 && basicSalary >= 28000.00) {
                ES = 350.00f; return ES;
            } else if (basicSalary <= 29999.99 && basicSalary >= 29000.00) {
                ES = 362.50f; return ES;
            } else if (basicSalary <= 30999.99 && basicSalary >= 30000.00) {
                ES = 375.00f; return ES;
            } else if (basicSalary <= 31999.99 && basicSalary >= 31000.00) {
                ES = 387.50f; return ES;
            } else if (basicSalary <= 32999.99 && basicSalary >= 32000.00) {
                ES = 400.00f; return ES;
            } else if (basicSalary <= 33999.99 && basicSalary >= 33000.00) {
                ES = 412.50f; return ES;
            } else if (basicSalary <= 34999.99 && basicSalary >= 34000.00) {
                ES = 425.00f; return ES;
            } else if (basicSalary >= 35000.00 || basicSalary1 >= 35000.00) {
                ES = 437.50f; return ES;
            } else {
                ES = 00.00f; return ES;
            }
        }
        else if (periodPosition == 1) {
            basicSalary1 = basicSalary * 2;
            if (basicSalary1 <= 8999.99) {
                ES = 100.00f; return ES;
            } else if (basicSalary1 <= 9999.99 && basicSalary1 >= 9000.00) {
                ES = 112.50f; return ES;
            } else if (basicSalary1 <= 10999.99 && basicSalary1 >= 10000.00) {
                ES = 125.00f; return ES;
            } else if (basicSalary1 <= 11999.99 && basicSalary1 >= 11000.00) {
                ES = 137.50f; return ES;
            } else if (basicSalary1 <= 12999.99 && basicSalary1 >= 12000.00) {
                ES = 150.00f; return ES;
            } else if (basicSalary1 <= 13999.99 && basicSalary1 >= 13000.00) {
                ES = 162.50f; return ES;
            } else if (basicSalary1 <= 14999.99 && basicSalary1 >= 14000.00) {
                ES = 175.00f; return ES;
            } else if (basicSalary1 <= 15999.99 && basicSalary1 >= 15000.00) {
                ES = 187.50f; return ES;
            } else if (basicSalary1 <= 16999.99 && basicSalary1 >= 16000.00) {
                ES = 200.00f; return ES;
            } else if (basicSalary1 <= 17999.99 && basicSalary1 >= 17000.00) {
                ES = 212.50f; return ES;
            } else if (basicSalary1 <= 18999.99 && basicSalary1 >= 18000.00) {
                ES = 225.00f; return ES;
            } else if (basicSalary1 <= 19999.99 && basicSalary1 >= 19000.00) {
                ES = 237.50f; return ES;
            } else if (basicSalary1 <= 20999.99 && basicSalary1 >= 20000.00) {
                ES = 250.00f; return ES;
            } else if (basicSalary1 <= 21999.99 && basicSalary1 >= 21000.00) {
                ES = 262.50f; return ES;
            } else if (basicSalary1 <= 22999.99 && basicSalary1 >= 22000.00) {
                ES = 275.00f; return ES;
            } else if (basicSalary1 <= 23999.99 && basicSalary1 >= 23000.00) {
                ES = 287.50f; return ES;
            } else if (basicSalary1 <= 24999.99 && basicSalary1 >= 24000.00) {
                ES = 300.00f; return ES;
            } else if (basicSalary1 <= 25999.99 && basicSalary1 >= 25000.00) {
                ES = 312.50f; return ES;
            } else if (basicSalary1 <= 26999.99 && basicSalary1 >= 26000.00) {
                ES = 325.00f; return ES;
            } else if (basicSalary1 <= 27999.99 && basicSalary1 >= 27000.00) {
                ES = 337.50f; return ES;
            } else if (basicSalary1 <= 28999.99 && basicSalary1 >= 28000.00) {
                ES = 350.00f; return ES;
            } else if (basicSalary1 <= 29999.99 && basicSalary1 >= 29000.00) {
                ES = 362.50f; return ES;
            } else if (basicSalary1 <= 30999.99 && basicSalary1 >= 30000.00) {
                ES = 375.00f; return ES;
            } else if (basicSalary1 <= 31999.99 && basicSalary1 >= 31000.00) {
                ES = 387.50f; return ES;
            } else if (basicSalary1 <= 32999.99 && basicSalary1 >= 32000.00) {
                ES = 400.00f; return ES;
            } else if (basicSalary1 <= 33999.99 && basicSalary1 >= 33000.00) {
                ES = 412.50f; return ES;
            } else if (basicSalary1 <= 34999.99 && basicSalary1 >= 34000.00) {
                ES = 425.00f; return ES;
            } else if (basicSalary1 >= 35000.00) {
                ES = 437.50f; return ES;
            } else {
                ES = 00.00f; return ES;
            }
        }
        return ES;
    }
}
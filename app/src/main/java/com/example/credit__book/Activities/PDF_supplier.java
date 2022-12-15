package com.example.credit__book.Activities;


import static java.lang.Float.parseFloat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.credit__book.Model.SessionManager;
import com.example.credit__book.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class PDF_supplier extends AppCompatActivity {

    private TextView nom, numb, adr, ema, balanceC;
    private Button down;
    private Bitmap bitmap;
    private String  nam, email, address;
    private String phone;
    private LinearLayout pdf;
//    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://creditbook-9d8ab-default-rtdb.firebaseio.com/");

    private TextView CashIn;
    private TextView CashOut;
    float cashIn = 0;
    float cashOut = 0;
    float bala = 0;


//    public static final int REQUEST_STORAGE = 101;
//    String storagePermission[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_supplier);


//        storagePermission=new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE};

        pdf = findViewById(R.id.PdfLayout);
        down = findViewById(R.id.save);
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bitmap = loadBitmapFromView(pdf,pdf.getWidth(),pdf.getHeight());
                createPdf(bitmap);
            }
        });


        nam = getIntent().getStringExtra("Supplier Name");
        phone = getIntent().getStringExtra("Supplier Phone");
        email = getIntent().getStringExtra("Supplier Email");
        address = getIntent().getStringExtra("Supplier Address");




        nom = findViewById(R.id.name);
        numb = findViewById(R.id.app);
        adr = findViewById(R.id.adre);
        ema = findViewById(R.id.em);
        balanceC = findViewById(R.id.bal);


        nom.setText(nam);
        numb.setText(phone);
        adr.setText(address);
        ema.setText(email);


        CashIn = findViewById(R.id.cashin);
        CashOut = findViewById(R.id.cashout);

        FirebaseDatabase dbRef = FirebaseDatabase.getInstance();

        DatabaseReference db = dbRef.getReference("OperationsSuppliers").child(Objects.requireNonNull(new SessionManager(this).getUserDetails().get(SessionManager.TELEPHONE)));

        db.child(phone).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot snapshot1 : dataSnapshot.getChildren()) {
                        if (snapshot1.child("operationType").getValue().toString().equalsIgnoreCase("Cash In")) {
                            cashIn += parseFloat(snapshot1.child("balance_supplier").getValue().toString());
                        } else {
                            cashOut += parseFloat(snapshot1.child("balance_supplier").getValue().toString());
                        }
//                        }
                    }
                }
                Log.i("debug", "cashIn: " + cashIn + " cashOut: " + cashOut);
                // format to morrocan dirham
                NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("fr", "MA"));
                CashIn.setText(formatter.format(cashIn));
                CashOut.setText(formatter.format(cashOut));
                bala = cashIn-cashOut;
                balanceC.setText(formatter.format(bala));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("debug", "onFailure: " + e.getMessage());
            }
        });







    }

    private void createPdf(Bitmap bitmap) {
        DisplayMetrics displayMetrics=new DisplayMetrics();
        this.getWindowManager ().getDefaultDisplay().getMetrics (displayMetrics);
        float height=displayMetrics.heightPixels;
        float width=displayMetrics.widthPixels;

        int convertHeight=(int) height,
                convertwidth=(int)width;

        PdfDocument document=new PdfDocument ();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertwidth,convertHeight,1).create();
        PdfDocument.Page page=document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();
        Paint paint=new Paint();
        canvas.drawPaint (paint);
        this.bitmap = Bitmap.createScaledBitmap (this.bitmap, convertwidth, convertHeight, true);
        canvas.drawBitmap(this.bitmap, 0, 0, null);
        document.finishPage(page);

        File filepath=new File(this.getExternalFilesDir(null),"supplier.pdf");
        try {
            document.writeTo(new FileOutputStream(filepath));
            Toast.makeText( this,  "pdf created successfully !", Toast.LENGTH_SHORT).show();

        }catch (IOException e) {
            e.printStackTrace();
        }

        document.close();
        openPdf();
    }

    private void openPdf() {
        File file=new File(this.getExternalFilesDir(null),"/supplier.pdf");
        Intent intent=new Intent(Intent.ACTION_VIEW);
//        Uri uri = Uri.fromFile(file);
        Uri uri = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".provider", file);

        intent.setDataAndType(uri, "application/pdf");
        intent.setFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        try {
            startActivity (intent);
        }catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No application found for pdf reader !", Toast.LENGTH_SHORT).show();
        }
    }

    private Bitmap loadBitmapFromView(LinearLayout pdf, int width, int height) {
        bitmap = Bitmap.createBitmap (width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        pdf.draw(canvas);
        return bitmap;
    }


}
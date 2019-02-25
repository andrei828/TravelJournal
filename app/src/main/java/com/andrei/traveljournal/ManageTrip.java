package com.andrei.traveljournal;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Objects;

public class ManageTrip extends AppCompatActivity {

    public RatingBar rating_bar;
    public TextView name_textView;
    public TextView destination_textView;

    public Button camera_button;
    public Button gallery_button;
    public Button end_date_button;
    public Button start_date_button;
    public TextView camera_textView;
    public TextView gallery_textView;
    public ImageView camera_imageView;
    public ImageView gallery_imageView;

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_GALLERY = 100;
    public static final String EXTRA_REPLY_NAME = "com.andrei.traveljournal.REPLY_NAME";
    public static final String EXTRA_REPLY_DESTINATION = "com.andrei.traveljournal.REPLY_DESTINATION";
    public static final String EXTRA_REPLY_NUM_OF_STARS = "com.andrei.traveljournal.REPLY_NUM_OF_STARS";

    private DatePickerDialog.OnDateSetListener mEndDateSetListener;
    private DatePickerDialog.OnDateSetListener mStartDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_trip);

        camera_imageView = findViewById(R.id.camera_image);
        end_date_button = findViewById(R.id.end_trip_date);
        gallery_imageView = findViewById(R.id.gallery_image);
        camera_button = findViewById(R.id.open_camera_button);
        start_date_button = findViewById(R.id.start_trip_date);
        gallery_button = findViewById(R.id.open_gallery_button);
        camera_textView = findViewById(R.id.open_camera_text_view);
        gallery_textView = findViewById(R.id.open_gallery_text_view);

        mStartDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                start_date_button.setText(date);
            }
        };

        mEndDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                end_date_button.setText(date);
            }
        };

        rating_bar = findViewById(R.id.rating_bar);
        name_textView = findViewById(R.id.name_textview);
        destination_textView = findViewById(R.id.destination_textview);

        final Button button = findViewById(R.id.save_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(name_textView.getText().toString())
                        || TextUtils.isEmpty(destination_textView.getText().toString())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    double num_of_stars = (double) rating_bar.getRating();
                    String name = name_textView.getText().toString();
                    String destination = destination_textView.getText().toString();

                    replyIntent.putExtra(EXTRA_REPLY_NAME, name);
                    replyIntent.putExtra(EXTRA_REPLY_DESTINATION, destination);
                    replyIntent.putExtra(EXTRA_REPLY_NUM_OF_STARS, num_of_stars);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }

    public void openCamera(View view) {
        dispatchTakePictureIntent();
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void openGallery(View view) {
        dispatchGalleryIntent();
    }

    private void dispatchGalleryIntent() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        if (galleryIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(galleryIntent, REQUEST_IMAGE_GALLERY);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
    // Bitmap -> FileoutputStream
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            imageBitmap = Bitmap.createBitmap(imageBitmap,
                    0, 0, imageBitmap.getWidth(), imageBitmap.getHeight(), matrix, true);

            camera_imageView.setImageBitmap(imageBitmap);
            camera_imageView.setVisibility(View.VISIBLE);
            camera_textView.setText("Retake Photo");
            camera_button.setVisibility(View.GONE);
        } else if (requestCode == REQUEST_IMAGE_GALLERY && resultCode == RESULT_OK) {
            gallery_imageView.setImageURI(data.getData());
            gallery_imageView.setVisibility(View.VISIBLE);
            gallery_button.setVisibility(View.GONE);
        }
    }

    public void startDateClicked(View view) {
        displayDatePickerDialog(mStartDateSetListener);
    }

    public void endDateClicked(View view) {
        displayDatePickerDialog(mEndDateSetListener);
    }

    public void displayDatePickerDialog(DatePickerDialog.OnDateSetListener DateSetListener)
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) - 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        cal.set(year, month-2, day);

        DatePickerDialog dialog = new DatePickerDialog(
                ManageTrip.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                DateSetListener,
                year,month,day);

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}

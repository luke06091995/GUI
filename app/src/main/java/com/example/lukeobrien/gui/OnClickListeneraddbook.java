package com.example.lukeobrien.gui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class OnClickListeneraddbook implements View.OnClickListener {
    @Override
    public void onClick(View view) {


        final Context  context = view.getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.book_info, null, false);

        final EditText editTextTitle= (EditText) formElementsView.findViewById(R.id.editTextTitle);
        final EditText editTextAuthor = (EditText) formElementsView.findViewById(R.id.editTextAuthor);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Add Book")
                .setPositiveButton("Save",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                String bookTitle = editTextTitle.getText().toString();
                                String bookAuthor = editTextAuthor.getText().toString();

                                Bookobject bookobject = new Bookobject();
                                bookobject.title= bookTitle ;
                                bookobject.author= bookAuthor;

                                boolean createSuccessful = new BookController(context).create(bookobject);


                                if(createSuccessful){
                                    Toast.makeText(context, "Book Saved", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(context, "Unable to save book.", Toast.LENGTH_SHORT).show();
                                }

                              ((MainActivity) context).readRecords();


                                //

                                dialog.cancel();
                            }

                        }).show();

    }
}

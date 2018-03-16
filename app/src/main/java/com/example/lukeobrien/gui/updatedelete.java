package com.example.lukeobrien.gui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class updatedelete implements View.OnLongClickListener {

    Context context;
    String id;

    @Override
    public boolean onLongClick(View view) {

        context = view.getContext();
        id = view.getTag().toString();

        final CharSequence[] items = { "Update", "Delete" };

        new AlertDialog.Builder(context).setTitle("Book Information")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {

                        //Toast.makeText(context, String.valueOf(item), Toast.LENGTH_SHORT).show();

                        if (item == 0) {
                            Toast.makeText(context, "Edit Record", Toast.LENGTH_LONG).show();
                            editRecord(Integer.parseInt(id));
                        }

                        else if (item == 1) {

                            boolean deleteSuccessful = new BookController(context).delete(Integer.parseInt(id));

                            if (deleteSuccessful){
                                Toast.makeText(context, "Book Deleted", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Unable to delete book", Toast.LENGTH_SHORT).show();
                            }


                            ((MainActivity) context).readRecords();

                        }



                        dialog.dismiss();

                    }
                }).show();


        return false;}


    public void editRecord(final int bookId) {

        final BookController bookController = new BookController(context);
//**        Bookobject bookobject = bookController.readSingleRecord(bookId);

//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        final View formElementsView = inflater.inflate(R.layout.book_info, null, false);

//        final EditText editTexttitle = (EditText) formElementsView.findViewById(R.id.editTextTitle);
//        final EditText editTextauthor = (EditText) formElementsView.findViewById(R.id.editTextAuthor);

//        editTexttitle.setText(bookobject.title);
//        editTextauthor.setText(bookobject.author);

        Toast.makeText(context, "in edit record", Toast.LENGTH_SHORT).show();

//        new AlertDialog.Builder(context)
//                .setView(formElementsView)
//                .setTitle("Edit Record")
//                .setPositiveButton("Save Changes",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {

//                                Bookobject bookobject = new Bookobject();
//                                bookobject.id = bookId;
//                                bookobject.title = editTexttitle.getText().toString();
//                                bookobject.author = editTextauthor.getText().toString();

//                                boolean updateSuccessful = bookController.update(bookobject);

//                                if(updateSuccessful){
//                                    Toast.makeText(context, "Student record was updated.", Toast.LENGTH_SHORT).show();
//                                }else{
//                                    Toast.makeText(context, "Unable to update student record.", Toast.LENGTH_SHORT).show();
//                                }


//                                ((MainActivity) context).readRecords();

//                                dialog.cancel();
//                            }

//                        }).show();
    }


}
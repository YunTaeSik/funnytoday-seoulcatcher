package todday.funny.seoulcatcher.ui.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import todday.funny.seoulcatcher.R;

public class AlertDialogCreate {
    private static volatile AlertDialogCreate singletonInstance = null;
    private AlertDialog.Builder alertDialog;

    public static AlertDialogCreate getInstance(Context context) {
        if (singletonInstance == null) {
            synchronized (AlertDialogCreate.class) {
                if (singletonInstance == null) {
                    singletonInstance = new AlertDialogCreate(context);
                }
            }
        }
        return singletonInstance;
    }

    private AlertDialogCreate(Context context) {
        alertDialog = new AlertDialog.Builder(context);
        alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
    }
}

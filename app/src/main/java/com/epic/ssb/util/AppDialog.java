package com.epic.ssb.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.epic.ssb.R;
import com.epic.ssb.R;

import dmax.dialog.SpotsDialog;


public class AppDialog {

  public static AlertDialog initProgressDialog(Context context, String msg) {
    AlertDialog alertDialog = new SpotsDialog.Builder()
        .setContext(context)
        .setTheme(R.style.CustomProgress)
        .setMessage(msg)
        .build();

    return alertDialog;

    //        ProgressDialog dialog = new ProgressDialog(context, R.style.DialogTheme);
    //        dialog.setCode(context.getResources().getString(R.string.app_name));
    //        dialog.setMessage(msg);
    //        dialog.setCancelable(false);
    //        return dialog;
  }

  public static void showQuestionDialog(Context context, String msg, String btnPos, String btnNeg,
                                        final DialogEvent event) {
    AlertDialog.Builder builder =
        new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
    builder.setCancelable(false);
    builder.setIcon(R.mipmap.ic_launcher);
    builder.setTitle(context.getResources().getString(R.string.app_name))
        .setMessage(msg)
        .setPositiveButton(btnPos, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            if (event != null) {
              event.onPositiveClicked();
            }
          }
        })
        .setNegativeButton(btnNeg, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            if (event != null) {
              event.onNegativeClicked();
            }
          }
        })
        .show();
  }

  public static void showQuestionDialog(Context context, String msg, final DialogEvent event) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setCancelable(false);
    builder.setIcon(R.mipmap.ic_launcher);
    builder.setTitle(context.getResources().getString(R.string.app_name))
        .setMessage(msg)
        .setPositiveButton(context.getResources().getString(R.string.btn_3),
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (event != null) {
                  event.onPositiveClicked();
                }
              }
            })
        .setNegativeButton(context.getResources().getString(R.string.btn_4),
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (event != null) {
                  event.onNegativeClicked();
                }
              }
            })
        .show();
  }

  public static void showQuestionDialog(Context context, String title, String msg,
                                        final DialogEvent event) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setCancelable(false);
    builder.setIcon(R.mipmap.ic_launcher);
    builder.setTitle(title)
        .setMessage(msg)
        .setPositiveButton(context.getResources().getString(R.string.btn_3),
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (event != null) {
                  event.onPositiveClicked();
                }
              }
            })
        .setNegativeButton(context.getResources().getString(R.string.btn_6),
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (event != null) {
                  event.onNegativeClicked();
                }
              }
            })
        .show();
  }

  public static void showRetryDialog(Context context, String msg, final DialogEvent event) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setCancelable(false);
    builder.setIcon(R.mipmap.ic_launcher);
    builder.setTitle(context.getResources().getString(R.string.app_name))
        .setMessage(msg)
        .setPositiveButton(context.getResources().getString(R.string.btn_7),
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (event != null) {
                  event.onPositiveClicked();
                }
              }
            })
        .setNegativeButton(context.getResources().getString(R.string.btn_8),
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (event != null) {
                  event.onNegativeClicked();
                }
              }
            })
        .show();
  }

  public static void showMsgDialog(Context context, String msg, final DialogEvent event) {
    AlertDialog.Builder builder =
        new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
    builder.setCancelable(false);
    builder.setIcon(R.mipmap.ic_launcher);
    builder.setTitle(context.getResources().getString(R.string.app_name))
        .setMessage(msg)
        .setPositiveButton(context.getResources().getString(R.string.btn_9),
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (event != null) {
                  event.onPositiveClicked();
                }
              }
            }).show();
  }

  public static void showMsgDialog(Context context, String title, String msg, String posButton,
                                   final DialogEvent event) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setCancelable(false);
    builder.setIcon(R.mipmap.ic_launcher);
    builder.setTitle(title)
        .setMessage(msg)
        .setPositiveButton(posButton, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            if (event != null) {
              event.onPositiveClicked();
            }
          }
        }).show();
  }

  public static void showOptionDialog(Context context, String title, String msg,
                                      final DialogEvent event) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setCancelable(false);
    builder.setIcon(R.mipmap.ic_launcher);
    builder.setTitle(title)
        .setMessage(msg)
        .setPositiveButton(context.getResources().getString(R.string.btn_9),
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (event != null) {
                  event.onPositiveClicked();
                }
              }
            })
        .setNegativeButton(context.getResources().getString(R.string.btn_8),
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (event != null) {
                  event.onNegativeClicked();
                }
              }
            })
        .show();
  }

  public static void showOptionDialog(Context context, String msg, final DialogEvent event) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setCancelable(false);
    builder.setIcon(R.mipmap.ic_launcher);
    builder.setTitle(context.getResources().getString(R.string.app_name))
        .setMessage(msg)
        .setPositiveButton(context.getResources().getString(R.string.btn_9),
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (event != null) {
                  event.onPositiveClicked();
                }
              }
            })
        .setNegativeButton(context.getResources().getString(R.string.btn_8),
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (event != null) {
                  event.onNegativeClicked();
                }
              }
            })
        .show();
  }

  public static void showMsgDialog(Context context, int icon, String title, String msg,
                                   final DialogEvent event) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setCancelable(false);
    builder.setIcon(R.mipmap.ic_launcher);
    builder.setTitle(title)
        .setMessage(msg)
        .setPositiveButton(context.getResources().getString(R.string.btn_3),
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (event != null) {
                  event.onPositiveClicked();
                }
              }
            }).show();
  }

  public static void showMsgDialog(Context context, String title, String msg,
                                   final DialogEvent event) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setCancelable(false);
    builder.setIcon(R.mipmap.ic_launcher);
    builder.setTitle(title)
        .setMessage(msg)
        .setPositiveButton(context.getResources().getString(R.string.btn_3),
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (event != null) {
                  event.onPositiveClicked();
                }
              }
            }).show();
  }

  public static abstract class DialogEvent {
    protected void onPositiveClicked() {
    }

    protected void onNegativeClicked() {
    }
  }
}

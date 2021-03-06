/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gedevanishvili.sipdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import org.doubango.ngn.sip.NgnAVSession;

/**
 *
 * @author alexx
 * This class is to alert error messages
 */
public class MyAlert {
	
	private static AlertDialog alertDialog = null;
	
    /**
     * Static method to alert message in alert dialog
     */
    public static void alertWin(Context context, String str){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Event Alert");
        alertDialogBuilder.setMessage(str);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){  
                    public void onClick(DialogInterface dialog, int id) {  
                        dialog.dismiss(); 
                    }  
                });
        
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    
    public static void alertSuccessWin(Context context, String title, String str){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(str);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){  
                    public void onClick(DialogInterface dialog, int id) {  
                        dialog.dismiss(); 
                    }  
                });
        
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    
    public static void alertWinWithDisconnect(final Context context, String str){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Event Alert");
        alertDialogBuilder.setMessage(str);
        alertDialogBuilder.setPositiveButton("Disconnect", new DialogInterface.OnClickListener(){  
                    public void onClick(DialogInterface dialog, int id) {  
                        ((MainActivity)context).disconnectCall();
                        dialog.dismiss();
                    }  
                });
        
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    
    public static void dismissDialog(){
    	if (alertDialog != null){
    		alertDialog.cancel();
    		alertDialog = null;
    	}
    }
    
    public static void alertIncomingCall(Context context, final NgnAVSession avSession){
    	
    	//start activity
    	Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    	
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Incoming call");
        
        String fromNumber = avSession.getRemotePartyDisplayName();
        alertDialogBuilder.setMessage(fromNumber);
        alertDialogBuilder.setPositiveButton("Answer", new DialogInterface.OnClickListener(){  
                    public void onClick(DialogInterface dialog, int id) {
                        avSession.acceptCall();
                    	dialog.dismiss(); 
                    }
                });
        alertDialogBuilder.setNegativeButton("Reject", new DialogInterface.OnClickListener(){  
            public void onClick(DialogInterface dialog, int id) {
            	avSession.hangUpCall();
                dialog.dismiss(); 
            }  
        });
        
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}

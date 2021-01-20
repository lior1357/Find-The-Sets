package afinal.game.lior.findthesets;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by user on 03/12/2017.
 */

public class MessageReciever extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent)
    {
// TODO Auto-generated method stub
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED"))
        {
            Bundle bundle = intent.getExtras();
            Object [] pdus = (Object[]) bundle.get("pdus");
            String smsInfo = "";
            String inPhoneNum = "";
            for (int i = 0 ; i < pdus.length ; i++ )
            {
                SmsMessage smsMsg = SmsMessage.createFromPdu((byte[])pdus[i]);
                inPhoneNum = smsMsg.getDisplayOriginatingAddress();
                smsInfo += "From:" +inPhoneNum + "\n" +
                    "Body:" + smsMsg.getDisplayMessageBody();

            }
            Log.d("MyTelephonyReciver" , "SMS_REVEIVED:" + smsInfo);
            Toast.makeText(context, "You've got an SMS!\n" + smsInfo, Toast.LENGTH_LONG).show();
            if(smsInfo.contains("shat are you doing?"))
            {
                SmsManager mng = SmsManager.getDefault();
                mng.sendTextMessage(inPhoneNum, null, "Playing Find The Sets!", null, null);
            }
        }
    }
}

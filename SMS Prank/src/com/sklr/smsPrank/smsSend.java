package com.sklr.smsPrank;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class smsSend{

    public String ACCOUNT_SID = "";
    public String AUTH_TOKEN = "";

    /**
     * Handles all of the Twilio sending stuff
     * @param to_number The phone number the text is going to
     * @param from_number The phone number the text is coming from
     * @param MessageToSend The message to send
     */
    void send(String to_number, String from_number, String MessageToSend) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(to_number)   /* The number the text is going to */
                , new PhoneNumber(from_number) /* The number the text is coming from */
                , MessageToSend                /* The message to send */
        ).create();
    }
}

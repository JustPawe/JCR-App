package com.markpaveszka.jcrapp.ui.contact;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.markpaveszka.jcrapp.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactFragment extends Fragment {

    private ContactViewModel contactViewModel;
    private EditText nameET;
    private EditText emailET;
    private EditText subjectET;
    private EditText messageET;
    private Button submitBtn;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        contactViewModel =
                ViewModelProviders.of(this).get(ContactViewModel.class);
        View root = inflater.inflate(R.layout.fragment_contact, container, false);

        nameET = (EditText) root.findViewById(R.id.nameET);
        emailET = (EditText) root.findViewById(R.id.emailET);
        subjectET = (EditText) root.findViewById(R.id.subjectET);
        messageET = (EditText) root.findViewById(R.id.messageET);
        submitBtn = (Button) root.findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name      = nameET.getText().toString();
                String email     = emailET.getText().toString();
                String subject   = subjectET.getText().toString();
                String message   = messageET.getText().toString();
                if (TextUtils.isEmpty(name)){
                    nameET.setError("Enter Your Name");
                    nameET.requestFocus();
                    return;
                }

                Boolean onError = false;
                if (!isValidEmail(email)) {
                    onError = true;
                    emailET.setError("Invalid Email");
                    emailET.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(subject)){
                    subjectET.setError("Enter Your Subject");
                    subjectET.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(message)){
                    messageET.setError("Enter Your Message");
                    messageET.requestFocus();
                    return;
                }

                Intent sendEmail = new Intent(android.content.Intent.ACTION_SEND);

                /* Fill it with Data */
                sendEmail.setType("plain/text");
                sendEmail.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"jcr@hulmehall.co.uk"});
                sendEmail.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
                sendEmail.putExtra(android.content.Intent.EXTRA_TEXT,
                        "name:"+name+'\n'+"Email ID:"+email+'\n'+"Message:"+'\n'+message);

                /* Send it off to the Activity-Chooser */
                startActivity(Intent.createChooser(sendEmail, "Send mail..."));
            }
        });


        return root;
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
package com.subltecreation.numberreader;

import java.util.Locale;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements TextToSpeech.OnInitListener{
	Button readNumberButton;
	EditText inputNumberView;
	TextToSpeech tts;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tts = new TextToSpeech(this, this);
		inputNumberView = (EditText)findViewById(R.id.inputNumber);
		readNumberButton = (Button)findViewById(R.id.readNumberButton);
		readNumberButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				speakOut();
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public void onInit(int status) {
		if(status == TextToSpeech.SUCCESS){
			int result = tts.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {            	
                Log.d("TTS", "This Language is not supported");
            } else {
                readNumberButton.setEnabled(true);
                speakOut();
            }
		}else{
			Log.d("TTS", "Failed at initialization.");
		}
	}
    private void speakOut() {    	 
        String text = inputNumberView.getText().toString(); 
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}

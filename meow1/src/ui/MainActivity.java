package ui;

import java.util.ArrayList;

import com.example.meow1.R;

import data.EventDataAccessor;
import data.TestEvent;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class MainActivity extends ListActivity {

	private EventDataAccessor eda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);   
        System.out.println("Hello SQLite ---- mainActivity.java");
        eda = new EventDataAccessor(this);
        eda.open();
//        eda.createEvent("event1");
//        eda.createEvent("event2");
        ArrayList<TestEvent> eventList = eda.getAllEvents();
        for (TestEvent event : eventList)
        {
        	System.out.println("Showing event ----mainActivity.java  :" + event.toString());
        }
        ArrayAdapter<TestEvent> eventListAdapter = new ArrayAdapter<TestEvent>(this, android.R.layout.simple_expandable_list_item_1, eventList);
        setListAdapter(eventListAdapter);
    }

    public void onClick(View view)
    {
    	ArrayAdapter<TestEvent> adapter = (ArrayAdapter<TestEvent>) getListAdapter();
    	TestEvent event = null;
    	EditText eventNameInput = (EditText) findViewById(R.id.event_name_input); 
    	switch(view.getId())
    	{
    	case R.id.add_event_button:
    		System.out.println("The add button is clicked!");
    		String inputEventName = eventNameInput.getText().toString();
    		System.out.println("The EditText input content is: " + inputEventName);
    		if(inputEventName!=null)
    		{
    			TestEvent addedEvent = eda.createEvent(inputEventName);
    			System.out.println("The event " + inputEventName + " is added to DB!");
    			adapter.add(addedEvent);
    		}
    		break;
    	case R.id.delete_event_button:
    		if(getListAdapter().getCount() > 0)
    		{
    			event = (TestEvent) getListAdapter().getItem(0);
    			eda.deleteEvent(event);
    			adapter.remove(event);
    		}
    		System.out.println("The delete button is clicked! Delete Event: " + event.toString());
    		break;
    	}
    	adapter.notifyDataSetChanged(); 
    }
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    //stella == lola == true~
    
}

package jp.gr.java_conf.yamashita.masterdetailsample;

import android.app.Activity;
import android.app.ListFragment;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import javax.security.auth.callback.Callback;

import jp.gr.java_conf.yamashita.masterdetailsample.dummy.DummyContent;

/**
 * Created by satoshi on 2016/01/04.
 * todo: onListItemClickから
 */
public class ItemListFragment extends ListFragment{

    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    private Callbacks mCallbacks = sDummyCallbacks;

    private int mActivatedPosition = ListView.INVALID_POSITION;

    public interface Callbacks{
        public void onItemselected(String id);
    }

    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemselected(String id) {
        }
    };

    public ItemListFragment{
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<DummyContent.DummyItem>(
                getActivity(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                DummyContent.ITEMS));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)){
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(!(context instanceof Callbacks)){
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }
        mCallbacks = (Callbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mCallbacks = sDummyCallbacks;
    }
}

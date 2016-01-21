package jp.gr.java_conf.yamashita.masterdetailsample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */

/**
 * todo:onItemSelectedメソッドの実装
 */
public class ItemListActivity extends FragmentActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        // 適用されたレイアウトにid="@+id/item_detail_container"で定義した
        //R.id.item_detail_containerがあれば、2ペイン
        if (findViewById(R.id.item_detail_container) != null) {
            mTwoPane = true;
        }
    }


}
